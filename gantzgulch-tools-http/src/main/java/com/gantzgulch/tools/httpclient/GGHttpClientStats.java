package com.gantzgulch.tools.httpclient;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.pool.PoolStats;


public class GGHttpClientStats {

    private GGHttpClientRouteStats totalPoolStatistics;
    
    private Map<String,GGHttpClientRouteStats> routePoolStatistics;
    
    public GGHttpClientStats() {
        
    }

    public GGHttpClientStats(final PoolingHttpClientConnectionManager connectionManager) {
        
        if( connectionManager != null ){
            buildStats(connectionManager);
        }
    }

    private void buildStats(final PoolingHttpClientConnectionManager connectionManager) {
        
        totalPoolStatistics = new GGHttpClientRouteStats(connectionManager.getTotalStats());
        
        routePoolStatistics = new HashMap<>();
        
        for(final HttpRoute route : connectionManager.getRoutes()){

            final PoolStats poolStats = connectionManager.getStats(route);
            
            routePoolStatistics.put(route.toString(), new GGHttpClientRouteStats(poolStats) );
            
        }
    }


}
