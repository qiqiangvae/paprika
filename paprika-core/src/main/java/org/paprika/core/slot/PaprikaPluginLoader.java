package org.paprika.core.slot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author qiqiang
 */
@Component
@Slf4j
public class PaprikaPluginLoader implements ApplicationContextAware, InitializingBean {
    private ApplicationContext parentContext;
    private Map<String, ConfigurableApplicationContext> pluginContextMap;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.parentContext = applicationContext;
        this.pluginContextMap = new HashMap<>();

    }

    public Map<String, ConfigurableApplicationContext> getPluginContextMap() {
        return pluginContextMap;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("正在加载插件");
        Map<String, org.paprika.slot.PaprikaPluginLoader> childBeans = parentContext.getBeansOfType(org.paprika.slot.PaprikaPluginLoader.class);
        for (Map.Entry<String, org.paprika.slot.PaprikaPluginLoader> entry : childBeans.entrySet()) {
            org.paprika.slot.PaprikaPluginLoader contextLoader = entry.getValue();
            ConfigurableApplicationContext context = contextLoader.getApplicationContext(parentContext);
            context.setParent(parentContext);
            context.refresh();
            pluginContextMap.put(entry.getKey(), context);
            log.info("加载插件[{}]完成", entry.getKey());
        }
    }
}