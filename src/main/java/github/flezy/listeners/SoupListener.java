package github.flezy.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;


public class SoupListener implements Listener {

    @EventHandler
    public void playerInteractEvent(PlayerInteractEvent event){

        Player player = event.getPlayer();
        Action action = event.getAction();

        if(action != Action.RIGHT_CLICK_BLOCK && action != Action.RIGHT_CLICK_AIR) return;

        ItemStack item = player.getItemInHand();

        if(item == null || item.getType() != Material.MUSHROOM_SOUP) return;

        if(player.getHealth() < player.getMaxHealth()){
            double newHealth = Math.min(player.getHealth() + 6.0,player.getMaxHealth());

            item.setType(Material.BOWL);
            player.setHealth(newHealth);
        }
        event.setCancelled(true);
    }
}
