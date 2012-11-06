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
	roll.all(); 			// [1,3,4,5x2]
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

## Random and Loaded Dice ##

Per default a dice roll is randomized using `java.util.Random`. You can also supply your own implementation of a `Random` number generator, such as `com.srhub.dicecup.dice.CyclicRandom` that cycles through predefined values.

If you need a loaded dice with preset discrete probabilities for each die face you can use `com.srhub.dicecup.dice.LoadedDice`.