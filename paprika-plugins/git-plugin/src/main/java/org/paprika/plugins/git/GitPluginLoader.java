package org.paprika.plugins.git;

import org.paprika.slot.CommandDispatcher;
import org.paprika.slot.PaprikaPlugin;
import org.paprika.slot.PaprikaPluginLoader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * git plugin
 *
 * @author qiqiang
 */
@PaprikaPlugin("git")
public class GitPluginLoader implements PaprikaPluginLoader {
    private ConfigurableApplicationContext context;

    @Override
    public ConfigurableApplicationContext getApplicationContext(ApplicationContext parent) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(GitPluginConfiguration.class);
        this.context = context;
        return context;
    }

    @Override
    public CommandDispatcher getCommandDispatcher() {
        return context.getBean(GitCommandDispatcher.class);
    }
}