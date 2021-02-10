package com.wiesner.concord.plugins.awsassumerole;

import com.walmartlabs.concord.runtime.v2.sdk.Task;
import com.walmartlabs.concord.runtime.v2.sdk.TaskResult;
import com.walmartlabs.concord.runtime.v2.sdk.Variables;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Named;

@Named("assumeRole")
public class AssumeRoleTask
        implements Task
{
    private final Logger log = LoggerFactory.getLogger(AssumeRoleTask.class);

    @Override
    public TaskResult execute(Variables input)
            throws Exception
    {
        String awsAccessKey = input.getString("awsAccessKey");
        String awsSecretKey = input.getString("awsSecretKey");
        String externalId = input.getString("externalId");
        String roleArn = input.getString("roleArn");
        String region = input.getString("region");

        AwsCredentials aws = AwsAssumeRole.perform(awsAccessKey, awsSecretKey, externalId, roleArn, region);

        return TaskResult.success()
                .value("accessKey", aws.getAccessKey())
                .value("secretKey", aws.getSecretKey())
                .value("sessionKey", aws.getSessionToken());
    }

}
