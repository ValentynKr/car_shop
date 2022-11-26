package com.epam.lab.shop.listener;

import com.epam.lab.shop.constant.Constant;
import com.epam.lab.shop.database.ConnectionManager;
import com.epam.lab.shop.database.TransactionManager;
import com.epam.lab.shop.factory.OrderFactory;
import com.epam.lab.shop.factory.UserFactory;
import com.epam.lab.shop.repository.impl.BlackListRepositoryImpl;
import com.epam.lab.shop.repository.impl.CarRepositoryImpl;
import com.epam.lab.shop.repository.impl.CategoryRepositoryImpl;
import com.epam.lab.shop.repository.impl.ManufacturerRepositoryImpl;
import com.epam.lab.shop.repository.impl.OrderDetailRepositoryImpl;
import com.epam.lab.shop.repository.impl.OrderRepositoryImpl;
import com.epam.lab.shop.repository.impl.UserRepositoryImpl;
import com.epam.lab.shop.service.impl.BlackListServiceImpl;
import com.epam.lab.shop.service.impl.CarServiceImpl;
import com.epam.lab.shop.service.impl.CategoryServiceImpl;
import com.epam.lab.shop.service.impl.ManufacturerServiceImpl;
import com.epam.lab.shop.service.impl.OrderServiceImpl;
import com.epam.lab.shop.service.impl.UserServiceImpl;
import com.epam.lab.shop.utility.AvatarManager;
import com.epam.lab.shop.utility.CaptchaGarbageCollector;
import com.epam.lab.shop.utility.CaptchaGenerator;
import com.epam.lab.shop.utility.Extractor;
import com.epam.lab.shop.utility.PasswordHashingUtil;
import com.epam.lab.shop.utility.RequestBuilder;
import com.epam.lab.shop.utility.Validator;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

@WebListener
public class ContextListener implements ServletContextListener {

    private final UserServiceImpl userService;
    private final BlackListServiceImpl blackListService;
    private final CarServiceImpl carService;
    private final CategoryServiceImpl categoryService;
    private final ManufacturerServiceImpl manufacturerService;
    private final OrderServiceImpl orderService;
    private final Logger LOGGER;
    private final List<String> locations;
    private final ConcurrentHashMap<String, Constant> userCaptchaMap;

    public ContextListener() {
        ConnectionManager connectionManager = new ConnectionManager();
        TransactionManager transactionManager = new TransactionManager(connectionManager);
        this.userService = new UserServiceImpl(new UserRepositoryImpl(), transactionManager);
        this.blackListService = new BlackListServiceImpl(new BlackListRepositoryImpl(), transactionManager);
        this.carService = new CarServiceImpl(new CarRepositoryImpl(), transactionManager);
        this.categoryService = new CategoryServiceImpl(new CategoryRepositoryImpl(), transactionManager);
        this.manufacturerService = new ManufacturerServiceImpl(new ManufacturerRepositoryImpl(), transactionManager);
        this.orderService = new OrderServiceImpl(new OrderRepositoryImpl(), new OrderDetailRepositoryImpl(), transactionManager);
        this.LOGGER = Logger.getLogger(ContextListener.class.getName());
        this.locations = new ArrayList<>(Arrays.asList(Constant.SESSION, Constant.FIELD, Constant.COOKIE));
        this.userCaptchaMap = new ConcurrentHashMap<>();
    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext context = servletContextEvent.getServletContext();
        initProperties(context);
        initServices(context);
        initUtils(context);
    }

    private void initProperties(ServletContext context) {
        String captchaLocation = context.getInitParameter(Constant.CAPTCHA_LOCATION);
        String captchaTimeout = context.getInitParameter(Constant.CAPTCHA_TIMEOUT);
        String captchaCollectorInterval = context.getInitParameter(Constant.CAPTCHA_COLLECTOR_INTERVAL);
        context.setAttribute(Constant.CAPTCHA_LOCATION, locations.contains(captchaLocation) ? captchaLocation : Constant.SESSION);
        context.setAttribute(Constant.CAPTCHA_TIMEOUT, captchaTimeout == null ? Constant.DEFAULT_TIMEOUT : captchaTimeout);
        context.setAttribute(Constant.CAPTCHA_COLLECTOR_INTERVAL, captchaCollectorInterval == null ? Constant.DEFAULT_CAPTCHA_COLLECTOR_INTERVAL : captchaCollectorInterval);
        context.setAttribute(Constant.USER_CAPTCHA_MAP, userCaptchaMap);
        LOGGER.info(Constant.APPLICATION_PROPERTIES_WERE_INITIALIZED);
    }

    private void initServices(ServletContext context) {
        context.setAttribute(Constant.USER_SERVICE, userService);
        context.setAttribute(Constant.BLACK_LIST_SERVICE, blackListService);
        context.setAttribute(Constant.CAR_SERVICE, carService);
        context.setAttribute(Constant.CATEGORY_SERVICE, categoryService);
        context.setAttribute(Constant.MANUFACTURER_SERVICE, manufacturerService);
        context.setAttribute(Constant.ORDER_SERVICE, orderService);
    }

    private void initUtils(ServletContext context) {
        PasswordHashingUtil passwordHashingUtil = new PasswordHashingUtil();
        context.setAttribute(Constant.PASSWORD_HASHING_UTIL, passwordHashingUtil);
        context.setAttribute(Constant.USER_FACTORY, new UserFactory(passwordHashingUtil));
        context.setAttribute(Constant.ORDER_FACTORY, new OrderFactory());
        context.setAttribute(Constant.CAPTCHA_FACTORY, new CaptchaGenerator());
        context.setAttribute(Constant.EXTRACTOR, new Extractor());
        context.setAttribute(Constant.VALIDATOR, new Validator(userService));
        context.setAttribute(Constant.AVATAR_MANAGER, new AvatarManager());
        context.setAttribute(Constant.QUERY_BUILDER, new RequestBuilder());
        CaptchaGarbageCollector captchaGarbageCollector = new CaptchaGarbageCollector(context);
        captchaGarbageCollector.start();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }
}