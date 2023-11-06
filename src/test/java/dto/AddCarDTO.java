package dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString

public class AddCarDTO {

    String city;
    String manufacture;
    String model;
    String year;
    String fuel;
    String seats;
    String carClass;
    String serialNumber;
    String pricePerDay;
    String about;
}
