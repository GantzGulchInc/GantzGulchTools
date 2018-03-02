package com.gantzgulch.tools.hibernate.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import com.gantzgulch.tools.common.lang.Cast;
import com.gantzgulch.tools.common.logging.GGLogger;
import com.gantzgulch.tools.hibernate.Dao;
import com.gantzgulch.tools.hibernate.SearchRequest;
import com.gantzgulch.tools.hibernate.SearchResponse;
import com.gantzgulch.tools.hibernate.domain.DomainObject;

public class AbstractHbrDao<T extends DomainObject> implements Dao<T> {

    protected GGLogger LOG = GGLogger.getLogger(getClass());

    private final Class<T> itemClass;
    private final SessionFactory sessionFactory;
    private final String itemName;


    public AbstractHbrDao(final Class<T> itemClass, final SessionFactory sessionFactory) {
        this.itemClass = itemClass;
        this.sessionFactory = sessionFactory;
        this.itemName = itemClass.getName();
    }

    @Override
    public Class<T> getObjectClass() {
        return itemClass;
    }

    @Override
    public T findById(final String id) {

        final String HSQL = String.format("SELECT i FROM %s i WHERE i.id = :id", itemName);

        final TypedQuery<T> query = query(HSQL);
        query.setParameter("id", id);

        return populate(findOne(query));
    }

    @Override
    public T saveOrUpdate(final T item) {

        sessionFactory.getCurrentSession().saveOrUpdate(item);

        return item;
    }

    protected T save(final T item) {

        sessionFactory.getCurrentSession().save(item);

        return item;
    }

    @Override
    public T delete(final T item) {

        sessionFactory.getCurrentSession().delete(item);

        return item;
    }

    @Override
    public List<T> findAll() {

        final String HSQL = String.format("SELECT i FROM %s i", itemName);

        final TypedQuery<T> query = query(HSQL);

        return query.getResultList();
    }

    @Override
    public int countAll() {

        final String HSQL = String.format("SELECT count(i) FROM %s i", itemName);

        final TypedQuery<Number> query = query(HSQL, Number.class);

        return query.getSingleResult().intValue();
    }

    protected Query<?> queryUt(final String queryString) {

        return sessionFactory.getCurrentSession().createQuery(queryString);

    }

    protected Query<T> queryUt2(final String queryString) {

        return sessionFactory.getCurrentSession().createQuery(queryString, itemClass);

    }

    protected NativeQuery<Object[]> sqlQuery(final String sql) {
        return Cast.cast(sessionFactory.getCurrentSession().createNativeQuery(sql));
    }

    protected TypedQuery<T> query(final String query) {

        return sessionFactory.getCurrentSession().createQuery(query, itemClass);
    }

    protected <Z> TypedQuery<Z> query(final String query, Class<Z> queryType) {

        return sessionFactory.getCurrentSession().createQuery(query, queryType);
    }

    protected CriteriaBuilder criteriaBuilder() {
        return sessionFactory.getCriteriaBuilder();
    }

    protected T findOne(final TypedQuery<T> query) {

        try {
            return query.getSingleResult();
        } catch (final NoResultException nre) {
            return null;
        }

    }

    protected T findFirstOne(final TypedQuery<T> query) {

        List<T> result = query.getResultList();

        return result != null && !result.isEmpty() ? result.get(0) : null;

    }

    public T populate(final T item) {
        return item;
    }

    /**
     * New search
     */
    @Override
    public SearchResponse<T> search(final SearchRequest searchRequest) {

        final CriteriaBuilder builder = criteriaBuilder();

        //
        // Compute Search Count
        //
        final CriteriaQuery<Long> countCritQuery = builder.createQuery(Long.class);

        final Root<T> countRoot = countCritQuery.from(itemClass);

        countCritQuery.select(builder.count(countRoot));

        final Predicate countPred = buildSearchWhere(builder, countRoot, searchRequest.getSearchFields());

        if (countPred != null) {
            countCritQuery.where(countPred);
        }

        final Query<Long> itemCountQuery = sessionFactory.getCurrentSession().createQuery(countCritQuery);

        long itemCount = itemCountQuery.getSingleResult();

        //
        // Compute page specs
        //

        final int pageCount = computePageCount((int) itemCount, searchRequest.getPageSize());
        final int realPageNumber = Math.min(pageCount - 1, searchRequest.getPageNumber() - 1);

        //
        // Do Search
        //

        final CriteriaQuery<T> critQuery = builder.createQuery(itemClass);

        final Root<T> root = critQuery.from(itemClass);

        critQuery.select(root);

        final Predicate pred = buildSearchWhere(builder, root, searchRequest.getSearchFields());

        if (pred != null) {

            critQuery.where(pred);
        }

        final List<Order> order = buildSearchOrder(builder, root, searchRequest.getOrderFields());

        if (order != null && order.size() > 0) {
            critQuery.orderBy(order);
        }

        Query<T> itemQuery = sessionFactory.getCurrentSession().createQuery(critQuery);

        itemQuery.setFirstResult(searchRequest.getPageSize() * realPageNumber);
        itemQuery.setMaxResults(searchRequest.getPageSize());

        final List<T> items = itemQuery.getResultList();

        for (final T item : items) {
            populate(item);
        }

        return new SearchResponse<T>(searchRequest.getPageSize(), realPageNumber + 1, pageCount, (int) itemCount, items);
    }

    protected Predicate buildSearchWhere(final CriteriaBuilder builder, final Root<T> root, final Map<String, String> fields) {

        return null;
    }

    protected List<Order> buildSearchOrder(final CriteriaBuilder builder, final Root<T> root, final List<String> fields) {

        final List<Order> order = new ArrayList<>();

        if (fields != null) {

            for (final String field : fields) {

                Path<T> p = null;

                try {
                    p = root.get(field);
                } catch (final Exception e) {
                    p = null;
                }

                if (p != null) {
                    final Order o = builder.asc(p);
                    order.add(o);
                }

            }
        }

        return order;
    }

    private int computePageCount(final int count, final int pageSize) {

        if (count == 0) {
            return 0;
        }

        if (count % pageSize == 0) {
            return count / pageSize;
        }

        return (count / pageSize) + 1;
    }

    @Override
    public void evict(final T item) {
        sessionFactory.getCurrentSession().evict(item);
    }

}
