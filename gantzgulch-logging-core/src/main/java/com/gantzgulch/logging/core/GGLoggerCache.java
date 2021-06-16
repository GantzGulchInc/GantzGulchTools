package com.gantzgulch.logging.core;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ServiceLoader;

import com.gantzgulch.logging.core.impl.GGLoggerFactory;

public final class GGLoggerCache {

	public static final GGLoggerCache INSTANCE = new GGLoggerCache();
	
	private final ServiceLoader<GGLoggerFactory> serviceLoader;
	
	private final GGLoggerFactory loggerFactory;
	
	private Map<String,GGLogger> logCache = new HashMap<>();
    
	private GGLoggerCache() {

	    serviceLoader = ServiceLoader.load(GGLoggerFactory.class);

	    if( serviceLoader == null ) {
	        throw new RuntimeException("Failed to load GGLogger Factory Service Loader.");
	    }
	    
	    final Iterator<GGLoggerFactory> i = serviceLoader.iterator();
	    
	    if( i.hasNext() ) {
	        
	        loggerFactory = i.next();
	        
	    } else {
	        throw new RuntimeException("ServiceLoader provided no GGLoggerFactory objects.");
	    }
	    

	}
	
	public GGLogger getLogger(final String name) {
		
		GGLogger logger = null;
		
		synchronized (logCache) {
		
			logger = logCache.get(name);
			
			if( logger == null ){
			    
				logger = createLogger(name);
				
				logCache.put(name, logger);
			}
			
		}
		
		return logger;
	}
	
	public GGLogger getLogger(final Class<?> logClass){
		
		return getLogger(logClass.getName());
	}
	
	private GGLogger createLogger(final String name) {
	    
	    return loggerFactory.create(name);
	    
	}
	
}
