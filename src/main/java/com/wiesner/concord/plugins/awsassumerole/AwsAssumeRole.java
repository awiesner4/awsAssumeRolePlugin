package com.wiesner.concord.plugins.awsassumerole;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.securitytoken.AWSSecurityTokenService;
import com.amazonaws.services.securitytoken.AWSSecurityTokenServiceClientBuilder;
import com.amazonaws.services.securitytoken.model.AssumeRoleRequest;
import com.amazonaws.services.securitytoken.model.AssumeRoleResult;
import com.amazonaws.services.securitytoken.model.Credentials;

import static java.util.Objects.requireNonNull;

public class AwsAssumeRole
{
    public static AwsCredentials perform(String accessKeyId, String secretAccessKey, String externalId, String roleArn, String region)
    {
        AWSSecurityTokenService stsClient = AWSSecurityTokenServiceClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(
                        requireNonNull(accessKeyId, "accessKeyId is null"),
                        requireNonNull(secretAccessKey, "secretAccessKey is null"))))
                .withRegion(region)
                .build();
        AssumeRoleRequest arReq = new AssumeRoleRequest()
                .withRoleArn(roleArn)
                .withExternalId(externalId)
                .withRoleSessionName("galaxy-flows")
                .withDurationSeconds(60 * 60);

        AssumeRoleResult arRes = stsClient.assumeRole(arReq);
        Credentials credentials = arRes.getCredentials();
        return new AwsCredentials(
                credentials.getAccessKeyId(),
                credentials.getSecretAccessKey(),
                credentials.getSessionToken(),
                credentials.getExpiration().getTime());
    }

    private AwsAssumeRole()
    {
    }
}