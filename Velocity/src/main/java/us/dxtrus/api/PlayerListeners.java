package us.dxtrus.api;

import com.velocitypowered.api.event.connection.DisconnectEvent;
import com.velocitypowered.api.event.connection.LoginEvent;
import us.dxtrus.api.database.DatabaseManager;
import us.dxtrus.api.models.user.EditableUser;

/**
 * Listeners to monitor user activity and keep the data up-to-date.
 */
public class PlayerListeners {
    /**
     * Monitors player joins to update online status, current proxy and player usernames.
     *
     * @param e login event
     */
    public void onProxyJoin(LoginEvent e) {
        DatabaseManager.getInstance().get(EditableUser.class, e.getPlayer().getUniqueId()).thenAccept(user -> {

        });
    }

    /**
     * Monitors player disconnects to update online status and current proxy.
     *
     * @param e the disconnect event
     */
    public void onProxyLeave(DisconnectEvent e) {

    }
}
