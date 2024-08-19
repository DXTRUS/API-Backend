package us.dxtrus.api.database;

import org.jetbrains.annotations.NotNull;
import us.dxtrus.api.LogManager;
import us.dxtrus.commons.database.DatabaseHandler;
import us.dxtrus.commons.database.DatabaseObject;

import java.util.*;
import java.util.concurrent.*;

/**
 * This is the manager for all database interactions.
 * There should be no case where this is modified.
 * Access this class via {@link DatabaseManager#getInstance()}
 */
public final class DatabaseManager {
    private static DatabaseManager instance;

    private final ScheduledExecutorService schedular = Executors.newSingleThreadScheduledExecutor();
    private final Queue<@NotNull Runnable> databaseOps = new ConcurrentLinkedDeque<>();

    private final Map<DatabaseType, Class<? extends DatabaseHandler>> databaseHandlers = new HashMap<>();
    private final DatabaseHandler handler;

    private DatabaseManager() {
        LogManager.info("Connecting to Database and populating caches...");
        databaseHandlers.put(DatabaseType.MONGO, MongoHandler.class);

        this.handler = initHandler();
        schedular.scheduleAtFixedRate(() -> {
            Runnable runnable = databaseOps.poll();
            if (runnable != null) {
                runnable.run();
            }
        }, 0L, 20L, TimeUnit.MILLISECONDS);
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

    public <T extends DatabaseObject> void save(Class<T> clazz, T t) {
        if (!isConnected()) {
            LogManager.severe("Tried to perform database action when the database is not connected!");
            return;
        }
        databaseOps.offer(() -> handler.save(clazz, t));
    }

    public <T extends DatabaseObject> void delete(Class<T> clazz, T t) {
        if (!isConnected()) {
            LogManager.severe("Tried to perform database action when the database is not connected!");
            return;
        }
        databaseOps.offer(() -> handler.delete(clazz, t));
    }

    public <T extends DatabaseObject> void update(Class<T> clazz, T t, String[] params) {
        if (!isConnected()) {
            LogManager.severe("Tried to perform database action when the database is not connected!");
            return;
        }
        databaseOps.offer(() -> handler.update(clazz, t, params));
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