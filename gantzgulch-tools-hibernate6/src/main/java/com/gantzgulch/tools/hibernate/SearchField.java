package com.gantzgulch.tools.hibernate;

import java.util.List;
import java.util.Map;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.metamodel.SingularAttribute;

public interface SearchField {

    String getFormName();
    
    String getType();
    
    String getDescription();
    
    List<SingularAttribute<?,?>> getAttributes();
    
    Predicate execute(CriteriaBuilder builder, Root<?> root, Map<String,String> formFields);
}
