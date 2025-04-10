
package com.portfolio.martina_b.servicio;

import com.portfolio.martina_b.entidad.HardAndSoftSkills;
import com.portfolio.martina_b.repositorio.RHardAndSoftSkills;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional //para la persistencia de datos
@Service
public class SHardAndSoftSkills {
    @Autowired
    RHardAndSoftSkills rHardAndSoftSkills;
    
    public List<HardAndSoftSkills> list(){
        return rHardAndSoftSkills.findAll();
    }
    
    public Optional<HardAndSoftSkills> getOne(int id){
        return rHardAndSoftSkills.findById(id);
    }
    
    public Optional<HardAndSoftSkills> getByNombre(String nombre){
        return rHardAndSoftSkills.findByNombre(nombre);
    }
    
    public void save(HardAndSoftSkills skill){
        rHardAndSoftSkills.save(skill);
    }
    
    public void delete(int id){
        rHardAndSoftSkills.deleteById(id);
    }
    
    public boolean existsById(int id){
        return rHardAndSoftSkills.existsById(id);
    }
    
     public boolean existsByNombre(String nombre){
        return rHardAndSoftSkills.existsByNombre(nombre);
    }
}
