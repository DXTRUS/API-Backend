package us.dxtrus.api.models.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class DatabaseUser implements User {
    private final String name;
    private final UUID uniqueId;
    private final String currentProxy;
    private final String currentServer;

    @Override
    public @NotNull String toJson() {
        return "";
    }
}
