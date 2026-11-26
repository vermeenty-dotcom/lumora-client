package net.lumora.screen;

import net.lumora.core.Theme;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerScreen;
import net.minecraft.client.gui.screen.option.OptionsScreen;
import net.minecraft.client.gui.screen.world.SelectWorldScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public class LumoraTitleScreen extends LumoraScreen {
    public LumoraTitleScreen() { super(Text.literal("Lumora")); }

    @Override
    protected void init() {
        super.init();
        int w = 200, h = 22, x = width / 2 - w / 2, y = height / 2 - 20, gap = 26;
        addDrawableChild(ButtonWidget.builder(Text.literal("Singleplayer"),
            b -> client.setScreen(new SelectWorldScreen(this))).dimensions(x, y, w, h).build());
        addDrawableChild(ButtonWidget.builder(Text.literal("Multiplayer"),
            b -> client.setScreen(new MultiplayerScreen(this))).dimensions(x, y + gap, w, h).build());
        addDrawableChild(ButtonWidget.builder(Text.literal("Lumora Settings"),
            b -> client.setScreen(new SettingsScreen(this))).dimensions(x, y + gap * 2, w, h).build());
        addDrawableChild(ButtonWidget.builder(Text.literal("Options"),
            b -> client.setScreen(new OptionsScreen(this, client.options))).dimensions(x, y + gap * 3, w / 2 - 2, h).build());
        addDrawableChild(ButtonWidget.builder(Text.literal("Quit"),
            b -> client.scheduleStop()).dimensions(x + w / 2 + 2, y + gap * 3, w / 2 - 2, h).build());
    }

    @Override
    public void render(DrawContext ctx, int mx, int my, float delta) {
        renderBackground(ctx, mx, my, delta);
        Theme t = Theme.current();
        float f = fade();
        int ty = (int) (height / 2 - 78 + (1 - f) * 12);
        var m = ctx.getMatrices();
        m.pushMatrix();
        m.translate(width / 2f, ty);
        m.scale(3f, 3f);
        ctx.drawCenteredTextWithShadow(textRenderer, Text.literal("LUMORA"), 0, 0, Theme.alpha(t.accent(), f));
        m.popMatrix();
        ctx.drawCenteredTextWithShadow(textRenderer, Text.literal("see the world in a different light"),
            width / 2, ty + 32, Theme.alpha(0xFFC9C3DA, f));
        super.render(ctx, mx, my, delta);
        ctx.drawText(textRenderer, "Press Right Shift in-game for settings", 6, height - 12, 0x80FFFFFF, false);
    }

    @Override public boolean shouldCloseOnEsc() { return false; }
}
