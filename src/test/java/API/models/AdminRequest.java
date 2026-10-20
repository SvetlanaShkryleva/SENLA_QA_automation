package API.models;

import lombok.Data;

@Data
public class AdminRequest {

    private String dateofbirth;
    private String personalFirstName;
    private String personalLastName;
    private String personalMiddleName;
    private String personalNumberOfPassport;
    private String personalPhoneNumber;

}