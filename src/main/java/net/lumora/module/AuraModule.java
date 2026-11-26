package net.lumora.module;

import net.lumora.core.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.particle.ParticleTypes;

/** Client-side-only particle spiral around you, visible in third person. Other players do not see it. */
public class AuraModule extends Module {
    public AuraModule() { super("aura", "Aura", "A soft spiral of light around you (third person)", false); }

    @Override
    public void tick(MinecraftClient c) {
        if (c.player == null || c.world == null || c.isPaused()) return;
        if (c.options.getPerspective().isFirstPerson()) return;
        double a = c.player.age * 0.2;
        double h = (c.player.age % 30) / 30.0 * 1.8;
        c.world.addParticleClient(ParticleTypes.END_ROD,
            c.player.getX() + Math.cos(a) * 0.8, c.player.getY() + h, c.player.getZ() + Math.sin(a) * 0.8,
            0, 0.005, 0);
    }
}
