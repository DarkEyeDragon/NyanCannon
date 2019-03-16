package me.darkeyedragon.nyancannon;

import co.aikar.commands.PaperCommandManager;
import me.darkeyedragon.nyancannon.commands.SpawnNyan;
import org.bukkit.plugin.java.JavaPlugin;

public final class Nyancannon extends JavaPlugin {


    private PaperCommandManager commandManager;

    @Override
    public void onEnable() {
        commandManager = new PaperCommandManager(this);
        commandManager.registerCommand(new SpawnNyan(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public PaperCommandManager getCommandManager() {
        return commandManager;
    }
}
