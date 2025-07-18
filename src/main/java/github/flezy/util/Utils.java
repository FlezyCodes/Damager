package github.flezy.util;


import net.minecraft.server.v1_8_R3.ChatComponentText;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.Arrays;
import java.util.List;

public class Utils {

    public void sendActionBar(Player player, String message) {
        IChatBaseComponent chat = new ChatComponentText(message);
        PacketPlayOutChat packet = new PacketPlayOutChat(chat, (byte) 2);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
    }



    public static void sendRecraft(Player player) {
        sendRecraft(player, 64);
    }

    public static void sendRecraft(Player player, int amount) {
        PlayerInventory inv = player.getInventory();

        for (int i = 0; i < 36; i++)
            inv.setItem(i, new ItemStack(Material.MUSHROOM_SOUP));

        inv.setItem(13, new ItemStack(Material.BOWL, amount));
        inv.setItem(14, new ItemStack(Material.RED_MUSHROOM, amount));
        inv.setItem(15, new ItemStack(Material.BROWN_MUSHROOM, amount));
    }
}
