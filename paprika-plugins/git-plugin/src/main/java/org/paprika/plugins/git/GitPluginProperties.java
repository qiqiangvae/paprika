package org.paprika.plugins.git;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author qiqiang
 */
@Component
@ConfigurationProperties(prefix = "paprika.git")
@Getter
@Setter
@ToString
public class GitPluginProperties implements InitializingBean {
    @Value("${paprika.git.home}")
    private String home;
    @Value("${paprika.git.project-home}")
    private String projectHome;

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}