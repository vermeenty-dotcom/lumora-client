package net.lumora.module;

import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;
import net.lumora.core.Module;
import net.lumora.core.Theme;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.Identifier;

/** Small themed info panel: FPS + block coordinates. Display only. */
public class HudModule extends Module {
    public HudModule() { super("hud", "Themed HUD", "FPS and position panel in your theme colours", true); }

    @Override
    public void init() {
        HudElementRegistry.attachElementAfter(VanillaHudElements.MISC_OVERLAYS,
            Identifier.of("lumora", "info_panel"), (ctx, tick) -> {
                if (!enabled()) return;
                MinecraftClient mc = MinecraftClient.getInstance();
                if (mc.player == null || mc.options.hudHidden) return;
                draw(ctx, mc);
            });
    }

    private void draw(DrawContext ctx, MinecraftClient mc) {
        Theme t = Theme.current();
        String l1 = mc.getCurrentFps() + " FPS";
        String l2 = mc.player.getBlockX() + "  " + mc.player.getBlockY() + "  " + mc.player.getBlockZ();
        int w = Math.max(mc.textRenderer.getWidth(l1), mc.textRenderer.getWidth(l2)) + 14;
        int x = 6, y = 6, h = 30;
        ctx.fill(x, y, x + w, y + h, 0x99000000);
        ctx.fill(x, y, x + 2, y + h, t.accent());
        ctx.drawText(mc.textRenderer, l1, x + 8, y + 5, t.accent(), true);
        ctx.drawText(mc.textRenderer, l2, x + 8, y + 17, 0xFFE8E8F0, true);
    }
}
