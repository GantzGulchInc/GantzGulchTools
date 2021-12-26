package com.gantzgulch.tools.common.lang;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;

public class GGBiPredicateTest {

    private Map<String, Boolean> map;

    @Before
    public void before() {

        this.map = new HashMap<>();

        map.put("one", true);
        map.put("two", false);
        map.put("three", true);
        map.put("four", false);

    }

    @Test
    public void mapBiFunctionTest() {

        final Set<String> set = map //
                .entrySet() //
                .stream() //
                .filter(GGBiPredicate.mapPredicate(this::trueBi)) //
                .map(GGBiFunction.mapFunction(this::key)) //
                .collect(Collectors.toSet());

        assertThat(set, notNullValue());
        assertThat(set, hasSize(2));
        assertThat(set, containsInAnyOrder("one", "three"));

    }

    public boolean trueBi(final String v1, final Boolean v2) {
        return v2.booleanValue();
    }

    public String key(final String v1, final Boolean v2) {
        return v1;
    }

}
