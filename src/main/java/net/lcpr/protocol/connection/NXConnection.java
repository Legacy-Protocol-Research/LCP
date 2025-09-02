package net.lcpr.protocol.connection;

import net.lcpr.protocol.PacketListener;
import net.lcpr.protocol.packet.Packet;
import net.lcpr.protocol.packet.c2s.PlayerActionPacket;
import net.lcpr.protocol.packet.s2c.KeepAlivePacket;
import net.lcpr.protocol.types.DisconnectReason;
import net.lcpr.protocol.utils.EndianInputStream;
import net.lcpr.protocol.utils.EndianOutputStream;
import net.lcpr.protocol.utils.PacketType;
import net.lcpr.protocol.utils.Platform;

import java.io.*;
import java.net.Socket;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

@SuppressWarnings({
        "FieldMayBeFinal" // Some fields 100% shouldn't be final, this class just isn't complete yet.
})
public class NXConnection implements Connection {
    private static int dword_7101786598;  // runRead
    private static int dword_710178659C;  // runWrite

    private static int[] dword_71017865A0 = new int[256];  // read
    private static int[] dword_71017869A0 = new int[256];  // write

    private Socket socket;
    private long remoteSocketAddress;
    private EndianInputStream dataInputStream;
    private EndianOutputStream dataOutputStream;
    private EndianOutputStream dataOutputStream2;
    private ByteArrayOutputStream byteArrayOutputStream;
    private OutputStream socketOutputStream;
    private boolean isRunning;
    private ReentrantLock isRunningMutex;
    private Deque<Packet> incomingQueue;
    private ReentrantLock incomingMutex;
    private Deque<Packet> outgoingQueue;
    private Deque<Packet> slowOutgoingQueue;
    private PacketListener packetListener;
    private boolean isDisconnecting;
    private boolean field_119;
    private ExecutorService runReadThread;
    private ExecutorService runWriteThread;
//    C4JEventImpl* mC4JEventImpl1;
//    C4JEventImpl* mC4JEventImpl2;
    private Object qword_140;
    private boolean byte_148;
    private int dword_14c;
    private Object qword_150;
    private int ticksSinceLastPacket;
    private int estimatedSize;
    private long field_160; // UInt
    private long field_164; // UInt
    private int fakeLag;
    private int dword_16C;
    private ReentrantLock countMutex;
    private ReentrantLock outgoingMutex;
    private int delay;
    private int dword_1b4;
    private long timeInMs;

    private final boolean isServerSided;

    public NXConnection(
            // Base connection constructor
            Socket socket,
            String name,
            PacketListener packetListener,

            // Other constructor parameters
            boolean isServerSided
    ) throws IOException {
        this.outgoingMutex = new ReentrantLock();
        this.countMutex = new ReentrantLock();
        this.incomingMutex = new ReentrantLock();
        this.isRunning = true;
        this.isRunningMutex = new ReentrantLock();
        this.isDisconnecting = false;
        this.field_119 = false;
        this.byte_148 = false;
        this.dword_14c = 0;
        this.fakeLag = 0;
        this.delay = 0;
        this.qword_140 = null;
        this.ticksSinceLastPacket = 0;
        this.estimatedSize = 0;
        this.field_160 = 0;
        this.field_164 = 0;

        this.incomingQueue = new ArrayDeque<>();
        this.outgoingQueue = new ArrayDeque<>();
        this.slowOutgoingQueue = new ArrayDeque<>();

        this.socket = socket;
//        this.remoteSocketAddress = socket.getRemoteSocketAddress(); // TODO: find a way to represent this as a long, or maybe not? we could probably adapt to SocketAddress
        this.packetListener = packetListener;

        this.socket.setSoTimeout(30000);
        this.socket.setTrafficClass(24);

        this.dataInputStream = getPlatform().getInputStream(socket.getInputStream());

        this.socketOutputStream = socket.getOutputStream();
        this.dataOutputStream = getPlatform().getOutputStream(new BufferedOutputStream(this.socketOutputStream, 0x1400));

        this.byteArrayOutputStream = new ByteArrayOutputStream(0x1400);
        this.dataOutputStream2 = getPlatform().getOutputStream(this.byteArrayOutputStream);

        // TODO Find if needed, appears to be platform specific, but potentially only needed on the client?
//        mC4JEventImpl1 = new C4JEventImpl(C4JEvent::EMode::_0);
//        mC4JEventImpl2 = new C4JEventImpl(C4JEvent::EMode::_0);

        String readName = "%s read".formatted(name);
        String writeName = "%s write".formatted(name);

        this.runReadThread = Executors.newSingleThreadExecutor(r -> new Thread(r, readName));
        this.runWriteThread = Executors.newSingleThreadExecutor(r -> new Thread(r, writeName));

        this.timeInMs = 0;

        this.isServerSided = isServerSided;
    }

