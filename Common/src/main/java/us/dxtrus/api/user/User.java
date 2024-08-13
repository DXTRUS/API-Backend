package us.dxtrus.api.user;

import us.dxtrus.api.database.Serializable;

import java.util.Optional;
import java.util.UUID;

public interface User extends Serializable {
    String getName();
    UUID getUnqiueId();

    Optional<String> getCurrentProxy();
    Optional<String> getCurrentServer();
    void takeOwnership();
}
