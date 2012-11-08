package com.srhub.test.core;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.srhub.dicecup.api.Dice;
import com.srhub.dicecup.core.Cup;
import com.srhub.dicecup.core.Roll;
import com.srhub.dicecup.dice.CyclicDice;
import com.srhub.test.api.SuccessTest;

public class SuccessTestTest {

	private Roll roll;

	@Before
	public void setup() {
		final Dice dice = CyclicDice.add(1, 2, 4, 5, 5).build(6);
		final Cup cup = Cup.add(dice).build();
		roll = cup.roll(5);
	}

	@Test
	public void testBase() {
		final SuccessTest base = Tests.newSuccessTest().build();
		final int actualResult = base.evaluate(roll, 4);
		final int expectedResult = 3;

		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void testNetto() {
		final SuccessTest base = Tests.newSuccessTest().failOn(1).netto()
				.build();
		final int actualResult = base.evaluate(roll, 4);
		// roll includes one `1` => 3 - 1 = 2 successes
		final int expectedResult = 2;
		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void testCriticalMiss() {
		final SuccessTest base = Tests.newSuccessTest().failOn(1).netto()
				.build();
		final int actualResult = base.evaluate(roll, 4);
		// roll includes one `1` => 3 - 1 = 2 successes
		final int expectedResult = 2;
		assertEquals(expectedResult, actualResult);
	}

}
