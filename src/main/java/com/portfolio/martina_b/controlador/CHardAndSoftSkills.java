
package com.portfolio.martina_b.controlador;

import com.portfolio.martina_b.Security.Controller.Mensaje;
import com.portfolio.martina_b.entidad.HardAndSoftSkills;
import com.portfolio.martina_b.servicio.SHardAndSoftSkills;
import com.portfolio.martina_b.Dto.DtoHardAndSoftSkills;
import io.micrometer.common.util.StringUtils;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/skills")
public class CHardAndSoftSkills {
    @Autowired
    SHardAndSoftSkills sHardAndSoftSkills;
    
    @GetMapping("/lista")
    public ResponseEntity<List<HardAndSoftSkills>> list() {
        List<HardAndSoftSkills> list = sHardAndSoftSkills.list(); //una variable que contiene una lista
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @PostMapping("/create") //le vamos a mandar al servidor
    //el signo ? significa que puede recibir cualquier cosa
    public ResponseEntity<?> create(@RequestBody DtoHardAndSoftSkills dtohys) {
        if (StringUtils.isBlank(dtohys.getNombre()))//si el campo es blanco, busca el dato, que es nombre
        {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        //pero el nombre es obligatorio, por eso manda el mensaje de advertencia
        if (sHardAndSoftSkills.existsByNombre(dtohys.getNombre())) {
            return new ResponseEntity(new Mensaje("Esa skill ya existe"), HttpStatus.BAD_REQUEST);
        }

        HardAndSoftSkills hardAndSoftSkills = new HardAndSoftSkills(dtohys.getNombre());
        sHardAndSoftSkills.save(hardAndSoftSkills);

        return new ResponseEntity(new Mensaje("Skill agregada"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody DtoHardAndSoftSkills dtohys) { //traemos datos del body
        if (!sHardAndSoftSkills.existsById(id))//validamos si existe el ID
        {
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
        }

        //compara nombre de experiencias
        if (sHardAndSoftSkills.existsByNombre(dtohys.getNombre())
                && sHardAndSoftSkills.getByNombre(dtohys.getNombre()).get().getId() != id) {
            return new ResponseEntity(new Mensaje("Esa skill ya existe"), HttpStatus.BAD_REQUEST);
        }

        if (StringUtils.isBlank(dtohys.getNombre())) //no puede estar vacio
        {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }

        HardAndSoftSkills hardAndSoftSkills = sHardAndSoftSkills.getOne(id).get();
        hardAndSoftSkills.setNombre(dtohys.getNombre());

        sHardAndSoftSkills.save(hardAndSoftSkills);
        return new ResponseEntity(new Mensaje("Skill actualizada"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!sHardAndSoftSkills.existsById(id))//validamos si (no) existe el ID
        {
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
        }

        sHardAndSoftSkills.delete(id);
        return new ResponseEntity(new Mensaje("Experiencia eliminada"), HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<HardAndSoftSkills> getById(@PathVariable("id") int id) {
        if (!sHardAndSoftSkills.existsById(id)) {
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.NOT_FOUND);
        }
        HardAndSoftSkills hardAndSoftSkills = sHardAndSoftSkills.getOne(id).get();
        return new ResponseEntity(hardAndSoftSkills, HttpStatus.OK);
    }
}
