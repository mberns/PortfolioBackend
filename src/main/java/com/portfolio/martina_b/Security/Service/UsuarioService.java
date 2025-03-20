
package com.portfolio.martina_b.Security.Service;

//import com.portfolio.martina_b.Security.Entity.Rol;
import com.portfolio.martina_b.Security.Entity.Usuario;
import com.portfolio.martina_b.Security.Repository.IUsuarioRepositorio;
import jakarta.transaction.Transactional;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Transactional 
public class UsuarioService {
    @Autowired
    IUsuarioRepositorio iUsuarioRepositorio;
    
    public Optional<Usuario> getByNombreUsuario(String nombreUsuario){ 
        return iUsuarioRepositorio.findByNombreUsuario(nombreUsuario);
    }
    
    public boolean existsByNombreUsuario(String nombreUsuario){
        return iUsuarioRepositorio.existsByNombreUsuario(nombreUsuario);
    }
    
    public boolean existsByEmail(String email){
        return iUsuarioRepositorio.existsByEmail(email);
    }
    
    public void save(Usuario usuario){ //void porque no devuelve nada, solo guarda los cambios
        iUsuarioRepositorio.save(usuario);
    }
    
}
