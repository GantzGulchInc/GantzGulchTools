package com.gantzgulch.tools.hibernate.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;

import org.apache.commons.lang3.StringUtils;

import com.gantzgulch.logging.core.GGLogger;

public class GGPredicates {

    private static final GGLogger LOG = GGLogger.getLogger(GGPredicates.class);

    public static boolean hasField(final Map<String, String> fields, final String fieldName) {
        return fields != null && StringUtils.isNotBlank(fields.get(fieldName));
    }

    public static Predicate or(final CriteriaBuilder builder, final List<Predicate> pList) {
        return builder.or(toArray(pList));
    }

    public static Predicate and(final CriteriaBuilder builder, final List<Predicate> pList) {
        return builder.and(toArray(pList));
    }

    public static <T> Predicate like(final CriteriaBuilder builder, final Path<T> root, final String fieldName, final String searchValue) {

        LOG.debug("like: %s", fieldName);

        return builder.like(//
                builder.lower(root.get(fieldName)), //
                String.format("%%%s%%", searchValue.toLowerCase()));
    }

    public static <T> Predicate eq(final CriteriaBuilder builder, final Path<T> root, final String fieldName, final Object searchValue) {

        LOG.debug("eq: %s", fieldName);

        return builder.equal(root.get(fieldName), searchValue);
    }

    public static <T> Predicate gt(final CriteriaBuilder builder, final Path<T> root, final String fieldName, final Date searchValue) {

        LOG.debug("gt: %s", fieldName);

        return builder.greaterThan(root.get(fieldName), searchValue);
    }

    public static <T> Predicate lt(final CriteriaBuilder builder, final Path<T> root, final String fieldName, final Date searchValue) {

        LOG.debug("gt: %s", fieldName);

        return builder.lessThan(root.get(fieldName), searchValue);
    }

    public static Predicate[] toArray(final List<Predicate> predicates) {

        return predicates != null ? predicates.toArray(new Predicate[predicates.size()]) : new Predicate[0];

    }
}
