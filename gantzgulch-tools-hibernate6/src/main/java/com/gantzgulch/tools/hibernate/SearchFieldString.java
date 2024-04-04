package com.gantzgulch.tools.hibernate;

import java.util.List;
import java.util.Map;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.metamodel.SingularAttribute;

import com.gantzgulch.tools.hibernate.impl.GGPredicates;

public class SearchFieldString extends AbstractSearchField {

    public SearchFieldString(final SingularAttribute<?, ?> attr, final String description) {
        super(attr.getName(), AbstractSearchField.makeList(attr), "String", description);
    }

    public SearchFieldString(final String formName, final List<SingularAttribute<?, ?>> attrs, final String description) {
        super(formName, attrs, "String", description);
    }

    public Predicate executeImpl(CriteriaBuilder builder, Path<?> root, SingularAttribute<?,?> attr, Map<String, String> formFields) {
        
        return GGPredicates.like(builder, root, attr.getName(), getValue(formFields));
        
        
    }

}
