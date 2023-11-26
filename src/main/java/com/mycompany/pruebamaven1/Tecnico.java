package com.mycompany.pruebamaven1;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Tecnico")
public class Tecnico implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id_tecnico", unique = true)
    private Long id_tecnico;
    @Column(name = "nombreApellido", nullable = false)
    private String nombreApellido;

    public Tecnico() {}

    public Tecnico(Long id) {this.id_tecnico = id;}
    public Long getId_tecnico() {return id_tecnico;}
    public String getNombreApellido() {return nombreApellido;}
    public void setNombreApellido(String nombreApellido) {this.nombreApellido = nombreApellido;}
}
