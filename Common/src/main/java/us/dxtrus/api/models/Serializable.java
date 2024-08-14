package us.dxtrus.api.models;

import org.jetbrains.annotations.NotNull;

/**
 * Represents a data object that is to be stored in the database.
 */
public interface Serializable {
    @NotNull
    String toJson();
}
