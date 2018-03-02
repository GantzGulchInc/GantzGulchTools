package com.gantzgulch.tools.hibernate.impl;

import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;

import com.gantzgulch.tools.common.logging.GGLogger;

public class GGPredicates {

    private static final GGLogger LOG = GGLogger.getLogger(GGPredicates.class);

    public static boolean hasField(final Map<String, String> fields, final String fieldName) {
        return fields != null && StringUtils.isNotBlank(fields.get(fieldName));
    }

    public static Predicate or(final CriteriaBuilder builder, final List<Predicate> pList) {
        return builder.or(pList.toArray(new Predicate[pList.size()]));
    }

    public static <T> Predicate like(final CriteriaBuilder builder, final Root<T> root, final String fieldName, final String searchValue) {

        LOG.debug("like: %s", fieldName);

        return builder.like(//
                builder.lower(root.get(fieldName)), //
                String.format("%%%s%%", searchValue.toLowerCase()));
    }

}
