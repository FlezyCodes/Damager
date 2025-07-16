package github.flezy.user;

import lombok.AllArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
public class User {
    private final UUID uuid;
    private final String name;
   private long time;


}
