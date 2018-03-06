package com.gantzgulch.tools.hibernate.tx.impl;

import org.hibernate.SessionFactory;
import org.hibernate.context.internal.ThreadLocalSessionContext;

import com.gantzgulch.tools.common.logging.GGLogger;
import com.gantzgulch.tools.hibernate.tx.ApplicationTransaction;
import com.gantzgulch.tools.hibernate.tx.ApplicationTransactionFactory;

public class ApplicationTransactionFactoryImpl implements ApplicationTransactionFactory {

    private static final GGLogger LOG = GGLogger.getLogger(ApplicationTransactionFactoryImpl.class);
    
    protected static final ThreadLocal<ApplicationTransaction> THREAD_TRANSACTION = new ThreadLocal<>();

    private final SessionFactory sessionFactory;

    public ApplicationTransactionFactoryImpl(//
            final SessionFactory sessionFactory) {
        
        this.sessionFactory = sessionFactory;
    }

    @Override
    public ApplicationTransaction create() {

        ApplicationTransaction tx = THREAD_TRANSACTION.get();
        
        LOG.info("begin: existing tx: %s", tx);

        if( tx != null ) {
            throw new IllegalStateException("A thread level transaction already exists.");
        }
        
        tx = new ApplicationTransactionImpl(this, sessionFactory);
        
        THREAD_TRANSACTION.set(tx);
        
        return tx;
    }
    
    public void destroy() { 

        if( THREAD_TRANSACTION.get() != null ){
            
            ThreadLocalSessionContext.unbind(sessionFactory);
            
        }
        
        THREAD_TRANSACTION.remove();
        
    }
    
    public void clearExisting() { 

        if( THREAD_TRANSACTION.get() != null ){
            
            LOG.warn("clearThread: There are left over transactions....");

            ThreadLocalSessionContext.unbind(sessionFactory);
            
        }
        
        THREAD_TRANSACTION.remove();
        
    }
    
}
