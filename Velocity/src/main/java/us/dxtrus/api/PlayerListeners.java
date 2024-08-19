package us.dxtrus.api;

import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.connection.DisconnectEvent;
import com.velocitypowered.api.event.connection.LoginEvent;
import com.velocitypowered.api.event.player.KickedFromServerEvent;
import com.velocitypowered.api.event.player.ServerConnectedEvent;
import com.velocitypowered.api.event.proxy.ProxyShutdownEvent;
import com.velocitypowered.api.proxy.Player;
import us.dxtrus.api.configuration.Settings;
import us.dxtrus.api.database.DatabaseManager;
import us.dxtrus.api.models.user.DatabaseUser;
import us.dxtrus.api.models.user.User;

/**
 * Listeners to monitor user activity and keep the data up-to-date.
 */
public class PlayerListeners {
    /**
     * Monitors player joins to update online status, current proxy and player usernames.
     *
     * @param e login event
     */
    @Subscribe
    public void onProxyJoin(LoginEvent e) {
        Player player = e.getPlayer();
        User user = new DatabaseUser(
                player.getUsername(),
                player.getUniqueId(),
                Settings.getInstance().getProxyName(),
                "Limbo",
                true
        );
        DatabaseManager.getInstance().save(User.class, user);
    }


    public void onProxyStop(KickedFromServerEvent e) {

    }

    /**
     * Monitors player disconnects to update online status and current proxy.
     *
     * @param e the disconnect event
     */
    @Subscribe
    public void onProxyLeave(DisconnectEvent e) {
        Player player = e.getPlayer();
        DatabaseManager.getInstance().save(User.class,
                new DatabaseUser(
                        player.getUsername(),
                        player.getUniqueId(),
                        "None",
                        "None",
                        false
                )
        );
    }

    @Subscribe
    public void onProxyKick(KickedFromServerEvent e) {
        if (!(e.getResult() instanceof KickedFromServerEvent.DisconnectPlayer)) return;

        Player player = e.getPlayer();
        DatabaseManager.getInstance().save(User.class,
                new DatabaseUser(
                        player.getUsername(),
                        player.getUniqueId(),
                        "None",
                        "None",
                        false
                )
        );
    }

    /**
     * Monitors player server changes to update current server.
     *
     * @param e the server connect event
     */
    @Subscribe
    public void onServerChange(ServerConnectedEvent e) {
        DatabaseManager dbManager = DatabaseManager.getInstance();
        Player player = e.getPlayer();
        dbManager.save(User.class,
                new DatabaseUser(
                        player.getUsername(),
                        player.getUniqueId(),
                        Settings.getInstance().getProxyName(),
                        e.getServer().getServerInfo().getName(),
                        true
                )
        );
    }
}
