package com.portfolio.martina_b.entidad;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Persona {
    @Id //primary key
    @GeneratedValue (strategy = GenerationType.IDENTITY) //autogenerado
    //con ctrl + barra espaciadora dsp de strategy se completa el = y si hacemos
    //eso devuelta nos tira un listado de opciones para completar, ahi elegimos generation
    //type IDENTITY
    
    //@NotNull
    //@Size(min = 1, max = 50, message = "no cumple con la longitud") //minimo 1 letra, maximo 50
    //si no cumple los requisitos, tira un mensaje
    private Long id; //porque el long permite una mayor cantidad de nros
    
    @NotNull
    @Size(min = 1, max = 50, message = "no cumple con la longitud")
    private String nombre;
    
    @NotNull
    @Size(min = 1, max = 50, message = "no cumple con la longitud")
    private String apellido;
    
    @Size(min = 1, max = 50, message = "no cumple con la longitud")
    private String img;

}
