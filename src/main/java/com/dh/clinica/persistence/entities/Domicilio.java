package com.dh.clinica.persistence.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
@Getter @Setter
public class Domicilio implements Serializable {

    @Id
    @SequenceGenerator(name = "domicilio_sequence", sequenceName = "domicilio_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "domicilio_sequence")
    private Integer id;
    private String calle;
    private String numero;
    private String localidad;
    private String provincia;

    @OneToMany(mappedBy = "domicilio")
    private Set<Paciente> pacientes = new HashSet<>();

    public Domicilio() {
    }

    public Domicilio(String calle, String numero, String localidad, String provincia, Set<Paciente> pacientes) {
        this.calle = calle;
        this.numero = numero;
        this.localidad = localidad;
        this.provincia = provincia;
        this.pacientes = pacientes;
    }


    @Override
    public String toString() {
        return "Domicilio{" +
                "id=" + id +
                ", calle='" + calle + '\'' +
                ", numero='" + numero + '\'' +
                ", localidad='" + localidad + '\'' +
                ", provincia='" + provincia + '\'' +
                '}';
    }
}
