package us.dxtrus.api.models.user.database;

import us.dxtrus.api.database.Dao;
import us.dxtrus.api.models.user.EditableUser;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class UserDao implements Dao<EditableUser> {
    /**
     * Get an object from the database by its id.
     *
     * @param id the id of the object to get.
     * @return an optional containing the object if it exists, or an empty optional if it does not.
     */
    @Override
    public Optional<EditableUser> get(UUID id) {
        return Optional.empty();
    }

    /**
     * Get all objects of type T from the database.
     *
     * @return a list of all objects of type T in the database.
     */
    @Override
    public List<EditableUser> getAll() {
        return List.of();
    }

    /**
     * Save an object of type T to the database.
     *
     * @param editableUser the object to save.
     */
    @Override
    public void save(EditableUser editableUser) {

    }

    /**
     * Update an object of type T in the database.
     *
     * @param editableUser the object to update.
     * @param params       the parameters to update the object with.
     */
    @Override
    public void update(EditableUser editableUser, String[] params) {

    }

    /**
     * Delete an object of type T from the database.
     *
     * @param editableUser the object to delete.
     */
    @Override
    public void delete(EditableUser editableUser) {

    }
}
