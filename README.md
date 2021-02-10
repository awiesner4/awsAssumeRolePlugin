# awsAssumeRoleTask

## Usage

Requires Java 8 and Docker. Tested on Linux.

```
$ ./mvnw clean install
```

```yaml
configuration:
  runtime: "concord-v2"
  dependencies:
    - "mvn://com.wiesner.concord:awsAssumeRoleTask:1.0-SNAPSHOT"

flows:
  default:
    - task: assumeRole
      in:
        awsAccessKey: ""
        awsSecretKey: ""
        externalId: ""
        roleArn: "arn:aws:iam::{account_num}:role/{role_name}"
        region: "us-east-2"
      out: awsCredentials
    
    - set:
        accessKeyId: awsCredentials.accessKey
        secretKey: awsCredentials.secretKey
        sessionKey: awsCredentials.sessionKey
```