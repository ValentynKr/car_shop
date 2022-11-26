package com.epam.lab.shop.tag;

import com.epam.lab.shop.constant.Constant;
import com.epam.lab.shop.entity.User;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Logger;

public class LoginTag extends SimpleTagSupport {

    private final Logger LOGGER = Logger.getLogger(LoginTag.class.getName());
    private Object user;
    private String avatarName;
    private String contextPath;
    private String email;
    private String password;
    private Locale currentLocale;
    private Map<String, Map<String, String>> translationMap;
    private Map<String, String> localizedStrings;

    private void initTranslationMap() {
        this.translationMap = new HashMap<>();
        Map<String,String> enTranslation = new HashMap<>();
        enTranslation.put("Log out", "Log out");
        enTranslation.put("Email address", "Email address");
        enTranslation.put("Enter email", "Enter email");
        enTranslation.put("Password", "Password");
        enTranslation.put("Enter password", "Enter password");
        enTranslation.put("Login", "Login");
        Map<String,String> ruTranslation = new HashMap<>();
        ruTranslation.put("Log out", "Выйти");
        ruTranslation.put("Email address", "Электронная почта");
        ruTranslation.put("Enter email", "Введите эл. почту");
        ruTranslation.put("Password", "Пароль");
        ruTranslation.put("Enter password", "Введите пароль");
        ruTranslation.put("Login", "Войти");
        this.translationMap.put("en", enTranslation);
        this.translationMap.put("ru", ruTranslation);
    }

    private Map<String, String> getLocalizedStrings(Locale locale, Map<String, Map<String, String>> translationMap) {
        return translationMap.get(locale.getLanguage());
    }

    public Locale getCurrentLocale() {
        return currentLocale;
    }

    public void setCurrentLocale(Locale currentLocale) {
        this.currentLocale = currentLocale;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatarName() {
        return avatarName;
    }

    public void setAvatarName(String avatarName) {
        this.avatarName = avatarName;
    }

    public String getContextPath() {
        return contextPath;
    }

    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }

    public Object getUser() {
        return user;
    }

    public void setUser(Object user) {
        this.user = user;
    }

    private String getUserLoginData() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<div class=\"container-fluid\">\n");
        stringBuilder.append(String.format("<img class=\"mx-auto d-block\" src='%s/user-images/%s' alt=\"avatar\" " +
                        "width=\"100\" height=\"100\"/>\n",
                contextPath, avatarName))
                .append("<p class='text-center'>")
                .append(((User) user).getEmail())
                .append("  ")
                .append("</p>\n")
                .append("<p style='display:inline;'>\n")
                .append(String.format("<a class='dropdown-item text-center' href='%s/logout?logout=1'>%s</a>\n",
                        contextPath, localizedStrings.get("Log out")))
                .append("</p>\n")
                .append("</div>");
        return stringBuilder.toString();
    }

    private String getLoginForm() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<div class=\"container-fluid\">\n")
                .append(String.format("<form action='%s/login' method='post'>\n", contextPath))
                .append("<div class=\"form-group form-field\">\n")
                .append(String.format("<label for=\"email\">%s:</label>\n", localizedStrings.get("Email address")))
                .append(String.format("<input type=\"email\" id=\"email\" name=\"email\" class=\"form-control\" " +
                        "placeholder=\"%s\" value=\"%s\" required>\n", localizedStrings.get("Enter email"), email))
                .append("</div>\n")
                .append("<div class='form-group form-field'>\n")
                .append(String.format("<label for=\"password\" style=\"display:inline\">%s:</label>\n",
                        localizedStrings.get("Password")))
                .append(String.format("<input type=\"password\" name=\"password\" style=\"display:inline\" " +
                        "class=\"form-control\" placeholder=\"%s\" id=\"password\" value=\"%s\" required>\n",
                        localizedStrings.get("Enter password"), password))
                .append("</div>\n")
                .append(String.format("<button type=\"submit\" class=\"btn btn-primary col-md-12\" " +
                        "style=\"margin-top: 20px\">%s</button>\n", localizedStrings.get("Login")))
                .append("</form>\n")
                .append("</div>\n");
        return stringBuilder.toString();
    }

    @Override
    public void doTag() throws IOException {
        initTranslationMap();
        localizedStrings = getLocalizedStrings(currentLocale, translationMap);
        if (user != null) {
            getJspContext().getOut().write(getUserLoginData());
            LOGGER.info(Constant.TAG_GAVE_USER_DATA);
        } else {
            getJspContext().getOut().write(getLoginForm());
            LOGGER.info(Constant.TAG_GAVE_LOGIN_FORM);
        }
    }
}