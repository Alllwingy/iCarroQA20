package manager;

import dto.UserDTO;
import dto.UserDTOWith;
import dto.UserDtoLombok;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class UserHelper extends BaseHelper {

    public UserHelper(WebDriver driver) {
        super(driver);
    }

    By btnLoginNavigatorMenu = By.xpath("//a[contains(@href, '/login')]");
    By inputEmailLoginForm = By.xpath("//input[@id='email']");
    By inputPasswordLoginForm = By.xpath("//input[@id='password']");
    By btnYallaLoginForm = By.xpath("//button[@type='submit']");
    By textSuccessLoginPopUp = By.xpath("//h2[@class='message']");
    By btnOpenRegForm = By.xpath("//a[contains(@href, '/registration')]");
    By inputNameReg = By.xpath("//input[@id='name']");
    By inputLastNameReg = By.xpath("//input[@id='lastName']");
    By inputEmailReg = By.xpath("//input[@id='email']");
    By inputPasswordReg = By.xpath("//input[@id='password']");
    String btnRegNewUser = "document.querySelector('#terms-of-use').click();";
    String btnOkPopUpStr = "document.querySelector(`[type='button']`).click();";
    By checkBoxReg = By.xpath("//input[@id='terms-of-use']");
    String checkBoxRegJS = "document.querySelector('#terms-of-use').click()";
    By btnUallaReg = By.xpath("//button[@type='submit']");
    By textPopUpSuccessRegH1 = By.xpath("//*[text()='Registered']");
    By btnLogout = By.xpath("//a[contains(@href, 'logout')]");
    String buttonLogout = "document.querySelector('a.navigation-link.ng-star-inserted').click();";
    By btnOkPopUp = By.xpath("//button[@type='button']");
    By errorMessageWrongEmailReg = By.xpath("//input[@autocomplete='email']/..//div//div");
    By errorMessageIncorrectPasswordReg = By.xpath("//input[@autocomplete='new-password']/..//div//div");

    public void login(UserDTO userDTO) {
        clickBase(btnLoginNavigatorMenu);
        typeTextBase(inputEmailLoginForm, userDTO.getEmail());
        typeTextBase(inputPasswordLoginForm, userDTO.getPassword());
        clickBase(btnYallaLoginForm);
    }

    public void login(UserDTOWith userDTO) {
        clickBase(btnLoginNavigatorMenu);
        typeTextBase(inputEmailLoginForm, userDTO.getEmail());
        typeTextBase(inputPasswordLoginForm, userDTO.getPassword());
        clickBase(btnYallaLoginForm);
    }

    public void loginUserDtoLombok(UserDtoLombok user) {
        clickBase(btnLoginNavigatorMenu);
        typeTextBase(inputEmailLoginForm, user.getEmail());
        typeTextBase(inputPasswordLoginForm, user.getPassword());
        clickBase(btnYallaLoginForm);
    }

    public boolean validatePopUpMessageSuccessAfterLogin() {
        return isTextEqual(textSuccessLoginPopUp, waitBase(textSuccessLoginPopUp, "Logged in success"));
    }

    public boolean validatePopUpMessageLoginIncorrect() {
        return isTextEqual(textSuccessLoginPopUp, waitBase(textSuccessLoginPopUp, "\"Login or Password incorrect\""));
    }

    public void fillRegistrationForm(UserDtoLombok user) {
        clickBase(btnOpenRegForm);
        typeTextBase(inputNameReg, user.getName());
        typeTextBase(inputLastNameReg, user.getLastName());
        typeTextBase(inputEmailReg, user.getEmail());
        typeTextBase(inputPasswordReg, user.getPassword());

        if (!isSelected(checkBoxReg))
            jsClickBase(checkBoxRegJS);

        clickBase(btnUallaReg);
    }

    public boolean validatePopUpMessageSuccessAfterRegistration() {
        String expectedResult = "Registered".toUpperCase();
//        return isTextEqual(textPopUpSuccessRegH1, waitBase(textPopUpSuccessRegH1, expectedResult));
        return isTextEqual(textPopUpSuccessRegH1, expectedResult);
    }

    public boolean btnLogoutExist() {
        return isElementExist(btnLogout);
    }

    public void logout() {
        jsClickBase(buttonLogout);
    }

    public void clickOkPopUpSuccessLogin() {
        // clickBase(btnOkPopUp);
        // typeTextBase(textPopUpSuccessRegH1, String.valueOf(Keys.ESCAPE));
        jsClickBase(btnOkPopUpStr);
        // clickByXY(btnOkPopUp, 0.5, 2);
        //      clickBase(textPopUpSuccessRegH1);
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }

//        Actions actions = new Actions(driver);
//        // Use the sendKeys method to simulate pressing the "Enter" key on the active element
//        actions.sendKeys(Keys.TAB).perform();
//        actions.sendKeys(Keys.ESCAPE).perform();
    }

    public boolean validateMessageIncorrectEmailReg() {
        return isTextEqual(errorMessageWrongEmailReg, waitBase(errorMessageWrongEmailReg, "Wrong email format"));
    }

    public boolean validateMessageWrongPasswordReg() {
        return isTextEqual(errorMessageIncorrectPasswordReg, waitBase(errorMessageIncorrectPasswordReg,
                "Password must contain minimum 8 symbols"));
    }

    public boolean validateErrorEmptyEmailReg() {
        return isTextEqual(errorMessageWrongEmailReg, waitBase(errorMessageWrongEmailReg, "Email is required"));
    }
}