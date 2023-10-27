package tests;

import org.testng.Assert;
import org.testng.annotations.*;

import java.lang.reflect.Method;

public class LoginTests extends BaseTest {

    String repetedLoggerText;

    @BeforeMethod (alwaysRun = true)
    public void beforeMethod() {

        repetedLoggerText = " fill email input field with: %s and password input field with: %s and click button Yalla";

        app.navigateToMainPage();
        app.getUserHelper().openLoginPage();
    }

    @AfterMethod (alwaysRun = true)
    public void afterMethod() {

        flagAfterMethod();
    }

    @Test (groups = { "smoke", "all" })
    public void positiveLoginUserDTO(Method method) {

        logger.info(String.format("in method: " + method.getName()
                + repetedLoggerText, userDTO.getEmail(), userDTO.getPassword()));

        app.getUserHelper().login(userDTO);

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

    @Test
    public void negativePasswordWithoutSymbol(Method method) {

        userDtoLombok.setPassword("123456Aaa");

        logger.info(String.format("in method: " + method.getName()
                + repetedLoggerText, userDTOWith.getEmail(), userDTOWith.getPassword()));

        app.getUserHelper().login(userDtoLombok);

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