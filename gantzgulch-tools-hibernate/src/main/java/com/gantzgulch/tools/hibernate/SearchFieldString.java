package com.gantzgulch.tools.hibernate;

import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.metamodel.SingularAttribute;

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
