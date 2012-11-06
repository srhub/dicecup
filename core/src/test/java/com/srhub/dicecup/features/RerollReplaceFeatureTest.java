package com.srhub.dicecup.features;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.srhub.dicecup.api.Feature;
import com.srhub.dicecup.util.Lists;

public class RerollReplaceFeatureTest extends AbstractFeatureTest {

	@Test
	public void test() {
		final Feature reroll = Features.REROLL_REPLACE_AT(6);
		reroll.setDice(dice);

		final List<Integer> t = reroll.apply(input);
		final List<Integer> headMultiset = Lists.lesserThan(t, 6);

		assertEquals(input.size(), t.size());
		assertEquals(5, headMultiset.size());
	}

}
