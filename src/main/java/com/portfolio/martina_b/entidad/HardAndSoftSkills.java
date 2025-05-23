
package com.portfolio.martina_b.entidad;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class HardAndSoftSkills {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;
    //no voy a poner porcentaje -> ver que hago
    //ver el temita de las imagenes
    
    //Constructores
    public HardAndSoftSkills() {
    }

    public HardAndSoftSkills(String nombre) {
        this.nombre = nombre;
    }
    
    //Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
    
}
