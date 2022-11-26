package com.epam.lab.shop.constant;

import com.epam.lab.shop.exception.ActionNotPermittedException;
import java.util.logging.Logger;

public class Constant {

    private static final Logger LOGGER = Logger.getLogger(Constant.class.getName());

    private Constant () {
        try {
            throw new ActionNotPermittedException(ACTION_IS_NOT_PERMITTED);
        } catch (ActionNotPermittedException actionNotPermittedException) {
            LOGGER.severe(actionNotPermittedException.getMessage());
        }
    }

    public static final String ACCESS_PRESET_FILE_PATH = "accessPresetFilePath";
    public static final String PAGE_IS_NOT_RESTRICTED = "Page is not in restricted access list. Next doFilter invoked";
    public static final String USER_IS_NOT_IDENTIFIED = "user is not identified. Send redirect to login";
    public static final String LOGIN_JSP = "/jsp/login.jsp";
    public static final String USER_IS_AUTHORIZED = "User is authorized. Page was given";
    public static final String USER_IS_NOT_AUTHORIZED = "User is not authorized. Send redirect to access denied page";
    public static final String ACCESS_DENIED_JSP = "/jsp/accessDenied.jsp";
    public static final String ROOT_URI = "/car_shop_war_exploded";
    public static final String FAILED_TO_PARSE_MSG = "Failed to parse access preset file, cause: ";
    public static final String ROLE = "role";
    public static final String URL_PATTERN = "url-pattern";
    public static final String CONSTRAINT = "constraint";
    public static final String PROFILE_JSP = "jsp/profile.jsp";
    public static final int FAILED_LOGIN_ATTEMPTS_LIMIT = 5;
    public static final String LIMIT_EXCEEDED = "Failed login attempts limit exceeded";
    public static final String PASSWORD_IS_INCORRECT = "Password is incorrect. %d attempts left";
    public static final String ACCOUNT_WAS_BLOCKED = "Account was blocked. Too much failed attempts";
    public static final String LOGIN_IS_INCORRECT = "Login is incorrect";
    public static final String BLACK_LIST_SERVICE = "blackListService";
    public static final String TEXT_HTML = "text/html";
    public static final String CONTENT_ENCODING = "Content-Encoding";
    public static final String CANNOT_GET_PRINT_WRITER = "Can not get print writer";
    public static final String CANNOT_GET_OUTPUT_STREAM = "Can not get output stream";
    public static final String NO_CACHE_MAX_AGE_0 = "no-cache, max-age=0";
    public static final String CACHE_CONTROL = "Cache-control";
    public static final String ACCEPT_ENCODING = "Accept-Encoding";
    public static final String GZIP = "gzip";
    public static final String LOCALE = "locale";
    public static final String LOCALES = "locales";
    public static final String LANG = "lang";
    public static final String LOCALE_COOKIE_LIFE_TIME = "localeCookieLifeTime";
    public static final String LOCALE_STORAGE = "localeStorage";
    public static final String DEFAULT = "defaultLocale";
    public static final String AVAILABLE = "availableLocales";
    public static final String REGEX = "\\s";
    public static final String ORDER_FACTORY = "orderFactory";
    public static final String USER_AUTHORISED_WITH_ORDERS = "User authorised and has orders. Orders and map put in session";
    public static final String NOT_LOGGED_IN_MSG = "You are not logged in (only logged users can order)";
    public static final String ORDER = "order";
    public static final String ORDER_ERROR = "orderError";
    public static final String ORDER_DETAIL = "orderDetail";
    public static final String USER_MADE_ORDER_BASKET_IS_CLEARED = "User made an order. Basket is cleared";
    public static final String USER_COMMITTED_MISTAKES_BASKET_NOT_CHANGED = "User committed mistakes during order details inserting. Basket not changed";
    public static final String ORDERS_JSP = "jsp/orders.jsp";
    public static final String ORDERS = "orders";
    public static final String CART_CREDENTIAL_PATTERN = "%s %s %s";
    public static final String PAYMENT = "payment";
    public static final String CREDENTIAL = "credential";
    public static final String EXP_DATE = "expDate";
    public static final String CVV = "cvv";
    public static final String DELIVERY = "delivery";
    public static final String ADDRESS = "address";
    public static final String BASKET = "basket";
    public static final String QUANTITY = "quantity";
    public static final String ID = "id";
    public static final String ADDED_ORDERED_CARS_CURRENT_BASKET_SIZE = "Added ordered cars. Current size of basket: %d, total cost: %f";
    public static final String OPERATION = "operation";
    public static final String BASKET_JSP = "jsp/basket.jsp";
    public static final String USER_SKIPPED_ALL_CARS = "User skipped all cars from basket. Basket is empty";
    public static final String BASKET_OBJECT_CREATED = "New basket object was created";
    public static final String CAR_WAS_DELETED_FROM_BASKET = "Car by id %d was deleted from basket by user";
    public static final String CAR_QUANTITY_WAS_INCREASED = "Car by id %d quantity was increased by user";
    public static final String CAR_QUANTITY_WAS_DECREASED = "Car by id %d quantity was decreased by user";
    public static final String INSERT = "INSERT INTO users VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String UPDATE = "UPDATE users SET firstName=?, secondName=?, " +
            "email=?, phoneNumber=?, birthDate=?, password=?, subscription=?, u_login_attempts=?," +
            "u_date_of_unblocking=?, u_role=? WHERE id=?";
    public static final String SELECT_ALL_FROM_BLACK_LIST = "SELECT * FROM black_list";
    public static final String LABEL_GET_ALL_FROM_BLACK_LIST = "get all from black list";
    public static final String SELECT_FROM_BLACK_LIST_WHERE_USER_ID = "SELECT * FROM black_list WHERE bl_user_id=?";
    public static final String LABEL_GET_BLACK_LIST_RECORD_BY_USER_ID = "get black list record by id";
    public static final String INSERT_INTO_BLACK_LIST = "INSERT INTO black_list VALUES (?, ?, ?)";
    public static final String LABEL_SAVE_BLACK_LIST_RECORD = "save black list record";
    public static final String UPDATE_BLACK_LIST_RECORD = "UPDATE black_list SET bl_cause=?, " +
            "bl_date_of_blocking=? WHERE bl_user_id=?";
    public static final String LABEL_UPDATE_BLACK_LIST_RECORD = "update black list record";
    public static final String DELETE_RECORD_FROM_BLACK_LIST = "DELETE FROM black_list WHERE bl_user_id = ?";
    public static final String LABEL_DELETE_BLACK_LIST_RECORD = "delete black list record by id";
    public static final String LABEL_CHECK_IN_BLACK_LIST_EXIST_BY_ID = "check whether black list exist by id";
    public static final String SELECT_FROM_ORDER_WHERE_ID = "SELECT * FROM `order` WHERE o_id = ?";
    public static final String SELECT_FROM_ORDER_DETAIL_WHERE_ORDER_ID_DTO = "SELECT od_id, od_price_per_one, od_quantity, od_order_id,\n" +
            "od_car_id, c_name, m_name\n" +
            "FROM order_detail\n" +
            "JOIN cars ON od_car_id = cars.c_id\n" +
            "JOIN c_manufacturer cm on cm.m_id = cars.c_manufacturer\n" +
            "WHERE od_order_id=?";
    public static final String SELECT_FROM_ORDER_WHERE_USER_ID = "SELECT * FROM `order` WHERE o_user_id=? ORDER BY o_creation_date DESC";
    public static final String SELECT_FROM_ORDER = "SELECT * FROM `order` ORDER BY o_creation_date DESC";
    public static final String INSERT_INTO_ORDER = "INSERT INTO `order` VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String UPDATE_ORDER_BY_ID = "UPDATE `order` " +
            "SET o_order_status=?, o_detail=?, o_payment_type=?, o_card_credential=?, " +
            "o_delivery_type=?, o_delivery_address=?, o_user_id=?, o_creation_date=? WHERE o_id=?";
    public static final String DELETE_FROM_ORDER_WHERE_ID = "DELETE FROM `order` WHERE o_id = ?";
    public static final String GET_ALL_ORDERS = "get all orders";
    public static final String GET_ALL_ORDERS_BY_USER_ID = "get all orders by user id";
    public static final String GET_ORDER_BY_ID = "get order by id";
    public static final String SAVE_ORDER = "save order";
    public static final String UPDATE_ORDER = "update order";
    public static final String DELETE_ORDER = "delete order";
    public static final String EXIST_ORDER_BY_ID = "exist order by id";
    public static final String SELECT_ALL_FROM_ORDER_DETAIL = "SELECT * FROM order_detail";
    public static final String SELECT_FROM_ORDER_DETAIL_WHERE_ID = "SELECT * FROM order_detail WHERE od_id = ?";
    public static final String INSERT_INTO_ORDER_DETAIL = "INSERT INTO order_detail VALUES (?, ?, ?, ?, ?)";
    public static final String UPDATE_TO_ORDER_DETAIL = "UPDATE order_detail SET od_price_per_one=?, od_quantity=?, " +
            "od_order_id=?, od_car_id=? WHERE od_id=?";
    public static final String DELETE_FROM_ORDER_DETAIL = "DELETE FROM order_detail WHERE od_id = ?";
    public static final String SELECT_FROM_ORDER_DETAIL_WHERE_ORDER_ID = "SELECT * FROM order_detail WHERE od_order_id=?";
    public static final String GET_ALL_ORDER_DETAILS = "get all order details";
    public static final String GET_ALL_ORDER_DETAILS_BY_ORDER_ID = "get all order details by order id";
    public static final String GET_ORDER_DETAIL_BY_ID = "get order detail by id";
    public static final String SAVE_ORDER_DETAIL = "save order detail";
    public static final String SAVE_ALL_ORDER_DETAILS = "save all order details";
    public static final String UPDATE_ORDER_DETAIL = "update order detail";
    public static final String DELETE_ORDER_DETAIL = "delete order detail";
    public static final String EXIST_ORDER_DETAIL_BY_ID = "exist order detail by id";
    public static final String NEGATIVE_SEARCH_SPECIAL_SYM_REGEX = "[^a-z0-9.,\\- \\p{IsCyrillic}]";
    public static final String NEGATIVE_SEARCH_LETTERS_SYMBOLS_REGEX = "[^0-9]";
    public static final String EXPIRATION_DATE_PATTERN_REGEX = "^[0-9]{2}[\\\\/][0-9]{2}$";
    public static final String CVV_CODE_REGEX = "^[0-9]{3}$";
    public static final String ADDRESS_INCORRECT_MSG = "Address is incorrect (do not use special symbols apart .,- and not less 10 letters)";
    public static final String CARD_NUMBER_INCORRECT_MSG = "Card number is incorrect (use only 18 digits without spaces)";
    public static final String CARD_EXPIRATION_DATE_INCORRECT_MSG = "Card expiration date is incorrect (follow template)";
    public static final String CVV_INCORRECT_MSG = "CVV-code is incorrect (only 3 digits)";
    public static final String CARD = "CARD";
    public static final String ORDER_SERVICE = "orderService";
    public static final String SPECIAL_CHARS_REGEX = "[<>%$!?|^_*#&\\\\/()'\"{}\\]\\[]";
    public static final String AND_KEY_VALUE_PATTERN_URI = "&%s=%s";
    public static final String CATALOG_SERVLET_URI = "http://localhost:8080/car_shop_war_exploded/catalog?";
    public static final String WHERE = "WHERE ";
    public static final String AND = " AND ";
    public static final String SELECT_CLAUSE = "SELECT SQL_CALC_FOUND_ROWS c_id, c_name, c_price, c_mileage, c_engine_volume, ca_id, ca_name, m_id, m_name, c_production_year, c_image_name ";
    public static final String TABLE_CLAUSE = "FROM cars ";
    public static final String JOIN_CATEGORY_CLAUSE = "JOIN c_category cc on cc.ca_id = cars.c_category ";
    public static final String JOIN_MANUFACTURER_CLAUSE = "JOIN c_manufacturer cm on cm.m_id = cars.c_manufacturer ";
    public static final String ORDER_BY_CLAUSE = " ORDER BY c_%s %s";
    public static final String LIMIT_CLAUSE = " LIMIT %s,%s";
    public static final String UNDERSCORE = "_";
    public static final String LIKE_CLAUSE = "c_name LIKE '%";
    public static final String MANUFACTURER_NAME_VALUE = "m_name='%s'";
    public static final String CATEGORY_NAME_VALUE = "ca_name='%s'";
    public static final String OR = " OR ";
    public static final String PARENTHESIS_OPEN = "(";
    public static final String PARENTHESIS_CLOSE = ")";
    public static final String PRICE_MORE_OR_EQ = "c_price>=%s";
    public static final String PRICE_LESS_OR_EQ = "c_price<=%s";
    public static final String ENDING_LIKE_CLAUSE = "%'";
    public static final String NAME_PARAM = "name";
    public static final String MANUFACTURER_PARAM = "manufacturer";
    public static final String CATEGORY_PARAM = "category";
    public static final String MIN_PRICE_PARAM = "minPrice";
    public static final String MAX_PRICE_PARAM = "maxPrice";
    public static final String SORT_BY_PARAM = "sortBy";
    public static final String PAGE_SIZE_PARAM = "pageSize";
    public static final String PAGE_PARAM = "page";
    public static final String NOT_SELECTED = "Not selected";
    public static final String NAME_ASC = "name_ASC";
    public static final String NAME_DESC = "name_DESC";
    public static final String PRICE_ASC = "price_ASC";
    public static final String PRICE_DESC = "price_DESC";
    public static final String NAME_A_Z = "Name (A-Z)";
    public static final String NAME_Z_A = "Name (Z-A)";
    public static final String PRICE_LOW_HIGH = "Price (low-high)";
    public static final String PRICE_HIGH_LOW = "Price (high-low)";
    public static final String CANCEL = "cancel";
    public static final String CATALOG_JSP_PAGE = "/jsp/catalog.jsp";
    public static final String FILTER_PARAMS = "filterParams";
    public static final String URI_FOR_PAGINATION = "uriForPagination";
    public static final String CAR_LIST = "carList";
    public static final String NUMBER_OF_PAGES = "numberOfPages";
    public static final String CATEGORY_LIST = "categoryList";
    public static final String MANUFACTURER_LIST = "manufacturerList";
    public static final String SORTING_PARAMS = "sortingParams";
    public static final String SELECT_ALL_FROM_MANUFACTURER = "SELECT * FROM c_manufacturer";
    public static final String SELECT_MANUFACTURER_WHERE_ID = "SELECT * FROM c_manufacturer WHERE m_id=?";
    public static final String INSERT_INTO_MANUFACTURER = "INSERT INTO c_manufacturer VALUES (DEFAULT, ?)";
    public static final String UPDATE_MANUFACTURER = "UPDATE c_manufacturer SET m_name=? WHERE m_id=?";
    public static final String DELETE_FROM_MANUFACTURER = "DELETE FROM c_manufacturer WHERE m_id =?";
    public static final String GET_ALL_MANUFACTURERS = "get all manufacturers from database";
    public static final String GET_MANUFACTURER_BY_ID = "get manufacturer by id";
    public static final String SAVE_MANUFACTURER = "save manufacturer to database";
    public static final String UPDATE_MANUFACTURER_BY_ID = "update manufacturer by id";
    public static final String DELETE_MANUFACTURER = "delete manufacturer from database";
    public static final String GET_KNOWN_WHETHER_MANUFACTURER_EXISTS = "get known whether manufacturer exists by id";
    public static final String SELECT_ALL_FROM_CATEGORY = "SELECT * FROM c_category";
    public static final String SELECT_CATEGORY_WHERE_ID = "SELECT * FROM c_category WHERE ca_id=?";
    public static final String INSERT_INTO_CATEGORY = "INSERT INTO c_category VALUES (DEFAULT, ?)";
    public static final String UPDATE_CATEGORY = "UPDATE c_category SET ca_name=? WHERE ca_id=?";
    public static final String DELETE_FROM_CATEGORY = "DELETE FROM c_category WHERE ca_id =?";
    public static final String GET_ALL_CATEGORIES = "get all categories from database";
    public static final String GET_CATEGORY_BY_ID = "get category by id";
    public static final String SAVE_CATEGORY = "save category to database";
    public static final String UPDATE_CATEGORY_BY_ID = "update category by id";
    public static final String DELETE_CATEGORY = "delete category from database";
    public static final String GET_KNOWN_WHETHER_CATEGORY_EXISTS = "get known whether category exists by id";
    public static final String SELECT_FOUND_ROWS = "SELECT FOUND_ROWS()";
    public static final String GET_ALL_CARS = "get all cars from database";
    public static final String GET_CAR_BY_ID = "get car by id";
    public static final String SAVE_CAR = "save car to database";
    public static final String UPDATE_CAR_LABEL = "update car by id";
    public static final String DELETE_CAR = "delete car from database";
    public static final String GET_KNOWN_WHETHER_CAR_EXISTS = "get known whether car exists by id";
    public static final String INSERT_INTO_CARS_VALUES = "INSERT INTO cars VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String UPDATE_CAR = "UPDATE cars SET c_name=?, c_price=?, c_mileage=?, c_engine_volume=?, " +
            "c_category=?, c_manufacturer=?, c_production_year=?, c_image_name=? WHERE c_id=?";
    public static final String DELETE_CAR_WHERE_ID = "DELETE FROM cars WHERE c_id =?";
    public static final String SELECT_CAR_WHERE_EXISTS = "SELECT * FROM cars WHERE c_id =?";
    public static final String SELECT_FROM_CARS_WHERE_ID = "SELECT c_id, c_name, c_price, c_mileage, c_engine_volume,\n" +
            "ca_id, ca_name, m_id, m_name, c_production_year, c_image_name\n" +
            "FROM cars\n" +
            "JOIN c_category cc on cc.ca_id = cars.c_category\n" +
            "JOIN c_manufacturer cm on cm.m_id = cars.c_manufacturer\n" +
            "WHERE c_id =?";
    public static final String SELECT_FROM_CARS = "SELECT c_id, c_name, c_price, c_mileage, c_engine_volume,\n" +
            "ca_id, ca_name, m_id, m_name, c_production_year, c_image_name\n" +
            "FROM cars\n" +
            "JOIN c_category cc on cc.ca_id = cars.c_category\n" +
            "JOIN c_manufacturer cm on cm.m_id = cars.c_manufacturer";
    public static final String DEFAULT_NAME = "name";
    public static final int DEFAULT_PAGE_SIZE = 10;
    public static final int DEFAULT_PAGE_NUMBER = 1;
    public static final String QUERY_BUILDER = "queryBuilder";
    public static final String CATEGORY_SERVICE = "categoryService";
    public static final String MANUFACTURER_SERVICE = "manufacturerService";
    public static final String PNG_TYPE = ".png";
    public static final String CAR_SERVICE = "carService";
    public static final String DEFAULT_IMAGE_NAME = "carImage";
    public static final int DEFAULT_PRODUCTION_YEAR = 1993;
    public static final String SAVE_AVATAR = "INSERT INTO u_avatar VALUES (?, ?)";
    public static final String DEFAULT_CAR_NAME = "defaultCar";
    public static final String DELETE_AVATAR = "DELETE FROM u_avatar WHERE a_user_id =?";
    public static final String SELECT_AVATAR = "SELECT u_avatar.a_avatar_name FROM u_avatar WHERE a_user_id = ?";
    public static final String JPG_FORMAT = ".jpg";
    public static final String MESSAGE = "message";
    public static final String AVATAR_NAME = "avatarName";
    public static final String AVATAR = "avatar";
    public static final String FILE_NAME = "avatar%d.jpg";
    public static final String USER_IMAGES_DIRECTORY = "user-images";
    public static final String IO_EXCEPTION_THROWN_WHILE_WRITING_FILE = "IO Exception thrown while writing file on server dir, cause: ";
    public static final String IO_EXCEPTION_THROWN_WHILE_GETTING_PART = "IO Exception thrown while getting part from jsp, cause: ";
    public static final String NO_CONNECTION_TRANSACTION_NULL = "There was no connection, transaction manager returns null";
    public static final String TAG_GAVE_USER_DATA = "Tag gave user's data";
    public static final String TAG_GAVE_LOGIN_FORM = "Tag gave login form";
    public static final String USER_WAS_LOGGED_OUT = "User was logged out, session invalidated";
    public static final String REFERER = "referer";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String LOGIN_ERRORS = "loginErrors";
    public static final String USER_LOGGED_IN_SUCCESSFULLY = "User logged in successfully";
    public static final String USER_FAILS_LOGIN = "User fails log in attempt";
    public static final String GET_USER_BY_EMAIL_LABEL = "get user by email from database";
    public static final String SELECT_WHERE_EMAIL = "SELECT * FROM users WHERE email = ?";
    public static final String AVATAR_MANAGER = "avatarManager";
    public static final String NAMING_EXCEPTION_INITIALIZATION = "Naming exception thrown while connection manager initialization, cause: ";
    public static final String JDBC_TASK_12 = "jdbc/task12";
    public static final String JAVA_COMP_ENV = "java:comp/env";
    public static final String SQL_EXCEPTION_GET_CONNECTION = "SQL Exception thrown while getting connection, cause: ";
    public static final String SQL_EXCEPTION_CLOSE_CONNECTION = "Can not close connection, cause: ";
    public static final String SQL_EXCEPTION_ROLLBACK_TRANSACTION = "Can not rollback transaction, cause: ";
    public static final String EXCEPTION_THROWN_DURING_TRANSACTION = "Exception was thrown during transaction, cause: ";
    public static final String SELECT_ALL = "SELECT * FROM users";
    public static final String SELECT_WHERE_ID = "SELECT * FROM users WHERE id = ?";
    public static final String DELETE = "DELETE FROM users WHERE id = ?";
    public static final String DELETE_LABEL = "delete user from database";
    public static final String OPERATION_WITH_DATABASE_FAILED = "Failed to %s, cause: %s";
    public static final String UPDATE_LABEL = "update user in database";
    public static final String GET_ALL_USERS_LABEL = "get all users from database";
    public static final String SAVE_LABEL = "save user to database";
    public static final String GET_BY_ID_LABEL = "get user by id from database";
    public static final String EXISTS_BY_ID_LABEL = "find out whether user exists by id";
    public static final String ACTION_IS_NOT_PERMITTED = "Action is not permitted. Instance of constant class can not be created";
    public static final String DEFAULT_TIMEOUT = "60000";
    public static final String CAPTCHA_COLLECTOR_INTERVAL = "captchaCollectorInterval";
    public static final int DEFAULT_CAPTCHA_COLLECTOR_INTERVAL = 1000000;
    public static final String CAPTCHA_MAP_CHECKED_AND_CLEANED = "Captcha map was checked and cleaned. Initial size: %s, result size: %s";
    public static final String EMAIL_CHECK_REGEX = "^([A-Za-z0-9_.,#$\"'!?\\\\/*\\-+%&^;:()])+@([A-Za-z-_]{2,15})\\.([A-Za-z]{2,3})$";
    public static final String SHOULD_CONTAIN_MORE_THAT_TWO_CHARACTERS = "This form should contain more that two characters";
    public static final String EMAIL_SHOULD_LOOK_LIKE = "Email should look like 'email@gmail.com'";
    public static final String PHONE_VALIDATION_REGEX = "^\\+?([0-9]{2})[- ]?([0-9]{3})[- ]?([0-9]{3})[- ]?([0-9]{2})[- ]?([0-9]{2})$";
    public static final String PHONE_SHOULD_LOOK_LIKE = "Phone should look like '+380994775097' either '+38 099 477 50 97' or '+38-099-477-50-97'";
    public static final String DATE_VALIDATION_REGEX = "^[\\d]{2}[.][\\d]{2}[.][\\d]{4}$";
    public static final String DATE_SHOULD_LOOK_LIKE = "Date should be correct and look like '01.01.2000', month.date.year -> 12.31.2000";
    public static final String PASS_VALIDATION_REGEX = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";
    public static final String PASSWORD_SHOULD_CONTAIN = "Password should be longer than 7 symbols, contain at least one big letter and one number";
    public static final String REPEATED_PASSWORD_SHOULD_BE_EQUALS_TO_PASSWORD_ABOVE = "Repeated password should be equals to password above";
    public static final String TYPE_PNG = "png";
    public static final String STYLE_ARIAL = "Arial";
    public static final int BOUND_RGB = 255;
    public static final int FONT_SIZE = 15;
    public static final char[] RANGE_OF_NUMBERS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    public static final char[] RANGE_OF_SPECIAL_SYMBOLS = {'.', ',', '~', '\'', '`', '|', '/', '$', '%', '&', '*'};
    public static final int CAPTCHA_VALUE_LENGTH = 4;
    public static final int PICTURE_WIDTH = 100;
    public static final int PICTURE_HEIGHT = 50;
    public static final int X_COORDINATE_PLACEMENT = 30;
    public static final int Y_COORDINATE_PLACEMENT = 30;
    public static final String IO_EXCEPTION_APPEARED = "IO Exception appeared, cause: ";
    public static final String CAPTCHA_CREATED = "Captcha created and returned";
    public static final String DATE_FORMAT = "MM.dd.yyyy";
    public static final String PARSE_EXCEPTION = "Parse exception, cause: ";
    public static final String LAST_SPACE_REGEX = "[ \\t]+$";
    public static final String EMPTY = "";
    public static final String FIRST_SPACE_REGEX = "^[ \\t]+";
    public static final String SEPARATORS_REGEX = "[ .-]";
    public static final String REQUEST_ENCODING = "requestEncoding";
    public static final String UTF_8 = "UTF-8";
    public static final String TEXT_HTML_CHARSET_UTF_8 = "text/html; charset=UTF-8";
    public static final String SESSION = "session";
    public static final String COOKIE = "cookie";
    public static final String FIELD = "field";
    public static final String CAPTCHA_LOCATION = "captchaLocation";
    public static final String CAPTCHA_TIMEOUT = "captchaTimeout";
    public static final String APPLICATION_PROPERTIES_WERE_INITIALIZED = "Application properties were initialized";
    public static final String USER_SERVICE = "userService";
    public static final String VALIDATOR = "validator";
    public static final String PASSWORD_HASHING_UTIL = "passwordHashingUtil";
    public static final String USER_FACTORY = "userFactory";
    public static final String EXTRACTOR = "extractor";
    public static final String CAPTCHA_FACTORY = "captchaFactory";
    public static final String CAPTCHA_ID = "captchaId";
    public static final String USER_CAPTCHA_MAP = "userCaptchaMap";
    public static final String USE_COOKIES_MSG = "To use app in this mode turn on cookies";
    public static final String CAPTCHA_TIME_IS_OUT = "Captcha time is out";
    public static final String CAPTCHA_CODE = "captchaCode";
    public static final String VALUE_OF_CAPTCHA_IS_INCORRECT = "Entered value of captcha is incorrect";
    public static final String CAPTCHA_IS_SAVED_HIDDEN_FIELDS = "Captcha %s is saved into hidden fields";
    public static final String CAPTCHA_VALIDATION_FAILS_TIME_OUT = "Captcha validation fails because if time out";
    public static final String CAPTCHA_VALIDATION_IS_SUCCESSFUL = "Captcha validation is successful";
    public static final String CAPTCHA_VALIDATION_FAILS_CODE_INVALID = "Captcha validation fails, code doesn't match the value of captcha";
    public static final String CAPTCHA_VALIDATION_FAILS_NOT_FOUND_ID = "Captcha validation fails, not found user cookie with captcha id";
    public static final String CAPTCHA_IS_SAVED_USER_COOKIES = "Captcha %s is saved into user cookies";
    public static final String CAPTCHA_IS_SAVED_USER_SESSION = "Captcha %s is saved into user session";
    public static final String CONTENT_TYPE_IMAGE_PNG = "image/png";
    public static final String CAPTCHA_SERVLET_INVOKED = "Captcha servlet doGet() invoked: image returned to tag, parameters passed to captcha service";
    public static final String REGISTRATION_URI = "/jsp/registration.jsp";
    public static final String SUCCESSFUL_REGISTRATION_URI = "jsp/successfulRegistration.jsp";
    public static final String REGISTRATION_SERVLET_URI = "registration";
    public static final String USER_VALIDATION_ERR = "userValidationErr";
    public static final String USER_DTO = "userDTO";
    public static final String USER_REGISTRATION_FAILS = "Registration of user %s fails";
    public static final String USER = "user";
    public static final String USER_REGISTRATION_IS_SUCCESSFUL = "Registration of user %s was successful";
    public static final String REFRESHED_CAPTCHA = "Refreshed captcha for user %s";
    public static final String REFRESH = "refresh";
    public static final String CAPTCHA_ID_LOGGER = "Captcha id: ";
    public static final String HIDDEN_ATTRIBUTE_HTML = "<input type=\"hidden\" name=\"captchaId\" value=\"%s\"/>\n";
    public static final String IMAGE_HTML = "<img src=\"captcha?captchaId=%s\"/>\n";
    public static final String FIRST_NAME = "firstName";
    public static final String SECOND_NAME = "secondName";
    public static final String PHONE = "phone";
    public static final String BIRTH_DATE = "birthDate";
    public static final String REPEAT_PASS = "repeatPass";
    public static final String SUBSCRIPTION = "subscription";
    public static final int ITERATIONS = 10;
    public static final int SALT_LENGTH = 16;
    public static final int DESIRED_KEY_LINE = 256;
    public static final String SHA_1_PRNG = "SHA1PRNG";
    public static final String PBKDF_2_WITH_HMAC_SHA_1 = "PBKDF2WithHmacSHA1";
    public static final String PASSWORD_HAS_NO_SALT_HASH = "The stored password haven't form 'salt$hash'";
    public static final String $_REGEX = "\\$";
    public static final String $_SIGN = "$";
    public static final String NO_SUCH_ALGORITHM_EXCEPTION_WHILE_HASHING = "NoSuchAlgorithmException while hashing ";
    public static final String USER_WITH_SUCH_EMAIL_WAS_REGISTERED = "User with such email(login) is already registered";
    public static final int MIN_NAME_LENGTH = 2;
    public static final int MIN_YEAR = 1920;
    public static final int MAX_YEAR = 2004;
}