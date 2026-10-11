package model;


public class BirthApplicationData extends BaseApplicationData {
    // ===== Данные услуги (форма 3) =====
    private String placeOfBirth;
    private String mother;
    private String father;
    private String grandmother;
    private String grandfather;

    // ===== Геттеры и сеттеры: услуга =====

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public String getMother() {
        return mother;
    }

    public void setMother(String mother) {
        this.mother = mother;
    }

    public String getFather() {
        return father;
    }

    public void setFather(String father) {
        this.father = father;
    }

    public String getGrandmother() {
        return grandmother;
    }

    public void setGrandmother(String grandmother) {
        this.grandmother = grandmother;
    }

    public String getGrandfather() {
        return grandfather;
    }

    public void setGrandfather(String grandfather) {
        this.grandfather = grandfather;
    }
}