    @Override
    public boolean getAndSetRunning(boolean running) {
        this.isRunningMutex.lock();
        boolean prevIsRunning = this.isRunning;
        this.isRunning = running;
        this.isRunningMutex.unlock();
        return prevIsRunning;
    }

    @Override
    public void send(Packet packet) {
        if (!this.isDisconnecting) {
            this.timeInMs = System.currentTimeMillis();
            this.outgoingMutex.lock();
            this.estimatedSize += packet.getEstimatedSize() + 1;
            if (packet.isShouldDelay()) {
                packet.setShouldDelay(false);
                this.slowOutgoingQueue.add(packet);
            } else {
                if (packet.getType() == PacketType.ClientboundMapItemDataPacket) {
                    if (packet.tryReplaceDuplicatePacket(outgoingQueue)) {
                        this.outgoingMutex.unlock();
                        return;
                    }
                }

                this.outgoingQueue.add(packet);
            }
            this.outgoingMutex.unlock();
        }
    }

    @Override
    public void queue(Packet packet) {
        if (!this.isDisconnecting) {
            this.outgoingMutex.lock();
            this.estimatedSize += packet.getEstimatedSize() + 1;
            this.slowOutgoingQueue.add(packet);
            this.outgoingMutex.unlock();
        }
    }

    @Override
    public void setListener(PacketListener packetListener) {
        this.packetListener = packetListener;
    }

    @Override
    public boolean writeTick() throws IOException {
        if (this.dataOutputStream == null || this.dataOutputStream2 == null)
            return false;

        boolean returnValue;
        if (!this.outgoingQueue.isEmpty() && (this.fakeLag <= 0 || (System.currentTimeMillis() - this.outgoingQueue.peek().getCreatedTime() >= this.fakeLag))) {
            Packet packet;
            this.outgoingMutex.lock();

            packet = this.outgoingQueue.pop();

            this.estimatedSize -= packet.getEstimatedSize() + 1;
            this.outgoingMutex.unlock();

            Packet.writePacket(packet, this.dataOutputStream, this.field_164);

            dword_71017869A0[packet.getType().getPacketId()] += packet.getEstimatedSize() + 1;

            returnValue = true;
        } else {
            returnValue = false;
        }

        if (this.delay-- > 0 || this.slowOutgoingQueue.isEmpty()
                || (this.fakeLag > 0
                && System.currentTimeMillis() - this.slowOutgoingQueue.peek().getCreatedTime() < this.fakeLag)) {
            return returnValue;
        }

        Packet packet;
        this.outgoingMutex.lock();
        packet = this.slowOutgoingQueue.pop();

        this.estimatedSize -= packet.getEstimatedSize() + 1;
        this.outgoingMutex.unlock();

        if (packet.isShouldDelay()) {
            Packet.writePacket(packet, this.dataOutputStream2, this.field_164);

            if (this.dataOutputStream != null)
                dataOutputStream.flush();
            this.socketOutputStream.write(getPlatform().parseBytes(this.byteArrayOutputStream.toByteArray()), 0, this.byteArrayOutputStream.size());
            this.byteArrayOutputStream.close();
            this.byteArrayOutputStream = new ByteArrayOutputStream(0x1400);
        } else {
            Packet.writePacket(packet, this.dataOutputStream, this.field_164);
        }

        dword_71017869A0[packet.getType().getPacketId()] += packet.getEstimatedSize() + 1;

        this.delay = 0;
        return true;
    }

    @Override
    public void flush() {
        // TODO See constructor comment on these 2
//        mC4JEventImpl1->Set();
//        mC4JEventImpl2->Set();
    }

