package com.srhub.dicecup.features;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.srhub.dicecup.api.Dice;
import com.srhub.dicecup.dice.RandomDice;

public abstract class AbstractFeatureTest {

	protected Dice dice;
	protected List<Integer> input;
	protected int constant;

	@Before
	public void setup() {
		input = new LinkedList<>();
		input.add(1);
		input.add(4);
		input.add(5);
		input.add(6);
		input.add(6);

		constant = 2;

		dice = new RandomDice(6);
	}

	@Test
	public void testSize() {
		assertEquals(5, input.size());
	}
}
