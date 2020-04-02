package com.gantzgulch.tools.hibernate;

import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.metamodel.SingularAttribute;

import com.gantzgulch.tools.hibernate.impl.GGPredicates;

public class SearchFieldBoolean extends AbstractSearchField {

    public SearchFieldBoolean(final SingularAttribute<?,?> attr, final String description) {
        super(attr.getName(), AbstractSearchField.makeList(attr), "Boolean", description);
    }

    public SearchFieldBoolean(final String formName, final List<SingularAttribute<?,?>> attrs, final String description) {
        super(formName, attrs, "Boolean", description);
    }

    public Predicate executeImpl(CriteriaBuilder builder, Path<?> root, SingularAttribute<?,?> attr, Map<String, String> formFields) {

        final boolean value = parseBoolean( getValue(formFields) );
        
        return GGPredicates.eq(builder, root, attr.getName(), value );

    }
    
    private boolean parseBoolean(final String value) {
        return Boolean.parseBoolean(value);
    }
    

}
