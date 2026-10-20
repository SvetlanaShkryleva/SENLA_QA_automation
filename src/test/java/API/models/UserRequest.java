package API.models;

import lombok.Data;

@Data
public class UserRequest {

    private String mode;

    // Заявитель
    private String personalLastName;
    private String personalFirstName;
    private String personalMiddleName;
    private String personalPhoneNumber;
    private String personalNumberOfPassport;
    private String personalAddress;

    // Гражданин
    private String citizenLastName;
    private String citizenFirstName;
    private String citizenMiddleName;
    private String citizenBirthDate;
    private String citizenNumberOfPassport;
    private String citizenGender;
    private String citizenAddress;

    // Услуга: брак
    private String dateOfMarriage;
    private String newLastName;
    private String anotherPersonLastName;
    private String anotherPersonFirstName;
    private String anotherPersonMiddleName;
    private String birth_of_anotoherPerson;
    private String anotherPersonPassport;

    // Услуга: рождение
    private String birth_place;
    private String birth_mother;
    private String birth_father;
    private String birth_grandma;
    private String birth_grandpa;

    // Услуга: смерть
    private String death_dateOfDeath;
    private String death_placeOfDeath;

}