package model;

public class DeathApplicationData extends BaseApplicationData {

    // ===== Данные услуги (форма 3) =====
    private String deathDate;
    private String placeOfDeath;

    // ===== Геттеры и сеттеры =====

    public String getDeathDate() {
        return deathDate;
    }

    public void setDeathDate(String deathDate) {
        this.deathDate = deathDate;
    }

    public String getPlaceOfDeath() {
        return placeOfDeath;
    }

    public void setPlaceOfDeath(String placeOfDeath) {
        this.placeOfDeath = placeOfDeath;
    }
}