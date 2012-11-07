package com.srhub.dicecup.extra;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Random;

import org.junit.Test;

import com.google.common.collect.Lists;
import com.srhub.dicecup.api.Dice;
import com.srhub.dicecup.dice.RandomDice;

public class CyclicIntegerRandomTest {

	@Test
	public void test() {
		final Random random = new CyclicIntegerRandom(
				Lists.newArrayList(0, 1, 3, 4, 4));

		final Dice dice = new RandomDice(6, random);
		final List<Integer> roll = dice.roll(5);

		// the dice adds +1 to #nextInt
		assertEquals(Lists.newArrayList(1, 2, 4, 5, 5), roll);
	}

	@Test
	public void testFloat() {

		final Random random = new CyclicIntegerRandom(
				Lists.newArrayList(0, 1, 3, 4, 4));

		System.out.println(random.nextFloat());
		System.out.println(random.nextFloat());
		System.out.println(random.nextFloat());
		System.out.println(random.nextFloat());
		System.out.println(random.nextFloat());
		System.out.println(random.nextFloat());


	}
}
