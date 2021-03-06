package com.gantzgulch.tools.hibernate;

import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;

public interface SearchField {

    String getFormName();
    
    String getType();
    
    String getDescription();
    
    List<SingularAttribute<?,?>> getAttributes();
    
    Predicate execute(CriteriaBuilder builder, Root<?> root, Map<String,String> formFields);
}
