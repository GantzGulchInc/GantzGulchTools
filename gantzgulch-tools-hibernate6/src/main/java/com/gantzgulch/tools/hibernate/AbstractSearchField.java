package com.gantzgulch.tools.hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.From;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.metamodel.SingularAttribute;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

public abstract class AbstractSearchField implements SearchField {

    // private final GGLogger LOG = GGLogger.getLogger(getClass());
    
    protected final String formName;
    
    protected final List<SingularAttribute<?,?>> attributes = new ArrayList<>();

    private String type;

    private String description;
    
    public AbstractSearchField(final String formName, final List<SingularAttribute<?,?>> attributes, final String type, final String description) {
        this.formName = formName;
        this.attributes.addAll(attributes);
        this.type = type;
        this.description = description;
    }

    @Override
    public String getFormName() {
        return formName;
    }

    @Override
    public List<SingularAttribute<?,?>> getAttributes() {
        return attributes;
    }

    @Override
    public String getType() {
        return type;
    }
    
    @Override
    public String getDescription() {
        return description;
    }
   
    @Override
    public Predicate execute(CriteriaBuilder builder, Root<?> root, Map<String, String> formFields) {
    	
    	if( hasFormField(formFields) ) {
    	
    	    final Pair<Path<?>, SingularAttribute<?,?>> pathPair = computePath(builder, root);
    	    
    		return executeImpl(builder, pathPair.getLeft(), pathPair.getRight(), formFields);
    		
    	}
    	
    	return null;
    }
   
    public String getValue(final Map<String, String> formFields) {
        return formFields.get(formName);
    }
    
    public Pair<Path<?>, SingularAttribute<?,?>> computePath(final CriteriaBuilder builder, final Root<?> root) {
        
        
        final List<SingularAttribute<?,?>> w = new ArrayList<>();
        
        w.addAll(attributes);
        
        From<?, ?> from = root;
        
        while( w.size() > 1 ) {
            
            final SingularAttribute<?,?> a = w.remove(0);
            
            from = from.join( a.getName() );
        }
        
        return new ImmutablePair<>(from, w.get(0) );
        
    }
    
    public abstract Predicate executeImpl(CriteriaBuilder builder, Path<?> root, SingularAttribute<?,?> attr, Map<String, String> formFields);
    
    protected boolean hasFormField(final Map<String,String> formFields) {
        
        if( formFields != null ) {
            
            return StringUtils.isNotBlank(formFields.get(formName) );
            
        }
        
        return false;
    }
    
    public static List<SingularAttribute<?,?>> makeList(final SingularAttribute<?,?> attr) {
        
        final List<SingularAttribute<?,?>> list = new ArrayList<>();
        
        list.add(attr);
        
        return list;
    }
}
