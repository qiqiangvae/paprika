package org.paprika.core.controller;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.paprika.core.slot.PaprikaPluginBoot;
import org.paprika.core.slot.PaprikaPluginLoaderHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qiqiang
 */
@RestController
@Slf4j
public class ExecuteController {
    @Autowired
    private PaprikaPluginBoot paprikaPluginBoot;

    @RequestMapping("/execute")
    public void command(@RequestHeader("service") String service, @RequestBody JSONObject command) {
        PaprikaPluginLoaderHolder holder = paprikaPluginBoot.getLoaderHolderMap().get(service);
        if (holder.isEnable()) {
            holder.getCommandDispatcher().exec(command);
        } else {
            log.info("[{}]未启用", service);
        }
    }
}