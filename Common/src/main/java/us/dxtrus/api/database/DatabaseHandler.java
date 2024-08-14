package us.dxtrus.api.database;

import us.dxtrus.api.models.Serializable;

/**
 * An interface for interacting with a database via passing DAOs.
 * Inherits the methods and functionality from {@link DataHandler}.
 */
public interface DatabaseHandler extends DataHandler {
    boolean isConnected();
    void connect();
    void destroy();
    void registerDao(Dao<? extends Serializable> dao);
    void wipeDatabase();
}
