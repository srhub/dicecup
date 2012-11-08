package com.srhub.dicecup.dice;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class CyclicDice extends AbstractDice {

	private final int faces;
	private final List<Integer> numbers;

	public CyclicDice(final int faces, final List<Integer> numbers) {
		this.faces = faces;
		this.numbers = numbers;
		// shift right once so that the first #next return first index of list
		Collections.rotate(this.numbers, 1);
	}

	@Override
	public int roll() {
		Collections.rotate(numbers, -1);
		return numbers.get(0);
	}

	@Override
	public int getFaces() {
		return faces;
	}

	public static Builder add(final int number) {
		final List<Integer> numbers = new LinkedList<>();
		numbers.add(number);
		return new Builder(numbers);
	}

	public static Builder add(final int... numbers) {
		final List<Integer> n = new LinkedList<>();
		for (final int i : numbers) {
			n.add(i);
		}
		return new Builder(n);
	}

	public static Builder add(final List<Integer> numbers) {
		return new Builder(numbers);
	}

	public static class Builder {

		private final List<Integer> numbers;

		public Builder(final List<Integer> numbers) {
			this.numbers = numbers;
		}

		public Builder add(final int number) {
			numbers.add(number);
			return this;
		}

		public Builder add(final int... numbers) {
			for (final int i : numbers) {
				this.numbers.add(i);
			}
			return this;
		}

		public Builder add(final List<Integer> numbers) {
			this.numbers.addAll(numbers);
			return this;
		}

		public CyclicDice build(final int faces) {
			return new CyclicDice(faces, numbers);
		}

	}

}
