package com.srhub.dicecup.features;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import com.srhub.dicecup.api.Feature;

public class AddConstantFeatureTest extends AbstractFeatureTest {

	@Test
	public void test() {
		final Feature reroll = Features.ADD_CONSTANT(constant);

		final List<Integer> output = reroll.apply(input);

		assertEquals(input.size(), output.size());

		for (final int i : input) {
			assertTrue(output.contains(i + constant));
		}
	}

}
