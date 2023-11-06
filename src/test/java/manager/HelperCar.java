package manager;

import com.fasterxml.jackson.databind.ser.Serializers;
import dto.AddCarDTO;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
    By labelPhoto = By.id("photos");
    By buttonSubmit = By.cssSelector("button[type='submit']");
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

        getElementBase(labelPhoto)
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
        clickBase(button);
    }
}
