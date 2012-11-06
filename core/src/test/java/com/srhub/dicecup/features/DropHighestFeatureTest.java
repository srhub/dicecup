package com.srhub.dicecup.features;

import static org.junit.Assert.assertEquals;

import java.util.Collections;
import java.util.List;

import org.junit.Test;

import com.srhub.dicecup.api.Feature;

public class DropHighestFeatureTest extends AbstractFeatureTest {

	@Test
	public void test() {
		final Feature reroll = Features.DROP_HIGHEST;

		final List<Integer> t = reroll.apply(input);

		assertEquals(input.size() - 1, t.size());
		assertEquals(1, Collections.frequency(t, 6));
	}

}
