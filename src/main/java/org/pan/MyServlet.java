package org.pan;

import com.caucho.hessian.server.HessianServlet;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.pan.service.MyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;

public class MyServlet extends HessianServlet implements MyService {

    private final static Logger LOGGER = LoggerFactory.getLogger(MyServlet.class);

    @Inject
    private MyService myService;

    @Override
    public void init() throws ServletException {
        LOGGER.debug("init");
    }

    @Override
    public String sayHello() {
        return myService.sayHello();
    }
}
