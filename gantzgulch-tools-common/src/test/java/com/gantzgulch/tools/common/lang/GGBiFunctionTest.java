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

public class GGBiFunctionTest {

    
    private Map<String,Integer> map;
    
    @Before
    public void before() {
        
        this.map = new HashMap<>();
        
        map.put("one",1);
        map.put("two",2);
        map.put("three",3);
               
    }
    
    @Test
    public void mapBiFunctionTest() {
        
    
        final Set<String> set = map //
                .entrySet() //
                .stream() //
                .map(GGBiFunction.mapFunction(this::compute)) //
                .collect(Collectors.toSet());
        
        assertThat( set, notNullValue() );
        assertThat( set, hasSize(3));
        assertThat( set, containsInAnyOrder("one/1", "two/2", "three/3"));
        
        
    }
    
    public String compute(final String v1, final Integer v2) {
        return String.format("%s/%d", v1, v2);
    }
}
