package com.portfolio.martina_b.controlador;

import com.portfolio.martina_b.entidad.Persona;
import com.portfolio.martina_b.interfaz.IPersonaServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin(origins = {"http://localhost:4200","https://frontend-mb-portfolio.web.app"})
public class PersonaControlador {
    //el controlador llama al servicio
    //entonces hacemos un autowired al servicio
    @Autowired IPersonaServicio IpersonaServicio;
    
    @GetMapping("personas/traer")//para ver una persona, lo puede hacer cualquiera, admin o user
    public List<Persona> getPersona(){
        return IpersonaServicio.getPersona();
    }
    
    //El CRUD no lo puede hacer cualquiera
    @PreAuthorize("hasRole('ADMIN')")//para hacer esto hay que estar logueado como admin
    @PostMapping("/personas/crear")//para guardar en la BD
    public String crearPersona(@RequestBody Persona persona){ //obj persona tipo Persona
        IpersonaServicio.savePersona(persona);
        return "La persona fue creada correctamente";
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("personas/borrar/{id}")//le pasamos el id en llaves
    public String deletePersona(@PathVariable Long id){
        IpersonaServicio.deletePersona(id);
        return "la persona fue eliminada correctamente";
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping ("/personas/editar/{id}")
    public Persona editPersona(@PathVariable Long id,
                               @RequestParam("nombre") String nuevoNombre,
                               @RequestParam("apellido") String nuevoApellido,
                               @RequestParam("img") String nuevoImg){
        
        Persona persona = IpersonaServicio.findPersona(id); //buscamos a la persona por id
        persona.setNombre(nuevoNombre);
        persona.setApellido(nuevoApellido);
        persona.setImg(nuevoImg);
        
        IpersonaServicio.savePersona(persona);
        
        return persona;
        
    }
    
    @GetMapping("personas/traer/perfil")
    public Persona findPersona(){
        return IpersonaServicio.findPersona(1L); //Long.MIN_VALUE
    }
}
