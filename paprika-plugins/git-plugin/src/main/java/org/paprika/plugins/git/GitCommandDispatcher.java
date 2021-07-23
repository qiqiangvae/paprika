package org.paprika.plugins.git;

import com.alibaba.fastjson.JSONObject;
import org.paprika.slot.CommandDispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author qiqiang
 */
@Component
public class GitCommandDispatcher implements CommandDispatcher {
    @Autowired
    private GitPluginServiceExecutor gitPluginServiceExecutor;

    @Override
    public void exec(JSONObject command) {
        gitPluginServiceExecutor.clone("git@github.com:qiqiangvae/paprika.git", "demo");
    }
}