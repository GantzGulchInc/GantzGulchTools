package com.gantzgulch.tools.hibernate;

import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.Attribute;

import com.gantzgulch.tools.hibernate.impl.GGPredicates;

public class SearchFieldString extends AbstractSearchField {

    public SearchFieldString(final Attribute<?,String> attr, final String description) {
        super(attr.getName(), attr, "String", description);
    }

    public Predicate execute(final CriteriaBuilder builder, final Root<?> root, final Map<String,String> formFields ) {
        
        if( hasFormField(formFields) ) {
            
            return GGPredicates.like(builder, root, attr.getName(), formFields.get(formName) );
        }
        
        return null;
    }
    

}
