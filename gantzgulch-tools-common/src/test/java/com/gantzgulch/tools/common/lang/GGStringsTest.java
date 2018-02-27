package com.gantzgulch.tools.common.lang;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class GGStringsTest {

	
	@Test
	public void repeat() {
		
		assertThat(GGStrings.repeat(null, -100), equalTo(null));
		assertThat(GGStrings.repeat(null, -1), equalTo(null));
		assertThat(GGStrings.repeat(null, 0), equalTo(null));
		assertThat(GGStrings.repeat(null, 1), equalTo(null));
		assertThat(GGStrings.repeat(null, 100), equalTo(null));
		
		assertThat(GGStrings.repeat("a", -100), equalTo(""));
		assertThat(GGStrings.repeat("a", -1), equalTo(""));
		assertThat(GGStrings.repeat("a", 0), equalTo(""));
		assertThat(GGStrings.repeat("a", 1), equalTo("a"));
		assertThat(GGStrings.repeat("a", 100), equalTo("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
		
		assertThat(GGStrings.repeat("Car", -100), equalTo(""));
		assertThat(GGStrings.repeat("Car", -1), equalTo(""));
		assertThat(GGStrings.repeat("Car", 0), equalTo(""));
		assertThat(GGStrings.repeat("Car", 1), equalTo("Car"));
		assertThat(GGStrings.repeat("Car", 100), equalTo("CarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCar"));
		
	}
	
	@Test
	public void leftPad() {
		
		assertThat(GGStrings.leftPad(null, -100), equalTo(null));
		assertThat(GGStrings.leftPad(null, -1), equalTo(null));
		assertThat(GGStrings.leftPad(null, 0), equalTo(null));
		assertThat(GGStrings.leftPad(null, 1), equalTo(null));
		assertThat(GGStrings.leftPad(null, 100), equalTo(null));
		
		assertThat(GGStrings.leftPad("a", -100), equalTo("a"));
		assertThat(GGStrings.leftPad("a", -1), equalTo("a"));
		assertThat(GGStrings.leftPad("a", 0), equalTo("a"));
		assertThat(GGStrings.leftPad("a", 1), equalTo("a"));
		assertThat(GGStrings.leftPad("a", 5), equalTo("    a"));
		assertThat(GGStrings.leftPad("a", 100), equalTo("                                                                                                   a"));
		
		assertThat(GGStrings.leftPad("Car", -100), equalTo("Car"));
		assertThat(GGStrings.leftPad("Car", -1), equalTo("Car"));
		assertThat(GGStrings.leftPad("Car", 0), equalTo("Car"));
		assertThat(GGStrings.leftPad("Car", 1), equalTo("Car"));
		assertThat(GGStrings.leftPad("Car", 7), equalTo("    Car"));
		assertThat(GGStrings.leftPad("Car", 100), equalTo("                                                                                                 Car"));
		
	}
	
	@Test
	public void rightPad() {
		
		assertThat(GGStrings.rightPad(null, -100), equalTo(null));
		assertThat(GGStrings.rightPad(null, -1), equalTo(null));
		assertThat(GGStrings.rightPad(null, 0), equalTo(null));
		assertThat(GGStrings.rightPad(null, 1), equalTo(null));
		assertThat(GGStrings.rightPad(null, 100), equalTo(null));
		
		assertThat(GGStrings.rightPad("a", -100), equalTo("a"));
		assertThat(GGStrings.rightPad("a", -1), equalTo("a"));
		assertThat(GGStrings.rightPad("a", 0), equalTo("a"));
		assertThat(GGStrings.rightPad("a", 1), equalTo("a"));
		assertThat(GGStrings.rightPad("a", 5), equalTo("a    "));
		assertThat(GGStrings.rightPad("a", 100), equalTo("a                                                                                                   "));
		
		assertThat(GGStrings.rightPad("Car", -100), equalTo("Car"));
		assertThat(GGStrings.rightPad("Car", -1), equalTo("Car"));
		assertThat(GGStrings.rightPad("Car", 0), equalTo("Car"));
		assertThat(GGStrings.rightPad("Car", 1), equalTo("Car"));
		assertThat(GGStrings.rightPad("Car", 7), equalTo("Car    "));
		assertThat(GGStrings.rightPad("Car", 100), equalTo("Car                                                                                                 "));
		
	}
	
	@Test
	public void center() {
		
		assertThat(GGStrings.center(null, -100), equalTo(null));
		assertThat(GGStrings.center(null, -1), equalTo(null));
		assertThat(GGStrings.center(null, 0), equalTo(null));
		assertThat(GGStrings.center(null, 1), equalTo(null));
		assertThat(GGStrings.center(null, 100), equalTo(null));
		
		assertThat(GGStrings.center("a", -100), equalTo("a"));
		assertThat(GGStrings.center("a", -1), equalTo("a"));
		assertThat(GGStrings.center("a", 0), equalTo("a"));
		assertThat(GGStrings.center("a", 1), equalTo("a"));
		assertThat(GGStrings.center("a", 5), equalTo("  a  "));
		assertThat(GGStrings.center("a", 100), equalTo("                                                 a                                                  "));
		
		assertThat(GGStrings.center("Car", -100), equalTo("Car"));
		assertThat(GGStrings.center("Car", -1), equalTo("Car"));
		assertThat(GGStrings.center("Car", 0), equalTo("Car"));
		assertThat(GGStrings.center("Car", 1), equalTo("Car"));
		assertThat(GGStrings.center("Car", 7), equalTo("  Car  "));
		assertThat(GGStrings.center("Car", 100), equalTo("                                                Car                                                 "));
		
	}

	@Test
	public void isBlank() {
	    
	    assertThat(GGStrings.isBlank(null), is(true) );
        assertThat(GGStrings.isBlank(""), is(true) );
        assertThat(GGStrings.isBlank(" "), is(true) );
        assertThat(GGStrings.isBlank("\t"), is(true) );
        assertThat(GGStrings.isBlank(" \t"), is(true) );
	    
        assertThat(GGStrings.isBlank(" a"), is(false) );
        assertThat(GGStrings.isBlank("a "), is(false) );
	}
	
    @Test
    public void isNotBlank() {
        
        assertThat(GGStrings.isNotBlank(null), is(false) );
        assertThat(GGStrings.isNotBlank(""), is(false) );
        assertThat(GGStrings.isNotBlank(" "), is(false) );
        assertThat(GGStrings.isNotBlank("\t"), is(false) );
        assertThat(GGStrings.isNotBlank(" \t"), is(false) );
        
        assertThat(GGStrings.isNotBlank(" a"), is(true) );
        assertThat(GGStrings.isNotBlank("a "), is(true) );
    }
    
    @Test
    public void stripEnd() {
        
        
        assertThat(GGStrings.stripEnd(null, null), nullValue() );
        assertThat(GGStrings.stripEnd(null, ""), nullValue() );
        assertThat(GGStrings.stripEnd(null, "abc"), nullValue() );
        
        assertThat(GGStrings.stripEnd("abc", "def"), equalTo("abc") );
        assertThat(GGStrings.stripEnd("abc", "ac"), equalTo("ab") );

        
        assertThat(GGStrings.stripEnd("ℤbc", "aℤ"), equalTo("ℤbc") );
        assertThat(GGStrings.stripEnd("aℤc", "aℤ"), equalTo("aℤc") );
        assertThat(GGStrings.stripEnd("abℤ", "aℤ"), equalTo("ab") );

        assertThat(GGStrings.stripEnd("ℤbc", "ℤa"), equalTo("ℤbc") );
        assertThat(GGStrings.stripEnd("aℤc", "ℤa"), equalTo("aℤc") );
        assertThat(GGStrings.stripEnd("abℤ", "ℤa"), equalTo("ab") );

    }
}
