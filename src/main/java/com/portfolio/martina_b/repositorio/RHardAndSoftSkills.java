
package com.portfolio.martina_b.repositorio;

import com.portfolio.martina_b.entidad.HardAndSoftSkills;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RHardAndSoftSkills extends JpaRepository<HardAndSoftSkills, Integer> {
    Optional<HardAndSoftSkills> findByNombre(String nombre);
    public boolean existsByNombre(String nombre);
    
}
