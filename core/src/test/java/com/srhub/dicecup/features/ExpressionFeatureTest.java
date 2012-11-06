package com.srhub.dicecup.features;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import com.srhub.dicecup.api.Feature;

public class ExpressionFeatureTest extends AbstractFeatureTest {

	@Test
	public void test() {
		final Feature reroll = Features.EXPRESSION("x * 2 + 2");

		final List<Integer> output = reroll.apply(input);

		assertEquals(input.size(), output.size());

		for (final int i : input) {
			assertTrue(output.contains(i * 2 + 2));
		}
	}

	@SuppressWarnings("unused")
	@Test
	public void testWithoutX() {
		final Feature reroll = Features.EXPRESSION("2 + 3");

		final List<Integer> output = reroll.apply(input);

		assertEquals(input.size(), output.size());

		for (final int i : input) {
			assertTrue(output.contains(2 + 3));
		}
	}

	@Test(expected = RuntimeException.class)
	public void testMalformedExpression() {
		final Feature reroll = Features.EXPRESSION("fobar");

		reroll.apply(input);

	}

}
