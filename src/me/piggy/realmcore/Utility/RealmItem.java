package me.piggy.realmcore.Utility;


import me.piggy.realmcore.RealmCore;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import org.apache.commons.lang3.text.WordUtils;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import sun.security.krb5.Realm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RealmItem {

    ItemStack item;
    String Description;
    String id = null;
    Map<Attribute, Integer> attributes = new HashMap<>();


    public RealmItem(ItemStack itemStack) {
        net.minecraft.server.v1_8_R3.ItemStack nmsStack = CraftItemStack.asNMSCopy(itemStack);
        NBTTagCompound compound = nmsStack.hasTag() ? nmsStack.getTag() : new NBTTagCompound();
        NBTTagCompound attributes = compound.hasKey("attributes") ? compound.getCompound("attributes") : new NBTTagCompound();
        for(String string : attributes.c()) {
            this.attributes.put(Attribute.valueOf(string.toUpperCase()), attributes.getInt(string));
        }
        compound.set("attributes", attributes);
        nmsStack.setTag(compound);
        itemStack = CraftItemStack.asBukkitCopy(nmsStack);
        this.item = itemStack;
    }

    public RealmItem(String string) {
        if(RealmCore.getInstance().getItemsConfig().contains("items." + string)) {
            this.Description = RealmCore.getInstance().getItemsConfig().getString("items." + string + ".description");
            this.id = string;
        }
    }

    public String getId() {
        return this.id;
    }

    public String GetDesc(){
        return this.Description;
    }

    public ItemStack getItem() {
        return this.item;
    }

    public Map<Attribute, Integer> getAttributes() {
        return this.attributes;
    }

    public void addAttribute(Attribute attribute, Integer value) {
        net.minecraft.server.v1_8_R3.ItemStack nmsStack = CraftItemStack.asNMSCopy(this.item);
        NBTTagCompound compound = nmsStack.getTag();
        NBTTagCompound attributes = compound.getCompound("attributes");
        attributes.setInt(attribute.toString().toLowerCase(), value);
        compound.set("attributes", attributes);
        nmsStack.setTag(compound);
        this.item = CraftItemStack.asBukkitCopy(nmsStack);
        this.attributes.put(attribute, value);
    }

    public void removeAttribute(Attribute attribute) {
        net.minecraft.server.v1_8_R3.ItemStack nmsStack = CraftItemStack.asNMSCopy(this.item);
        NBTTagCompound compound = nmsStack.getTag();
        NBTTagCompound attributes = compound.getCompound("attributes");
        attributes.remove(attribute.toString().toLowerCase());
        compound.set("attributes", attributes);
        nmsStack.setTag(compound);
        this.item = CraftItemStack.asBukkitCopy(nmsStack);
        this.attributes.remove(attribute);
    }

    public void updateLore() {
        ArrayList<String> lore = new ArrayList<>();
        ItemMeta meta = item.getItemMeta();
        if(this.attributes.size() != 0) {
            lore.add(Utils.color("&aAttributes (" + attributes.size() + "):"));
            for (Map.Entry entry : this.attributes.entrySet()) {
                lore.add(Utils.color(" &7+" + entry.getValue() + " " + WordUtils.capitalizeFully(entry.getKey().toString())));
            }
            meta.setLore(lore);
        }
        else {
            lore.add(Utils.color("&cNo attributes!"));
            meta.setLore(lore);
        }
        item.setItemMeta(meta);
    }

}
