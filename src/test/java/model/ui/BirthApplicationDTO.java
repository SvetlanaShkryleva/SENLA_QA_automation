package model.ui;

import lombok.Data;

@Data
public class BirthApplicationDTO extends BaseApplicationDTO {

    // ===== Данные услуги (форма 3) =====
    private String placeOfBirth;
    private String mother;
    private String father;
    private String grandmother;
    private String grandfather;

}