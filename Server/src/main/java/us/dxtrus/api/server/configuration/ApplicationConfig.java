package us.dxtrus.api.server.configuration;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import us.dxtrus.api.database.DatabaseType;
import us.dxtrus.commons.config.*;

import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("FieldMayBeFinal")
@Getter
@Configuration
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ApplicationConfig {
    private static ApplicationConfig instance;

    private static final String CONFIG_HEADER = """
            #########################################
            #           Application Config          #
            #########################################
            """;

    private static final YamlConfigurationProperties PROPERTIES = YamlConfigurationProperties.newBuilder()
            .charset(StandardCharsets.UTF_8)
            .setNameFormatter(NameFormatters.LOWER_KEBAB_CASE)
            .header(CONFIG_HEADER).build();

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

    @Comment("Configure what endpoints should be enabled.")
    private Endpoints endpoints = new Endpoints();

    @Getter
    @Configuration
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Endpoints {
        private UserInfo userInfo = new UserInfo();

        @Getter
        @Configuration
        @NoArgsConstructor(access = AccessLevel.PRIVATE)
        public static class UserInfo {
            private boolean enabled = true;
            private long ttl = 30;
            private TimeUnit ttlUnit = TimeUnit.SECONDS;
        }

        private ServerInfo serverInfo = new ServerInfo();

        @Getter
        @Configuration
        @NoArgsConstructor(access = AccessLevel.PRIVATE)
        public static class ServerInfo {
            private boolean enabled = true;
            private long ttl = 60;
            private TimeUnit ttlUnit = TimeUnit.SECONDS;
        }

        private ProxyInfo proxyInfo = new ProxyInfo();

        @Getter
        @Configuration
        @NoArgsConstructor(access = AccessLevel.PRIVATE)
        public static class ProxyInfo {
            private boolean enabled = true;
            private long ttl = 5;
            private TimeUnit ttlUnit = TimeUnit.MINUTES;
        }
    }


    private static Path getConfigDirectory() {
        String currentDirectory = System.getProperty("user.dir");
        return Paths.get(currentDirectory).resolve("..").normalize();
    }

    public static void reload() {
        instance = YamlConfigurations
                .load(getConfigDirectory().resolve("config.yml"), ApplicationConfig.class, PROPERTIES);
        System.out.println("[DxtrusAPI Server] Configuration automatically reloaded from disk.");
    }

    public static ApplicationConfig getInstance() {
        if (instance == null) {
            instance = YamlConfigurations
                    .update(getConfigDirectory().resolve("config.yml"), ApplicationConfig.class, PROPERTIES);
            AutoReload.watch(getConfigDirectory(), "config.yml", ApplicationConfig::reload);
        }
        return instance;
    }
}