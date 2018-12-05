package com.gantzgulch.tools.hibernate;

import java.util.Date;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.Attribute;

import org.apache.commons.lang3.StringUtils;

import com.gantzgulch.tools.hibernate.impl.GGPredicates;

public class SearchFieldDateAfter extends AbstractSearchField {

    public SearchFieldDateAfter(final String formName, final Attribute<?,Date> attr, final String description) {
        super(formName, attr, "Date", description);
    }

    public Predicate execute(final CriteriaBuilder builder, final Root<?> root, final Map<String,String> formFields ) {
        
        if( hasFormField(formFields) ) {
            
            final Date date = parseDate(formName, formFields);
            
            if( date != null ) {
                return GGPredicates.gt(builder, root, attr.getName(), date );
            }
        }
        
        return null;
    }

    private Date parseDate(final String formName, final Map<String, String> formFields) {
        
        final String textValue = formFields.get(formName);
        
        if( StringUtils.isBlank(textValue) ) {
            return null;
        }
        
        try {
            final long longValue = Long.parseLong(textValue);
            
            return new Date(longValue);
            
        }catch(final RuntimeException e){
            return null;
        }
        
    }
    

}
