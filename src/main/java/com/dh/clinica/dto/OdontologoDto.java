package com.dh.clinica.dto;

import com.dh.clinica.persistence.entities.Odontologo;
import com.dh.clinica.persistence.entities.Paciente;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OdontologoDto {
    private Integer id;
    private String nombre;
    private String apellido;
    private String matricula;

    public OdontologoDto() {
    }

    public OdontologoDto(Integer id, String nombre, String apellido, String matricula) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.matricula = matricula;
    }

    public OdontologoDto(Odontologo o) {
        this.id = o.getId();
        this.nombre = o.getNombre();
        this.apellido = o.getApellido();
        this.matricula = o.getMatricula();
    }

   public Odontologo toEntity(){
        Odontologo entity = new Odontologo();

        entity.setId(id);
        entity.setNombre(nombre);
        entity.setApellido(apellido);
        entity.setMatricula(matricula);

        return entity;
    }

    @Override
    public String toString() {
        return "OdontologoDto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", matricula='" + matricula + '\'' +
                '}';
    }
}
