package com.dh.clinica.service.impl.login;

import com.dh.clinica.persistence.entities.login.AppUser;
import com.dh.clinica.persistence.repositories.login.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppUserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public AppUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).get();
    }

    public AppUser guardar(AppUser user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        return userRepository.save(user);

    }

    public List<AppUser> buscarTodos (){
        return userRepository.findAll();
    }

    public Optional<AppUser> buscarPorId (Integer id){
        return userRepository.findById(id);
    }

    public AppUser actualizar (AppUser user){
        return userRepository.save(user);
    }

    public String borrar (Integer id){
        String response = null;
        if(buscarPorId(id) != null) {
            userRepository.deleteById(id);
            response = "Eliminado exitosamente";
        }
        return response;
    }
}
