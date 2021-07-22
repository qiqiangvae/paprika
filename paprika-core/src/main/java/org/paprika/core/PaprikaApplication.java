package org.paprika.core;

import org.paprika.core.slot.PaprikaPluginBoot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author qiqiang
 */
@SpringBootApplication
public class PaprikaApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(PaprikaApplication.class);
        PaprikaPluginBoot pluginBoot = context.getBean(PaprikaPluginBoot.class);
    }
}