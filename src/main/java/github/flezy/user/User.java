package github.flezy.user;

import github.flezy.timer.Updater;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
public class User {

    private final static Set<User> list = new HashSet<>();

    private final UUID id;
    private final String name;

    private int seconds = 0;

    private  Updater.DamageLevel stageDamage = Updater.DamageLevel.NONE;

    public User(Player player){
        this.id = player.getUniqueId();
        this.name = player.getName();

        new Updater().run();

        list.add(this);

    }

    public int getSeconds(){
        return seconds++;
    }

    public static User of(UUID id){
        return list.stream().filter(user-> user.getId().equals(id)).findFirst().orElse(null);
    }
    public static void remove(UUID id){
        list.removeIf(user -> user.getId().equals(id));
    }

}
