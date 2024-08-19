package us.dxtrus.api.models;

import org.jetbrains.annotations.NotNull;
import us.dxtrus.commons.database.DatabaseObject;

/**
 * Represents a data object that is to be stored in the database.
 */
public interface Serializable extends DatabaseObject {
    @NotNull
    String toJson();
}
