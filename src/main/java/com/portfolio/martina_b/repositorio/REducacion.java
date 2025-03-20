
package com.portfolio.martina_b.repositorio;

import com.portfolio.martina_b.entidad.Educacion;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface REducacion extends JpaRepository<Educacion, Integer>{ //integer porque el id es un entero
    public Optional<Educacion> findByNombreE(String nombreE); //la funcion se llama como lo hayamos llamado en la entidad
    public boolean existsByNombreE(String nombreE); //dice si existe o no existe el nombre
}
