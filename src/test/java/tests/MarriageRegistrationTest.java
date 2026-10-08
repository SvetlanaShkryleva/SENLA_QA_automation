package tests;

import model.MarriageApplicationData;
import org.junit.jupiter.api.Test;
import pages.*;

public class MarriageRegistrationTest extends BaseTest {

    @Test
    public void registerMarriage() {
        MarriageApplicationData data = new MarriageApplicationData();

        // ===== Форма 1: данные заявителя =====
        data.setApplicantLastName("Иванов");
        data.setApplicantFirstName("Иван");
        data.setApplicantMiddleName("Иванович");
        data.setApplicantPhone("89999999999");
        data.setApplicantPassport("00000000");
        data.setApplicantAddress("ул дом");

        // ===== Форма 2: данные гражданина =====
        data.setCitizenLastName("Петрова");
        data.setCitizenFirstName("Мария");
        data.setCitizenMiddleName("Сергеевна");
        data.setCitizenBirthDate("01.01.1995");
        data.setCitizenPassport("11111111");
        data.setCitizenGender("Женский");
        data.setCitizenAddress("ул. Пушкина, д. 1");

        // ===== Форма 3: данные услуги =====
        data.setRegistrationDate("11.11.2025");
        data.setNewLastName("Иванова");
        data.setSpouseLastName("Иванова");
        data.setSpouseFirstName("Мария");
        data.setSpouseMiddleName("Сергеевна");
        data.setSpouseBirthDate("01.01.1995");
        data.setSpousePassport("11111111");

        // ===== Сценарий =====
        new LoginPage(driver).clickLoginAsUser();

        new ApplicantsData(driver)
                .fillApplicantData(data)
                .clickNext();

        new ServiceChoice(driver)
                .chooseService("Регистрация брака");

        new CitizensData(driver)
                .fillCitizenData(data)
                .clickNext();

        new ServicesData(driver)
                .fillMarriageServiceData(data)
                .submit();
    }
}