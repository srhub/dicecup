package com.srhub.dicecup.features;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.srhub.dicecup.api.Feature;
import com.srhub.dicecup.util.Lists;

public class ExplodeFeatureTest extends AbstractFeatureTest {

	@Test
	public void test() {
		final Feature explode = Features.EXPLODE_AT(6);
		explode.setDice(dice);

		final List<Integer> t = explode.apply(input);

		final List<Integer> tailMultiset = Lists.greaterThan(t, 6);

		assertEquals(input.size(), t.size());
		assertEquals(2, tailMultiset.size());
	}

}
