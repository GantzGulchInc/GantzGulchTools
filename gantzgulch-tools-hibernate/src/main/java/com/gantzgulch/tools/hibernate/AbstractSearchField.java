package com.gantzgulch.tools.hibernate;

import java.util.Map;

import javax.persistence.metamodel.Attribute;

import org.apache.commons.lang3.StringUtils;

public abstract class AbstractSearchField implements SearchField {

    protected final String formName;
    
    protected final Attribute<?,?> attr;

    private String type;

    private String description;
    
    public AbstractSearchField(final String formName, final Attribute<?,?> attr, final String type, final String description) {
        this.formName = formName;
        this.attr = attr;
        this.type = type;
        this.description = description;
    }

    @Override
    public String getFormName() {
        return formName;
    }

    @Override
    public Attribute<?,?> getAttribute() {
        return attr;
    }

    @Override
    public String getType() {
        return type;
    }
    
    @Override
    public String getDescription() {
        return description;
    }
    
    protected boolean hasFormField(final Map<String,String> formFields) {
        
        if( formFields != null ) {
            
            return StringUtils.isNotBlank(formFields.get(formName) );
            
        }
        
        return false;
    }
    
}
