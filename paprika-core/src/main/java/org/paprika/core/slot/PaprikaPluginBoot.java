package org.paprika.core.slot;

import lombok.extern.slf4j.Slf4j;
import org.paprika.slot.PaprikaPluginLoader;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 插件启动器
 *
 * @author qiqiang
 */
@Component
@Slf4j
public class PaprikaPluginBoot implements ApplicationContextAware, InitializingBean {
    private ApplicationContext parentContext;
    private SlotPluginProperties slotPluginProperties;
    private Map<String, PaprikaPluginLoaderHolder> loaderHolderMap;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.parentContext = applicationContext;
        this.slotPluginProperties = parentContext.getBean(SlotPluginProperties.class);
        this.loaderHolderMap = new HashMap<>();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.refreshAllPlugin();
    }

    private void refreshAllPlugin() {
        log.info("正在加载插件");
        Map<String, PaprikaPluginLoader> childBeans = parentContext.getBeansOfType(PaprikaPluginLoader.class);
        for (Map.Entry<String, PaprikaPluginLoader> entry : childBeans.entrySet()) {
            String pluginName = entry.getKey();
            Boolean enable = slotPluginProperties.getEnable().get(pluginName);
            PaprikaPluginLoaderHolder holder = new PaprikaPluginLoaderHolder();
            holder.setPluginName(pluginName);
            PaprikaPluginLoader pluginLoader = entry.getValue();
            holder.setPaprikaPluginLoader(pluginLoader);
            if (Boolean.TRUE.equals(enable)) {
                holder.setEnable(true);
                ConfigurableApplicationContext context = pluginLoader.getApplicationContext(parentContext);
                context.setParent(parentContext);
                context.refresh();
                holder.setContext(context);
                holder.setCommandDispatcher(pluginLoader.getCommandDispatcher());
                log.info("成功开启插件[{}]", pluginName);
            } else {
                log.info("未开启插件[{}]", pluginName);
                holder.setEnable(false);
            }
            loaderHolderMap.put(pluginName, holder);
        }
    }

    public void enablePlugin(String pluginName) {
        PaprikaPluginLoaderHolder holder = loaderHolderMap.get(pluginName);
        PaprikaPluginLoader pluginLoader = holder.getPaprikaPluginLoader();
        ConfigurableApplicationContext context = pluginLoader.getApplicationContext(parentContext);
        context.setParent(parentContext);
        context.refresh();
        holder.setContext(context);
        holder.setCommandDispatcher(pluginLoader.getCommandDispatcher());
        log.info("成功开启插件[{}]", pluginName);
        holder.setEnable(true);
    }

    public void disablePlugin(String pluginName) {
        PaprikaPluginLoaderHolder holder = loaderHolderMap.get(pluginName);
        ConfigurableApplicationContext context = holder.getContext();
        if (context.isRunning()) {
            context.close();
        }
        log.info("成功关闭插件[{}]", pluginName);
        holder.setEnable(false);
        holder.setContext(null);
        holder.setCommandDispatcher(null);
    }

    public Map<String, PaprikaPluginLoaderHolder> getLoaderHolderMap() {
        return loaderHolderMap;
    }
}