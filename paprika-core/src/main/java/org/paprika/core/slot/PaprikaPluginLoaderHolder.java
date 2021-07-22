package org.paprika.core.slot;

import lombok.Getter;
import lombok.Setter;
import org.paprika.slot.PaprikaPluginLoader;

/**
 * @author qiqiang
 */
@Getter
@Setter
public class PaprikaPluginLoaderHolder {
    private String pluginName;
    private boolean enable;
    private PaprikaPluginLoader paprikaPluginLoader;

}