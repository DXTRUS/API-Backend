package us.dxtrus.api.server.database;

import us.dxtrus.api.LogManager;
import us.dxtrus.api.database.DatabaseType;
import us.dxtrus.api.server.database.handlers.MongoHandler;
import us.dxtrus.commons.database.DatabaseHandler;
import us.dxtrus.commons.database.DatabaseObject;

import java.util.*;
import java.util.concurrent.CompletableFuture;

/**
 * This is the manager for all database interactions.
 * There should be no case where this is modified.
 * Access this class via {@link DatabaseManager#getInstance()}
 */
public final class DatabaseManager {
    private static DatabaseManager instance;

    private final Map<DatabaseType, Class<? extends DatabaseHandler>> databaseHandlers = new HashMap<>();
    private final DatabaseHandler handler;

    private DatabaseManager() {
        LogManager.info("Connecting to Database and populating caches...");
        databaseHandlers.put(DatabaseType.MONGO, MongoHandler.class);

        this.handler = initHandler();
        LogManager.debug("Connected to Database and populated caches!");
    }

    public <T extends DatabaseObject> CompletableFuture<List<T>> getAll(Class<T> clazz) {
        if (!isConnected()) {
            LogManager.severe("Tried to perform database action when the database is not connected!");
            return CompletableFuture.completedFuture(List.of());
        }
        return CompletableFuture.supplyAsync(() -> handler.getAll(clazz));
    }

    public <T extends DatabaseObject> CompletableFuture<Optional<T>> get(Class<T> clazz, UUID id) {
        if (!isConnected()) {
            LogManager.severe("Tried to perform database action when the database is not connected!");
            return CompletableFuture.completedFuture(Optional.empty());
        }
        return CompletableFuture.supplyAsync(() -> handler.get(clazz, id));
    }

    /**
     * Search the database for something matching name.
     * @param clazz the class to search for.
     * @param name the name, either a username or a servername.
     * @return a completable future of the optional object or an empty optional
     */
    public <T extends DatabaseObject> CompletableFuture<Optional<T>> search(Class<T> clazz, String name) {
        if (!isConnected()) {
            LogManager.severe("Tried to perform database action when the database is not connected!");
            return CompletableFuture.completedFuture(Optional.empty());
        }
        return CompletableFuture.supplyAsync(() -> handler.search(clazz, name));
    }

    public <T extends DatabaseObject> CompletableFuture<Void> save(Class<T> clazz, T t) {
        if (!isConnected()) {
            LogManager.severe("Tried to perform database action when the database is not connected!");
            return CompletableFuture.completedFuture(null);
        }
        return CompletableFuture.supplyAsync(() -> {
            handler.save(clazz, t);
            return null;
        });
    }

    public <T extends DatabaseObject> CompletableFuture<Void> delete(Class<T> clazz, T t) {
        if (!isConnected()) {
            LogManager.severe("Tried to perform database action when the database is not connected!");
            return CompletableFuture.completedFuture(null);
        }
        return CompletableFuture.supplyAsync(() -> {
            handler.delete(clazz, t);
            return null;
        });
    }

    public <T extends DatabaseObject> CompletableFuture<Void> update(Class<T> clazz, T t, String[] params) {
        if (!isConnected()) {
            LogManager.severe("Tried to perform database action when the database is not connected!");
            return CompletableFuture.completedFuture(null);
        }
        return CompletableFuture.supplyAsync(() -> {
            handler.update(clazz, t, params);
            return null;
        });
    }

    public boolean isConnected() {
        return handler.isConnected();
    }

    public void shutdown() {
        handler.destroy();
    }

    private DatabaseHandler initHandler() {
        DatabaseType type = DatabaseType.MONGO;
        LogManager.debug("DB Type: %s".formatted(type.getFriendlyName()));
        try {
            Class<? extends DatabaseHandler> handlerClass = databaseHandlers.get(type);
            if (handlerClass == null) {
                throw new IllegalStateException("No handler for database type %s registered!".formatted(type.getFriendlyName()));
            }
            return handlerClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static DatabaseManager getInstance() {
        if (instance == null) {
            instance = new DatabaseManager();
            instance.handler.connect();
        }
        return instance;
    }
}