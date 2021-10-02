package com.dh.clinica.controller;

import com.dh.clinica.dto.OdontologoDto;
import com.dh.clinica.exceptions.ResourceBadRequestException;
import com.dh.clinica.exceptions.ResourceNotFoundException;
import com.dh.clinica.service.IService;
import org.apache.log4j.Logger;
import org.h2.server.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("/odontologos")
public class OdontologoController<id> {

    @Autowired
    IService<OdontologoDto> ServiceOdontologoDtoImpl;

    private static final Logger logger = Logger.getLogger(Service.class);


    @GetMapping("/todos")
    public ResponseEntity<List<OdontologoDto>> buscarTodos() {
        List<OdontologoDto> odontologos = new ArrayList<>();

        try {
            odontologos = this.ServiceOdontologoDtoImpl.buscarTodos();
            logger.debug("Listando odontólogos: " + odontologos);
            for (OdontologoDto o : odontologos) {
                logger.info(o.toString());
            }
        } catch (Exception e){
            logger.error(e.getMessage());
        }

        return ResponseEntity.ok(odontologos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable Integer id) throws ResourceNotFoundException {
        OdontologoDto odontologo = ServiceOdontologoDtoImpl.buscar(id);
        logger.debug("Buscando al odontólogo con id: " + id);
        return ResponseEntity.ok(odontologo);

    }

    @PostMapping("/nuevo")
    public ResponseEntity<OdontologoDto> crearNuevoOdontologo(@RequestBody OdontologoDto odontologo) throws ResourceBadRequestException {
        OdontologoDto o =  this.ServiceOdontologoDtoImpl.guardar(odontologo);
        logger.debug("Operación exitosa de creado de odontólogo");
        return ResponseEntity.ok(o);
    }

    @PutMapping("/actualizar")
    public ResponseEntity<OdontologoDto> actualizarOdontologo(@RequestBody OdontologoDto odontologo) throws ResourceNotFoundException{
        logger.debug("Se ha actualizado correctamente el odontólogo");
        return ResponseEntity.ok(ServiceOdontologoDtoImpl.actualizar(odontologo));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> borrar(@PathVariable Integer id) throws ResourceNotFoundException {
        ServiceOdontologoDtoImpl.borrar(id);
        logger.debug("Se eliminó al odontólogo");
        return ResponseEntity.ok("El odontologo con id: " + id + " fue eliminado.");
    }


}
