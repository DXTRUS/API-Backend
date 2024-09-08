package us.dxtrus.api.models.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import us.dxtrus.api.models.Serializable;

import java.util.UUID;

/**
 * Represents a user.
 */
@Getter
public class User extends SimpleUser implements Serializable {
    @Expose
    @SerializedName("current_proxy")
    private final String currentProxy;
    @Expose
    @SerializedName("current_server")
    private final String currentServer;
    @Expose
    private final boolean online;

    public User(String name, UUID uniqueId, String currentProxy, String currentServer, boolean online) {
        super(name, uniqueId);
        this.currentProxy = currentProxy;
        this.currentServer = currentServer;
        this.online = online;
    }
}
