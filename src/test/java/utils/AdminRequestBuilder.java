package utils;

import model.api.AdminRequestDTO;

public class AdminRequestBuilder {

    private AdminRequestBuilder() {}

    public static AdminRequestDTO validAdmin() {
        AdminRequestDTO admin = new AdminRequestDTO();
        admin.setDateofbirth("1980-01-01");
        admin.setPersonalFirstName("Тестовый");
        admin.setPersonalLastName("Админов");
        admin.setPersonalMiddleName("Тестович");
        admin.setPersonalNumberOfPassport("99990001");
        admin.setPersonalPhoneNumber("89990000001");
        return admin;
    }
}