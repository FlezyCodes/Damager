package github.flezy.timer;

import github.flezy.user.User;
import github.flezy.util.Utils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;
import java.util.Random;


@Getter
@Setter
public class Updater extends BukkitRunnable {

    private final Random random = new Random();


    @Override
    public void run() {
        Bukkit.getOnlinePlayers().forEach(this::updateDamage);
    }

    private void updateDamage(Player player) {
        User user = User.of(player.getUniqueId());

        if (user != null) {
            new Utils().sendActionBar(player, "§aTempo: " + user.getSeconds());
            applyDamage(player);
        }
    }

    private void applyDamage(Player player) {

        Block below = player.getLocation().subtract(0, 1, 0).getBlock();
        User user = User.of(player.getUniqueId());

        if(user == null) return;

        Arrays.stream(DamageLevel.values()).forEach(damageLevel -> {
            if(damageLevel.getMaterial() == null) return;

            double criticalDamage = damageLevel.getDamage();

            if(below.getType() == damageLevel.getMaterial()){
                user.setStageDamage(damageLevel);

                if(random.nextDouble() <= 0.20){
                    criticalDamage+= random.nextDouble();
                    new Utils().sendActionBar(player, "§aTempo: " + user.getSeconds() + " §c§lCRITICAL");
                }

                player.damage(damageLevel.getDamage());
//                System.out.print("LEVEL: " + damageLevel.getName() + " DAMAGE: " + damageLevel.getDamage() + " BLOCKTYPE: " + damageLevel.getMaterial().name());
            }
        });

    }


    @Getter
    @AllArgsConstructor
    public enum DamageLevel {
        NONE("", 0, null),
        EASY("§aFacíl", 2.0, Material.EMERALD_BLOCK),
        MEDIUM("§aMedio", 3.0, Material.DIAMOND_BLOCK),
        HARD("§aDíficil", 3.5, Material.REDSTONE);

        private final String name;
        private final double damage;
        private final Material material;


    }
}
