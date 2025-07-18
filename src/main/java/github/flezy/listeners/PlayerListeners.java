package github.flezy.listeners;

import github.flezy.timer.Updater;
import github.flezy.user.User;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.UUID;

public class PlayerListeners implements Listener {

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        if (event.getFrom().getBlock().equals(event.getTo().getBlock())) {
            return;
        }

        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();

        Material blockType = player.getLocation().subtract(0, 1, 0).getBlock().getType();
        User user = User.of(uuid);




        if(blockType == Material.EMERALD_BLOCK){
            if(user != null){
                player.sendMessage("§aNivel: " + user
                        .getStageDamage().getName());
            }else{
                new User(player);
            }
        }else{
            User.remove(player.getUniqueId());
        }
    }



}
