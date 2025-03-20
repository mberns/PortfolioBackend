
package com.portfolio.martina_b.Dto;

import jakarta.validation.constraints.NotBlank;

public class DtoEducacion {

    @NotBlank
    private String nombreE;
    @NotBlank
    private String descripcionE;

    //Constructores
    public DtoEducacion() {
    }

    public DtoEducacion(String nombreE, String descripcionE) {
        this.nombreE = nombreE;
        this.descripcionE = descripcionE;
    }
    
    //getters y setters

    public String getNombreE() {
        return nombreE;
    }

    public void setNombreE(String nombreE) {
        this.nombreE = nombreE;
    }

    public String getDescripcionE() {
        return descripcionE;
    }

    public void setDescripcionE(String descripcionE) {
        this.descripcionE = descripcionE;
    }
    
    
}