    @Override
    public boolean readTick() {
        if (this.dataInputStream != null && !this.field_119) {
            Packet packet = Packet.readPacket(this.dataInputStream, this.field_160, this.field_119);
            if (packet != null) {
                dword_71017865A0[packet.getType().getPacketId()] += packet.getEstimatedSize() + 1; // TODO
                this.incomingMutex.lock();
                if (!this.isDisconnecting)
                    this.incomingQueue.add(packet);
                this.incomingMutex.unlock();
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public void tick() throws IOException, InterruptedException {
        if (this.field_119)
            close(DisconnectReason.TWENTY_NINE);
        if (this.estimatedSize > 0x100000)  // 1MB
            close(DisconnectReason.OVERFLOW);
        this.incomingMutex.lock();
        long incomingQueueSize = this.incomingQueue.size();
        this.incomingMutex.unlock();
        if (incomingQueueSize != 0)
            this.ticksSinceLastPacket = 0;
        else {
            if (this.ticksSinceLastPacket++ == 1200)
                close(DisconnectReason.TIMEOUT);
        }
        if ((System.currentTimeMillis() - this.timeInMs) > 1000) {
            send(this.isServerSided ? new KeepAlivePacket() : new net.lcpr.protocol.packet.c2s.KeepAlivePacket());
        }

        this.incomingMutex.lock();

        Deque<Packet> queue = new ArrayDeque<>();

        int maxIterations = 1000;

        while (/*!CGameNetworkManager::sInstance.IsLeavingGame()*/true) { // TODO
            if (/*!CGameNetworkManager::sInstance.IsInSession()*/false) // TODO
                break;
            if (this.incomingQueue.isEmpty())
                break;
            if (maxIterations-- < 0)
                break;

            Packet packet = this.incomingQueue.peek();
            if (!this.byte_148) {
                if (packet.getType() == PacketType.ServerboundPlayerActionPacket) {
                    int action = ((PlayerActionPacket) packet).getAction();

                    if (action < 5 && action > 2) {
                        queue.addFirst(packet);
                    } else {
                        queue.addLast(packet);
                    }
                } else {
                    queue.add(packet);
                }
            }
            this.incomingQueue.pop();
        }
        this.incomingMutex.unlock();

        queue.forEach(this.packetListener::handlePacket);

        flush();

        if (this.socket != null/* && this.socket.sub_71000EA668()*/) // TODO
            close(DisconnectReason.CLOSED);
        if (this.byte_148) {
            this.incomingMutex.lock();
            long incomingQueueSize1 = incomingQueue.size();
            this.incomingMutex.unlock();
            if (incomingQueueSize1 == 0) {
                this.packetListener.onDisconnect(DisconnectReason.fromId(dword_14c));
                this.byte_148 = false;
            }
        }
    }

    @Override
    public void close(DisconnectReason reason) throws IOException, InterruptedException {
        if (!this.getAndSetRunning(false))
            return;
        this.byte_148 = true;
        this.dword_14c = reason.getId();
        this.qword_150 = null;

        if (this.dataInputStream != null)
            this.dataInputStream.close();

        // These don't exist in 4J's world, the method wouldn't return a boolean, it simply doesn't care, so neither do we.
        //noinspection ResultOfMethodCallIgnored
        this.runReadThread.awaitTermination(0xFFFFFFFFL, TimeUnit.MILLISECONDS);
        //noinspection ResultOfMethodCallIgnored
        this.runWriteThread.awaitTermination(0xFFFFFFFFL, TimeUnit.MILLISECONDS);

        this.dataInputStream = null;

        if (this.dataOutputStream != null) {
            this.dataOutputStream.close();
            this.dataOutputStream = null;
        }

        if (this.dataOutputStream2 != null) {
            this.dataOutputStream2.close();
            this.dataOutputStream2 = null;
        }

        if (this.socket != null) {
            this.socket.close();
            this.socket = null;
        }
    }

    @Override
    public void sendAndQuit() throws IOException, InterruptedException {
        if (!this.isDisconnecting) {
            flush();
            this.isDisconnecting = true;
            this.close(DisconnectReason.CLOSED);
        }
    }

    @Override
    public Platform getPlatform() {
        return Platform.NX;
    }
}
