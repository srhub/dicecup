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

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.srhub.dicecup.util.Lists;

import de.congrace.exp4j.Calculable;
import de.congrace.exp4j.ExpressionBuilder;
import de.congrace.exp4j.UnknownFunctionException;
import de.congrace.exp4j.UnparsableExpressionException;

/**
 * Transform each dice result using a mathematical expression. Use
 * <code>x</code> as the variable to be replaced with the value of each dice
 * result.
 *
 * <p>
 * Example: <code>3 + x * 5</code>
 *
 * <p>
 * Combine with {@link Features#SUM} to apply the change to the sum of all dice
 * instead of each dice
 *
 * @author Oliver Schrenk <oliver.schrenk@gmail.com>
 */
public class ExpressionFeature extends AbstractFeature {

	/** The expression. */
	private final String expression;

	/**
	 * Instantiates the feature.
	 *
	 * @param expression
	 *            the expression
	 */
	public ExpressionFeature(final String expression) {
		this.expression = expression;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.srhub.dicecup.features.AbstractFeature#apply(java.util.List)
	 */
	@Override
	public List<Integer> apply(final List<Integer> input) {
		if (input.isEmpty()) {
			return input;
		}

		final List<Integer> output = new LinkedList<>();
		final Calculable calc;
		try {
			calc = new ExpressionBuilder(expression).withVariableNames("x")
					.build();
		} catch (UnknownFunctionException | UnparsableExpressionException e) {
			throw new RuntimeException(e);
		}

		if (!expression.contains("x")) {
			final int calculate = (int) calc.calculate();
			return Lists.prefilled(input.size(), calculate);

		}

		for (final int i : input) {
			calc.setVariable("x", i);
			final int calculate = (int) calc.calculate();
			output.add(calculate);
		}

		Collections.sort(output);
		return output;
	}

	@Override
	public int hashCode() {
		final int prime = 47;
		int result = 1;
		result = prime * result
				+ ((expression == null) ? 0 : expression.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final ExpressionFeature other = (ExpressionFeature) obj;
		if (expression == null) {
			if (other.expression != null) {
				return false;
			}
		} else if (!expression.equals(other.expression)) {
			return false;
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ExpressionFeature [expression=" + expression + "]";
	}

}
