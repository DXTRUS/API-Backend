package us.dxtrus.api.models.dungeons;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import us.dxtrus.api.DataConversion;
import us.dxtrus.api.models.Serializable;

import java.util.List;

@Getter
@AllArgsConstructor
public class Dungeon implements Serializable {
    @Expose
    @SerializedName("dungeon_name")
    private final String dungeonName;
    @Expose
    @SerializedName("active_dungeons")
    private final List<ActiveDungeon> activeDungeons;

    @Override
    public @NotNull String toJson() {
        return "{\"name\": \"%s\", \"active_dungeons\": %s}".formatted(dungeonName, DataConversion.gson.toJson(activeDungeons));
    }
}
