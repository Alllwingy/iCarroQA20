package data;

import com.github.javafaker.Faker;
import dto.UserDtoLombok;
import org.testng.annotations.DataProvider;

import java.io.*;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviderRegistration {

    List<Object[]> list = new ArrayList<>();
    Faker faker = new Faker();

    @DataProvider
    public Iterator<Object[]> data(Method method) {

        switch (method.getName()) {

            case "positiveRegistration": return write();

            case "negativeRegistrationWrongEmail": return read("src/test/resources/negativeemaildata.csv");

            case "negativeRegistrationWrongPassword": return read("src/test/resources/negativepassworddata.csv");
        }

        return null;
    }

    public Iterator<Object[]> read(String path) {

        try (BufferedReader in = new BufferedReader(new FileReader(new File(path)))) {

            String line;

            while ((line = in.readLine()) != null) {

                String[] split = line.split(",");

                list.add(new Object[]{

                        UserDtoLombok.builder()
                                .name(faker.name().firstName())
                                .lastName(faker.name().lastName())
                                .email(split[0])
                                .password(split[1])
                                .build()
                });
            }
        } catch (IOException e) {

            throw new RuntimeException(e);
        }

        return list.iterator();
    }

    public Iterator<Object[]> write() {

        String email = faker.internet().emailAddress();
        String password = faker.internet().password() + "A$";

        list.add(new Object[]{

                UserDtoLombok.builder()
                        .name(faker.name().firstName())
                        .lastName(faker.name().lastName())
                        .email(email)
                        .password(password)
                        .build()
        });

        try (BufferedWriter out = new BufferedWriter(new FileWriter("src/test/resources/positivedata.csv", true))) {

            out.write(email + "," + password);
            out.newLine();

        } catch (IOException e) {

            throw new RuntimeException(e);
        }

        return list.iterator();
    }
}