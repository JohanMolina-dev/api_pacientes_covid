package co.com.sofka.apiPacientes.test.data;

import co.com.sofka.apiPacientes.test.model.JsonAppointment;
import com.github.javafaker.Faker;
import com.github.javafaker.service.FakerIDN;

import java.security.SecureRandom;
import java.util.Locale;

public class RequestAppointment {
    private static final Faker faker = Faker.instance(new Locale("es", "CO"), new SecureRandom());
    public static JsonAppointment getAppointmentInformation(int patientId){

     return JsonAppointment.builder()
             .conclude(true)
             .date(faker.date().birthday().toString())
             .patientId(patientId)
             .recommendations(faker.medical().symptoms())
             .time(faker.regexify("([0]){1}([1-9]){1}"+ "AM")).build();
 }
}
