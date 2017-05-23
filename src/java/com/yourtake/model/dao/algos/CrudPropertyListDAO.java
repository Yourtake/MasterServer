/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yourtake.model.dao.algos;


import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 *
 * @author MumbaiZone
 * @param <T>
 * @param <K>
 */
@Repository
public class CrudPropertyListDAO<T,K> implements CrudPropertyListDAOInterface<Object,Object,SessionFactory,Class<T>,Class<K>>{

  private Class<T> type;
   private SessionFactory sessionFactory;
   
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
    public List<Object> fetchAll(String ascOrder, String descOrder) {
            Session session = sessionFactory.getCurrentSession();
            Criteria criteria=session.createCriteria(getMyType());
            if(ascOrder!=null){
                    criteria.addOrder(Order.asc(ascOrder));
            }
            if(descOrder!=null){
                  criteria.addOrder(Order.asc(descOrder));
            }
            return criteria.list();
    }
    
    

    @Override
    public List<Object> fetchByUserDefined(Class<K> type, Object property,String descOrder,String ascOrder,MatchMode m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Object> fetchByNative(String type, Object property, String ascOrder, String descOrder,MatchMode m) {
            Session session = sessionFactory.getCurrentSession();
             Criteria criteria=session.createCriteria(getMyType());
            if(ascOrder!=null){
                    criteria.addOrder(Order.asc(ascOrder));
            }
            if(descOrder!=null){
                  criteria.addOrder(Order.asc(descOrder));
            }
            return criteria.add(Restrictions.ilike(type, (String) property, m))
                    .setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY).list();
    }

    @Override
    public boolean deleteAll() {
            Session session = sessionFactory.getCurrentSession();
            List<Object> list=session.createCriteria(getMyType()).list();
            for(Object obj:list){
                session.delete(obj);
            }
            return true;
    
    }

    @Override
    public List<Object> fetchByNativeFilterByGreaterThanOrEqual(String type, Object property) {
        Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(getMyType()).add(Restrictions.ge(type, property)).setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY).list();
        
    }

    @Override
    public List<Object> fetchByNativeFilterByLessThanOrEqual(String type, Object property) {
        Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(getMyType()).add(Restrictions.le(type, property)).setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY).list();
        
    }

    @Override
    public List<Object> fetchByInnerNative(String type,String innerType, Object innerProperty,MatchMode m) {
        Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(getMyType()).createAlias(type, "innerVal").add(Restrictions.like("innerVal."+innerType, (String) innerProperty, m)).list();
    }
    
      @Override
    public List<Object> fetchByNativeFilterByTwo(String type1, Object property1, MatchMode m1, String type2, Object property2, MatchMode m2) {
               Session session = sessionFactory.getCurrentSession();
             Criteria criteria=session.createCriteria(getMyType());
            return criteria.add(Restrictions.ilike(type1, (String) property1, m1))
                    .add(Restrictions.ilike(type2, (String) property2, m2))
                    .setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY).list();
    }

    @Override
    public List<Object> fetchByNativeRange(String type, Object fromValue, Object toValue) {
        Session session = sessionFactory.getCurrentSession();
                     Criteria criteria = session.createCriteria(getMyType());
                      criteria.setReadOnly(true);
                             criteria.add(
                              Restrictions.and(
                                      Restrictions.ge(type,fromValue),
                                      Restrictions.le(type,toValue)
                                              
                              )
                      );
                      return criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY).list();
    }

    @Override
    public List<Object> executeDetachedCriteria(DetachedCriteria dc) {
        Session session = sessionFactory.getCurrentSession();
        return dc.getExecutableCriteria(session).list();
    }
    
    @Override
    public List<Object> fetchByNativeMax(String type) {
        List<Object> list=new ArrayList<>();
        
                   Session session = sessionFactory.getCurrentSession();
                   Criteria criteria =session.createCriteria(getMyType());
                   criteria =criteria.setProjection(Projections.projectionList().add(Projections.max(type)));
                   List<Object> tempList =criteria.list();
                    if(tempList.size()>0){
                    list =session.createCriteria(getMyType())
                           .add(Restrictions.like(type, tempList.get(0)))
                            .list();
                   }

               return  list;
    }
    
}
