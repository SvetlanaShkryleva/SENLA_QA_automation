package model.ui;
import lombok.Data;

@Data
public class DeathApplicationDTO extends BaseApplicationDTO {

    // ===== Данные услуги (форма 3) =====
    private String deathDate;
    private String placeOfDeath;

}