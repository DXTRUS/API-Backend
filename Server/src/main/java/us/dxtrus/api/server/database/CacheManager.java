package us.dxtrus.api.server.database;

import org.glassfish.jersey.internal.guava.Cache;
import org.glassfish.jersey.internal.guava.CacheBuilder;
import us.dxtrus.api.models.server.BackendServer;
import us.dxtrus.api.models.server.ProxyServer;
import us.dxtrus.api.models.user.User;
import us.dxtrus.api.server.configuration.ApplicationConfig;

import java.util.Optional;
import java.util.UUID;

public class CacheManager {
    private static CacheManager instance;

    private final Cache<UUID, User> usersCache;
    private final Cache<String, User> yuckyUsersCache;
    private final Cache<String, BackendServer> serverCache;
    private final Cache<String, ProxyServer> proxyCache;

    private CacheManager() {
        ApplicationConfig.Endpoints endpointConfig = ApplicationConfig.getInstance().getEndpoints();

        usersCache = CacheBuilder.newBuilder().expireAfterAccess(endpointConfig.getUserInfo().getTtl(), endpointConfig.getUserInfo().getTtlUnit()).build();
        yuckyUsersCache = CacheBuilder.newBuilder().expireAfterAccess(endpointConfig.getUserInfo().getTtl(), endpointConfig.getUserInfo().getTtlUnit()).build();
        serverCache = CacheBuilder.newBuilder().expireAfterAccess(endpointConfig.getServerInfo().getTtl(), endpointConfig.getServerInfo().getTtlUnit()).build();
        proxyCache = CacheBuilder.newBuilder().expireAfterAccess(endpointConfig.getProxyInfo().getTtl(), endpointConfig.getProxyInfo().getTtlUnit()).build();
    }

    public void cacheUser(User user) {
        usersCache.put(user.getUniqueId(), user);
        yuckyUsersCache.put(user.getName(), user);
    }

    public Optional<User> getUser(UUID uuid) {
        return Optional.ofNullable(usersCache.getIfPresent(uuid));
    }

    public Optional<User> searchUser(String name) {
        return Optional.ofNullable(yuckyUsersCache.getIfPresent(name));
    }

    public void cacheServer(BackendServer server) {
        serverCache.put(server.getName(), server);
    }

    public Optional<BackendServer> getServer(String serverName) {
        return Optional.ofNullable(serverCache.getIfPresent(serverName));
    }

    public void cacheProxy(ProxyServer server) {
        proxyCache.put(server.getName(), server);
    }

    public Optional<ProxyServer> getProxy(String serverName) {
        return Optional.ofNullable(proxyCache.getIfPresent(serverName));
    }

    public static CacheManager getInstance() {
        if (instance == null) {
            instance = new CacheManager();
        }
        return instance;
    }
}
