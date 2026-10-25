package model.ui;
import lombok.Data;
@Data
public abstract class BaseApplicationDTO {

    // ===== Данные заявителя =====
    private String applicantLastName;
    private String applicantFirstName;
    private String applicantMiddleName;
    private String applicantPhone;
    private String applicantPassport;
    private String applicantAddress;

    // ===== Данные гражданина =====
    private String citizenLastName;
    private String citizenFirstName;
    private String citizenMiddleName;
    private String citizenBirthDate;
    private String citizenPassport;
    private String citizenGender;
    private String citizenAddress;

}