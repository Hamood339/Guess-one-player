package com.springbootapp.spring_boot_app.service.DTO;

public class GuessResponse {

    private String choosen;

    public GuessResponse(String choosen) {
        this.choosen = choosen;
    }

    public String getChoosen() {
        return choosen;
    }

    public void setChoosen(String choosen) {
        this.choosen = choosen;
    }
}
