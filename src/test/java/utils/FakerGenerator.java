package utils;

import com.github.javafaker.Faker;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FakerGenerator {

    Faker faker = new Faker();
    String filePath = "listOfEmails.txt";

    public String generateEmail_Faker() {

        String email = faker.internet().emailAddress();

        try (BufferedWriter out = new BufferedWriter(new FileWriter(filePath))) {

            out.write(email);
            out.newLine();

        } catch (IOException e) {

            System.out.println("Wrong data");
        }

        return email;
    }

    public String firstName() {

        return faker.name().firstName();
    }

    public String lastName() {

        return faker.name().lastName();
    }
}