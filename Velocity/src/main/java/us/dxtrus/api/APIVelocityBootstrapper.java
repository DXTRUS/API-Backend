package us.dxtrus.api;

import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.plugin.annotation.DataDirectory;
import com.velocitypowered.api.proxy.ProxyServer;
import us.dxtrus.api.database.DatabaseManager;
import us.dxtrus.api.models.user.User;

import java.nio.file.Path;
import java.util.concurrent.TimeUnit;

@Plugin(id = "api")
public class APIVelocityBootstrapper {
    private final ProxyServer proxy;

    @Inject
    public APIVelocityBootstrapper(ProxyServer proxy, @DataDirectory Path dataDirectory) {
        this.proxy = proxy;
    }

    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {
        proxy.getEventManager().register(this, new PlayerListeners());
//
//        proxy.getScheduler().buildTask(this, () -> {
//            DatabaseManager.getInstance().getAll(User.class).thenAccept(users -> {
//                for (User user : users) {
//                    DatabaseManager.getInstance().delete(User.class, user);
//                }
//            });
//        }).repeat(60L, TimeUnit.MINUTES).schedule();
    }
}
