package tests;

import data.DataProviderRegistration;
import dto.UserDtoLombok;
import org.testng.Assert;
import org.testng.annotations.*;

import java.lang.reflect.Method;

public class RegistrationTests extends BaseTest{

    String repetedLoggerText;

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod() {

        repetedLoggerText = " fill email input field with: %s and password input field with: %s and click button Yalla";

        app.navigateToMainPage();
        app.getUserHelper().openRegistrationPage();
    }

    @AfterMethod (alwaysRun = true)
    public void afterMethod() {

        flagAfterMethod();
    }

    @Test (groups = { "regression", "all" }, dataProvider = "data", dataProviderClass = DataProviderRegistration.class)
    public void positiveRegistration(UserDtoLombok userDP, Method method) {

//        String email = randomUtils.generateEmail(7);

//        UserDtoLombok user = UserDtoLombok.builder()
//                .email(email)
//                .password("123456Aa$")
//                .lastName(faker.lastName())
//                .name(faker.firstName())
//                .build();

        logger.info(String.format("in method: " + method.getName()
                + repetedLoggerText, userDP.getEmail(), userDP.getPassword()));

        app.getUserHelper().fillRegistrationForm(userDP);

        isFlagPopUp = true;
        isFlagLogin = true;

        Assert.assertTrue(app.getUserHelper().validatePopUpMessageSuccessAfterRegistration());
    }

    @Test (dataProvider = "data", dataProviderClass = DataProviderRegistration.class)
    public void negativeRegistrationWrongEmail(UserDtoLombok userDP, Method method) {

//        UserDtoLombok user = UserDtoLombok.builder()
//                .email("abc@")
//                .password("123456Aa$")
//                .lastName(faker.lastName())
//                .name(faker.firstName())
//                .build();

        logger.info(String.format("in method: " + method.getName()
                + repetedLoggerText, userDP.getEmail(), userDP.getPassword()));

        app.getUserHelper().fillRegistrationForm(userDP);
        Assert.assertTrue(app.getUserHelper().validateMessageIncorrectEmailReg());
    }

    @Test (dataProvider = "data", dataProviderClass = DataProviderRegistration.class)
    public void negativeRegistrationWrongPassword(UserDtoLombok userDP, Method method) {

//        String email = randomUtils.generateEmail(7);
//
//        UserDtoLombok user = UserDtoLombok.builder()
//                .email(email)
//                .password("123456")
//                .lastName(faker.lastName())
//                .name(faker.firstName())
//                .build();

        logger.info(String.format("in method: " + method.getName()
                + repetedLoggerText, userDP.getEmail(), userDP.getPassword()));

        app.getUserHelper().fillRegistrationForm(userDP);
        Assert.assertTrue(app.getUserHelper().validateMessageWrongPasswordReg());
    }

    @Test
    public void negativeRegistrationBlankEmail() {
        UserDtoLombok user = UserDtoLombok.builder()
                .email("")
                .password("123456Aa$")
                .lastName(faker.lastName())
                .name(faker.firstName())
                .build();

        app.getUserHelper().fillRegistrationForm(user);
        Assert.assertTrue(app.getUserHelper().validateErrorEmptyEmailReg());
    }
}