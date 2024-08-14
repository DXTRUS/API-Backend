package us.dxtrus.api.database;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DatabaseType {
    MONGO("mongodb", "MongoDB")
    ;
    private final String id;
    private final String friendlyName;
}