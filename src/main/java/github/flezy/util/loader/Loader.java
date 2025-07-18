package github.flezy.util.loader;

import lombok.SneakyThrows;
import org.bukkit.event.Listener;

public interface Loader {


    @SneakyThrows
    void loadEvent(Listener... listeners);

    void sendConsole(String... messages);

    @SneakyThrows
    void updater(long l1, Runnable ...runnables);

}
