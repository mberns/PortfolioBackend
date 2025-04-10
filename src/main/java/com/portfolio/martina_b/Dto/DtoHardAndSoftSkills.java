
package com.portfolio.martina_b.Dto;

import jakarta.validation.constraints.NotBlank;


public class DtoHardAndSoftSkills {
    @NotBlank
    private String nombre;
    
    //Constructor
    public DtoHardAndSoftSkills() {
    }

    public DtoHardAndSoftSkills(String nombre) {
        this.nombre = nombre;
    }
    
    //Getters and setters

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
}
