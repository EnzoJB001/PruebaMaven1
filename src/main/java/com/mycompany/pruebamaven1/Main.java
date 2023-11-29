package com.mycompany.pruebamaven1;

import org.hibernate.Session;

import java.util.List;
import java.util.Scanner;

public class Main {
    private void ordenesPorFecha(String fechaMin, String fechaMax) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Orden> result = session.createQuery("select a from Orden a", Orden.class).stream().filter(fecha -> fecha.getFecha_orden().compareTo(fechaMax) <= 0 && fecha.getFecha_orden().compareTo(fechaMin) >= 0).toList();
        session.getTransaction().commit();
        if (result.isEmpty()){
            System.out.println("\nNo hay facturas en ese tramo de tiempo\n");
        }
        else{
            for (Orden evento : result) {
                System.out.printf("\nFactura N°%s\nCliente: %s\nTécnico: %s\nFecha: %s\n", evento.getId_orden() , evento.getCliente().getNombre_cliente(), evento.getTecnico().getNombreApellido(), evento.getFecha_orden());
            }
        }
        System.out.println("\nFinalizado\n");
    }

    private void crearOrden(String descripcion, Long costo, String fecha, String estado, Cliente cliente, Long id_categoria, Long id_tecnico) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Categoria categoria = session.createQuery("Select a from Categoria a where id_categoria='" + id_categoria + "'", Categoria.class).list().get(0);
        Tecnico tecnico = session.createQuery("Select a from Tecnico a where id_tecnico='" + id_tecnico + "'", Tecnico.class).list().get(0);
        Orden orden = new Orden(descripcion, costo, fecha, estado, cliente, categoria, tecnico);
        session.saveOrUpdate(orden);
        System.out.printf("\nFactura N°%s\nCliente: %s\nTécnico: %s\nFecha: %s\n\n", orden.getId_orden() , orden.getCliente().getNombre_cliente(), orden.getTecnico().getNombreApellido(), orden.getFecha_orden());
        session.getTransaction().commit();
    }

    private Cliente validarCliente(Long dni){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Cliente cliente;
        try{
            cliente = session.createQuery("Select a from Cliente a where dni='" + dni + "'", Cliente.class).list().get(0);
        }
        catch (Exception e){
            Scanner in = new Scanner(System.in);
            System.out.println("Por favor ingrese nombre y apellido:");
            String nombre = in.nextLine();
            System.out.println("Por favor ingrese su direccion:");
            String direccion = in.nextLine();
            System.out.println("Por favor ingrese email:");
            String mail = in.nextLine();
            cliente = new Cliente(dni, nombre, direccion, mail);
        }
        session.getTransaction().commit();
        System.out.println(cliente.getNombre_cliente());
        return cliente;
        
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.ordenesPorFecha("1900/10/19", "2012/10/21");
        main.crearOrden("Monitor no funciona a 144hz", 7000L, "2011/1/21", "Terminado", main.validarCliente(44978481L), 1L, 1L);
        HibernateUtil.getSessionFactory().close();        
    }
}
