/**
 * Sent from the client to initiate login
 *
 * @c2s Tells the server that the client is ready to login
 */
@Getter
@Setter
public class PreLoginPacket extends Packet {
    private short id; // might be something different but the type is correct
    private String name;

    private static final String PROTOCOL_VERSION = 1920; // nx latest

    @Override
    public void read(EndianInputStream inputStream) throws IOException {
        id = inputStream.readShort();
        name = inputStream.readUtf(0x20);
    }

    @Override
    public void write(EndianOutputStream outputStream) throws IOException {
        outputStream.writeShort(PROTOCOL_VERSION) // THIS IS PROTOCOL VER
        outputStream.writeUtf(this.name);
    }
}
