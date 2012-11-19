package com.srhub.dicecup.core;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.srhub.dicecup.features.Features;
import com.srhub.dicecup.util.Lists;
import com.srhub.task.core.Result;

public class RolzParser {

	private static final String SUPPORTED_TYPES = "[DLH]";
	private static final String SUPPORTED_SUBTYPES = "[ERFMSXz]";

	private static final String START = "^";
	private static final String END = "$";
	private static final String INT_PLUS = "(\\d+)";
	private static final String TYPE = "(" + SUPPORTED_TYPES + ")";
	private static final String SUBTYPE = "(" + SUPPORTED_SUBTYPES + ")";
	private static final String OPERATOR = "([\\+-/\\*])";

	private static final String REQ_TYPE = INT_PLUS + TYPE + INT_PLUS;

	private static final String OPT_SUBTYPE = "(" + SUBTYPE + INT_PLUS + "?)?";
	private static final String OPT_MATH = "((" + OPERATOR + INT_PLUS + ")+)?";

	private static final Pattern PATTERN = Pattern.compile(
//@formatter:off
		START + REQ_TYPE  + OPT_SUBTYPE + OPT_MATH + END
		//@formatter:on
			);

	private static final int G_NUMBER_OF_DICE = 1;
	private static final int G_TYPE = 2;
	private static final int G_FACES = 3;
	private static final int G_SUBTYPE = 5;
	private static final int G_TARGET = 6;
	private static final int G_MATH = 7;
	private static final int G_OPERATOR = 9;
	private static final int G_OPERAND = 10;

	public Result<Roll> parse(final String code) {

		System.out.println(code);

		final Matcher m = PATTERN.matcher(code);
		if (m.matches()) {
			final int groupCount = m.groupCount();

			for (int i = 1; i <= groupCount; i++) {
				System.out.print(i + ": " + m.group(i) + " -> ");
				System.out.println(m.start(i));
			}

			final boolean hasSubtype = m.start(G_SUBTYPE) >= 0;
			final boolean hasMath = m.start(G_MATH) >= 0;

			// get required parameter
			final int count = Integer.valueOf(m.group(G_NUMBER_OF_DICE));
			final char type = m.group(G_TYPE).charAt(0);
			final int faces = Integer.valueOf(m.group(G_FACES));

			final Roll roll;
			// 1. default case
			if (!hasSubtype) {

				switch (type) {
				case 'D':
					;
					roll = Cup.add(faces, Features.SUM).build().roll(count);
					return new Result<Roll>(roll, roll.sum());
					// drop lowest and sum
				case 'L':
					roll = Cup.add(faces, Features.DROP_LOWEST).build()
							.roll(count);
					return new Result<Roll>(roll, roll.sum());
				case 'H':
					roll = Cup.add(faces, Features.RETURN_HIGHEST).build()
							.roll(count);
					return new Result<Roll>(roll, roll.sum());
				default:
					throw new IllegalArgumentException("Unknown type.");
				}

				// // TODO getMathFeature
				//
				// roll = cup.roll();
				// return new Result<Roll>(roll);
			}

			// found subtype
			if (hasSubtype) {
				final char subType = m.group(G_SUBTYPE).charAt(0);
				final int target = Integer.valueOf(m.group(G_TARGET));

				int successes;
				final int fails;

				switch (subType) {
				case 'E':
					roll = Cup.add(faces).build().roll(count);
					successes = Lists.greaterThanOrEquals(roll.all(), target)
							.size();

					return new Result<Roll>(roll, successes);
				case 'R':
					roll = Cup.add(faces, Features.EXPLODE_AT(faces)).build()
							.roll(count);
					successes = Lists.greaterThanOrEquals(roll.all(), target)
							.size();

					return new Result<Roll>(roll, successes);
				case 'F':
					roll = Cup.add(faces).build().roll(count);
					successes = Lists.greaterThanOrEquals(roll.all(), target)
							.size();
					fails = Lists.count(roll.all(), 1);

					return new Result<Roll>(roll, successes - fails);
				case 'M':
					roll = Cup.add(faces, Features.REROLL_ADD_AT(faces))
							.build().roll(count);
					successes = Lists.greaterThanOrEquals(roll.all(), target)
							.size();

					return new Result<Roll>(roll, successes);
				case 'S':
					roll = Cup.add(faces, Features.REROLL_ADD_AT(faces))
							.build().roll(count);
					successes = Lists.greaterThanOrEquals(roll.all(), target)
							.size();
					fails = Lists.count(roll.all(), 1);

					return new Result<Roll>(roll, successes - fails);
				case 'X':
					roll = Cup.add(faces).build().roll(count);
					successes = Lists.greaterThanOrEquals(roll.all(), target)
							.size();

					if (faces > target) {
						successes += Lists.count(roll.all(), faces);
					}
					return new Result<Roll>(roll, successes);

				default:
					throw new IllegalArgumentException("Unknown sub type.");
				}

			}

			// found operator
			if (hasMath) {
				System.out.println("hasMath");

				while (m.find()) {
					final char operator = m.group(G_OPERATOR).charAt(0);
					final int operand = Integer.valueOf(m.group(G_OPERAND));
					System.out.println("sdsd");
				}

			}

			System.out.println(groupCount);

		}
		return null;
	}
}
