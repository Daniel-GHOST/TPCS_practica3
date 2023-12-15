/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.uv.TPCS_Practica03.dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.uv.TPCS_Practica03.domain.Producto;
import org.uv.TPCS_Practica03.hibernate.HibernateUtil;

/**
 *
 * @author miran
 */
public class DAOProducto implements IDAOGeneral<Producto, Long>{

    @Override
    public Producto crear(Producto t) {
        Session session=HibernateUtil.getSession();
        Transaction transaction=session.beginTransaction();
        
        session.save(t);
        
        transaction.commit();
        session.close();
        
        return t;
    }

    @Override
    public boolean eliminar(Long id) {
        boolean pase=false;
        Session session=HibernateUtil.getSession();
        Transaction t=session.beginTransaction();
        
        Producto producto=session.get(Producto.class, id);
        if (producto!=null){
            session.delete(producto);
            t.commit();
            pase=true;
        }
        
        session.close();
        return pase;
    }

    @Override
    public Producto actualizar(Producto t, Long id) {
        Session session=HibernateUtil.getSession();
        Transaction transaction=session.beginTransaction();
        
        Producto pro=session.get(Producto.class, id);
        if(pro!=null){
            session.update(t);
            transaction.commit();
        }
        
        session.close();
        return t;
    }

    @Override
    public List<Producto> buscar() {
        Session session=HibernateUtil.getSession();
        Transaction transaction=session.beginTransaction();
        
        List<Producto> productos=session.createQuery("From Producto e order by e.productoId").list();
        
        transaction.commit();
        session.close();
        
        return productos;
    }

    @Override
    public Producto buscarUno(Long id) {
        Session session=HibernateUtil.getSession();
        Producto p=session.get(Producto.class, id);
        session.close();
        return p;
    }
    
}
