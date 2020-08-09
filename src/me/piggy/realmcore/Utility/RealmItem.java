package me.piggy.realmcore.Utility;


import net.minecraft.server.v1_8_R3.NBTTagCompound;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class RealmItem {

    ItemStack item;
    int id;
    Map<Statistic, Integer> attributes;


    public RealmItem(ItemStack itemStack) {
        Map<Statistic, Integer> attMap = new HashMap<Statistic, Integer>();
        this.id = 1;
        net.minecraft.server.v1_8_R3.ItemStack nmsStack = CraftItemStack.asNMSCopy(itemStack);
        NBTTagCompound compound = nmsStack.hasTag() ? nmsStack.getTag() : new NBTTagCompound();
        NBTTagCompound attributes = compound.hasKey("attributes") ? compound.getCompound("attributes") : new NBTTagCompound();
        for(String string : attributes.c()) {
            attMap.put(Statistic.valueOf(string), attributes.getInt(string));
        }
        compound.set("attributes", attributes);
        nmsStack.setTag(compound);
        itemStack = CraftItemStack.asBukkitCopy(nmsStack);
        this.item = itemStack;
        this.attributes = attMap;
    }

    public int getId() {
        return this.id;
    }

    public ItemStack getItem() {
        return this.item;
    }

    public Map<Statistic, Integer> getAttributes() {
        return this.attributes;
    }

}
