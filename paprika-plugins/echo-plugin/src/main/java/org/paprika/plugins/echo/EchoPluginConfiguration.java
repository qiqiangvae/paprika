package org.paprika.plugins.echo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author qiqiang
 */
@Configuration
@ComponentScan(basePackageClasses = EchoPluginConfiguration.class)
public class EchoPluginConfiguration {
    @Bean
    public TimeEcho timeEcho() {
        return new TimeEcho();
    }
}