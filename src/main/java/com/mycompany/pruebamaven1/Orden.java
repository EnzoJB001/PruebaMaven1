package com.mycompany.pruebamaven1;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "orden")
public class Orden implements Serializable {
    @Id
    @Column(name = "id_orden", unique = true)
    private Long id_orden;

    @Column(name = "descripcion_orden", nullable = false)
    private String descripcion;

    @Column(name = "costo", nullable = false)
    private Long costo;

    @Column(name = "fecha_orden", nullable = false)
    private String fecha_orden;

    @Column(name = "estado", nullable = false)
    private String estado;

    @ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "cliente")
    private Cliente cliente;

    @ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "tecnico")
    private Tecnico tecnico;

    @ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "categoria")
    private Categoria categoria;

    public Orden(Long id, String descripcion, Long costo, String fecha, String estado, Cliente cliente, Categoria categoria, Tecnico tecnico) {
        this.id_orden = id;
        this.descripcion = descripcion;
        this.costo = costo;
        this.fecha_orden = fecha;
        this.estado = estado;
        this.cliente = cliente;
        this.categoria = categoria;
        this.tecnico = tecnico;
    }
    public Orden(){}
    public Long getId_orden() {
        return id_orden;
    }
    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    public Tecnico getTecnico() {
        return tecnico;
    }
    public void setTecnico(Tecnico tecnico) {
        this.tecnico = tecnico;
    }
    public Categoria getCategoria() {
        return categoria;
    }
    public void setCategoria(Categoria categoria) {this.categoria = categoria;}
    public String getFecha_orden() {
        return fecha_orden;
    }
    public void setFecha_orden(String fecha_orden) {this.fecha_orden = fecha_orden;}
    public String getEstado() {return estado;}
    public void setEstado(String estado) {this.estado = estado;}
    public Long getCosto() {return costo;}
    public void setCosto(Long costo) {this.costo = costo;}
}