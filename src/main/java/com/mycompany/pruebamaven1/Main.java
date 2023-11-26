package com.mycompany.pruebamaven1;

import org.hibernate.Session;

import java.util.List;

public class Main {
    private static void listEvents(String fechaMin, String fechaMax) {
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


    //Estos son unos overload de la misma funcion, como para si ya tenes una de las cosas creadas
    //Era nada mas de prueba, despues lo voy a cambiar para que funcione mejor
    //Desde ya todavia no funciona para guardar las cosas

    private static void insertarFacturaCliente(Long id, String descripcion, Long costo, String fecha, String estado, Cliente cliente, Categoria categoria, Tecnico tecnico) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(new Orden(id, descripcion, costo, fecha, estado, cliente, categoria, tecnico));
        session.getTransaction().commit();
        HibernateUtil.getSessionFactory().close();
    }

    private static void insertarFacturaCliente(Long id, String descripcion, Long costo, String fecha, String estado, Long dni, Categoria categoria, Tecnico tecnico) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Cliente cliente = session.createQuery("Select a from Cliente a where dni='" + dni + "'", Cliente.class).stream().filter(t -> t.getDni() == dni).toList().get(0);
        session.save(new Orden(id, descripcion, costo, fecha, estado, cliente, categoria, tecnico));
        session.getTransaction().commit();
        HibernateUtil.getSessionFactory().close();
    }

    private static void insertarFacturaCliente(Long id, String descripcion, Long costo, String fecha, String estado, Cliente cliente, Long id_categoria, Tecnico tecnico) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Categoria categoria = session.createQuery("Select a from Categoria a where id_categoria='" + id_categoria + "'", Categoria.class).stream().filter(c -> c.getId_categoria() == id_categoria).toList().get(0);
        Orden orden = new Orden(id, descripcion, costo, fecha, estado, cliente, categoria, tecnico);
        session.save(orden);
        session.getTransaction().commit();
        HibernateUtil.getSessionFactory().close();
    }

    private static void insertarFacturaCliente(Long id, String descripcion, Long costo, String fecha, String estado, Cliente cliente, Categoria categoria, Long id_tecnico) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Tecnico tecnico = session.createQuery("Select a from Tecnico a where id_tecnico='" + id_tecnico + "'", Tecnico.class).stream().filter(t -> t.getId_tecnico() == id_tecnico).toList().get(0);
        session.saveOrUpdate(new Orden(id, descripcion, costo, fecha, estado, cliente, categoria, tecnico));
        session.getTransaction().commit();
        HibernateUtil.getSessionFactory().close();
    }

    public static void main(String[] args) {
        //Esto es lo Que estaba corriendo para probar
        listEvents("2010/10/19", "2010/10/21");
        Cliente cliente = new Cliente(2L, "Samus Aran");
        Categoria categoria = new Categoria(2L, "Vintage");
        insertarFacturaCliente(2L, "Gameboy sin pilas", 5000L, "1999/12/31", "Terminado", cliente, categoria, 1L);
        HibernateUtil.getSessionFactory().close();
    }
}
