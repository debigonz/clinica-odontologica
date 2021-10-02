package com.dh.clinica.service.impl;

import com.dh.clinica.dto.DomicilioDto;
import com.dh.clinica.dto.OdontologoDto;
import com.dh.clinica.dto.PacienteDto;
import com.dh.clinica.exceptions.ResourceBadRequestException;
import com.dh.clinica.exceptions.ResourceNotFoundException;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
public class ServicePacienteDtoImplTest {

    @Autowired
    private ServicePacienteDtoImpl servicePacienteDto;

    public void cargarDataSet () throws ResourceBadRequestException {
        DomicilioDto domicilio = new DomicilioDto("Av Santa fe", "444", "CABA", "Buenos Aires");
        PacienteDto p = servicePacienteDto.guardar(new PacienteDto(1,"Santiago", "Paz", "88888888", LocalDate.now(), domicilio));
        DomicilioDto domicilio1 = new DomicilioDto("Av Avellaneda", "333", "CABA", "Buenos Aires");
        PacienteDto p1 = servicePacienteDto.guardar(new PacienteDto(1,"Micaela", "Perez", "99999999", LocalDate.now(), domicilio1));
    }


    @Test
    public void tieneQueGuardarUnPaciente() throws ResourceBadRequestException {
        cargarDataSet();
        DomicilioDto domicilio2 = new DomicilioDto("San Martin", "555", "Ciudad", "Buenos Aires");
        PacienteDto paciente2 = new PacienteDto(3, "Ana", "Perez", "33005285", LocalDate.now(), domicilio2);
        servicePacienteDto.guardar(paciente2);
        Assertions.assertTrue(paciente2.getId() != null);
    }

    @Test
    public void comprobarQueListaTodosOdontologos() throws ResourceBadRequestException{
        //Dado
        cargarDataSet();
        List<PacienteDto> listaPacientes = new ArrayList<>();
        Integer respuestaEsperada = 2;
        //Cuando
        Integer respuestaObtenida = servicePacienteDto.buscarTodos().size();
        //Entonces
        Assertions.assertEquals(respuestaEsperada, respuestaObtenida);
    }

    @Test
    @Transactional
    public void Test03Borrar() throws ResourceNotFoundException,ResourceBadRequestException {
        cargarDataSet();
        List<PacienteDto> listaOdontologos = new ArrayList<>();
        servicePacienteDto.borrar(1);
        Integer respuestaEsperada = 0;
        //Cuando
        Integer respuestaObtenida = servicePacienteDto.buscarTodos().size();
        //Entonces
        Assertions.assertEquals(respuestaEsperada, respuestaObtenida);

    }

}