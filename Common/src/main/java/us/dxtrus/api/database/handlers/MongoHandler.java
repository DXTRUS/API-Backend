package us.dxtrus.api.database.handlers;

import lombok.Getter;
import us.dxtrus.api.database.Dao;
import us.dxtrus.api.database.DatabaseHandler;
import us.dxtrus.api.models.Serializable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Getter
public final class MongoHandler implements DatabaseHandler {
    private boolean connected = false;

    @Override
    public void connect() {

        connected = false;
    }

    @Override
    public void destroy() {

    }

    @Override
    public void registerDao(Dao<? extends Serializable> dao) {

    }

    @Override
    public void wipeDatabase() {

    }

    @Override
    public <T> List<T> getAll(Class<T> clazz) {
        return List.of();
    }

    @Override
    public <T> Optional<T> get(Class<T> clazz, UUID id) {
        return Optional.empty();
    }

    @Override
    public <T> void save(Class<T> clazz, T t) {

    }

    @Override
    public <T> void update(Class<T> clazz, T t, String[] params) {

    }

    @Override
    public <T> void delete(Class<T> clazz, T t) {

    }

    @Override
    public <T> void deleteSpecific(Class<T> clazz, T t, Object o) {

    }
}
