package us.dxtrus.api.database;

import org.jetbrains.annotations.NotNull;

public interface Serializable {
    @NotNull String toJson();

}
