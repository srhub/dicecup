package com.srhub.dicecup.core;

import static org.junit.Assert.assertNull;

import org.junit.Test;

import com.srhub.task.core.Result;

public class RolzParserTest {

	@Test(expected = NullPointerException.class)
	public void testNull() {
		final String code = null;
		new RolzParser().parse(code);
	}

	@Test
	public void testEmpty() {
		final String code = "";
		final Result<Roll> result = new RolzParser().parse(code);

		assertNull(result);

	}

	@Test
	public void testDefault() {
		final String code = "4D6";
		final Result<Roll> result = new RolzParser().parse(code);
		System.out.println(result);
	}

	@Test
	public void testDefaultWithOperator() {
		final String code = "4D6+3";
		new RolzParser().parse(code);
	}

	@Test
	public void testDropLowestAndSum() {
		final String code = "4L6";
		final Result<Roll> parse = new RolzParser().parse(code);
		System.out.println(parse);
	}

	@Test
	public void testTakeHighest() {
		final String code = "7D10z4";
		final Result<Roll> parse = new RolzParser().parse(code);
		System.out.println(parse);
	}

}
