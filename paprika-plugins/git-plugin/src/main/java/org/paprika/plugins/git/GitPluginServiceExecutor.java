package org.paprika.plugins.git;

import org.paprika.component.CommandUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author qiqiang
 */
@Service
public class GitPluginServiceExecutor {
    @Autowired
    private GitPluginProperties gitPluginProperties;

    public void clone(String address, String projectName) {
        String format = "%s clone %s %s";
        String projectDir = gitPluginProperties.getProjectHome() + "/" + projectName;
        String command = String.format(format, gitPluginProperties.getHome() + "/git", address, projectDir);
        CommandUtils.exec(command);
    }

}