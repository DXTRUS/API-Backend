package us.dxtrus.api.server.database.handlers;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import us.dxtrus.api.LogManager;
import us.dxtrus.api.server.configuration.ApplicationConfig;
import us.dxtrus.api.models.user.User;
import us.dxtrus.api.models.user.database.UserDao;
import us.dxtrus.commons.database.DatabaseHandler;
import us.dxtrus.commons.database.DatabaseObject;
import us.dxtrus.commons.database.dao.Dao;
import us.dxtrus.commons.database.mongo.CollectionHelper;
import us.dxtrus.commons.database.mongo.MongoConnectionHandler;

import java.util.*;

@Getter
public class MongoHandler implements DatabaseHandler {
    private final Map<Class<?>, Dao<?>> daos = new HashMap<>();

    @Getter private boolean connected = false;

    private MongoConnectionHandler connectionHandler;
    private CollectionHelper collectionHelper;

    @Override
    public void connect() {
        ApplicationConfig.DatabaseSettings config = ApplicationConfig.getInstance().getDatabase();
        try {
            @NotNull String connectionURI = config.getUri();
            @NotNull String database = config.getDatabase();
            LogManager.debug("Connecting to: " + connectionURI);
            connectionHandler = new MongoConnectionHandler(connectionURI, database);
            collectionHelper = new CollectionHelper(connectionHandler.getDatabase());
            connected = true;
        } catch (Exception e) {
            destroy();
            throw new IllegalStateException("Failed to establish a connection to the MongoDB database. " +
                    "Please check the supplied database credentials in the config file", e);
        }
        registerDaos();
    }

    @Override
    public void destroy() {
        if (connectionHandler != null) connectionHandler.closeConnection();
    }

    @Override
    public void registerDao(Class<?> clazz, Dao<? extends DatabaseObject> dao) {
        daos.put(clazz, dao);
    }

    public void registerDaos() {
        registerDao(User.class, new UserDao(collectionHelper));
    }


    @Override
    public void wipeDatabase() {
        // nothing yet
    }

    @Override
    public <T extends DatabaseObject> List<T> getAll(Class<T> clazz) {
        return (List<T>) getDao(clazz).getAll();
    }

    @Override
    public <T extends DatabaseObject> Optional<T> get(Class<T> clazz, UUID id) {
        return (Optional<T>) getDao(clazz).get(id);
    }

    @Override
    public <T extends DatabaseObject> Optional<T> search(Class<T> clazz, String s) {
        return (Optional<T>) getDao(clazz).get(s);
    }

    @Override
    public <T extends DatabaseObject> void save(Class<T> clazz, T t) {
        getDao(clazz).save(t);
    }

    @Override
    public <T extends DatabaseObject> void update(Class<T> clazz, T t, String[] params) {
        getDao(clazz).update(t, params);
    }

    @Override
    public <T extends DatabaseObject> void delete(Class<T> clazz, T t) {
        getDao(clazz).delete((DatabaseObject) t);
    }

    @Override
    public <T extends DatabaseObject> void deleteSpecific(Class<T> clazz, T t, Object o) {
        getDao(clazz).deleteSpecific(t, o);
    }

    /**
     * Gets the DAO for a specific class.
     *
     * @param clazz The class to get the DAO for.
     * @param <T>   The type of the class.
     * @return The DAO for the specified class.
     */
    private <T extends DatabaseObject> Dao<T> getDao(Class<?> clazz) {
        if (!daos.containsKey(clazz))
            throw new IllegalArgumentException("No DAO registered for class " + clazz.getName());
        return (Dao<T>) daos.get(clazz);
    }
}
