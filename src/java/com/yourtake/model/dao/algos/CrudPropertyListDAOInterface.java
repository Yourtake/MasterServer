/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yourtake.model.dao.algos;

import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.springframework.stereotype.Repository;

/**
 *
 * @author MumbaiZone
 * @param <T>
 * @param <K>
 * @param <M>
 * @param <N>
 * @param <O>
 */
@Repository
public interface CrudPropertyListDAOInterface<T,K,M,N,O> {
    
    public void setOperator(M operator);
    public M getOperator();
     public void setMyType(N type) ;
     public N getMyType();
     public List<T> fetchAll(String ascOrder,String descOrder);
     public List<T> fetchByNativeMax(String type);
      public List<T> fetchByInnerNative(String type,String innerType, K innerProperty,MatchMode m);
     public List<T> fetchByNativeFilterByGreaterThanOrEqual(String type, K property);
     public List<T> fetchByNativeFilterByLessThanOrEqual(String type, K property);
      public List<T> fetchByNativeFilterByTwo(String type1, K property1,MatchMode m1 , String type2, K property2,MatchMode m2);
      public List<T> fetchByNativeRange(String type,K fromValue, K toValue);
     public List<T> fetchByNative(String type, K property,String ascOrder,String descOrder,MatchMode m);
     public List<T> fetchByUserDefined(O type, K property,String ascOrder,String descOrder,MatchMode m);
      public List<T> executeDetachedCriteria(DetachedCriteria dc);
     public boolean deleteAll();
}
