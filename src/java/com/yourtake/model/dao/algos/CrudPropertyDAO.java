/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yourtake.model.dao.algos;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.metadata.ClassMetadata;
import org.springframework.stereotype.Repository;

/**
 *
 * @author MumbaiZone
 * @param <T>
 */
@Repository
public class CrudPropertyDAO<T> implements CrudPropertyDAOInterface<Object,Object,SessionFactory,Class<T>>{
    
    
   private Class<T> type;
   private SessionFactory sessionFactory;
   
  

    @Override
    public Object buildEntity(Object entity) {
        T obj = (T) entity;
         Session session = getOperator().getCurrentSession();
         session.saveOrUpdate(obj);
         return (T) session.get(getMyType(),session.getSessionFactory().getClassMetadata(getMyType()).getIdentifier(obj, (SessionImplementor)session) );
    }

    @Override
    public Object readProperty(Object paramId) {
            Session session = getOperator().getCurrentSession();
            return (T) session.get(getMyType(), (Serializable) paramId);
            
    }

    @Override
    public boolean updateProperty(Object entity) {
        T obj = (T) entity;
        Session session = getOperator().getCurrentSession();
        obj = (T) session.merge(obj);
        session.update(obj);
        return true;
    }

    @Override
    public boolean deleteEntity(Object entity) {
             T obj = (T) entity;
            Session session = getOperator().getCurrentSession();
            obj = (T) session.get(getMyType(), session.getSessionFactory().getClassMetadata(getMyType()).getIdentifier(obj, (SessionImplementor)session));
            session.delete(obj);
            return true;
    }
    
   @Override
   public Class<T> getMyType() {
         return this.type;
     }
   
    @Override
   public void setMyType(Class<T> type) {
          this.type = type;
     }
   
  
   @Override
    public void setOperator(SessionFactory operator){
        this.sessionFactory=operator;
    }

    @Override
    public SessionFactory getOperator() {
        return sessionFactory;
    }

    
    @Override
    public Object readPropertyEagerly(Object paramId) {
        
            Session session = getOperator().getCurrentSession();
            Object obj =   session.get(getMyType(), (Serializable) paramId);
            ClassMetadata classMetadata=session.getSessionFactory().getClassMetadata(getMyType());
            System.out.println(Arrays.toString(classMetadata.getPropertyValues((T)obj)));
                for(Object objInner:classMetadata.getPropertyValues((T)obj)){
                   if(objInner instanceof List){
                       List list=(List) objInner;
                       list.size();
                   }
                }
            return (T)obj;
    }


}
