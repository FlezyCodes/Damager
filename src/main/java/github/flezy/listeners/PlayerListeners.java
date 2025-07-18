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

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

public class PlayerListeners implements Listener {

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        if (event.getFrom().getBlock().equals(event.getTo().getBlock())) {
            return;
        }

        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();

        Block blockBelow = player.getLocation().subtract(0, 1, 0).getBlock();
        Material blockType = blockBelow.getType();

        User user = User.of(uuid);

        Optional<Updater.DamageLevel> damageLevel = Arrays.stream(Updater.DamageLevel.values())
                .filter(level -> level.getMaterial() == blockType)
                .findFirst();

        if (damageLevel.isPresent()) {
            if (user == null) {
                user = new User(player);
            }
            user.setStageDamage(damageLevel.get());

        } else {
            if (user != null) {
                User.remove(player.getUniqueId());
            }
        }

    }




}
