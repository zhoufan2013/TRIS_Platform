package com.ai.tris.server;

import com.ai.tris.server.service.interfaces.ICommonService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Sam on 2015/6/9.
 */
public class AppContextFactory {

    private static AppContextFactory appContextFactory = new AppContextFactory();
    private ApplicationContext appContext;

    private AppContextFactory() {
        appContext = new ClassPathXmlApplicationContext("application.xml");
    }

    public static ApplicationContext getAppContext() {
        return appContextFactory.appContext;
    }


    public static void main(String[] args) {
        ICommonService iCommonService = AppContextFactory.getAppContext().getBean(ICommonService.class.getName(), ICommonService.class);
        iCommonService.printCallStack();
    }
}
