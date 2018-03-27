package com.gantzgulch.tools.common.lang;

public class GGPair<K,V> {

    private final K key;
    private final V value;
    
    public GGPair() {
        this.key = null;
        this.value = null;
    }

    public GGPair(final K key, final V value) {
        this.key = key;
        this.value = value;
    }
    
    public K getKey() {
        return key;
    }
    
    public V getValue() {
        return value;
    }
}
