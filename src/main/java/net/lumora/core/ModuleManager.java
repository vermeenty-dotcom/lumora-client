package net.lumora.core;

import java.util.ArrayList;
import java.util.List;

public class ModuleManager {
    private final List<Module> list = new ArrayList<>();

    public void register(Module m) { list.add(m); }
    public List<Module> all() { return list; }
    public boolean isOn(String id) {
        for (Module m : list) if (m.id.equals(id)) return m.enabled();
        return false;
    }
}
