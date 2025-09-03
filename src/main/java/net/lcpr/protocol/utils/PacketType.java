package net.lcpr.protocol.utils;

import lombok.Getter;
import net.lcpr.protocol.packet.*;
import net.lcpr.protocol.packet.c2s.*;
import net.lcpr.protocol.packet.s2c.*;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public enum PacketType {
    ClientboundKeepAlivePacket(0, net.lcpr.protocol.packet.s2c.KeepAlivePacket::new),
    ClientboundLoginPacket(1, null),
    ClientboundPreLoginPacket(2, null),
    ClientboundChatPacket(3, null),
    ClientboundSetTimePacket(4, SetTimePacket::new),
    ClientboundSetEquippedItemPacket(5, null),
    ClientboundSetSpawnPositionPacket(6, SetSpawnPositionPacket::new),
    ServerboundInteractPacket(7, InteractPacket::new),
    ClientboundSetHealthPacket(8, SetHealthPacket::new),
    ClientboundRespawnPacket(9, null),
    ServerboundMovePlayerPacket(10, MovePlayerPacket::new),
    ServerboundMovePlayerPosPacket(11, MovePlayerPacket.Pos::new),
    ServerboundMovePlayerRotPacket(12, MovePlayerPacket.Rot::new),
    ServerboundMovePlayerPosRotPacket(13, MovePlayerPacket.PosRot::new),
    ServerboundPlayerActionPacket(14, PlayerActionPacket::new),
    ServerboundUseItemPacket(15, UseItemPacket::new),
    ClientboundSetCarriedItemPacket(16, net.lcpr.protocol.packet.s2c.SetCarriedItemPacket::new),
    ClientboundPlayerSleepPacket(17, PlayerSleepPacket::new),
    ClientboundAnimatePacket(18, net.lcpr.protocol.packet.s2c.AnimatePacket::new),
    ServerboundPlayerCommandPacket(19, PlayerCommandPacket::new),
    ClientboundAddPlayerPacket(20, null),
    ClientboundTakeItemEntityPacket(22, TakeItemEntityPacket::new),
    ClientboundAddEntityPacket(23, null),
    ClientboundAddMobPacket(24, null),
    ClientboundAddPaintingPacket(25, null),
    ClientboundAddExperienceOrbPacket(26, AddExperienceOrbPacket::new),
    ServerboundPlayerInputPacket(27, PlayerInputPacket::new),
    ClientboundSetEntityMotionPacket(28, null),
    ClientboundRemoveEntitiesPacket(29, null),
    ClientboundMoveEntityPacket(30, null),
    ClientboundTeleportEntityPacket(34, null),
    ClientboundRotateHeadPacket(35, RotateHeadPacket::new),
    ClientboundEntityEventPacket(38, EntityEventPacket::new),
    ClientboundSetEntityLinkPacket(39, null),
    ClientboundSetEntityDataPacket(40, null),
    ClientboundUpdateMobEffectPacket(41, null),
    ClientboundRemoveMobEffectPacket(42, null),
    ClientboundSetExperiencePacket(43, SetExperiencePacket::new),
    ClientboundUpdateAttributesPacket(44, null),
    ChunkVisibilityPacket(50, null),
    BlockRegionUpdatePacket(51, null),
    ClientboundChunkBlocksUpdatePacket(52, null),
    ClientboundBlockUpdatePacket(53, null),
    ClientboundBlockEventPacket(54, null),
    ClientboundBlockDestructionPacket(55, BlockDestructionPacket::new),
    ClientboundExplodePacket(60, null),
    ClientboundLevelEventPacket(61, LevelEventPacket::new),
    ClientboundSoundPacket(62, null),
    ClientboundLevelParticlesPacket(63, null),
    ClientboundGameEventPacket(70, GameEventPacket::new),
    ClientboundAddGlobalEntityPacket(71, AddGlobalEntityPacket::new),
    ClientboundContainerOpenPacket(100, null),
    ClientboundContainerClosePacket(101, ContainerClosePacket::new),
    ServerboundContainerClickPacket(102, null),
    ClientboundContainerSetSlotPacket(103, null),
    ClientboundContainerSetContentPacket(104, null),
    ClientboundContainerSetDataPacket(105, ContainerSetDataPacket::new),
    ClientboundContainerAckPacket(106, ContainerAckPacket::new),
    ServerboundSetCreativeModeSlotPacket(107, null),
    ServerboundContainerButtonClickPacket(108, ContainerButtonClickPacket::new),
    ClientboundBlockEntityDataPacket(132, null),
    ClientboundMoveVehiclePacket(133, MoveVehiclePacket::new),
    ClientboundSetPassengersPacket(134, null),
    ServerboundAcceptTeleportationPacket(135, AcceptTeleportationPacket::new),
    ServerboundMoveVehiclePacket(136, MoveVehiclePacket::new),
    ServerboundUseItemOnPacket(137, UseItemOnPacket::new),
    ServerboundPaddleBoatPacket(138, PaddleBoatPacket::new),
    ClientboundBossEventPacket(139, null),
    CraftItemPacket(150, null),
    TradeItemPacket(151, TradeItemPacket::new),
    DebugOptionsPacket(152, DebugOptionsPacket::new),
    ServerSettingsChangedPacket(153, null),
    TexturePacket(154, null),
    ChunkVisibilityAreaPacket(155, null),
    UpdateProgressPacket(156, UpdateProgressPacket::new),
    TextureChangePacket(157, null),
    UpdateGameRuleProgressPacket(158, null),
    KickPlayerPacket(159, KickPlayerPacket::new),
    TextureAndGeometryPacket(160, null),
    TextureAndGeometryChangePacket(161, null),
    XZPacket(166, XZPacket::new),
    GameCommandPacket(167, GameCommandPacket::new),
    MapSelectInfoPacket(168, null),
    VotePacket(169, VotePacket::new),
    PlayerReadyPacket(170, PlayerReadyPacket::new),
    ClientboundPowerupPacket(171, PowerupPacket::new),
    ClientboundAwardStatPacket(200, AwardStatPacket::new),
    PlayerInfoPacket(201, null),
    ClientboundPlayerAbilitiesPacket(202, PlayerAbilitiesPacket::new),
    ServerboundClientCommandPacket(205, ClientCommandPacket::new),
    ScoreboardPacket(206, null),
    ClientboundSetPlayerTeamPacket(209, null),
    GameModePacket(210, null),
    ClientboundMapItemDataPacket(211, null),
    ClientboundOpenSignEditorPacket(212, OpenSignEditorPacket::new),
    ClientboundPlayerCombatPacket(213, PlayerCombatPacket::new),
    ClientboundPlayerPositionPacket(214, null),
    ClientboundResourcePackPacket(216, null),
    ClientboundSetBorderPacket(217, SetBorderPacket::new),
    ClientboundSetCameraPacket(218, SetCameraPacket::new),
    ClientboundSetTitlesPacket(219, null),
    ClientboundTabListPacket(220, TabListPacket::new),
    ServerboundChatAutoCompletePacket(221, null),
    ServerboundChatPacket(222, ChatPacket::new),
    ServerboundContainerAckPacket(223, ContainerAckPacket::new),
    ServerboundContainerClosePacket(224, ContainerClosePacket::new),
    ServerboundCustomPayloadPacket(225, null),
    ServerboundKeepAlivePacket(226, net.lcpr.protocol.packet.c2s.KeepAlivePacket::new),
    ServerboundPlayerAbilitiesPacket(227, null),
    ServerboundResourcePackPacket(228, ResourcePackPacket::new),
    ServerboundSetCarriedItemPacket(229, net.lcpr.protocol.packet.c2s.SetCarriedItemPacket::new),
    ServerboundSignUpdatePacket(230, null),
    ServerboundTeleportToEntityPacket(231, null),
    ServerboundPreLoginPacket(232, PreLoginPacket::new),
    ServerboundSwingPacket(233, SwingPacket::new),
    ClientboundDamageIndicatorPacket(234, DamageIndicatorPacket::new),
    ClientboundBlockCollectionDestructionPacket(235, null),
    ClientboundCooldownPacket(236, CooldownPacket::new),
    ClientboundMGPlayerSettingsUpdatePacket(239, null),
    ClientboundCustomPayloadPacket(250, null),
    ClientboundChangeDifficultyPacket(253, ChangeDifficultyPacket::new),
    GetInfoPacket(254, GetInfoPacket::new),
    DisconnectPacket(255, DisconnectPacket::new);

    private static final Map<Integer, PacketType> ID_MAP = new HashMap<>();

    public static PacketType byId(int id) {
        return ID_MAP.get(id);
    }

    @Getter
    private final int packetId;
    private final Supplier<Packet> packetSupplier;

    PacketType(int packetId, Supplier<Packet> packetSupplier) {
        this.packetId = packetId;
        this.packetSupplier = packetSupplier;
    }

    public Packet getPacket() {
        return packetSupplier.get();
    }

    static {
        for (PacketType packetType : values()) {
            ID_MAP.put(packetType.getPacketId(), packetType);
        }
    }
}

