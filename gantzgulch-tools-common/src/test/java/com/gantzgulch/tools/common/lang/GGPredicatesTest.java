package com.gantzgulch.tools.common.lang;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.function.Predicate;

import org.junit.Test;

public class GGPredicatesTest {

    
    @Test
    public void testAlwaysTrue() {
        
        final Predicate<Object> p = GGPredicates.alwaysTrue();

        assertThat(p.test(this),equalTo(true));
        
    }

    @Test
    public void testAlwaysFalse() {
        
        final Predicate<Object> p = GGPredicates.alwaysFalse();

        assertThat(p.test(this),equalTo(false));
        
    }

    @Test
    public void testFirstNonNullPredicate() {

        final Predicate<Object> p0 = null;
        final Predicate<Object> p1 = GGPredicates.alwaysFalse();
        final Predicate<Object> p2 = GGPredicates.alwaysTrue();

        assertThat(GGPredicates.firstNonNull(p1), equalTo(p1));
        assertThat(GGPredicates.firstNonNull(p0, p1), equalTo(p1));
        assertThat(GGPredicates.firstNonNull(p2, p0, p1), equalTo(p2));
        assertThat(GGPredicates.firstNonNull(null, null), equalTo(null));
        
    }

    
}
