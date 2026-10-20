package model;
import lombok.Data;
@Data
public class MarriageApplicationDTO extends BaseApplicationDTO {

    // ===== Данные услуги (форма 3) =====
    private String registrationDate;
    private String newLastName;
    private String spouseLastName;
    private String spouseFirstName;
    private String spouseMiddleName;
    private String spouseBirthDate;
    private String spousePassport;

}