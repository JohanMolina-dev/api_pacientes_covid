package co.com.sofka.apiPacientes.test.data;

import co.com.sofka.apiPacientes.test.model.JsonPatient;
import com.github.javafaker.Faker;

import java.security.SecureRandom;
import java.util.Locale;

public class RequestPatient {
    private static final Faker faker = Faker.instance(new Locale("es", "CO"), new SecureRandom());

    public static JsonPatient getPatientInformation() {
        return JsonPatient.builder()
                .address(faker.address().fullAddress())
                .cellPhoneNumber(Long.parseLong(faker.number().digits(7)))
                .email(faker.internet().emailAddress())
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .patientId(Long.parseLong(faker.number().digits(9)))
                .build();
    }
}
