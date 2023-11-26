package com.mycompany.pruebamaven1;


import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
// Defines the name of the table created for the entity
@Table(name = "Cliente")
public class Cliente implements Serializable {
    public Cliente(Long dni, String nombre_cliente, String direccion, String mail) {
        this.dni = dni;
        this.nombre_cliente = nombre_cliente;
        this.direccion = direccion;
        this.mail = mail;
    }
    public Cliente(Long dni) {
        this.dni = dni;
    }
    public Cliente() {
    }

    @Id
    @Column(name = "dni", unique = true)
    private Long dni;

    @Column(name = "nombre", nullable = false)
    private String nombre_cliente;

    @Column(name = "direccion", nullable = false)
    private String direccion;

    @Column(name = "mail", nullable = false)
    private String mail;

    public Long getDni() {
        return dni;
    }
    public void setDni(Long dni) {
        this.dni = dni;
    }
    public String getNombre_cliente() {
        return nombre_cliente;
    }
    public void setNombre_cliente(String nombre_cliente) {
        this.nombre_cliente = nombre_cliente;
    }
    public String getDireccion() {return direccion;}
    public void setDireccion(String direccion) {this.direccion = direccion;}
    public String getMail() {return mail;}
    public void setMail(String mail) {this.mail = mail;}
}
