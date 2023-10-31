package data;

import dto.UserDtoLombok;
import org.testng.annotations.DataProvider;

import java.io.*;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviderLogin {

    List<Object[]> list = new ArrayList<>();

    @DataProvider
    public Iterator<Object[]> data(Method method) {

        switch (method.getName()) {

            case "positiveLoginUserDTO":

                list.add(new Object[]{

                        UserDtoLombok.builder()
                                .email("testqa20@gmail.com")
                                .password("123456Aa$")
                                .build()
                });

                return list.iterator();

            case "negativePasswordWithoutSymbol":

                list.add(new Object[]{

                        UserDtoLombok.builder()
                                .email("testqa20@gmail.com")
                                .password("123456Aa")
                                .build()
                });

                list.add(new Object[]{

                        UserDtoLombok.builder()
                                .email("testqa20@gmail.com")
                                .password("123456$")
                                .build()
                });

                list.add(new Object[]{

                        UserDtoLombok.builder()
                                .email("testqa20@gmail.com")
                                .password("Aa$")
                                .build()
                });

                return list.iterator();
        }

        return null;
    }

    public String switchPath(String methodName) {

        switch (methodName) {

            case "positiveLoginUserDTO": return "src/test/resources/positivedata.csv";

            case "negativeWrongEmail": return "src/test/resources/negativeemaildata.csv";

            case "negativePasswordWithoutSymbol": return "src/test/resources/negativepassworddata.csv";
        }

        return null;
    }

    @DataProvider
    public Iterator<Object[]> dataCSV(Method method) {

        String path = switchPath(method.getName());

        try (BufferedReader in = new BufferedReader(new FileReader(new File(path)))) {

            String line;

            while ((line = in.readLine()) != null) {

                String[] split = line.split(",");

                list.add(new Object[]{

                        UserDtoLombok.builder()
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
}
