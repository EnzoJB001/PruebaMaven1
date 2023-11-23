package com.mycompany.pruebamaven1;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
// Defines the name of the table created for the entity
@Table(name = "Factura")
public class Factura implements Serializable {

	@Id
	
	@Column(name = "cod_factura", unique = true)
	private int cod_factura;
        
        @Column(name = "monto_total_factura", nullable = false)
        private int monto_total;
        
    @ManyToOne(cascade= CascadeType.ALL)
        @JoinColumn(name = "id_cliente_factura")
    private Cliente cli;

    public Cliente getCli() {
        return cli;
    }

    public void setCli(Cliente cli) {
        this.cli = cli;
    }

    public int getMonto_total() {
        return monto_total;
    }

    public void setMonto_total(int monto_total) {
        this.monto_total = monto_total;
    }
	
	@Column(name = "desc_factura", nullable = false)
    private String descripcion_factura;
    public int getCod_factura() {
        return cod_factura;
    }

    public void setCod_factura(int cod_factura) {
        this.cod_factura = cod_factura;
    }

    public String getDescripcion_factura() {
        return descripcion_factura;
    }

    public void setDescripcion_factura(String descripcion_factura) {
        this.descripcion_factura = descripcion_factura;
    }
    
    
   
}
