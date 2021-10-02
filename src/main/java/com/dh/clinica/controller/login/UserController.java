package com.dh.clinica.controller.login;

import com.dh.clinica.persistence.entities.login.AppUser;
import com.dh.clinica.service.impl.login.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    AppUserService service;

    @GetMapping("/")
    public String home(){
        return "<h1> Welcome </h1>";
    }

    @GetMapping("/user")
    public String user(){
        return "<h1> Welcome user </h1>";
    }

    @GetMapping("/admin")
    public String admin(){
        return "<h1> Welcome admin </h1>";
    }

    @PostMapping("/usuarios/crear")
    public ResponseEntity<?> crearNuevoUsuario(@RequestBody AppUser user){
        ResponseEntity<?> response = ResponseEntity.badRequest().body(user);
        AppUser user1 = service.guardar(user);
        if(user1 != null){
            response = ResponseEntity.ok(user1);
        }
        return response;
    }

    @GetMapping("/usuarios/todos")
    public ResponseEntity<List<AppUser>> buscarTodosLosUsuarios (){

        return ResponseEntity.ok(service.buscarTodos());
    }

    @PutMapping("/usuarios/actualizar")
    public ResponseEntity<?> actualizarUsuario(@RequestBody AppUser user){

        return ResponseEntity.ok(service.actualizar(user));
    }

    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<?> borrarUsuario (@PathVariable Integer id){
        service.borrar(id);
        return ResponseEntity.ok("El usuario con id: " + id + " fue eliminado.");
    }



}
