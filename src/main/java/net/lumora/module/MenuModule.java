package net.lumora.module;

import net.lumora.core.Module;

/** Toggle only; the swap itself happens in MinecraftClientMixin. */
public class MenuModule extends Module {
    public MenuModule() { super("menu", "Custom Main Menu", "Animated themed title screen", true); }
}
