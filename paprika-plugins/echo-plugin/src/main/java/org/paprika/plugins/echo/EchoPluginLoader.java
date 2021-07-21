package org.paprika.plugins.echo;

import org.paprika.slot.PaprikaPlugin;
import org.paprika.slot.PaprikaPluginLoader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * echo plugin
 *
 * @author qiqiang
 */
@PaprikaPlugin("echo")
public class EchoPluginLoader implements PaprikaPluginLoader {

    @Override
    public ConfigurableApplicationContext getApplicationContext(ApplicationContext parent) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(EchoPluginConfiguration.class);
        return context;
    }
}