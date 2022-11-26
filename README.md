### 1. CAR SHOP APPLICATION

#### 1.1. General overview

Current application is intended for placing goods like used cars for the further selling directly to the customer or
retail corporations. Providing photos and descriptions of good, as well as convenient user interface should promote
frequent trading and may involve funds as advertisement fees and payments for placing the current limit of goods by one
current shipper.

#### 1.2. Description of functionality

- creating users with different roles (administrator and user) and different level of access (front and back end
  validation of access level) by applying registration form, which might contain an uploaded avatar and be validated by
  jQuery and JS elements;
- login/logout bar with user profile information and avatar uploaded during the registration;
- placing and displaying goods (e.g. cars) via the car catalogue with description, price and image of good and storing
  such information in a database;
- adding cars to the user's cart with possibility to edit their quantity, removing cars from a cart and ordering with
  payment details and delivery specifications;
- reviewing previously made orders with status and general information displaying;
- overviewing the catalogue of all available cars, sorting them by name, price, filtering by a category, price,
  manufacturer, using custom pagination with specifying quantity of goods on a page;
- localisation of web-interface (EN and RU versions were provided);
- security and access filters implemented with presenting them by .xml file, which doesn't allow reaching pages with
  restricted access;
- implemented securing blocking user after passing incorrect authentication credentials specified number of times (5 by
  default).

#### 1.3. Required subsidiary applications

- Java 8 including JDK and JRE;
- Maven 3.8.1 installed or IDE embedded (or higher);
- Tomcat 9.0.521 installed or IDE embedded (or higher);
- Git 2.0.37 installed (or higher);
- MySQL Workbench 8.0.30 installed (or higher);
- MySQL Server 8.0.14 installed (or higher);
- MySQL Connector 8.0.30 installed (or higher);
- Intellij Idea is preferable as IDE (app may be deployed as WAR or JAR archive);
- Mozilla Firefox 107.0 is preferable as browser for manual testing and overviewing;
- internet connection may be required for dependency issues resolution.

#### 1.4. Structure of the project

- [_pom.xml_](pom.xml) - root maven configuration file.
  
_src/main_
- [_database_](src/main/database) - package for DB setup, which contains .sql scripts for creating tables and fulfilling them with preset data;
- [_java_](src/main/java) - java source package with java classes;
- [_resources_](src/main/resources) - resource package with language resource bundles (required for app localization);
- [_webapp_](src/main/webapp) - web application source package with web.xml descriptor, views (e.g. jsp-pages), tag libs, javascript elements, bootstrap presets, CSS tables, default photos of goods and user avatars;

_src/main/java/com.epam.lab.shop_
- [_constant_](src/main/java/com/epam/lab/shop/constant) - package for constant expressions (sql, regex) and data;
- [_database_](src/main/java/com/epam/lab/shop/database) - package for classes responsible for generating pool of connections to database and supplying transactional style of all operations with database;
- [_dto_](src/main/java/com/epam/lab/shop/dto) - package for data transfer objects necessary for views in MVC paradigm;
- [_entity_](src/main/java/com/epam/lab/shop/entity) - package for all application domain objects and enumerations;
- [_exception_](src/main/java/com/epam/lab/shop/exception) - package for custom application exception;
- [_factory_](src/main/java/com/epam/lab/shop/factory) - package for factory classes which produce current domain objects;
- [_filter_](src/main/java/com/epam/lab/shop/filter) - package for servlet filters which catch http requests and responsible for a locale, security management, http response caching and compressing;
- [_listener_](src/main/java/com/epam/lab/shop/listener) - package for a context listening class which responsible for storing and initialisation of all objects in application context;
- [_repository_](src/main/java/com/epam/lab/shop/repository) - package for the classes of a regular application repository layer (DAO) responsible for CRUD operation with domain objects in DB;
- [_service_](src/main/java/com/epam/lab/shop/service) - package for the classes of a regular application service layer;
- [_servlet_](src/main/java/com/epam/lab/shop/servlet) - package for the core servlets which responsible for web-mapping and http request handling;
- [_tag_](src/main/java/com/epam/lab/shop/tag) - package for custom jstl tags which operates with captcha, login, language and pagination settings on jsp-pages;
- [_utility_](src/main/java/com/epam/lab/shop/utility) - package for the additional utility classes responsible for parsing, validation, custom garbage collecting and other issues, according to single responsibility principle

_src/main/webapp_
- [_assets_](src/main/webapp/assets) - package for custom pictures or photos of goods;
- [_css_](src/main/webapp/css) - package for cascading style sheets from the Bootstrap 5.1 free template;
- [_js_](src/main/webapp/js) - package for javascript files responsible for client-side validation of user credentials provided during registration or logging in;
- [_jsp_](src/main/webapp/jsp) - package for views (jsp-pages);
- [_license_](src/main/webapp/license) - package for licenses and conditions of using pictures and other elements of visual representation of pages (by default - free of charge);
- [_META-INF_](src/main/webapp/META-INF) - package for application and application context configurations, manifest;
- [_user-images_](src/main/webapp/user-images) - package for user's avatars which would be uploaded by users in runtime;
- [_WEB-INF_](src/main/webapp/WEB-INF) - package for user access definitions, custom tag libs and core web.xml - application deployment descriptor.

#### 1.5. Areas of the growth and further development of the project

- preparing and designing more functional user's personal cabinet page of profile;
- preparing and designing a page with contacts;
- adding creating docker-file for deploying application and/or database from uploaded image;
- adding checks for the card details and more correct and secure storing and transferring of user's payment credentials;
- adding administrator's page (interface) for goods, users and orders management;
- adding REST-services for interacting with other applications;
- improving efficiency and structure by rewriting with Spring or Spring Boot frameworks;
- improving views by adding dynamic elements and using template generators as Thymeleaf etc.

#### 1.6. License and copyright issues

Issues of using templates of web-pages, pictures and elements of design placed on web-pages regulates according to 
free license (permissions) distributed by Freepik and Bootstrap which lay in [license](src/main/webapp/license) package.

### 2. HOW TO USE

1. Install all required applications mentioned in paragraph 1.3;
2. Launch MySQL Workbench and Server to start a server with following details:
```
url = http://localhost:3306/
port = 3306
server name = root
password = 1234
```
3. Test connection and status of database;
4. Using MySQL Workbench run .sql files from [database](src/main/database) package to generate tables and default data;
5. Open current project with the IDE and build it with pre-installed or embedded Maven with following command (or set appropriate goals):
```
mvn clean package
```
6. Check whether directory 'target' with built was successfully and correctly created;
7. Use Tomcat plugin or directly run Tomcat using following command (previously moving WAR archive to 'webapps' directory 
   of Tomcat and configuring environmental variable 'CATALINA_HOME') from 'bin' directory:
```
startup.bat - start for windows
shutdown.bat - stop for windows

startup.sh - start for linux
shutdown.sh - stop for linux
```
8. Configure port for Tomcat on 8080 and change name of built application to 'car_shop_war_exploded' (if it was not configured so by Maven automatically),
so that it could be reached after Tomcat startup with following URL:
```
http://localhost:8080/car_shop_war_exploded/
```
9. Check database is running before Tomcat startup process, run Tomcat and open Mozilla Firefox Web-browser with mentioned before URL.

### 3. CONTACTS FOR A FEEDBACK

If you have got any concerns or problem with starting application or have comments or advice about 
making application better - please, refer to me via social media or email. You are welcome.

krilovetsky@gmail.com
https://www.facebook.com/krylovetskyi/