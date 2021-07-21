package org.paprika.plugins.echo;

import org.springframework.beans.factory.InitializingBean;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author qiqiang
 */
public class TimeEcho implements InitializingBean {


    @Override
    public void afterPropertiesSet() throws Exception {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                System.out.println("hello paprika");
            }
        }, 3000, 3000);
    }
}