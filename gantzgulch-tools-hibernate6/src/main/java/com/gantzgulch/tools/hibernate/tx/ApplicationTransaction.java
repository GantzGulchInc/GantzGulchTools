package com.gantzgulch.tools.hibernate.tx;

import java.io.Closeable;


public interface ApplicationTransaction extends Closeable {

    void open();

    void commit();
    
    void rollback();
    
}
