package com.gantzgulch.tools.hibernate.domain;

import java.util.Objects;
import java.util.function.Predicate;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.GenericGenerator;

@MappedSuperclass
public abstract class AbstractDomainObject implements DomainObject {

    @Id
    @GeneratedValue(generator="uuid2")
    @GenericGenerator(name="uuid2", strategy="uuid2")
    @Column(name="id")
    protected String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String getObjectType() {
	    return getClass().getSimpleName();
	}
	
	@Override
	public String getDisplayValue() {
	    return getObjectType() + "/" + getId();
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
    
    public static <T extends DomainObject> Predicate<T> idPredicate(final String id){
        return e -> Objects.equals(e.getId(),id);
    }
}
