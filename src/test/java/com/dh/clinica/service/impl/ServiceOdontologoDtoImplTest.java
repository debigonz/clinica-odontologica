package com.dh.clinica.service.impl;

import com.dh.clinica.dto.OdontologoDto;
import com.dh.clinica.exceptions.ResourceBadRequestException;
import com.dh.clinica.exceptions.ResourceNotFoundException;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import javax.transaction.Transactional;
import java.util.List;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceOdontologoDtoImplTest {

    @Autowired
    private ServiceOdontologoDtoImpl serviceOdontologoDto;

    private OdontologoDto odontologo;


    public void cargarDataSet () throws ResourceBadRequestException{
        this.serviceOdontologoDto.guardar(new OdontologoDto(1,"Juan", "Perez", "123456"));
    }


    @Test
    public void Test01Guardar() throws ResourceBadRequestException {
        OdontologoDto odontologo1 = new OdontologoDto();
        odontologo1.setId(2);
        odontologo1.setNombre("Maria");
        odontologo1.setApellido("Guzman");
        odontologo1.setMatricula("987654");
        OdontologoDto odontologo = serviceOdontologoDto.guardar(odontologo1);
        Assertions.assertTrue(odontologo.getId() != null);
    }

    @Test
    public void Test02Listar() throws ResourceBadRequestException{
        //Dado
        cargarDataSet();
        List<OdontologoDto> listaOdontologos = new ArrayList<>();
        Integer respuestaEsperada = 1;
        //Cuando
        Integer respuestaObtenida = serviceOdontologoDto.buscarTodos().size();
        //Entonces
        Assertions.assertEquals(respuestaEsperada, respuestaObtenida);
    }


    @Test
    @Transactional
    public void Test03Borrar() throws ResourceNotFoundException,ResourceBadRequestException {
        cargarDataSet();
        List<OdontologoDto> listaOdontologos = new ArrayList<>();
        serviceOdontologoDto.borrar(1);
        Integer respuestaEsperada = 0;
        //Cuando
        Integer respuestaObtenida = serviceOdontologoDto.buscarTodos().size();
        //Entonces
        Assertions.assertEquals(respuestaEsperada, respuestaObtenida);
        //cargarDataSet();
        //serviceOdontologoDto.borrar(1);
        //Assert.assertNotNull(serviceOdontologoDto.borrar(1));
        //Assert.assertEquals(null, serviceOdontologoDto.borrar(1));
        //cargarDataSet();
        //String respuestaEsperada = "Eliminado con éxito";
        //String respuesta = serviceOdontologoDto.eliminar(1);
        //Assert.assertEquals(respuestaEsperada, respuesta);
    }






}