package org.paprika.slot;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author qiqiang
 */
public interface PaprikaPluginLoader {
    /**
     * 获取子容器
     *
     * @param parent 父容器
     * @return 子容器
     */
    ConfigurableApplicationContext getApplicationContext(ApplicationContext parent);
}