package org.pan;

import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.pan.service.MyService;
import org.pan.service.imp.MyServiceImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JettyServer {
    private final static Logger LOGGER = LoggerFactory.getLogger(JettyServer.class);

    public static void main(String[] args) throws Exception {
        new JettyServer().start();
    }

    public Server start() throws Exception {
        Injector injector = Guice.createInjector(new Module() {
            @Override
            public void configure(Binder binder) {
                binder.bind(MyService.class).to(MyServiceImp.class);
                binder.bind(MyServlet.class);
            }
        });
        Server server = new Server(8080);
        ServletHandler servletHandler = new ServletHandler();
        servletHandler.addServletWithMapping(new ServletHolder(injector.getInstance(MyServlet.class)),"/myservice");
        server.setHandler(servletHandler);
        server.start();
        LOGGER.info("启动jetty服务成功");
        return server;
    }
}
