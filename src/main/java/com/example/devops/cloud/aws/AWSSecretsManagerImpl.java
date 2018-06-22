package com.example.devops.cloud.aws;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.secretsmanager.AWSSecretsManager;
import com.amazonaws.services.secretsmanager.AWSSecretsManagerClientBuilder;
import com.amazonaws.services.secretsmanager.model.*;
import com.example.devops.cloud.SecretsManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("aws")
public class AWSSecretsManagerImpl implements SecretsManager {

    private final static Logger LOGGER = LoggerFactory.getLogger(AWSSecretsManagerImpl.class);

    @Value("${aws.region}")
    private String region;

    @Override
    public String getSecret(String key) {
        String endpoint = "secretsmanager."+region+".amazonaws.com";
        AwsClientBuilder.EndpointConfiguration config = new AwsClientBuilder.EndpointConfiguration(endpoint, region);
        AWSSecretsManagerClientBuilder clientBuilder = AWSSecretsManagerClientBuilder.standard();
        clientBuilder.setEndpointConfiguration(config);
        AWSSecretsManager client = clientBuilder.build();

        String secret = null;
        GetSecretValueRequest getSecretValueRequest = new GetSecretValueRequest()
                .withSecretId(key);
        GetSecretValueResult getSecretValueResult = null;

        try {
            getSecretValueResult = client.getSecretValue(getSecretValueRequest);

        } catch(ResourceNotFoundException e) {
            LOGGER.error("The requested secret " + key + " was not found");
        } catch (InvalidRequestException e) {
            LOGGER.error("The request was invalid due to: " + e.getMessage());
        } catch (InvalidParameterException e) {
            LOGGER.error("The request had invalid params: " + e.getMessage());
        }

        if(getSecretValueResult != null && getSecretValueResult.getSecretString() != null) {
            secret = getSecretValueResult.getSecretString();
        }

        return secret;
    }
}
