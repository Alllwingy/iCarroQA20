package manager;

import dto.AddCarDTO;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.regex.Pattern;

public class HelperCar extends BaseHelper {

    Actions action = new Actions(driver);

    public HelperCar(WebDriver driver) {
        super(driver);
    }

    By formAddCar = By.cssSelector("[id='1']");

    By inputCitySelection = By.id("pickUpPlace");
    By inputManufacture = By.id("make");
    By inputModel = By.id("model");
    By inputYear = By.id("year");
    By selectFuel = By.id("fuel");
    By inputSeats = By.id("seats");
    By inputClass = By.id("class");
    By inputSerialNumber = By.id("serialNumber");
    By inputPrice = By.id("price");
    By inputAbout = By.id("about");
    By inputPhoto = By.id("photos");
    By divPhoto = By.cssSelector("div.mat-chip-ripple");
    By buttonSubmit = By.cssSelector("button[type='submit']");
    By h2CarAddedPopUpText = By.cssSelector("h2.message");
    By button = By.cssSelector("button[type='button']:nth-child(3)");

    public void openFormAddCar() {

        action.moveToElement(getElementBase(formAddCar)).click().perform();
    }

    private void chooseCity(AddCarDTO carDTO) {

        action.moveToElement(getElementBase(inputCitySelection))
                .click()
                .sendKeys(carDTO.getCity())
                .pause(1500)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ENTER)
                .perform();
    }

    private void selectFuel(AddCarDTO carDTO) {

        action.moveToElement(getElementBase(selectFuel))
                .click()
                .sendKeys(carDTO.getFuel())
                .sendKeys(Keys.ENTER)
                .perform();
    }

    private void uploadPhoto() {

        getElementBase(inputPhoto)
                .sendKeys("C:\\Users\\Alll.wingy\\Alll.space\\IntelliJ_IDEA\\ilCarroQA20-main\\src\\test\\resources\\JavaRush.Variable_1.png");
    }

    public void fillFormAddCar(AddCarDTO carDTO) {

        chooseCity(carDTO);
        typeTextBase(inputManufacture, carDTO.getManufacture());
        typeTextBase(inputModel, carDTO.getModel());
        typeTextBase(inputYear, carDTO.getYear());
        selectFuel(carDTO);
        typeTextBase(inputSeats, carDTO.getSeats());
        typeTextBase(inputClass, carDTO.getCarClass());
        typeTextBase(inputSerialNumber, carDTO.getSerialNumber());
        typeTextBase(inputPrice, carDTO.getPricePerDay());
        typeTextBase(inputAbout, carDTO.getAbout());
        uploadPhoto();
        clickBase(buttonSubmit);
    }

    public void acceptPopUp() {

        clickBase(button);
    }

    public boolean validatePhotoAddSuccess() {

        return isElementExist(divPhoto);
    }

    public boolean validateTextAddCarSuccess(AddCarDTO carDTO) {

        return isTextEqual(h2CarAddedPopUpText, waitBase(h2CarAddedPopUpText, String.format("%s %s added successful", carDTO.getManufacture(), carDTO.getModel())));
    }
}
