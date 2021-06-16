package com.gantzgulch.logging.core.impl;

import com.gantzgulch.logging.core.GGLogger;

public interface GGLoggerFactory {

    GGLogger create(String name);
    
}
