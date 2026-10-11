package model;


public class MarriageApplicationData extends BaseApplicationData {
    // ===== Данные услуги (форма 3) =====
    private String registrationDate;
    private String newLastName;
    private String spouseLastName;
    private String spouseFirstName;
    private String spouseMiddleName;
    private String spouseBirthDate;
    private String spousePassport;

   // ===== Геттеры и сеттеры: услуга =====

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getNewLastName() {
        return newLastName;
    }

    public void setNewLastName(String newLastName) {
        this.newLastName = newLastName;
    }

    public String getSpouseLastName() {
        return spouseLastName;
    }

    public void setSpouseLastName(String spouseLastName) {
        this.spouseLastName = spouseLastName;
    }

    public String getSpouseFirstName() {
        return spouseFirstName;
    }

    public void setSpouseFirstName(String spouseFirstName) {
        this.spouseFirstName = spouseFirstName;
    }

    public String getSpouseMiddleName() {
        return spouseMiddleName;
    }

    public void setSpouseMiddleName(String spouseMiddleName) {
        this.spouseMiddleName = spouseMiddleName;
    }

    public String getSpouseBirthDate() {
        return spouseBirthDate;
    }

    public void setSpouseBirthDate(String spouseBirthDate) {
        this.spouseBirthDate = spouseBirthDate;
    }

    public String getSpousePassport() {
        return spousePassport;
    }

    public void setSpousePassport(String spousePassport) {
        this.spousePassport = spousePassport;
    }
}