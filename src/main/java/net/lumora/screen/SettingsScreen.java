package net.lumora.screen;

import net.lumora.Lumora;
import net.lumora.core.Module;
import net.lumora.core.Theme;
import net.lumora.module.CrosshairModule;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public class SettingsScreen extends LumoraScreen {
    private final Screen parent;

    public SettingsScreen(Screen parent) {
        super(Text.literal("Lumora Settings"));
        this.parent = parent;
    }

    @Override
    protected void init() {
        super.init();
        int colW = 170, h = 20, gap = 24, left = width / 2 - colW - 3, right = width / 2 + 3, top = 50;
        int i = 0;
        for (Module m : Lumora.modules().all()) {
            int x = (i % 2 == 0) ? left : right;
            int y = top + (i / 2) * gap;
            addDrawableChild(ButtonWidget.builder(label(m), b -> { m.toggle(); b.setMessage(label(m)); })
                .dimensions(x, y, colW, h).build());
            i++;
        }
        int y = top + ((i + 1) / 2) * gap + 10;
        addDrawableChild(ButtonWidget.builder(Text.literal("Theme: " + Theme.current().name()), b -> {
            Lumora.config().theme++;
            b.setMessage(Text.literal("Theme: " + Theme.current().name()));
        }).dimensions(left, y, colW, h).build());
        addDrawableChild(ButtonWidget.builder(Text.literal("Crosshair: " + style()), b -> {
            Lumora.config().crosshairStyle++;
            b.setMessage(Text.literal("Crosshair: " + style()));
        }).dimensions(right, y, colW, h).build());
        addDrawableChild(ButtonWidget.builder(Text.literal("Done"), b -> close())
            .dimensions(width / 2 - 60, y + gap + 6, 120, h).build());
    }

    private static String style() {
        return CrosshairModule.STYLES[Math.floorMod(Lumora.config().crosshairStyle, CrosshairModule.STYLES.length)];
    }

    private static Text label(Module m) {
        return Text.literal(m.name + ": " + (m.enabled() ? "ON" : "OFF"));
    }

    @Override
    public void render(DrawContext ctx, int mx, int my, float delta) {
        renderBackground(ctx, mx, my, delta);
        ctx.drawCenteredTextWithShadow(textRenderer, Text.literal("Lumora Settings"), width / 2, 22,
            Theme.alpha(Theme.current().accent(), fade()));
        super.render(ctx, mx, my, delta);
    }

    @Override public void close() { client.setScreen(parent); }
    @Override public void removed() { Lumora.config().save(); }
}
