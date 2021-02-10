package com.wiesner.concord.plugins.awsassumerole;

import java.io.Serializable;
import java.util.Objects;

public class AwsCredentials
        implements Serializable
{
    private final long expires;
    private final String accessKey;
    private final String secretKey;
    private final String sessionToken;

    public AwsCredentials(String accessKey, String secretKey, String sessionToken, long expires)
    {
        this.accessKey = Objects.requireNonNull(accessKey, "accessKey is null");
        this.secretKey = Objects.requireNonNull(secretKey, "secretKey is null");
        this.sessionToken = Objects.requireNonNull(sessionToken, "sessionToken is null");
        this.expires = expires;
    }

    public long getExpires()
    {
        return expires;
    }

    public String getAccessKey()
    {
        return accessKey;
    }

    public String getSecretKey()
    {
        return secretKey;
    }

    public String getSessionToken()
    {
        return sessionToken;
    }

}
