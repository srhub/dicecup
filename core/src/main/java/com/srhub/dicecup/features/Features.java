/*
 * Copyright 2012 Oliver Schrenk
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.srhub.dicecup.features;

import com.srhub.dicecup.api.Feature;

/**
 * Transform dice throws.
 *
 * @author Oliver Schrenk <oliver.schrenk@gmail.com>
 */
public class Features {

	/**
	 * Instantiates a new features.
	 */
	private Features() {
		// prevent from instantiation
	}

	/**
	 * Add a constant to each dice.
	 *
	 * <p>
	 * Combine with {@link Features#SUM} to apply the change to the sum of all
	 * dice instead of each dice
	 *
	 * @param constant
	 *            the constant
	 * @return the feature
	 */
	public static Feature ADD_CONSTANT(final int constant) {
		return new AddConstantFeature(constant);
	}

	/**
	 * Subtract a constant from each dice.
	 *
	 * <p>
	 * Combine with {@link Features#SUM} to apply the change to the sum of all
	 * dice instead of each dice
	 *
	 * @param constant
	 *            the constant
	 * @return the feature
	 */
	public static Feature SUBTRACT_CONSTANT(final int constant) {
		return new SubtractConstantFeature(constant);
	}

	/**
	 * Multiply a constant to each dice.
	 *
	 * <p>
	 * Combine with {@link Features#SUM} to apply the change to the sum of all
	 * dice instead of each dice
	 *
	 * @param constant
	 *            the constant
	 * @return the feature
	 */
	public static Feature MULTIPLY_CONSTANT(final int constant) {
		return new MultiplyConstantFeature(constant);
	}

	/**
	 * Divide each die by a constant.
	 *
	 * <p>
	 * Combine with {@link Features#SUM} to apply the change to the sum of all
	 * dice instead of each dice
	 *
	 * @param constant
	 *            the constant
	 * @return the feature
	 */
	public static Feature DIVIDE_CONSTANT(final int constant) {
		return new DivideConstantFeature(constant);
	}

	/**
	 * Transform each dice result using a mathematical expression. Use
	 * <code>x</code> as the variable to be replaced with the value of each dice
	 * result.
	 *
	 * <p>
	 * Example: <code>3 + x * 5</code>
	 *
	 * <p>
	 * Combine with {@link Features#SUM} to apply the change to the sum of all
	 * dice instead of each dice
	 *
	 * @param expression
	 *            the expression
	 * @return the feature
	 */
	public static Feature EXPRESSION(final String expression) {
		return new ExpressionFeature(expression);
	}

	/** Drop the lowest die result. */
	public static Feature DROP_LOWEST = new DropLowestFeature(1);

	/**
	 * Drop the <code>n</code>lowest dice results.
	 *
	 * @param n
	 *            the n
	 * @return the feature
	 */
	public static Feature DROP_N_LOWEST(final int n) {
		return new DropLowestFeature(n);
	}

	/** Drop the lowest die result. */
	public static Feature DROP_HIGHEST = new DropHighestFeature(1);

	/**
	 * Drop the <code>n</code>lowest dice results.
	 *
	 * @param n
	 *            the n
	 * @return the feature
	 */
	public static Feature DROP_N_HIGHEST(final int n) {
		return new DropHighestFeature(n);
	}

	/** Only return the highest result. */
	public static Feature RETURN_HIGHEST = new ReturnHighestFeature(1);

	/** Only return the highest result. */
	public static Feature RETURN_LOWEST = new ReturnLowestFeature(1);

	/**
	 * Only return the <code>n</code> highest result.
	 *
	 * @param n
	 *            the n
	 * @return the feature
	 */
	public static Feature RETURN_N_HIGHEST(final int n) {
		return new ReturnHighestFeature(n);
	}

	/**
	 * Only return the <code>n</code> highest result.
	 *
	 * @param n
	 *            the n
	 * @return the feature
	 */
	public static Feature RETURN_N_LOWEST(final int n) {
		return new ReturnLowestFeature(n);
	}

	/**
	 * Reroll all dice with the given number of pips and replace the new result
	 * with the old one.
	 *
	 * @param rerollAt
	 *            the reroll at
	 * @return the feature
	 */
	public static Feature REROLL_REPLACE_AT(final int rerollAt) {
		return new RerollReplaceFeature(rerollAt);
	}

	/**
	 * Reroll all dice with the given number of pips and add add the new result
	 * to the roll result while keeping the old dice result.
	 *
	 * @param rerollAt
	 *            the number to reroll the dice at
	 * @return the feature
	 */
	public static Feature REROLL_ADD_AT(final int rerollAt) {
		return new RerollAddFeature(rerollAt);
	}

	/**
	 * Explode all dice with the given number of pips - meaning that a dice gets
	 * rerolled and the new result cumulatively added until the new result
	 * doesn't show a number that has to be exploded.
	 *
	 * @param explodeAt
	 *            the number to explode the dice at
	 * @return the feature
	 */
	public static Feature EXPLODE_AT(final int explodeAt) {
		return new ExplodeFeature(explodeAt);
	}

	/** Return the sum of all dice. */
	public static Feature SUM = new SumFeature();

}
