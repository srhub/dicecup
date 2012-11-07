# README #

Per default **DiceCup**. 

	// add d6 to the cup
	Cup cup = new Cup().add(6);

But you can also define the number of sides

	// add d6 and d20 to the cup
	Cup cup = new Cup().add(6).add(20); 

You can also name the dice groups you are adding to the cup

	Cup cup;
	// add groups named "skill", "pool" bot with d6 
	cup = new Cup().add("skill",6).add("pool",6);

This is how you roll (all results are of course random, these are just examples) a single dice

	Cup cup = new Cup().add(20);
	// roll 1d20
	Roll roll = cup.roll(1)
	roll.all(); 			// [20]

Or if you added multiple dice to the cup

	Cup cup = new Cup().add("skill",6).add("pool",6);
	// roll 3d6 for "skill", 2d6 for "pool" 
	Roll roll = cup.roll(3,2)
	roll.all(); 			// [1,3,4,5,5]
	roll.byId("skill");		// [3,4,5]
	roll.byId("pool");		// [1,5]

## Features ##

**DiceCup** can change the result of a single dice group roll using the following features.

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

## Loaded Dice ##

Sometimes you need a loaded dice. *DiceCup* allows you to supply your random number generator for each dice. An example can be found in `com.srhub.dicecup.extra.CyclicIntegerRandom` that cycles through a supplied list of integer to generate integers. 

If you need a loaded dice with preset discrete probabilities for each die face you can use `com.srhub.dicecup.dice.LoadedDice`.