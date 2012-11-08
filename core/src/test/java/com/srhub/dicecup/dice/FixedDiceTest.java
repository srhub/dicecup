package com.srhub.dicecup.dice;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.google.common.collect.Lists;
import com.srhub.dicecup.api.Dice;

public class FixedDiceTest {

	@Test
	public void test() {

		final Dice dice = new FixedDice(6, 2);
		final List<Integer> roll = dice.roll(5);

		assertEquals(Lists.newArrayList(2, 2, 2, 2, 2), roll);

	}

}
