package tests;

import data.DataProviderLogin;
import dto.UserDtoLombok;
import org.testng.Assert;
import org.testng.annotations.*;
import utils.ConfigurationProperties;

import java.lang.reflect.Method;

public class LoginTests extends BaseTest {

    UserDtoLombok userDtoLombok;
    String repetedLoggerText;

    @BeforeMethod (alwaysRun = true)
    public void beforeMethod() {

        userDtoLombok = UserDtoLombok.builder()
                .email(ConfigurationProperties.getProperty("email"))
                .password(ConfigurationProperties.getProperty("password"))
                .build();

        repetedLoggerText = " fill email input field with: %s and password input field with: %s and click button Yalla";

        app.navigateToMainPage();
        app.getUserHelper().openLoginPage();
    }

    @AfterMethod (alwaysRun = true)
    public void afterMethod() {

        flagAfterMethod();
    }

    @Test (groups = { "smoke", "all" }, dataProvider = "dataCSV", dataProviderClass = DataProviderLogin.class)
    public void positiveLoginUserDTO(UserDtoLombok userDP, Method method) {

        logger.info(String.format("in method: " + method.getName()
                + repetedLoggerText, userDP.getEmail(), userDP.getPassword()));

        app.getUserHelper().login(userDP);

        isFlagPopUp = true;
        isFlagLogin = true;

        Assert.assertTrue(app.getUserHelper().validatePopUpMessageSuccessAfterLogin());
    }

    @Test (groups = { "smoke", "regression", "all" })
    public void positiveLoginUserDTOWith(Method method) {

        logger.info(String.format("in method: " + method.getName()
                + repetedLoggerText, userDTOWith.getEmail(), userDTOWith.getPassword()));

        app.getUserHelper().login(userDTOWith);

        isFlagPopUp = true;
        isFlagLogin = true;

        Assert.assertTrue(app.getUserHelper().validatePopUpMessageSuccessAfterLogin());
    }

    @Test
    public void positiveLogin(Method method) {

        logger.info(String.format("in method: " + method.getName()
                + repetedLoggerText, userDtoLombok.getEmail(), userDtoLombok.getPassword()));

        app.getUserHelper().login(userDtoLombok);

        isFlagPopUp = true;
        isFlagLogin = true;

        Assert.assertTrue(app.getUserHelper().validatePopUpMessageSuccessAfterLogin());
    }

    @Test (dataProvider = "dataCSV", dataProviderClass = DataProviderLogin.class)
    public void negativePasswordWithoutSymbol(UserDtoLombok userDP, Method method) {

        logger.info(String.format("in method: " + method.getName()
                + repetedLoggerText, userDP.getEmail(), userDP.getPassword()));

        app.getUserHelper().login(userDP);

        isFlagPopUp = true;

        Assert.assertTrue(app.getUserHelper().validatePopUpMessageLoginIncorrect());
    }

    @Test
    public void negativePasswordWithoutNumbers(Method method) {

        userDtoLombok.setPassword("ddsdhjAa$");

        logger.info(String.format("in method: " + method.getName()
                + repetedLoggerText, userDtoLombok.getEmail(), userDtoLombok.getPassword()));

        app.getUserHelper().login(userDtoLombok);

        isFlagPopUp = true;

        Assert.assertTrue(app.getUserHelper().validatePopUpMessageLoginIncorrect());
    }

    @Test
    public void negativePasswordWithoutLetters(Method method) {

        userDtoLombok.setPassword("12345678$");

        logger.info(String.format("in method: " + method.getName()
                + repetedLoggerText, userDTO.getEmail(), userDTO.getPassword()));

        app.getUserHelper().login(userDtoLombok);

        isFlagPopUp = true;

        Assert.assertTrue(app.getUserHelper().validatePopUpMessageLoginIncorrect());
    }
}