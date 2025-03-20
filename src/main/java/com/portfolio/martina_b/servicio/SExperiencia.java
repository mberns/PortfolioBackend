
package com.portfolio.martina_b.servicio;

import com.portfolio.martina_b.entidad.Experiencia;
import com.portfolio.martina_b.repositorio.RExperiencia;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional//va a tratar de mantener la persistencia, lo que tengamos aca lo vamos a tener en la BD
public class SExperiencia {
    @Autowired //vamos a inyectar el repositorio
    RExperiencia rExperiencia;
    
    public List<Experiencia> list(){ //arma una lista con todas las experiencias que encuentre
        return rExperiencia.findAll();
    }
    
    //Optional porque puede estar o no
    public Optional<Experiencia> getOne(int id){//que busque uno en particular por id
        return rExperiencia.findById(id);
    }
    
    public Optional<Experiencia> getByNombreE(String nombreE){
        return rExperiencia.findByNombreE(nombreE);
    }
    
    public void save(Experiencia experiencia){
        rExperiencia.save(experiencia);
    }
    
    public void delete(int id){
        rExperiencia.deleteById(id);
    }
    
    public boolean existsById(int id){
        return rExperiencia.existsById(id);
    }
    
    public boolean existsByNombreE(String nombreE){
        return rExperiencia.existsByNombreE(nombreE);
    }
}
