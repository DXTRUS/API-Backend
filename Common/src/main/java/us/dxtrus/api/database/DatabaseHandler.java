package us.dxtrus.api.database;

public interface DatabaseHandler extends DataHandler {
    boolean isConnected();
    void connect();
    void destroy();
    void registerDaos();
    void wipeDatabase();
}
