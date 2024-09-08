package us.dxtrus.api.models.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import us.dxtrus.api.DataConversion;
import us.dxtrus.api.models.Serializable;

import java.util.UUID;

@Getter
public class SimpleUser implements Serializable {
    @Expose
    @SerializedName("username")
    private final String name;
    @Expose
    @SerializedName("uuid")
    private final UUID uniqueId;

    public SimpleUser(String name, UUID uniqueId) {
        this.name = name;
        this.uniqueId = uniqueId;
    }

    @Override
    public @NotNull String toJson() {
        return DataConversion.gson.toJson(this);
    }
}
