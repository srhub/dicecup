# README #

Per default **DiceCup** uses _D6_. 

	// add 5d6 to the cup
	DiceCup cup = new DiceCup().add(5);

But you can also define the number of sides

	// add 3d6 and 2d20 to the cup
	DiceCup cup = new DiceCup().add(3,6).add(2,20); 

You can also name the dice groups you are adding to the cup

	DiceCup cup;
	cup = new DiceCup().add("skill", 3).add("skill", 2);
	cup = new DiceCup().add("skill",3,6).add("pool",2,6);

This is how you roll

	Roll roll = cup.roll();
	// of course the results are random, this is an example
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

Sometimes you need a loaded dice. *DiceCup* allows you to supply your your random number generator for each dice. An example can be found in `com.srhub.dicecup.extra.CyclicIntegerRandom` that cycles through a supplied list of integer to generate integers. 

If you need a loaded dice with preset discrete probabilities for each die face you can use `com.srhub.dicecup.dice.LoadedDice`.