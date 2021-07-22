package org.paprika.plugins.echo;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author qiqiang
 */
public class TimeEcho implements InitializingBean, DisposableBean {
    private ScheduledThreadPoolExecutor executor;


    @Override
    public void afterPropertiesSet() throws Exception {
        executor = new ScheduledThreadPoolExecutor(1, r -> new Thread(r, "echo-thread"));
        executor.scheduleAtFixedRate(() -> System.out.println("hello paprika echo"), 3, 3, TimeUnit.SECONDS);
    }


    @Override
    public void destroy() throws Exception {
        executor.shutdown();
    }
}