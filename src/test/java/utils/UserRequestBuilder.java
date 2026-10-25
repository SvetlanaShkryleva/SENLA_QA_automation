package utils;

import model.api.UserRequestDTO;

public class UserRequestBuilder {

    private UserRequestBuilder() {}

    public static UserRequestDTO weddingRequest() {
        UserRequestDTO u = new UserRequestDTO();
        u.setMode("wedding");
        u.setPersonalLastName("Иванов");
        u.setPersonalFirstName("Иван");
        u.setPersonalMiddleName("Иванович");
        u.setPersonalPhoneNumber("89999999999");
        u.setPersonalNumberOfPassport("00000000");
        u.setPersonalAddress("ул. Ленина, д. 1");
        u.setCitizenLastName("Петрова");
        u.setCitizenFirstName("Мария");
        u.setCitizenMiddleName("Сергеевна");
        u.setCitizenBirthDate("1995-01-01");
        u.setCitizenNumberOfPassport("11111111");
        u.setCitizenGender("Женский");
        u.setCitizenAddress("ул. Пушкина, д. 2");
        u.setDateOfMarriage("2025-11-11");
        u.setNewLastName("Иванова");
        u.setAnotherPersonLastName("Иванова");
        u.setAnotherPersonFirstName("Мария");
        u.setAnotherPersonMiddleName("Сергеевна");
        u.setBirth_of_anotoherPerson("1995-01-01");
        u.setAnotherPersonPassport("11111111");
        return u;
    }
}