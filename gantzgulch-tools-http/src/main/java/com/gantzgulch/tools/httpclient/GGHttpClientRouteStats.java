package com.gantzgulch.tools.httpclient;

import org.apache.http.pool.PoolStats;

public class GGHttpClientRouteStats {

    private int available;

    private int leased;

    private int max;

    private int pending;
    
    public GGHttpClientRouteStats() {
        
    }

    public GGHttpClientRouteStats(final PoolStats poolStats) {
        available = poolStats.getAvailable();
        leased = poolStats.getLeased();
        max = poolStats.getMax();
        pending = poolStats.getPending();
    }

    public int getAvailable() {
        return available;
    }

    public int getLeased() {
        return leased;
    }

    public int getMax() {
        return max;
    }

    public int getPending() {
        return pending;
    }

}
