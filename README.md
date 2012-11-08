# README #

Everything starts with simple dice

	int resultD6 = Dices.D6.roll();
	int resultD20 = Dices.D20.roll();
	List<Integer> result5D6 = Dices.D6.roll(5);

A `Cup` is filled with groups of dice. You define the type and features (see below) of a dice group. Only when you want to roll you declare how myn dice of a certain type are rolled. That way you can define a `DiceCup` specific to your game mechanics and reuse it over and over again to roll different results.

	// add d6 to the cup
	Cup cup = new Cup().add(Dices.D6);

	// add d6 and d4 to the cup
	cup = new Cup().add(Dices.D6).add(Dices.D4);

	// add groups named "skill", "pool" bot with d6 
	cup = new Cup().add("skill",Dices.D6).add("pool",Dices.D6);

This is how you roll (roll are randomized, this is just an example)

	Cup cup = new Cup().add(Dices.D20);
	// roll 1d20
	Roll roll = cup.roll(1)
	roll.all();  		// [20]
	//  reuse the cup to roll 5d20
	roll = cup.roll(5)	// [5,17,12,1,20]

If you defined multiple dice groups, yoo can use `varargs` to roll them

	Cup cup = new Cup().add("skill",Dices.D6).add("pool",Dices.D6);
	// roll 3d6 for "skill", 2d6 for "pool" 
	Roll roll = cup.roll(3,2)
	roll.all(); 			// [1,3,4,5,5]
	roll.byId("skill");		// [3,4,5]
	roll.byId("pool");		// [1,5]

## Features ##

Each dice group can also be transformed using a `Feature`. As an example imagine the following scenario: You have a magic sword that makes `D6+D4+2` damage. This can be defined as the following dice cup.

- _Add Constant_ Add constant to each die
- _Subtract Constant_ Subtract a constant from each die
- _Multiply Constant_ Multiply each die with a constant
- _Divide Constant_ Divide each by a constant
- _Expression_ Transform each die result using a mathematical expression
- _Drop Lowest_ Drop the lowest die result(s)
- _Return Lowest_ Only return the lowest die result(s)
- _Drop Highest_ Drop the highest die result(s)
- _Return Highest_ Only return the highest die result(s)
- _Reroll Add_ Reroll all dice with the given number of pips and add to the old result
- _Reroll Replace_ Reroll all dice with the given number of pips and replace the old result
- _Explode_  Reroll dice with given number of pips and cumulatively add dice result to old dice roll result.

## Fixing the Dice ##

Sometimes you need a loaded dice. Even if it just for testing. *DiceCup* offers four mechanisms to fix dice throws:

1. `FixedDice`. A fixed dice always returns the same result.
2. `LoadedDice`. A loaded dice allows you to set discrete probabilities for each dice face e.g. `LoadedDice.set(1, 0.5).set(2, 0.3)build(6)` builds a D6 that has a 50% chance of rolling a `1`, a 30% chance of a `2`, and a 5% chance of rolling either `3`,`4`,`5` or `6`.
3. `CyclicDice`. A cyclic dice allows you to supply a dice with a list of numbers that will be cycled through from left to right.
3. `RandomDice`. The default dice uses Java's default random number generator but you can also supply your own implementation of `java.util.Random` to override the behavior.
