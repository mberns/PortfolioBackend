
package com.portfolio.martina_b.servicio;

import com.portfolio.martina_b.entidad.Educacion;
import com.portfolio.martina_b.repositorio.REducacion;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional //por el tema de la persistencia de datos
public class SEducacion {
    @Autowired
    REducacion rEducacion; //tenemos que traer el repositorio
    
    //Luego vamos a desarrollar los metodos
    public List<Educacion> list() {
        return rEducacion.findAll(); // que nos haga una lista trayendonos todos
    }
    
    public Optional<Educacion> getOne(int id){
        return rEducacion.findById(id);
    }
    
    public Optional<Educacion> getByNombreE(String nombreE){
        return rEducacion.findByNombreE(nombreE);
    }
    
    public void save(Educacion educacion){
        rEducacion.save(educacion);
    }
    
    public void delete(int id){
        rEducacion.deleteById(id);
    }
    
    public boolean existsById(int id){
        return rEducacion.existsById(id);
    }
    
    public boolean existsByNombreE(String nombreE){
        return rEducacion.existsByNombreE(nombreE);
    }
}
