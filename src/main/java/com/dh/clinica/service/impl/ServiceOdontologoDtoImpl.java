package com.dh.clinica.service.impl;

import com.dh.clinica.dto.OdontologoDto;
import com.dh.clinica.exceptions.ResourceBadRequestException;
import com.dh.clinica.persistence.entities.Odontologo;
import com.dh.clinica.persistence.repositories.OdontologoRepository;
import com.dh.clinica.service.IService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceOdontologoDtoImpl implements IService<OdontologoDto> {

    @Autowired
    OdontologoRepository repository;

    @Autowired
    ObjectMapper mapper;

    @Override
    public OdontologoDto guardar(OdontologoDto o) throws ResourceBadRequestException {
        OdontologoDto response;
        if(o != null){
            Odontologo odontologo = repository.save(o.toEntity());
            response = new OdontologoDto(odontologo);
            //o.setId(repository.save(o.toEntity()).getId());
        } else {
            throw new ResourceBadRequestException("No se puede guardar el odontólogo en null");
        }

        return response;
    }

    @Override
    public OdontologoDto buscar(Integer id) throws ResourceNotFoundException {
        OdontologoDto response = null;

        if(id == null)
            throw new ResourceNotFoundException("No existe el odontólogo con id: " + id);
        else {
            response = new OdontologoDto(repository.getById(id));
        }
        return response;
    }

    @Override
    public List<OdontologoDto> buscarTodos() {
        List<OdontologoDto> odontologos = new ArrayList<>();

        for(Odontologo o : repository.findAll()){
            odontologos.add(new OdontologoDto(o));
        }

        return odontologos;
    }

    @Override
    public OdontologoDto actualizar(OdontologoDto o) throws ResourceNotFoundException {
        if(buscar(o.getId()) == null)
            throw new ResourceNotFoundException("No existe el odontólogo con id: " + o.getId());
        repository.save(o.toEntity());
        return o;
    }

    @Override
    public void borrar(Integer id) throws ResourceNotFoundException {
        if(buscar(id) == null)
            throw new ResourceNotFoundException("No existe el odontólogo con id: " + id);
        else {
            repository.deleteById(id);

        }

    }

    public String eliminar (Integer id) throws ResourceNotFoundException {
        String response = null;

        if(buscar(id) == null)
            throw new ResourceNotFoundException("No existe el odontólogo con id: " + id);
        else {
            repository.deleteById(id);
            response = "Eliminado con éxito";
        }
        return response;
    }
}
