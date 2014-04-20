package com.agenda.web.java.persistencia;

import com.agenda.web.java.entidades.Contacto;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author julio
 */
public class ContactoDao implements java.io.Serializable {

    public ContactoDao() {
    }

    public static Boolean guardar(Contacto contacto) {

        Boolean guardado = true;

        if (contacto == null) {
            System.out.println("contacto vacio a añadir");
            guardado = false;
            return guardado;
        }

        // Abrimos la sesión y la transacción con la Base de Datos.
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            //Guardamos el contacto.
            session.save(contacto);

            //actualizamos los datos en la bbdd
            tx.commit();
        } catch (Exception e) {
            guardado = false;
            System.out.println(e.getMessage());
            if (tx != null) {
                tx.rollback();
            }
            //Cerramos la conexión con la bbdd
            session.close();
            return guardado;
        }
        //Cerramos la conexión con la bbdd
        session.close();

        return guardado;
    }

    /*   public List<Contacto> listar() {
     Session session = HibernateUtil.getSessionFactory().openSession();

     Query query = session.createQuery("from Contacto");
     List lista = query.list();

     return lista;
     }
     */
    public static Boolean eliminar(Contacto contactoEliminar) {

        Boolean eliminado = true;

        if (contactoEliminar == null) {
            eliminado = false;
            return eliminado;
        }

        // Abrimos la sesión y la transacción con la Base de Datos.
        Session session = HibernateUtil.getSessionFactory().openSession();

        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            // Obtenemos el contacto con el nombre y teléfono especificado.
            Query query = session.createQuery("from Contacto where "
                    + "nombre = :nombre AND telefono = :telefono ");
            query.setParameter("nombre", contactoEliminar.getNombre());
            query.setParameter("telefono", contactoEliminar.getTelefono());
            Contacto contacto = (Contacto) query.list().get(0);

            //Borramos el contacto.
            session.delete(contacto);

            //actualizamos los datos en la bbdd
            tx.commit();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            eliminado = false;
            if (tx != null) {
                tx.rollback();
            }
            //Cerramos la conexión con la bbdd
            session.close();
            return eliminado;
        }

        //Cerramos la conexión con la bbdd
        session.close();

        return eliminado;

    }
}
