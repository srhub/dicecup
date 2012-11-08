package com.srhub.test.api;

import com.srhub.dicecup.core.Roll;

public interface OpposedTest {

	public int evaluate(Roll attackRoll, int attackTarget, Roll defendRoll,
			int defendTarget);

}
