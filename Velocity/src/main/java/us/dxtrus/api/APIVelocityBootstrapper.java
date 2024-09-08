package us.dxtrus.api;

import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.event.proxy.ProxyShutdownEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.plugin.annotation.DataDirectory;
import com.velocitypowered.api.proxy.ProxyServer;
import us.dxtrus.commons.utils.StringUtils;

import java.nio.file.Path;

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

    }

    @Subscribe
    public void onProxyShutdown(ProxyShutdownEvent event) {
        proxy.getAllPlayers().forEach(player -> player.disconnect(StringUtils.modernMessage("&cThis proxy is restarting...")));
    }
}
