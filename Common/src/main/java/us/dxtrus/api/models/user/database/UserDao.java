package us.dxtrus.api.models.user.database;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import us.dxtrus.api.models.user.DatabaseUser;
import us.dxtrus.api.models.user.User;
import us.dxtrus.commons.database.dao.Dao;
import us.dxtrus.commons.database.mongo.CollectionHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public class UserDao implements Dao<User> {
    private final CollectionHelper collectionHelper;

    /**
     * Get an object from the database by its id.
     *
     * @param id the id of the object to get.
     * @return an optional containing the object if it exists, or an empty optional if it does not.
     */
    @Override
    public Optional<User> get(UUID id) {
        try {
            MongoCollection<Document> collection = collectionHelper.getCollection("listings");
            final Document doc = collection.find().filter(Filters.eq("uuid", id.toString())).first();
            if (doc == null) return Optional.empty();
            final String uuid = doc.getString("uuid");
            final String name = doc.getString("name");
            final String proxy = doc.getString("proxy");
            final String server = doc.getString("server");
            final boolean online = doc.getBoolean("online");
            return Optional.of(new DatabaseUser(name, UUID.fromString(uuid), proxy, server, online));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    /**
     * Search for an object by a name or other string relation.
     * @param search the query to search for.
     * @return an optional containing the object if it exists, or an empty optional if it does not.
     */
    @Override
    public Optional<User> get(String search) {
        try {
            MongoCollection<Document> collection = collectionHelper.getCollection("users");
            final Document doc = collection.find().filter(Filters.eq("name", search)).first();
            if (doc == null) return Optional.empty();
            final String uuid = doc.getString("uuid");
            final String name = doc.getString("name");
            final String proxy = doc.getString("proxy");
            final String server = doc.getString("server");
            final boolean online = doc.getBoolean("online");
            return Optional.of(new DatabaseUser(name, UUID.fromString(uuid), proxy, server, online));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    /**
     * Get all objects of type T from the database.
     *
     * @return a list of all objects of type T in the database.
     */
    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        try {
            MongoCollection<Document> collection = collectionHelper.getCollection("users");
            for (Document doc : collection.find()) {
                if (doc == null) continue;
                final String uuid = doc.getString("uuid");
                final String name = doc.getString("name");
                final String proxy = doc.getString("proxy");
                final String server = doc.getString("server");
                final boolean online = doc.getBoolean("online");
                users.add(new DatabaseUser(name, UUID.fromString(uuid), proxy, server, online));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    /**
     * Save an object of type T to the database.
     *
     * @param user the object to save.
     */
    @Override
    public void save(User user) {
        try {
            MongoCollection<Document> collection = collectionHelper.getCollection("users");
            final Document doc = collection.find().filter(Filters.eq("uuid", user.getUniqueId().toString())).first();
            if (doc != null) {
                collectionHelper.updateDocument("users", doc, Updates.combine(
                        Updates.set("name", user.getName()),
                        Updates.set("proxy", user.getCurrentProxy()),
                        Updates.set("server", user.getCurrentServer()),
                        Updates.set("online", user.isOnline())
                ));
                return;
            }
            Document document = new Document("uuid", user.getUniqueId().toString())
                    .append("name", user.getName())
                    .append("proxy", user.getCurrentProxy())
                    .append("server", user.getCurrentServer())
                    .append("online", user.isOnline());
            collectionHelper.insertDocument("users", document);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Update an object of type T in the database.
     *
     * @param user the object to update.
     * @param params       the parameters to update the object with.
     */
    @Override
    public void update(User user, String[] params) {
        throw new UnsupportedOperationException();
    }

    /**
     * Delete an object of type T from the database.
     *
     * @param user the object to delete.
     */
    @Override
    public void delete(User user) {
        try {
            MongoCollection<Document> collection = collectionHelper.getCollection("users");
            final Document listingDocument = collection.find().filter(Filters.eq("uuid", user.getUniqueId())).first();
            if (listingDocument == null) return;
            collectionHelper.deleteDocument("users", listingDocument);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
