package us.dxtrus.api.models.dungeons;

import com.google.gson.annotations.Expose;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import us.dxtrus.api.models.Serializable;
import us.dxtrus.api.models.user.SimpleUser;

import java.util.List;

@Getter
@AllArgsConstructor
public class ActiveDungeon implements Serializable {
    @Expose(serialize = false, deserialize = false)
    private final Dungeon parent;
    @Expose
    private final int id;
    @Expose
    private final String state;
    @Expose
    private final List<SimpleUser> challengers;

    @Override
    public @NotNull String toJson() {
        return "";
    }
}
