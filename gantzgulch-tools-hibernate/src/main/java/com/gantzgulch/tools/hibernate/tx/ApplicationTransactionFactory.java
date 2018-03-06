package com.gantzgulch.tools.hibernate.tx;

public interface ApplicationTransactionFactory {

    ApplicationTransaction create();
 
    void destroy();
}
