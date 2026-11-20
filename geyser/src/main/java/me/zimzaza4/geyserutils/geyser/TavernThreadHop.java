package me.zimzaza4.geyserutils.geyser;

import me.zimzaza4.geyserutils.common.packet.CustomPayloadPacket;
import org.geysermc.geyser.session.GeyserSession;

/**
 * TavernMC patch: GeyserUtils plugin messages (model scale, hitbox, colour, properties, animations)
 * are handled on the player's own event loop instead of the Java network thread, so they no longer
 * race Geyser's entity metadata updates (Bedrock kick "Index -1 out of bounds for length 33").
 * Same queue and order as every other packet; a handler error is logged by Geyser instead of kicking.
 */
public final class TavernThreadHop {

    private TavernThreadHop() {
    }

    public static void handle(GeyserUtils plugin, GeyserSession session, CustomPayloadPacket packet) {
        session.ensureInEventLoop(() -> plugin.handleCustomPacket(session, packet));
    }
}
