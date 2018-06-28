package org.pan;

import com.caucho.hessian.client.HessianProxyFactory;
import org.eclipse.jetty.server.Server;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.pan.service.MyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;

public class JettyServerTest {

    private final static Logger LOGGER = LoggerFactory.getLogger(JettyServerTest.class);

    private static Server start;

    @BeforeClass
    public static void start() throws Exception {
        JettyServer jettyServer = new JettyServer();
        start = jettyServer.start();
    }

    @AfterClass
    public static void stop() throws Exception {
        start.stop();
    }

    @Test
    public void test() throws MalformedURLException {
        for (int i = 0; i < 10000; i++) {
            HessianProxyFactory hessianProxyFactory = new HessianProxyFactory();
            MyService o = (MyService) hessianProxyFactory.create(MyService.class, "http://127.0.0.1:8080/myservice");
            String sayHello = o.sayHello();
            LOGGER.info(sayHello);
        }
    }

}
