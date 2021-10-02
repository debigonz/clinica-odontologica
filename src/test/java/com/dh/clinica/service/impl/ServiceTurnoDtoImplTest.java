package com.dh.clinica.service.impl;

import com.dh.clinica.dto.DomicilioDto;
import com.dh.clinica.dto.OdontologoDto;
import com.dh.clinica.dto.PacienteDto;
import com.dh.clinica.dto.TurnoDto;
import com.dh.clinica.exceptions.ResourceBadRequestException;
import com.dh.clinica.exceptions.ResourceNotFoundException;
import com.dh.clinica.persistence.entities.Turno;
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
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceTurnoDtoImplTest {

    @Autowired
    private ServiceTurnoDtoImpl serviceTurnoDto;

    @Autowired
    private ServicePacienteDtoImpl servicePacienteDto;

    @Autowired
    private ServiceOdontologoDtoImpl serviceOdontologoDto;

    private TurnoDto turno;
    private OdontologoDto odontologo;
    private PacienteDto paciente;


    public void cargarDataSet () throws ResourceBadRequestException {
        DomicilioDto domicilio = new DomicilioDto("Av Santa fe", "444", "CABA", "Buenos Aires");
        PacienteDto p = servicePacienteDto.guardar(new PacienteDto(1,"Santiago", "Paz", "88888888", LocalDate.now(), domicilio));
        OdontologoDto o = serviceOdontologoDto.guardar(new OdontologoDto(1,"Juan", "Perez", "123456"));
        this.serviceTurnoDto.guardar(new TurnoDto(1,LocalDate.of(2021,10, 10), LocalTime.of(10,15, 0), "No hay observaciones", p, o));
    }


    @Test
    @Transactional
    public void Test01Guardar() throws ResourceBadRequestException {
        DomicilioDto domicilio = new DomicilioDto("Av Santa fe", "444", "CABA", "Buenos Aires");
        PacienteDto p = servicePacienteDto.guardar(new PacienteDto(2,"Maria", "Paz", "88888888", LocalDate.now(), domicilio));
        OdontologoDto o = serviceOdontologoDto.guardar(new OdontologoDto(2,"Maria", "Perez", "123456"));
        TurnoDto turno1 = new TurnoDto();
        turno1.setId(2);
        turno1.setFecha(LocalDate.of(2021, 10, 20));
        turno1.setHora(LocalTime.of(10,30,0));
        turno1.setAnotaciones("Nada");
        turno1.setPaciente(p);
        turno1.setOdontologo(o);
        TurnoDto turno = serviceTurnoDto.guardar(turno1);
        Assertions.assertTrue(turno.getId() != null);
    }

    @Test
    @Transactional
    public void Test02Listar() throws ResourceBadRequestException{
        //Dado
        cargarDataSet();
        List<TurnoDto> listaTurnos = new ArrayList<>();
        Integer respuestaEsperada = 1;
        //Cuando
        Integer respuestaObtenida = serviceTurnoDto.buscarTodos().size();
        //Entonces
        Assertions.assertEquals(respuestaEsperada, respuestaObtenida);
    }


    @Test
    @Transactional
    public void Test03Borrar() throws ResourceNotFoundException,ResourceBadRequestException {
        cargarDataSet();
        List<OdontologoDto> listaOdontologos = new ArrayList<>();
        serviceTurnoDto.borrar(1);
        Integer respuestaEsperada = 0;
        //Cuando
        Integer respuestaObtenida = serviceTurnoDto.buscarTodos().size();
        //Entonces
        Assertions.assertEquals(respuestaEsperada, respuestaObtenida);

    }


}