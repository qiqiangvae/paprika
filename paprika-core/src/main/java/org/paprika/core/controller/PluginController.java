package org.paprika.core.controller;

import org.paprika.core.slot.PaprikaPluginBoot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qiqiang
 */
@RestController
public class PluginController {
    @Autowired
    private PaprikaPluginBoot paprikaPluginBoot;

    @RequestMapping("/plugin/{pluginName}/enable")
    public void enable(@PathVariable("pluginName") String pluginName) {
        paprikaPluginBoot.enablePlugin(pluginName);
    }

    @RequestMapping("/plugin/{pluginName}/disable")
    public void disable(@PathVariable("pluginName") String pluginName) {
        paprikaPluginBoot.disablePlugin(pluginName);
    }
}