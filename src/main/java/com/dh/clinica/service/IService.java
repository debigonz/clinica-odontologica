package com.dh.clinica.service;

import com.dh.clinica.exceptions.ResourceBadRequestException;
import com.dh.clinica.exceptions.ResourceNotFoundException;

import java.util.List;


public interface IService<T> {
    T guardar (T t) throws ResourceBadRequestException;

    T buscar(Integer id) throws ResourceNotFoundException;

    List<T> buscarTodos();

    T actualizar(T t) throws ResourceNotFoundException;

    void borrar(Integer id) throws ResourceNotFoundException;
}
