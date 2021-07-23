package org.paprika.slot;

import com.alibaba.fastjson.JSONObject;

/**
 * @author qiqiang
 */
public interface CommandDispatcher {

    void exec(JSONObject command);

}