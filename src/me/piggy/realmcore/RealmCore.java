package me.piggy.realmcore;

import me.piggy.realmcore.Commands.CommandTest;
import org.bukkit.plugin.java.JavaPlugin;

public class RealmCore extends JavaPlugin {

    private static RealmCore instance;

    @Override
    public void onEnable() {
        getCommand("test").setExecutor(new CommandTest());
        instance = this;
    }

    public static RealmCore getInstance() {
        return instance;
    }
}
