package model.api;

import lombok.Data;

@Data
public class AdminRequestDTO {

    private String dateofbirth;
    private String personalFirstName;
    private String personalLastName;
    private String personalMiddleName;
    private String personalNumberOfPassport;
    private String personalPhoneNumber;

}