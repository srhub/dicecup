package com.srhub.dicecup.features;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.srhub.dicecup.api.Feature;

public class SumFeatureTest extends AbstractFeatureTest {

	@Test
	public void test() {
		final Feature sum = Features.SUM;

		final List<Integer> t = sum.apply(input);

		assertEquals(1, t.size());
		assertEquals(22, (int) t.get(0));
	}
}
