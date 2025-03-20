package com.portfolio.martina_b.servicio;

import com.portfolio.martina_b.entidad.Persona;
import com.portfolio.martina_b.interfaz.IPersonaServicio;
import com.portfolio.martina_b.repositorio.IPersonaRepositorio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImpPersonaServicio implements IPersonaServicio{
    @Autowired IPersonaRepositorio IpersonaRepositorio;

    @Override
    public List<Persona> getPersona() {
        //la variable persona va a contener una lista de personas
        List<Persona> persona = IpersonaRepositorio.findAll(); //cuando ponemos punto aparece el listado 
        //de funciones disponibles
        return persona;
    }

    @Override
    public void savePersona(Persona persona) {
        IpersonaRepositorio.save(persona);
    }

    @Override
    public void deletePersona(Long id) {
        //vamos a borrar por id, porque sino, si usamos la funcion deleteAll(), 
        //borramos todos los usuarios
        IpersonaRepositorio.deleteById(id);
    }

    @Override
    public Persona findPersona(Long id) {
        //vamos a buscar por id, sino lo encuentra devuelve null
        Persona persona = IpersonaRepositorio.findById(id).orElse(null);
        return persona;
    }
    
}
