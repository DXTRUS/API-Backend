package us.dxtrus.api.models.user;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class DatabaseUser implements User {
    @Expose
    @SerializedName("username")
    private final String name;
    @Expose
    @SerializedName("uuid")
    private final UUID uniqueId;
    @Expose
    @SerializedName("current_proxy")
    private final String currentProxy;
    @Expose
    @SerializedName("current_server")
    private final String currentServer;
    @Expose
    private final boolean online;

    @Override
    public @NotNull String toJson() {
        return new Gson().toJson(this);
    }
}
