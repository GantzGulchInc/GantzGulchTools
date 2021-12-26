package com.gantzgulch.tools.common.lang;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;

public class GGArraysTest {

    @Test
    public void testIntSizeNull() {

        assertThat(GGArrays.size((int[]) null), equalTo(0));
    }

    @Test
    public void testIntSize() {

        final int[] ints = { 100, 4, 9000, 250 };
        assertThat(GGArrays.size(ints), equalTo(4));
    }

    @Test
    public void testIntMinNull() {

        assertThat(GGArrays.min((int[]) null), equalTo(0));
    }

    @Test
    public void testIntMin() {

        final int[] ints = { 100, 4, 9000, 250 };
        assertThat(GGArrays.min(ints), equalTo(4));
    }

    @Test
    public void testIntMaxNull() {

        assertThat(GGArrays.max((int[]) null), equalTo(0));
    }

    @Test
    public void testIntMax() {

        final int[] ints = { 100, 4, 9000, 250 };
        assertThat(GGArrays.max(ints), equalTo(9000));
    }

    @Test
    public void testIntContains() {

        final int[] ints = { 100, 4, 9000, 250 };

        assertThat(GGArrays.contains(ints, v -> v == 4), equalTo(true));
        assertThat(GGArrays.contains(ints, v -> v == 5), equalTo(false));
    }

}
