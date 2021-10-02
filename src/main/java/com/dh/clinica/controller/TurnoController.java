package com.dh.clinica.controller;

import com.dh.clinica.dto.TurnoDto;
import com.dh.clinica.exceptions.ResourceBadRequestException;
import com.dh.clinica.exceptions.ResourceNotFoundException;
import com.dh.clinica.persistence.entities.Turno;
import com.dh.clinica.service.IService;
import com.dh.clinica.service.impl.ServiceTurnoDtoImpl;
import org.apache.log4j.Logger;
import org.h2.server.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/turnos")
public class TurnoController {

    private static final Logger logger = Logger.getLogger(Service.class);

    @Autowired
    IService<TurnoDto> ServiceTurnoDtoImpl;

    @Autowired
    ServiceTurnoDtoImpl serviceTurnoDto;

    @GetMapping("/todos")
    public ResponseEntity<List<TurnoDto>> buscarTodos() {
        return ResponseEntity.ok(ServiceTurnoDtoImpl.buscarTodos());
    }

    @GetMapping(params = {"anio", "mes", "dia", "cantdias"})
    public ResponseEntity<List<TurnoDto>> buscarProximosTurnos(@RequestParam int anio, @RequestParam int mes, @RequestParam int dia, @RequestParam int cantdias) {
        LocalDate fecha = LocalDate.of(anio, mes, dia);
        return ResponseEntity.ok(serviceTurnoDto.buscarTurnosProximos(fecha, cantdias));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable Integer id) throws ResourceNotFoundException {
        TurnoDto turno = ServiceTurnoDtoImpl.buscar(id);
        logger.debug("Buscando al turno con id: " + id);
        return ResponseEntity.ok(turno);
    }

    @PostMapping("/nuevo")
    public ResponseEntity<TurnoDto> crearNuevoTurno(@RequestBody TurnoDto turno) throws ResourceBadRequestException {
        logger.debug("Operación exitosa de creado de turno");
        return ResponseEntity.ok(ServiceTurnoDtoImpl.guardar(turno));
    }

    @PutMapping("/actualizar")
    public ResponseEntity<TurnoDto> actualizarTurno(@RequestBody TurnoDto turno) throws ResourceNotFoundException{
        logger.debug("Se ha actualizado correctamente el turno");
        return ResponseEntity.ok(ServiceTurnoDtoImpl.actualizar(turno));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> borrar(@PathVariable Integer id) throws ResourceNotFoundException{
        ServiceTurnoDtoImpl.borrar(id);
        logger.debug("Se eliminó al turno");
        return ResponseEntity.ok("El turno con id: " + id + " fue eliminado.");
    }
}
