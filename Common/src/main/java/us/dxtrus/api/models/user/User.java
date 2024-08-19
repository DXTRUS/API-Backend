package us.dxtrus.api.models.user;

import org.jetbrains.annotations.NotNull;
import us.dxtrus.api.models.Serializable;

import java.util.UUID;

/**
 * Represents a user that is stored in the database.
 */
public interface User extends Serializable {
    /**
     * Get the player's username.
     * @return the players username
     */
    @NotNull
    String getName();

    /**
     * Get the players UUID
     * @return an uuid that is unique to the player.
     */
    @NotNull
    UUID getUniqueId();

    /**
     * Get the current proxy of the player.
     * @return the proxy server the player is on.
     */
    @NotNull
    String getCurrentProxy();

    /**
     * Get the current server of the player.
     * @return the backend server the player is on.
     */
    @NotNull
    String getCurrentServer();

    /**
     * Whether the player is connected to the server.
     *
     * @return true if the player is online, else false
     */
    boolean isOnline();
}
