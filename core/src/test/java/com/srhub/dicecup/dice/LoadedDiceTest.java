package com.srhub.dicecup.dice;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.srhub.dicecup.api.Dice;

public class LoadedDiceTest {

	@Test
	public void test() {
		final Dice dice = LoadedDice.set(6, 1.0).build(6);

		final List<Integer> roll = dice.roll(100);

		for (final int r : roll) {
			assertEquals(6, r);
		}

	}
}
