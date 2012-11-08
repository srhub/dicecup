package com.srhub.dicecup.dice;

import static com.srhub.dicecup.dice.Dices.D6;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class DicesTest {

	@Test
	public void test() {

		assertTrue(D6.roll() > 0);

	}
}
