package com.gantzgulch.tools.hibernate;

import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.Attribute;

import com.gantzgulch.tools.hibernate.impl.GGPredicates;

public class SearchFieldBoolean extends AbstractSearchField {

    public SearchFieldBoolean(final Attribute<?,Boolean> attr, final String description) {
        super(attr.getName(), attr, "Boolean", description);
    }

    public Predicate execute(final CriteriaBuilder builder, final Root<?> root, final Map<String,String> formFields ) {
        
        if( hasFormField(formFields) ) {
            
            final boolean value = parseBoolean(formFields.get(formName) );
            
            return GGPredicates.eq(builder, root, attr.getName(), value );
        }
        
        return null;
    }

    private boolean parseBoolean(final String value) {
        return Boolean.parseBoolean(value);
    }
    

}
