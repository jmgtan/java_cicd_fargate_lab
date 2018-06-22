package com.example.devops.cloud.aws;

import com.example.devops.cloud.SecretsManager;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Configuration
@Profile("aws")
public class AWSComponentConfiguration {

    private final static Logger LOGGER = LoggerFactory.getLogger(AWSComponentConfiguration.class);

    @Value("${spring.datasource.url}")
    private String datasourceUrl;

    @Value("${spring.datasource.username}")
    private String datasourceUsername;

    @Value("${secrets.namespace}")
    private String secretsNamespace;

    @Value("${secrets.database_password_key}")
    private String databasePasswordKey;

    @Autowired
    private SecretsManager secretsManager;

    private ObjectMapper mapper = new ObjectMapper();

    @Bean
    public DataSource dataSource() throws IOException {
        Map<String, String> secrets = getSecrets();

        String password = secrets.get(databasePasswordKey);

        return DataSourceBuilder
                .create()
                .url(datasourceUrl)
                .username(datasourceUsername)
                .password(password)
                .build();
    }

    public Map<String, String> getSecrets() throws IOException {
        String secrets = secretsManager.getSecret(secretsNamespace);

        if (secrets != null && !secrets.isEmpty()) {
            return mapper.readValue(secrets, new TypeReference<Map<String, String>>() {});
        }

        return new HashMap<>();
    }
}
