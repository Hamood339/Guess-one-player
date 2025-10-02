package com.springbootapp.spring_boot_app.service.DTO;

import java.util.List;

public class GuessRequest {
     private List<String> name;

    public List<String> getName() {
        return name;
    }

    public void setName(List<String> name) {
        this.name = name;
    }
}
