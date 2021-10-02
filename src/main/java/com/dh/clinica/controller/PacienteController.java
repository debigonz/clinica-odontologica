package com.dh.clinica.controller;

import com.dh.clinica.dto.PacienteDto;
import com.dh.clinica.exceptions.ResourceBadRequestException;
import com.dh.clinica.exceptions.ResourceNotFoundException;
import com.dh.clinica.service.IService;
import org.apache.log4j.Logger;
import org.h2.server.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    IService<PacienteDto> ServicePacienteDtoImpl;

    private static final Logger logger = Logger.getLogger(Service.class);


    @GetMapping("/todos")
    public ResponseEntity<List<PacienteDto>> buscarTodos() {
        return ResponseEntity.ok(ServicePacienteDtoImpl.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable Integer id) throws ResourceNotFoundException {
        PacienteDto paciente = ServicePacienteDtoImpl.buscar(id);
        logger.debug("Buscando al paciente con id: " + id);
        return ResponseEntity.ok(paciente);
    }

    @PostMapping("/nuevo")
    public ResponseEntity<PacienteDto> crearNuevoPaciente(@RequestBody PacienteDto paciente) throws ResourceBadRequestException {
        logger.debug("Operación exitosa de creado de paciente");
        return ResponseEntity.ok(ServicePacienteDtoImpl.guardar(paciente));
    }

    @PutMapping("/actualizar")
    public ResponseEntity<PacienteDto> actualizarPaciente(@RequestBody PacienteDto paciente) throws ResourceNotFoundException{
        logger.debug("Se ha actualizado correctamente el paciente");
        return ResponseEntity.ok(ServicePacienteDtoImpl.actualizar(paciente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> borrar(@PathVariable Integer id) throws ResourceNotFoundException{
        ServicePacienteDtoImpl.borrar(id);
        logger.debug("Se eliminó al paciente");
        return ResponseEntity.ok("El paciente con id: " + id + " fue eliminado.");
    }

}
