package net.lumora.screen;

import net.lumora.core.Theme;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import net.minecraft.util.Util;

/** Shared animated background + fade-in transition. */
public abstract class LumoraScreen extends Screen {
    private long opened;

    protected LumoraScreen(Text title) { super(title); }

    @Override
    protected void init() { opened = Util.getMeasuringTimeMs(); }

    /** 0..1 eased fade used for smooth transitions. */
    protected float fade() {
        float t = Math.min(1f, (Util.getMeasuringTimeMs() - opened) / 350f);
        return 1f - (1f - t) * (1f - t) * (1f - t);
    }

    @Override
    public void renderBackground(DrawContext ctx, int mx, int my, float delta) {
        Theme t = Theme.current();
        ctx.fillGradient(0, 0, width, height, t.top(), t.bottom());
        double time = Util.getMeasuringTimeMs() / 1000.0;
        for (int i = 0; i < 70; i++) {
            int sx = (int) ((i * 7919L) % Math.max(1, width));
            int sy = (int) ((i * 104729L) % Math.max(1, height));
            float tw = 0.35f + 0.65f * (float) Math.abs(Math.sin(time * 0.8 + i));
            ctx.fill(sx, sy, sx + 1, sy + 1, Theme.alpha(0xFFFFFFFF, tw * 0.8f * fade()));
        }
        for (int i = 0; i < 14; i++) {
            int ox = (int) (width * (0.5 + 0.5 * Math.sin(time * 0.15 + i * 1.7)));
            int oy = (int) (height * (0.5 + 0.5 * Math.cos(time * 0.11 + i * 2.3)));
            int s = 3 + (i % 4) * 2;
            ctx.fill(ox, oy, ox + s, oy + s, Theme.alpha(t.accent(), 0.22f * fade()));
        }
    }
}
