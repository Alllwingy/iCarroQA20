package tests;

import data.DataProviderAddCar;
import dto.AddCarDTO;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

public class TestCar extends BaseTest {

    @BeforeClass
    public void beforeClass() {

        app.getUserHelper().loginAddCar(userDtoLombok);
    }

    @BeforeMethod
    public void beforeMethod() {

        app.getCarHelper().openFormAddCar();
    }

    @AfterMethod
    public void afterMethod() {

        app.getCarHelper().acceptPopUp();
    }

    @AfterClass
    public void afterClass() {

        app.getUserHelper().logout();
    }

    @Test(dataProvider = "data", dataProviderClass = DataProviderAddCar.class)
    public void positive(AddCarDTO carDTO) {

        app.getCarHelper().fillFormAddCar(carDTO);

        softAssert.assertTrue(app.getCarHelper().validatePhotoAddSuccess());
        softAssert.assertTrue(app.getCarHelper().validateTextAddCarSuccess(carDTO));

        softAssert.assertAll();
    }
}
