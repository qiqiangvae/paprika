package org.paprika.plugins.git;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author qiqiang
 */
@Configuration
@ComponentScan(basePackageClasses = GitPluginConfiguration.class)
public class GitPluginConfiguration {
}