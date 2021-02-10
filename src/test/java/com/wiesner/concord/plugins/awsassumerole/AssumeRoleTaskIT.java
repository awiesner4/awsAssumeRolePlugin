package com.wiesner.concord.plugins.awsassumerole;

import ca.ibodrov.concord.testcontainers.Concord;
import ca.ibodrov.concord.testcontainers.ConcordProcess;
import ca.ibodrov.concord.testcontainers.Payload;
import ca.ibodrov.concord.testcontainers.junit4.ConcordRule;
import com.walmartlabs.concord.client.ProcessEntry;
import org.junit.Rule;
import org.junit.Test;

/**
 * A simple integration test for our task.
 */
public class AssumeRoleTaskIT {

    @Rule
    public final ConcordRule concord = new ConcordRule()
            .mode(Concord.Mode.DOCKER)
            // we expect the task's JAR to be present in the local Maven repository
            // before the test starts
            .useLocalMavenRepository(true);

    @Test
    public void test() throws Exception {
        ConcordProcess proc = concord.processes().start(new Payload()
                .resource("concord.yml", ClassLoader.getSystemResource("test.yml")));

        proc.waitForStatus(ProcessEntry.StatusEnum.FINISHED);
//        proc.assertLog(".*Hello, User!.*");
    }
}
