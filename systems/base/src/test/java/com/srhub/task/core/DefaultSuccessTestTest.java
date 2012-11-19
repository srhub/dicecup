package com.srhub.task.core;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.srhub.dicecup.api.Dice;
import com.srhub.dicecup.core.Cup;
import com.srhub.dicecup.core.Roll;
import com.srhub.dicecup.dice.CyclicDice;
import com.srhub.task.api.CriticalHitException;
import com.srhub.task.api.SuccessTest;
import com.srhub.task.core.Tests;
import com.srhub.task.core.DefaultSuccessTest.Result;

public class DefaultSuccessTestTest {

	private Roll d6roll;

	@Before
	public void setup() {
		final Dice d6 = CyclicDice.add(1, 2, 4, 5, 5).build(6);
		final Cup d6Cup = Cup.add(d6).build();
		d6roll = d6Cup.roll(5);
	}

	@Test
	public void testBase() {
		final SuccessTest<Result> base = Tests.newSuccessTest().build();
		final int actualResult = base.evaluate(d6roll, 4).getSuccesses().size();
		final int expectedResult = 3;

		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void testNetto() {
		final SuccessTest<Result> base = Tests.newSuccessTest().failOn(1)
				.netto().build();
		final int actualResult = base.evaluate(d6roll, 4).getSuccesses().size();
		// d6roll includes one `1` => 3 - 1 = 2 successes
		final int expectedResult = 2;
		assertEquals(expectedResult, actualResult);
	}

	@Test(expected = CriticalHitException.class)
	public void testCriticalHit() {
		final Dice d20 = CyclicDice.add(1, 1, 4, 19, 20).build(20);
		final Cup d20Cup = Cup.add(d20).build();
		final Roll roll = d20Cup.roll(5);

		final SuccessTest<Result> base = Tests.newSuccessTest()
				.criticalHitOn(20).build();
		base.evaluate(roll, 5);
	}

	@Test
	public void testNoCriticalHit() {
		final Dice d20 = CyclicDice.add(1, 1, 4, 19, 19).build(20);
		final Cup d20Cup = Cup.add(d20).build();
		final Roll roll = d20Cup.roll(5);

		final SuccessTest<Result> base = Tests.newSuccessTest()
				.criticalHitOn(20).build();
		base.evaluate(roll, 5);
	}

	@Test(expected = CriticalHitException.class)
	public void testCriticalHitOnPercentage() {
		final Dice d20 = CyclicDice.add(1, 1, 4, 20, 20).build(20);
		final Cup d20Cup = Cup.add(d20).build();
		final Roll roll = d20Cup.roll(5);

		final SuccessTest<Result> base = Tests.newSuccessTest()
				.criticalHitOn("", 20, 0.25).build();
		base.evaluate(roll, 5);
	}

}
