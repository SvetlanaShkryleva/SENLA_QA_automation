package model;

public class MarriageApplicationData {

    // ===== Данные заявителя (форма 1) =====
    private String applicantLastName;
    private String applicantFirstName;
    private String applicantMiddleName;
    private String applicantPhone;
    private String applicantPassport;
    private String applicantAddress;

    // ===== Данные гражданина (форма 2) =====
    private String citizenLastName;
    private String citizenFirstName;
    private String citizenMiddleName;
    private String citizenBirthDate;
    private String citizenPassport;
    private String citizenGender;
    private String citizenAddress;

    // ===== Данные услуги (форма 3) =====
    private String registrationDate;
    private String newLastName;
    private String spouseLastName;
    private String spouseFirstName;
    private String spouseMiddleName;
    private String spouseBirthDate;
    private String spousePassport;

    // ===== Геттеры и сеттеры: заявитель =====

    public String getApplicantLastName() {
        return applicantLastName;
    }

    public void setApplicantLastName(String applicantLastName) {
        this.applicantLastName = applicantLastName;
    }

    public String getApplicantFirstName() {
        return applicantFirstName;
    }

    public void setApplicantFirstName(String applicantFirstName) {
        this.applicantFirstName = applicantFirstName;
    }

    public String getApplicantMiddleName() {
        return applicantMiddleName;
    }

    public void setApplicantMiddleName(String applicantMiddleName) {
        this.applicantMiddleName = applicantMiddleName;
    }

    public String getApplicantPhone() {
        return applicantPhone;
    }

    public void setApplicantPhone(String applicantPhone) {
        this.applicantPhone = applicantPhone;
    }

    public String getApplicantPassport() {
        return applicantPassport;
    }

    public void setApplicantPassport(String applicantPassport) {
        this.applicantPassport = applicantPassport;
    }

    public String getApplicantAddress() {
        return applicantAddress;
    }

    public void setApplicantAddress(String applicantAddress) {
        this.applicantAddress = applicantAddress;
    }

    // ===== Геттеры и сеттеры: гражданин =====

    public String getCitizenLastName() {
        return citizenLastName;
    }

    public void setCitizenLastName(String citizenLastName) {
        this.citizenLastName = citizenLastName;
    }

    public String getCitizenFirstName() {
        return citizenFirstName;
    }

    public void setCitizenFirstName(String citizenFirstName) {
        this.citizenFirstName = citizenFirstName;
    }

    public String getCitizenMiddleName() {
        return citizenMiddleName;
    }

    public void setCitizenMiddleName(String citizenMiddleName) {
        this.citizenMiddleName = citizenMiddleName;
    }

    public String getCitizenBirthDate() {
        return citizenBirthDate;
    }

    public void setCitizenBirthDate(String citizenBirthDate) {
        this.citizenBirthDate = citizenBirthDate;
    }

    public String getCitizenPassport() {
        return citizenPassport;
    }

    public void setCitizenPassport(String citizenPassport) {
        this.citizenPassport = citizenPassport;
    }

    public String getCitizenGender() {
        return citizenGender;
    }

    public void setCitizenGender(String citizenGender) {
        this.citizenGender = citizenGender;
    }

    public String getCitizenAddress() {
        return citizenAddress;
    }

    public void setCitizenAddress(String citizenAddress) {
        this.citizenAddress = citizenAddress;
    }

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