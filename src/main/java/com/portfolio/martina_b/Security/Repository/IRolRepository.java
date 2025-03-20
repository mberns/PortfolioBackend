package com.portfolio.martina_b.Security.Repository;

import com.portfolio.martina_b.Security.Entity.Rol;
import com.portfolio.martina_b.Security.Enums.RolNombre;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRolRepository extends JpaRepository<Rol, Integer> { //el id es integer
    Optional<Rol> findByRolNombre(RolNombre rolNombre);
}
