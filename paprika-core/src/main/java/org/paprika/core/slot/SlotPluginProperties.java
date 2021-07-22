package org.paprika.core.slot;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author qiqiang
 */
@Slf4j
@ToString
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "paprika.plugin")
public class SlotPluginProperties implements InitializingBean {
    private Map<String, Boolean> enable;

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("插件配置参数:[{}]", this.toString());
    }
}