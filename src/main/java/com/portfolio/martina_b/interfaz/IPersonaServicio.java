package com.portfolio.martina_b.interfaz;

import com.portfolio.martina_b.entidad.Persona;
import java.util.List;


public interface IPersonaServicio {
   //Traer una list de personas 
    public List<Persona> getPersona();
    
    //Guardar un objeto de tipo persona
    public void savePersona(Persona persona);
    
    //Eliminar un objeto pero lo buscamos por ID
    public void deletePersona(Long id);
    
    //Buscamos una persona por ID
    public Persona findPersona(Long id);
}
