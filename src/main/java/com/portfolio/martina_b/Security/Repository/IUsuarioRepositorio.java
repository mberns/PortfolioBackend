
package com.portfolio.martina_b.Security.Repository;

//import com.portfolio.martina_b.Security.Entity.Rol;
import com.portfolio.martina_b.Security.Entity.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsuarioRepositorio extends JpaRepository<Usuario, Integer>{
    Optional<Usuario> findByNombreUsuario(String nombreUsuario);
    
    //que nos devuelva V o F si existe el nombre o el email
    boolean existsByNombreUsuario(String nombreUsuario);
    boolean existsByEmail(String email);
}
