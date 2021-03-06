package com.gantzgulch.tools.hibernate;

import java.util.Collection;
import java.util.List;

import com.gantzgulch.tools.hibernate.domain.DomainObject;

public interface Dao<T extends DomainObject> {

    Class<T> getObjectClass();
    
    T findById(String id);
    
    T saveOrUpdate(T item);
    
    List<T> findAll();
    
    T delete(T item);
    
    int countAll();
    
    T populate(T item);
    
    Collection<SearchField> getSearchFields();

    SearchResponse<T> search(final SearchRequest searchRequest);

    void evict(T item);
    
    void flush();
    
    void refresh(T item);
    
}
