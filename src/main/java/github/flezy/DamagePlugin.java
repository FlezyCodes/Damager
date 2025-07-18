package github.flezy;

import github.flezy.listeners.PlayerListeners;
import github.flezy.timer.Updater;
import github.flezy.util.loader.Loader;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

@Setter
@Getter
public class DamagePlugin extends JavaPlugin implements Loader {


    public DamagePlugin instance;

    @Override
    public void onEnable() {
        setInstance(this);
        loadEvent(new PlayerListeners());
        updater(20L, 20L, new Updater());

        Bukkit.getOnlinePlayers().forEach(player -> player.kickPlayer("§aReniciando"));
    }


    @Override
    public void onLoad() {

    }

    @Override
    public void loadEvent(Listener... listeners) {
        for (Listener listener : listeners){
            getServer().getPluginManager().registerEvents(listener, this);
            }
    }

    @Override
    public void sendConsole(String... messages) {
        for (String message : messages)
            getServer().getConsoleSender().sendMessage(message);
    }

    @Override
    public void updater(long l, long l1, Runnable... runnables) {
        for(Runnable runnable : runnables)
            Bukkit.getScheduler().runTaskTimer(this, runnable, l, l1);
    }



}
