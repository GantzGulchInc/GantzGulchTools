package com.gantzgulch.tools.hibernate;

import java.util.Date;
import java.util.List;
import java.util.Map;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.metamodel.SingularAttribute;

import org.apache.commons.lang3.StringUtils;

import com.gantzgulch.tools.hibernate.impl.GGPredicates;

public class SearchFieldDateAfter extends AbstractSearchField {

    public SearchFieldDateAfter(final String formName, final SingularAttribute<?, ?> attr, final String description) {
        super(formName, AbstractSearchField.makeList(attr), "Date", description);
    }

    public SearchFieldDateAfter(final String formName, final List<SingularAttribute<?, ?>> attrs, final String description) {
        super(formName, attrs, "Date", description);
    }

    public Predicate executeImpl(CriteriaBuilder builder, Path<?> root, SingularAttribute<?,?> attr, Map<String, String> formFields) {

        final Date date = parseDate(formFields);
        
        if (date != null) {
            return GGPredicates.gt(builder, root, attr.getName(), date);
        }
        
        return null;

    }

    private Date parseDate(final Map<String, String> formFields) {

        final String textValue = getValue(formFields);

        if (StringUtils.isBlank(textValue)) {
            return null;
        }

        try {
            final long longValue = Long.parseLong(textValue);

            return new Date(longValue);

        } catch (final RuntimeException e) {
            return null;
        }

    }

}
