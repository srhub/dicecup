package com.srhub.dicecup.features;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.srhub.dicecup.api.Feature;

public class ReturnHighestFeatureTest extends AbstractFeatureTest {

	@Test
	public void test() {
		final Feature reroll = Features.RETURN_HIGHEST;

		final List<Integer> t = reroll.apply(input);

		assertEquals(1, t.size());
		assertEquals(6, (int) t.get(0));
	}

}
