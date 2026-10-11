package model;

public abstract class BaseApplicationData {

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

}