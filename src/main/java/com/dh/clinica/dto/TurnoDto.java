package com.dh.clinica.dto;

import com.dh.clinica.persistence.entities.Domicilio;
import com.dh.clinica.persistence.entities.Paciente;
import com.dh.clinica.persistence.entities.Turno;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Set;

@Getter @Setter
public class TurnoDto {

    private Integer id;
    private LocalDate fecha;
    private LocalTime hora;
    private String anotaciones;
    private PacienteDto paciente;
    private OdontologoDto odontologo;

    public TurnoDto() {
    }

    public TurnoDto(Integer id, LocalDate fecha, LocalTime hora, String anotaciones, PacienteDto paciente, OdontologoDto odontologo) {
        this.id = id;
        this.fecha = fecha;
        this.hora = hora;
        this.anotaciones = anotaciones;
        this.paciente = paciente;
        this.odontologo = odontologo;
    }

    public TurnoDto(Turno t) {
        id = t.getId();
        fecha = t.getFecha();
        hora = t.getHora();
        anotaciones = t.getAnotaciones();
        paciente = new PacienteDto(t.getPaciente());
        odontologo = new OdontologoDto(t.getOdontologo());
    }

    public Turno toEntity(){
        Turno entity = new Turno();

        entity.setId(id);
        entity.setFecha(fecha);
        entity.setHora(hora);
        entity.setAnotaciones(anotaciones);
        entity.setPaciente(paciente.toEntity());
        entity.setOdontologo(odontologo.toEntity());

        return entity;

    }

    @Override
    public String toString() {
        return "TurnoDto{" +
                "id=" + id +
                ", fecha=" + fecha +
                ", hora=" + hora +
                ", anotaciones='" + anotaciones + '\'' +
                ", paciente=" + paciente +
                ", odontologo=" + odontologo +
                '}';
    }
}
