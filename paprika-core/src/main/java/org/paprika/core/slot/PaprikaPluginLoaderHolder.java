package org.paprika.core.slot;

import lombok.Getter;
import lombok.Setter;
import org.paprika.slot.CommandDispatcher;
import org.paprika.slot.PaprikaPluginLoader;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author qiqiang
 */
@Getter
@Setter
public class PaprikaPluginLoaderHolder {
    private String pluginName;
    private boolean enable;
    private PaprikaPluginLoader paprikaPluginLoader;
    private ConfigurableApplicationContext context;
    private CommandDispatcher commandDispatcher;

}