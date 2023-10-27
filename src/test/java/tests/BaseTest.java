package tests;

import dto.UserDTO;
import dto.UserDTOWith;
import dto.UserDtoLombok;
import manager.ApplicationManager;
import manager.TestNGListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;
import utils.ConfigurationProperties;
import utils.FakerGenerator;
import utils.RandomUtils;

import java.lang.reflect.Method;
import java.util.Arrays;

@Listeners(TestNGListener.class)
public class BaseTest {

    Logger logger = LoggerFactory.getLogger(BaseTest.class);
    FakerGenerator faker = new FakerGenerator();
    UserDTO userDTO;
    UserDTOWith userDTOWith;
    UserDtoLombok userDtoLombok;

    static ApplicationManager app = new ApplicationManager();
    RandomUtils randomUtils = new RandomUtils();

    boolean isFlagLogin = false, isFlagPopUp = false;

    @BeforeSuite(alwaysRun = true)
    public void setup() {
        app.init();
    }

    @AfterSuite(alwaysRun = true)
    public void stop() {
        app.tearDown();
    }

    public void logoutIflogin() {
        if (app.getUserHelper().btnLogoutExist()) {
            app.getUserHelper().logout();
        }
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeEachMethod(Method method) {

        userDTO = new UserDTO(ConfigurationProperties.getProperty("email"),
                ConfigurationProperties.getProperty("password"));

        userDTOWith = new UserDTOWith()
                .withEmail(ConfigurationProperties.getProperty("email"))
                .withPassword(ConfigurationProperties.getProperty("password"));

        userDtoLombok = UserDtoLombok.builder()
                .email(ConfigurationProperties.getProperty("email"))
                .password(ConfigurationProperties.getProperty("password"))
                .build();

        logger.info("--------------------------------------------------------------");
        logger.info("started method: " + method.getName() + ", with parameters: " + Arrays.toString(method.getParameters()));
    }

    @AfterMethod
    public void afterEachMethod(Method method) {

        logger.info("stopped method: " + method.getName() + ", with parameters: " + Arrays.toString(method.getParameters()));
    }

    public void flagAfterMethod() {

        if (isFlagPopUp) {
            isFlagPopUp = false;
            app.getUserHelper().clickOkPopUpSuccessLogin();
        }
        if (isFlagLogin) {
            isFlagLogin = false;
            app.getUserHelper().logout();
        }
    }
}
