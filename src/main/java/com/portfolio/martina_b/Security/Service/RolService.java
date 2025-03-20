package com.portfolio.martina_b.Security.Service;

import com.portfolio.martina_b.Security.Entity.Rol;
import com.portfolio.martina_b.Security.Enums.RolNombre;
import com.portfolio.martina_b.Security.Repository.IRolRepository;
import jakarta.transaction.Transactional;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional //trata de mantener lo mismo q tenemos aca con la BD
//es decir, nos asegura q los mismos datos q tenemos aca son los mismos q los
//de la BD, eso se llama persistencia. Si falla, hace q el fallo no impacte en la BD
public class RolService {
    @Autowired
    IRolRepository iRolRepository;
    
    public Optional<Rol> getByRolNombre(RolNombre rolNombre){
        return iRolRepository.findByRolNombre(rolNombre);
    }
    
    public void save(Rol rol){
        iRolRepository.save(rol);
    }
}
