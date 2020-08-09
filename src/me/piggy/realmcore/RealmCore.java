package me.piggy.realmcore;

import me.piggy.realmcore.Commands.CommandTest;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.omg.CORBA.DynAnyPackage.Invalid;

import java.io.File;
import java.io.IOException;

public class RealmCore extends JavaPlugin {

    private static RealmCore instance;
    private static File items;
    private static FileConfiguration itemsConfig;

    @Override
    public void onEnable() {
        getCommand("test").setExecutor(new CommandTest());
        setup();
        instance = this;
    }

    private void setup() {
        try {
            items = new File(this.getDataFolder(), "items.yml");
            if(!items.exists()) {
                items.getParentFile().mkdirs();
                saveResource("items.yml", false);
            }
            itemsConfig = YamlConfiguration.loadConfiguration(items);
            try {
                itemsConfig.load(items);
            } catch(IOException | InvalidConfigurationException ex) {
                ex.printStackTrace();
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public static RealmCore getInstance() {
        return instance;
    }

    public FileConfiguration getItemsConfig() {
        return itemsConfig;
    }
}
