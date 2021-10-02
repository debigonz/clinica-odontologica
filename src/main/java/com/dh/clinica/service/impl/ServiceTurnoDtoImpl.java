package com.dh.clinica.service.impl;

import com.dh.clinica.dto.TurnoDto;
import com.dh.clinica.exceptions.ResourceBadRequestException;
import com.dh.clinica.exceptions.ResourceNotFoundException;
import com.dh.clinica.persistence.entities.Turno;
import com.dh.clinica.persistence.repositories.TurnoRepository;
import com.dh.clinica.service.IService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceTurnoDtoImpl implements IService<TurnoDto> {

    @Autowired
    TurnoRepository repository;

    @Autowired
    ServicePacienteDtoImpl pacienteService;

    @Autowired
    ServiceOdontologoDtoImpl odontologoService;
    private LocalTime horaApertura = LocalTime.of(8,0,0);
    private LocalTime horaCierre = LocalTime.of(22,0, 0);

    @Override
    public TurnoDto guardar(TurnoDto t) throws ResourceBadRequestException {
        boolean elPacienteExiste = ((pacienteService.buscar(t.getPaciente().getId())) != null);
        boolean elOdontologoExiste = ((odontologoService.buscar(t.getOdontologo().getId())) != null);
        boolean fechaValida = (LocalDate.now().isBefore(t.getFecha()));
        boolean horaValida= (t.getHora().isBefore(horaCierre) && t.getHora().isAfter(horaApertura));

        if(elPacienteExiste && elOdontologoExiste && fechaValida && horaValida){
            //if(verificarTurno(t.getOdontologo().getId(),t.getPaciente().getId(),t.getFecha(),t.getHora()));
            Turno turno = repository.save(t.toEntity());
            t.setId(turno.getId());
        } else {
            throw new ResourceBadRequestException("No se puede guardar el turno en null");
        }

        return t;
    }

    //public List<TurnoDto> verificarTurno (Integer idO, Integer idP, LocalDate fecha, LocalTime hora){
    //    List<TurnoDto> turnos = new ArrayList<>();
    //    for(Turno t : repository.findAll()){
    //        if(t.getFecha().isEqual(fecha) && t.getHora().equals(hora)){
    //            if(t.getOdontologo().getId().equals(idO) && t.getPaciente().getId().equals(idP)){
    //               turnos.add(new TurnoDto(t));
    //            }
    //        }
    //    }
    //    return turnos;
    //}

    @Override
    public TurnoDto buscar(Integer id) throws ResourceNotFoundException {
        if(id == null)
            throw new ResourceNotFoundException("No existe el turno con id: " + id);
        return new TurnoDto(repository.getById(id));
    }

    @Override
    public List<TurnoDto> buscarTodos() {
        List<TurnoDto> turnos = new ArrayList<>();

        for(Turno t : repository.findAll()){
            turnos.add(new TurnoDto(t));
        }

        return turnos;
    }

    public List<TurnoDto> buscarTurnosProximos (LocalDate fecha, int dia) {
        List<TurnoDto> turnos = new ArrayList<>();
        LocalDate fechaLimite = fecha.plusDays(dia+1);

        for ( Turno t : repository.findAll()) {
            if((t.getFecha().isAfter(fecha) || t.getFecha().equals(fecha)) && t.getFecha().isBefore(fechaLimite))
            turnos.add(new TurnoDto(t));
        }

        return turnos;

    }

    @Override
    public TurnoDto actualizar(TurnoDto t) throws ResourceNotFoundException {
        if(buscar(t.getId()) == null)
            throw new ResourceNotFoundException("No existe el turno con id: " + t.getId());
        repository.save(t.toEntity());
        return t;
    }

    @Override
    public void borrar(Integer id) throws ResourceNotFoundException {
        if(buscar(id) == null)
            throw new ResourceNotFoundException("No existe el turno con id: " + id);
        repository.deleteById(id);
    }
}
