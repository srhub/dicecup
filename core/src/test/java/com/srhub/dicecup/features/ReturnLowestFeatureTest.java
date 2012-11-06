package com.srhub.dicecup.features;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.srhub.dicecup.api.Feature;

public class ReturnLowestFeatureTest extends AbstractFeatureTest {

	@Test
	public void test() {
		final Feature reroll = Features.RETURN_LOWEST;

		final List<Integer> t = reroll.apply(input);

		assertEquals(1, t.size());
		assertEquals(1, (int) t.get(0));
	}

}
