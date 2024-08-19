package us.dxtrus.api.configuration;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import us.dxtrus.api.database.DatabaseType;
import us.dxtrus.commons.config.*;

import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;

@SuppressWarnings("FieldMayBeFinal")
@Getter
@Configuration
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Settings {
    private static Settings instance;

    private static final String CONFIG_HEADER = """
            #########################################
            #           Application Config          #
            #########################################
            """;

    private static final YamlConfigurationProperties PROPERTIES = YamlConfigurationProperties.newBuilder()
            .charset(StandardCharsets.UTF_8)
            .setNameFormatter(NameFormatters.LOWER_KEBAB_CASE)
            .header(CONFIG_HEADER).build();

    @Comment("The name of the proxy.")
    private String proxyName = "Proxy-01";

    @Comment("Database connection configuration.")
    private DatabaseSettings database = new DatabaseSettings();

    @Getter
    @Configuration
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class DatabaseSettings {
        private DatabaseType type = DatabaseType.MONGO;

        private String uri = "mongodb://username:password@localhost:27017/?retryWrites=true&retryReads=true";

        private String database = "API";
    }

    private static Path getConfigDirectory() {
        String currentDirectory = System.getProperty("user.dir");
        return Paths.get(currentDirectory).resolve("api").normalize();
    }

    public static Settings getInstance() {
        if (instance == null) {
            instance = YamlConfigurations
                    .update(getConfigDirectory().resolve("config.yml"), Settings.class, PROPERTIES);
        }
        return instance;
    }
}