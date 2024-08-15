package us.dxtrus.api.models.user;

import org.jetbrains.annotations.NotNull;

public interface EditableUser extends User {
    void setUsername(@NotNull String username);
    void

    @Override
    default @NotNull String toJson() {
        throw new UnsupportedOperationException();
    }
}
