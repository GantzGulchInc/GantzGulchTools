package com.gantzgulch.tools.hibernate.tx.impl;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.context.internal.ThreadLocalSessionContext;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import com.gantzgulch.logging.core.GGLogger;
import com.gantzgulch.tools.hibernate.tx.ApplicationTransaction;

public class ApplicationTransactionImpl implements ApplicationTransaction {

    private static final GGLogger LOG = GGLogger.getLogger(ApplicationTransactionImpl.class);

    private final ApplicationTransactionFactoryImpl appTxFactory;

    private final SessionFactory sessionFactory;

    private Session session = null;

    private Transaction tx = null;

    private int openCount = 0;

    public ApplicationTransactionImpl(//
            final ApplicationTransactionFactoryImpl appTxFactory, //
            final SessionFactory sessionFactory) {

        this.appTxFactory = appTxFactory;
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void open() {

        if (this.openCount == 0) {

            this.session = sessionFactory.openSession();

            this.tx = this.session.beginTransaction();

            ThreadLocalSessionContext.bind(session);

        }

        this.openCount += 1;

    }

    @Override
    public void close() {

        LOG.info("close: openCount: %d", openCount);

        if (openCount == 0) {
            throw new IllegalStateException("Transaction is already closed.");
        }

        this.openCount -= 1;

        if (openCount == 0) {

            realClose();
        }
    }

    @Override
    public void commit() {
        tx.commit();
    }

    @Override
    public void rollback() {
        tx.rollback();
    }

    private void realClose() {

        try {

            tryRollbackIfNotCommited();

            session.close();

        } finally {
            appTxFactory.destroy();
        }
    }


    private void tryRollbackIfNotCommited() {

        try {
            if (tx.getStatus() != TransactionStatus.COMMITTED) {
                LOG.warn("Rolling back non committed tx.");
                tx.rollback();
            }
        } catch (final RuntimeException e) {
            LOG.warn(e, "Error rolling back transaction...");
        }

    }

    @Override
    public String toString() {

        return ToStringBuilder.reflectionToString(this);
    }

}
