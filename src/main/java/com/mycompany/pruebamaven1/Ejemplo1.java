package com.mycompany.pruebamaven1;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.hibernate.Session;

public class Ejemplo1 {
 
    public static void main(String[] args) {
    
        
        Ejemplo1 es=new Ejemplo1();
        
        es.ejemplo2();
    }

    public void ejemplo2() {
      //createAndStoreEvent("La Factura");
      //  listEvents();
        insertaFacturaCliente();
        HibernateUtil.getSessionFactory().close();
    }

    private void createAndStoreEvent(String title) {
        //Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        //session.beginTransaction();
        Factura miFactura = new Factura();
       // theEvent.setNum_factura();
        miFactura.setDescripcion_factura("factura 13");
        miFactura.setMonto_total(35);
        //session.save(miFactura);
        //session.getTransaction().commit();
        listEvents();
    }
    
      private void listEvents() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Factura> result = (List<Factura>)session.createQuery("from Factura").list();
        session.getTransaction().commit();
        for (Factura evento : result) {
            System.out.println(evento.getDescripcion_factura());
        }
    }
      
      private void insertaFacturaCliente() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Factura miFactura = new Factura();
        //theEvent.setNum_factura();
        miFactura.setDescripcion_factura("factura 55");
        miFactura.setMonto_total(55);
        Cliente lucas = new Cliente();
        lucas.setCod_cliente(4);
        lucas.setNombre_cliente("Lucas");
        miFactura.setCli(lucas);
        session.save(miFactura);
        session.getTransaction().commit();
      }
      
}