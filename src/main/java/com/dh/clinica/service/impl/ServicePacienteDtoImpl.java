package com.dh.clinica.service.impl;

import com.dh.clinica.dto.OdontologoDto;
import com.dh.clinica.dto.PacienteDto;
import com.dh.clinica.exceptions.ResourceBadRequestException;
import com.dh.clinica.persistence.entities.Odontologo;
import com.dh.clinica.persistence.entities.Paciente;
import com.dh.clinica.service.IService;
import com.dh.clinica.persistence.repositories.PacienteRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ServicePacienteDtoImpl implements IService<PacienteDto> {

    @Autowired
    PacienteRepository repository;


    @Override
    public PacienteDto guardar(PacienteDto p) throws ResourceBadRequestException {
        //PacienteDto response;
        if(p != null) {
            //Paciente paciente = repository.save(p.toEntity());
            //response = new PacienteDto(paciente);

            p.setFechaIngreso(LocalDate.now());
            p.setDomicilio(p.getDomicilio());
            p.setId(repository.save(p.toEntity()).getId());

        } else {
            throw new ResourceBadRequestException("No se puede guardar el paciente en null");
        }
        return p;
    }

    public PacienteDto buscar(Integer id) throws ResourceNotFoundException {
        PacienteDto response = null;
        if(id == null)
            throw new ResourceNotFoundException("No existe el paciente con id: " + id);
        else {
            response = new PacienteDto(repository.getById(id)); //En la capa de servicio y superiores usamos un DTO para transferir datos.
        }
        return response;
    }

    public List<PacienteDto> buscarTodos() {
        List<PacienteDto> pacientes = new ArrayList<>();

        for(Paciente p : repository.findAll()){
            pacientes.add(new PacienteDto(p));
        }

        return pacientes;
    }


    @Override
    public PacienteDto actualizar(PacienteDto p) throws ResourceNotFoundException {
        if(buscar(p.getId()) == null)
            throw new ResourceNotFoundException("No existe el paciente con id: " + p.getId());
        repository.save(p.toEntity());
        return p;
    }

    @Override
    public void borrar(Integer id) throws ResourceNotFoundException {
        if(buscar(id) == null)
            throw new ResourceNotFoundException("No existe el paciente con id: " + id);
        else{
            repository.deleteById(id);
        }

    }

}
