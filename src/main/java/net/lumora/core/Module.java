package net.lumora.core;

import net.lumora.Lumora;
import net.minecraft.client.MinecraftClient;

/** One self-contained visual feature. */
public abstract class Module {
    public final String id, name, description;
    private final boolean defaultOn;

    protected Module(String id, String name, String description, boolean defaultOn) {
        this.id = id; this.name = name; this.description = description; this.defaultOn = defaultOn;
    }

    public boolean enabled() { return Lumora.config().modules.getOrDefault(id, defaultOn); }
    public void toggle() { Lumora.config().modules.put(id, !enabled()); }

    /** Called once at startup (register HUD elements, etc.). */
    public void init() { }
    /** Called every client tick while enabled. */
    public void tick(MinecraftClient client) { }
}
