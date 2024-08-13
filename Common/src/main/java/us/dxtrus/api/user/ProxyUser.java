package us.dxtrus.api.user;

import java.util.Optional;
import java.util.UUID;

public abstract class ProxyUser implements User {
    @Override
    public String getName() {
        return "";
    }

    @Override
    public UUID getUnqiueId() {
        return null;
    }

    @Override
    public Optional<String> getCurrentProxy() {
        return Optional.empty();
    }

    @Override
    public Optional<String> getCurrentServer() {
        return Optional.empty();
    }
}
