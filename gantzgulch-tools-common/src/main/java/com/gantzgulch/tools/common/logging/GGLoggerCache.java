package com.gantzgulch.tools.common.logging;

import java.util.HashMap;
import java.util.Map;

import com.gantzgulch.tools.common.lang.GGStrings;
import com.gantzgulch.tools.common.logging.impl.GGLoggerApacheCommons;
import com.gantzgulch.tools.common.logging.impl.GGLoggerLog4j;
import com.gantzgulch.tools.common.logging.impl.GGLoggerSLF4J;

public final class GGLoggerCache {

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
	    
	    if( GGStrings.isBlank(config) ){
	        return new GGLoggerApacheCommons(name);
	    }
	    
	    switch( config ){
	    case "APACHE":
	        return new GGLoggerApacheCommons(name);
	    case "LOG4J":
	        return new GGLoggerLog4j(name);
	    case "SLF4J":
	        return new GGLoggerSLF4J(name);
	    default:
	        return new GGLoggerApacheCommons(name);
	    }
	    
	}
}
