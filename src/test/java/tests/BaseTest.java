package tests;

import dto.AddCarDTO;
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

    static ApplicationManager app = new ApplicationManager();
    Logger logger = LoggerFactory.getLogger(BaseTest.class);
    RandomUtils randomUtils = new RandomUtils();
    FakerGenerator faker = new FakerGenerator();

    UserDTO userDTO = new UserDTO(ConfigurationProperties.getProperty("email"),
            ConfigurationProperties.getProperty("password"));
    UserDTOWith userDTOWith = new UserDTOWith()
            .withEmail(ConfigurationProperties.getProperty("email"))
            .withPassword(ConfigurationProperties.getProperty("password"));
    UserDtoLombok  userDtoLombok = UserDtoLombok.builder()
            .email(ConfigurationProperties.getProperty("email"))
            .password(ConfigurationProperties.getProperty("password"))
            .build();

    boolean isFlagLogin = false, isFlagPopUp = false;

    @BeforeSuite(alwaysRun = true)
    public void setup() {
        app.init();
    }

    @AfterSuite(alwaysRun = true)
    public void stop() {
//        app.tearDown();
    }

    public void logoutIflogin() {
        if (app.getUserHelper().btnLogoutExist()) {
            app.getUserHelper().logout();
        }
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeEachMethod(Method method) {

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
