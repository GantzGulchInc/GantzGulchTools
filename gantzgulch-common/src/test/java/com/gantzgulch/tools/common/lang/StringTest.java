package com.gantzgulch.tools.common.lang;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class StringTest {

	
	@Test
	public void repeat() {
		
		assertThat(Strings.repeat(null, -100), equalTo(null));
		assertThat(Strings.repeat(null, -1), equalTo(null));
		assertThat(Strings.repeat(null, 0), equalTo(null));
		assertThat(Strings.repeat(null, 1), equalTo(null));
		assertThat(Strings.repeat(null, 100), equalTo(null));
		
		assertThat(Strings.repeat("a", -100), equalTo(""));
		assertThat(Strings.repeat("a", -1), equalTo(""));
		assertThat(Strings.repeat("a", 0), equalTo(""));
		assertThat(Strings.repeat("a", 1), equalTo("a"));
		assertThat(Strings.repeat("a", 100), equalTo("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
		
		assertThat(Strings.repeat("Car", -100), equalTo(""));
		assertThat(Strings.repeat("Car", -1), equalTo(""));
		assertThat(Strings.repeat("Car", 0), equalTo(""));
		assertThat(Strings.repeat("Car", 1), equalTo("Car"));
		assertThat(Strings.repeat("Car", 100), equalTo("CarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCar"));
		
	}
	
	@Test
	public void leftPad() {
		
		assertThat(Strings.leftPad(null, -100), equalTo(null));
		assertThat(Strings.leftPad(null, -1), equalTo(null));
		assertThat(Strings.leftPad(null, 0), equalTo(null));
		assertThat(Strings.leftPad(null, 1), equalTo(null));
		assertThat(Strings.leftPad(null, 100), equalTo(null));
		
		assertThat(Strings.leftPad("a", -100), equalTo("a"));
		assertThat(Strings.leftPad("a", -1), equalTo("a"));
		assertThat(Strings.leftPad("a", 0), equalTo("a"));
		assertThat(Strings.leftPad("a", 1), equalTo("a"));
		assertThat(Strings.leftPad("a", 5), equalTo("    a"));
		assertThat(Strings.leftPad("a", 100), equalTo("                                                                                                   a"));
		
		assertThat(Strings.leftPad("Car", -100), equalTo("Car"));
		assertThat(Strings.leftPad("Car", -1), equalTo("Car"));
		assertThat(Strings.leftPad("Car", 0), equalTo("Car"));
		assertThat(Strings.leftPad("Car", 1), equalTo("Car"));
		assertThat(Strings.leftPad("Car", 7), equalTo("    Car"));
		assertThat(Strings.leftPad("Car", 100), equalTo("                                                                                                 Car"));
		
	}
	
	@Test
	public void rightPad() {
		
		assertThat(Strings.rightPad(null, -100), equalTo(null));
		assertThat(Strings.rightPad(null, -1), equalTo(null));
		assertThat(Strings.rightPad(null, 0), equalTo(null));
		assertThat(Strings.rightPad(null, 1), equalTo(null));
		assertThat(Strings.rightPad(null, 100), equalTo(null));
		
		assertThat(Strings.rightPad("a", -100), equalTo("a"));
		assertThat(Strings.rightPad("a", -1), equalTo("a"));
		assertThat(Strings.rightPad("a", 0), equalTo("a"));
		assertThat(Strings.rightPad("a", 1), equalTo("a"));
		assertThat(Strings.rightPad("a", 5), equalTo("a    "));
		assertThat(Strings.rightPad("a", 100), equalTo("a                                                                                                   "));
		
		assertThat(Strings.rightPad("Car", -100), equalTo("Car"));
		assertThat(Strings.rightPad("Car", -1), equalTo("Car"));
		assertThat(Strings.rightPad("Car", 0), equalTo("Car"));
		assertThat(Strings.rightPad("Car", 1), equalTo("Car"));
		assertThat(Strings.rightPad("Car", 7), equalTo("Car    "));
		assertThat(Strings.rightPad("Car", 100), equalTo("Car                                                                                                 "));
		
	}
	
	@Test
	public void center() {
		
		assertThat(Strings.center(null, -100), equalTo(null));
		assertThat(Strings.center(null, -1), equalTo(null));
		assertThat(Strings.center(null, 0), equalTo(null));
		assertThat(Strings.center(null, 1), equalTo(null));
		assertThat(Strings.center(null, 100), equalTo(null));
		
		assertThat(Strings.center("a", -100), equalTo("a"));
		assertThat(Strings.center("a", -1), equalTo("a"));
		assertThat(Strings.center("a", 0), equalTo("a"));
		assertThat(Strings.center("a", 1), equalTo("a"));
		assertThat(Strings.center("a", 5), equalTo("  a  "));
		assertThat(Strings.center("a", 100), equalTo("                                                 a                                                  "));
		
		assertThat(Strings.center("Car", -100), equalTo("Car"));
		assertThat(Strings.center("Car", -1), equalTo("Car"));
		assertThat(Strings.center("Car", 0), equalTo("Car"));
		assertThat(Strings.center("Car", 1), equalTo("Car"));
		assertThat(Strings.center("Car", 7), equalTo("  Car  "));
		assertThat(Strings.center("Car", 100), equalTo("                                                Car                                                 "));
		
	}
	
}
