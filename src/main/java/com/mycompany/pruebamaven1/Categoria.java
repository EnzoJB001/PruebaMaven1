package com.mycompany.pruebamaven1;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Categoria")
public class Categoria implements Serializable {

    public Categoria(Long id_categoria, String descripcion) {
        this.id_categoria = id_categoria;
        this.descripcion = descripcion;
    }
    public Categoria() {}

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id_categoria", unique = true)
    private Long id_categoria;

    @Column(name = "descripcion_categoria", nullable = false)
    private String descripcion;

    public Long getId_categoria() {return id_categoria;}
    public String getDescripcion() {return descripcion;}
    public void setDescripcion(String descripcion) {this.descripcion = descripcion;}
}
