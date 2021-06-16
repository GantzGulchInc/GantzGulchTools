package com.gantzgulch.logging.core;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public final class GGLoggerCache {

    public static final String COMMONS_CLASS = "com.gantzgulch.logging.commons.GGLoggerApacheCommons";
    public static final String LOG4J_CLASS = "com.gantzgulch.logging.log4j.GGLoggerLog4j";
    public static final String LOG4J2_CLASS = "com.gantzgulch.logging.log4j2.GGLoggerLog4j2";
    public static final String SLF4J_CLASS = "com.gantzgulch.logging.slf4j.GGLoggerSLF4J";
    
	public static final GGLoggerCache INSTANCE = new GGLoggerCache();
	
	private Map<String,GGLogger> logCache = new HashMap<>();
    
	private GGLoggerCache() {
		
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
	    
	    final String config = System.getProperty("com.gantzgulch.tools.common.logging.GGLogger.backend");
	    
	    String loggerClassname = COMMONS_CLASS;
	    
	    if( config != null ) {
    	    switch( config ){
    	    case "APACHE":
    	        loggerClassname = COMMONS_CLASS;
    	        break;
    	    case "LOG4J":
    	        loggerClassname = LOG4J_CLASS;
    	        break;
            case "LOG4J2":
                loggerClassname = LOG4J2_CLASS;
                break;
    	    case "SLF4J":
    	        loggerClassname = SLF4J_CLASS;
    	        break;
    	    }
	    }

	    return createInstance(loggerClassname, name);
	    
	}
	
	private GGLogger createInstance(final String className, final String argument) {
	    
	    try {
	        
	        final Class<?> loggerClass = Class.forName(className);
	        final Constructor<?> loggerCtor = loggerClass.getConstructor(String.class);
	        
	        return (GGLogger) loggerCtor.newInstance(argument);
	        
	    }catch(final Exception e) {
	        throw new RuntimeException(e);
	    }
	    
	}
}
