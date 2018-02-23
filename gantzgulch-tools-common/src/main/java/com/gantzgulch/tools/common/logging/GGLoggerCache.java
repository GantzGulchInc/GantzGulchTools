package com.gantzgulch.tools.common.logging;

import java.util.HashMap;
import java.util.Map;

import com.gantzgulch.tools.common.logging.impl.GGLoggerApacheCommons;

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
				logger = new GGLoggerApacheCommons(name);
				logCache.put(name, logger);
			}
			
		}
		
		return logger;
	}
	
	public GGLogger getLogger(final Class<?> logClass){
		
		return getLogger(logClass.getName());
	}
	
}
