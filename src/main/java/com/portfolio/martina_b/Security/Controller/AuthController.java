
package com.portfolio.martina_b.Security.Controller;

import com.portfolio.martina_b.Security.Dto.JwtDto;
import com.portfolio.martina_b.Security.Dto.LoginUsuario;
import com.portfolio.martina_b.Security.Dto.NuevoUsuario;
import com.portfolio.martina_b.Security.Entity.Rol;
import com.portfolio.martina_b.Security.Entity.Usuario;
import com.portfolio.martina_b.Security.Enums.RolNombre;
import com.portfolio.martina_b.Security.Service.RolService;
import com.portfolio.martina_b.Security.Service.UsuarioService;
import com.portfolio.martina_b.Security.jwt.JwtProvider;
import jakarta.validation.Valid;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")//de donde lo vamos a llamar
@CrossOrigin(origins = {"http://localhost:4200","https://frontend-mb-portfolio.web.app"})
public class AuthController {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UsuarioService usuarioService;
    @Autowired
    RolService rolService;
    @Autowired
    JwtProvider jwtProvider;
    
    @PostMapping("/nuevo")//con esto le decimos la ruta
    public ResponseEntity<?> nuevo(@Valid @RequestBody NuevoUsuario nuevoUsuario, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("Campos mal puestos o email invalido"), HttpStatus.BAD_REQUEST);
        
        if(usuarioService.existsByNombreUsuario(nuevoUsuario.getNombreUsuario()))
            return new ResponseEntity(new Mensaje("Ese nombre de usuario ya existe"), HttpStatus.BAD_REQUEST);
        
        if(usuarioService.existsByEmail(nuevoUsuario.getEmail()))
            return new ResponseEntity(new Mensaje("Ese email ya existe"), HttpStatus.BAD_REQUEST);
        
        Usuario usuario = new Usuario(nuevoUsuario.getNombre(), nuevoUsuario.getNombreUsuario(),
        nuevoUsuario.getEmail(), passwordEncoder.encode(nuevoUsuario.getPassword()));
        
        Set<Rol> roles = new HashSet<>();
        roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER).get());
        
        //nuevo -> no anda
        Optional<Rol> userRole = rolService.getByRolNombre(RolNombre.ROLE_USER);
        if (userRole.isPresent()) {
            roles.add(userRole.get());
        } else {
            return new ResponseEntity(new Mensaje("No existe el rol USER en la base de datos"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        //todos van a ser usuario salvo que sean admin -> viejo -> andaba
        /*
        if(nuevoUsuario.getRoles().contains("admin"))
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get());
        usuario.setRoles(roles);
        usuarioService.save(usuario);
        */
        
        //Nuevo -> no anda
        if (nuevoUsuario.getRoles().contains("admin")) {
            Optional<Rol> adminRole = rolService.getByRolNombre(RolNombre.ROLE_ADMIN);
            if (adminRole.isPresent()) {
                roles.add(adminRole.get());
            } else {
                return new ResponseEntity(new Mensaje("No existe el rol ADMIN en la base de datos"), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        usuario.setRoles(roles);
        usuarioService.save(usuario);

        

        return new ResponseEntity(new Mensaje("Usuario guardado"), HttpStatus.CREATED);
    }
    
    //Si queremos crear un nuevo usuario nos falta el login, entonces hacemos la sig funcion
    
    @PostMapping("/login") //la ruta - endpoint donde vamos a acceder
    public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("Campo mal puesto"), HttpStatus.BAD_REQUEST);
        
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
        loginUsuario.getNombreUsuario(), loginUsuario.getPassword()));
        
        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        String jwt = jwtProvider.generateToken(authentication);
        
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        
        JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
        
        return new ResponseEntity(jwtDto, HttpStatus.OK); //si todo esta bien devuelve ok
    }
}
