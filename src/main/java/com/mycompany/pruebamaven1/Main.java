package com.mycompany.pruebamaven1;

import org.hibernate.Session;

import java.util.List;

public class Main {
    private static void ordenesPorFecha(String fechaMin, String fechaMax) {
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

    private static void insertarFactura(String descripcion, Long costo, String fecha, String estado, Cliente cliente, Long id_categoria, Long id_tecnico) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Categoria categoria = session.createQuery("Select a from Categoria a where id_categoria='" + id_categoria + "'", Categoria.class).list().get(0);
        Tecnico tecnico = session.createQuery("Select a from Tecnico a where id_tecnico='" + id_tecnico + "'", Tecnico.class).list().get(0);
        Orden orden = new Orden(descripcion, costo, fecha, estado, cliente, categoria, tecnico);
        session.saveOrUpdate(orden);
        System.out.printf("\nFactura N°%s\nCliente: %s\nTécnico: %s\nFecha: %s\n\n", orden.getId_orden() , orden.getCliente().getNombre_cliente(), orden.getTecnico().getNombreApellido(), orden.getFecha_orden());
        session.getTransaction().commit();
    }

    private static Cliente validarCliente(Long dni){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Cliente cliente;
        try{
            cliente = session.createQuery("Select a from Cliente a where dni='" + dni + "'", Cliente.class).list().get(0);
        }
        catch (Exception e){
            cliente = new Cliente(dni, "Nombre", "Direccion", "Mail");
        }
        session.getTransaction().commit();
        return cliente;
    }

    public static void main(String[] args) {
        ordenesPorFecha("1900/10/19", "2010/10/21");
        insertarFactura("Gameboy sin pilas", 5000L, "1999/12/31", "Terminado", validarCliente(2L), 2L, 2L);
        HibernateUtil.getSessionFactory().close();
    }
}
