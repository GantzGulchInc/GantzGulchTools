package com.gantzgulch.logging.core.impl;

import com.gantzgulch.logging.core.GGLogger;

public interface GGLoggerFactory {

    void initialize();
    
    GGLogger create(String name);
    
}
