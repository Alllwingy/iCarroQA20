package tests;

import data.DataProviderAddCar;
import dto.AddCarDTO;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestCar extends BaseTest {

    @BeforeClass
    public void beforeClass() {

        app.getUserHelper().loginAddCar(userDtoLombok);
    }

    @BeforeMethod
    public void beforeMethod() {

        app.getCarHelper().openFormAddCar();
    }

    @AfterClass
    public void afterClass() {

        app.getUserHelper().logout();
    }

    @Test (dataProvider = "data", dataProviderClass = DataProviderAddCar.class)
    public void positive(AddCarDTO carDTO) {

        app.getCarHelper().fillFormAddCar(carDTO);
    }
}
