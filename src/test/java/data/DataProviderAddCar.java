package data;

import com.github.javafaker.Faker;
import dto.AddCarDTO;
import dto.UserDtoLombok;
import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviderAddCar {

    List<Object[]> list = new ArrayList<>();
    Faker faker = new Faker();

    @DataProvider
    public Iterator<Object[]> data(Method method) {

        switch (method.getName()) {

            case "positive": return read("src/test/resources/addcarpositive.csv");
        }

        return null;
    }

    public Iterator<Object[]> read(String path) {

        try (BufferedReader in = new BufferedReader(new FileReader(new File(path)))) {

            String line;

            while ((line = in.readLine()) != null) {

                String[] split = line.split(",");

                list.add(new Object[]{

                        AddCarDTO.builder()
                                .city(split[0])
                                .manufacture(split[1])
                                .model(split[2])
                                .year(split[3])
                                .fuel(split[4])
                                .seats(String.valueOf(faker.number().numberBetween(5,9)))
                                .carClass(split[5])
                                .serialNumber(String.valueOf(faker.number().numberBetween(1000000, 9999999)))
                                .pricePerDay(String.valueOf(faker.number().randomDouble(1,100,500)))
                                .about(split[6])
                                .build()
                });
            }
        } catch (IOException e) {

            throw new RuntimeException(e);
        }

        return list.iterator();
    }
}
