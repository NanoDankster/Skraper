package io.github.nanodankster.skraper;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public class Skraper extends JavaPlugin {
    private Skraper instance;
    private SkriptAddon addonInstance;

    public Skraper() {
        if (instance == null) {
            instance = this;
        } else {
            throw new IllegalStateException();
        }
    }

    @Override
    public void onEnable() {
        try {
            getAddonInstance().loadClasses("io.github.nanodankster.skraper", "skript");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private SkriptAddon getAddonInstance() {
        if (addonInstance == null) {
            addonInstance = Skript.registerAddon(getInstance());
        }
        return addonInstance;
    }

    private Skraper getInstance() {
        if (instance == null) {
            throw new IllegalStateException();
        }
        return instance;
    }
}
