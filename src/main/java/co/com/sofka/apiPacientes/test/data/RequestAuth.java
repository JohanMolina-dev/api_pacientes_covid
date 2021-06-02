package co.com.sofka.apiPacientes.test.data;

import co.com.sofka.apiPacientes.test.model.JsonAuth;

public  class RequestAuth {
    public static JsonAuth getValidUser(){
        return JsonAuth.builder()
                .username("Covid")
                .password("Covid")
                .build();
    }
    public static JsonAuth getInvalidCredentials(){
        return JsonAuth.builder()
                .username("Covid")
                .password("xd")
                .build();
    }
}
