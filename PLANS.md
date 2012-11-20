# Plans #

If you have some time please review the changes I intend to implement.

## Predefined Cups ##

Currently each cup has to be filled and redefined every time you intend to use it. A better or additional approach would be to define a cup and its features

	Cup cup = new Cup().add(D6, Features.DROP_LOWEST);
	cup.roll(5); 	// roll 5D6 and drop the lowest
	cup.roll(9);	// roll 9D6 and drop the lowest

Using `varargs` one can roll multiple dice groups.

	Cup cup = new Cup()
		.add(D6, Features.DROP_LOWEST)
		.add(D4)
		.add(D8);
	// roll 4D6, 1D4, 2D8
	cup.roll(4,1,2);

If the `varargs` parameter has too few (or too many) parameter a `RuntimeException` is thrown.

This is good as developers can define dice systems and reuse the defined `Cup` object.

**Open Questions**

How can thread safety be achieved? A good idea would be to have a `CupBuilder` build an immutable instance of a cup and reusing that should be thread safe, if the `#roll()` doesn't violate thread safe access to any fields.

## Basic Test ##

I'm not very satisfied with current API to run tests. Especially returning `Result<ResultType>` isn't very good. It isn't obvious and somewhat limiting.

I'd like to have a more general approach and leave the return type to the user itself.

A general approach to build test might look this

	Test.failsOn(1).build()

Define a `BoundType`. `OPEN` doesn't include the target, while `CLOSED` does.If the bound type is the parameter after the target it means greater than (or equals) the target number. If is the first it means lesser than (or equals) the target number. 

	#failsOn(6, OPEN)
	#failsOn(6, CLOSED)
	#failsOn(OPEN, 6)
	#failsOn(CLOSED, 6)

An exemplary success test for Shadowrun

	Test test = Test.failsOn(1).build();
	test.evaluate (roll, target);

Some game systems count successes only, some netto successes subtracting the fails from the successes.

	Test test = Test.failsOn(1).netto().build();

Combined with the predefined cups feature one can define complete game systems. Which brings me to this idea:
	
	Cup cup = new Cup()
		.add(D6, Features.DROP_LOWEST)
		.add(D4)
		.add(D8);
	Test test = Test.failsOn(1).using(cup).build();
	int target = TARGET_NUMBER;
	// roll 4D6, 1D4, 1D8 against the target
	test.evaluate(target, 4, 1, 2)

Or by using an indirection using a `Task` object

	Cup cup = new Cup()
		.add(D6, Features.DROP_LOWEST)
		.add(D4)
		.add(D8);
	Test test = Test.failsOn(1).build();
	Task task = new Task (cup, test);
	int target = TARGET_NUMBER;
	// roll 4D6, 1D4, 1D8 against the target
	task.evaluate(target, 4, 1, 2)

## Test types ##

Shadowrun 3 offers four different kinds of tests that might be good archetypes

**Open Test** Doesn't use a target number and just returns the highest result
	
	OpenTest openTest = OpenTest.failsOn(1).build();
	int result = openTest.evaluate(roll); // internally uses roll.sum()

**Success Test** Roll a number of dice equal to the skill/attribute/... rating against a target number.

	SuccessTest successTest = SuccessTest.failsOn(1).build();
	int result = successTest.evaluate(roll, target)

**Opposed Test** Two involved parties roll success test against each other using the skill of the opponent as the target number.

	OpposedTest opposedTest = OpposedTest.failsOn(1).build();
	int result = opposedTest.evaluate(rollA, targetA, rollB, targetB);

Positive result map to netto successes for the character that made `rollA`, (normally a player character) while negative netto successes map to succeses by the character that made `rollB` (normally an NPC).

**Success Contest** Like an opposed test, only asymmetrical. Each involved party rolls a success test based on another set of rating and target number.

The API suggestion for the opposed test is general enough to also map the success contest.
	
## Critical Hit & Miss #

Most game systems offer critical hits and/or critical failures. A possible extension to the above builder pattern could be something like:

	SuccessTest.criticalHitOn(20).criticalMissOn(1).build()

supporting the same bound type strategy. For most games this isn't enough though. For example a critical miss in Shadowrun 3 occurs only when all dice show a `1`, while in Shadowrun 4 it occurs if half of the rolled dice show `1`. An idea is to offer a percentage at which a critical hit/miss occurs.

	SuccessTest.criticalHitOn(20).criticalMissOn(1, 0.5).build()	

**Open Question** Shadowrun 4 uses two different levels of failures, called a glitch and a critical glitch. It might be possible to model this like so:

	SuccessTest.criticalMissOn(1, 0.5).criticalMissOn(1, 1, CRITICAL).build()

where `CRITICAL` is a `String` or an `Enum`

**Open Question** How do I know if a critical hit or miss has been made? The first idea was to throw `RuntimeException`s for each event but this does not feel right. The other solution would be to return a Java object offering methods to check for the occurrence of a critical hit/miss.

	Result result = successTest.result()
	result.isCriticalHit();
	result.isCriticalMiss();
	result.getSuccesses();
	result.getFails();

This solution feels kind of clunky. In addition this would also mean that for each different test (open, success, opposed) that there is a different result object and that game systems that don't include the idea of critical hits or misses would get an object with methods back that just aren't important. Throwing exceptions has the advantage that it can be used as a building block. The most popular game systems can be  implemented as examples and can offer more specific APIs.

## Task API ##

I think the API of both, `core` and `test` are taking shape, and are going to be good building blocks. But also I can see a more high level approach using `core` and `test`.

	SuccessTask succesTask = new SuccessTask(cup, successTest);
	succesTask.roll(target, count,...)

While `core` and `test` are offering a low level game mechanics API, `task` creates building blocks for specific games. 

## Name change ##

Nearing the completion of `test` API and already conceptualizing the `task` API, I think a change of the name is in order as DiceCup doesn't encompasses the scope of the project any more. I also somewhat dissatisfied with the umbrella group and package being `com.srhub` as `srhub` seems to be Shadowrun centric but then again, SHadowrun was the motivating factor. Maybe I keep `srhub` as the umbrella organization on [Github](https://github.com/) but change the group id of the maven artifact to something else.

**Brainstorming**

- Game Mechanics
- Dice Mechanics
- Dice Monkey
- Dice, Tests & Tasks

## Cup Features ##

The example in the `README.md` about the magic sword made me think about the Cup API. 

	import static com.srhub.dicecup.dice.Dices.*;
	...
	Cup cup = Cup.add(D6).add(D4, Features.ADD_CONSTANT(2)).build();
	cup.roll(1,1);	// 

It would better from the users perspective to add a constant to the cup and not to a specific dice group, like so
	
	import static com.srhub.dicecup.dice.Dices.*;
	...
	Cup cup = Cup.add(D6).add(D4).build(Features.ADD_CONSTANT(2));

The question then is to what the feature is applied. To each die, to each dice group, or to the cup? Thinking along this line leads to thinking about differentiating features into die, dice group and cup features, which may also lead to some interesting object oriented interface designs.

## Reroll a group ##

A new feature. Be able to reroll a dice group.

## Situations ##

The next layer on top the task API is the _situation_ layer.  At this point we leave dice mechanics behind and enter the realm of specific game rules. A situation defines the target number and the modifier, depending on the character and his surroundings. An exemplary situation would be resolving a single ranged combat attack:

The distance to the target defines the _base target_ number, while the condition of the character, is he walking or running, is he aiming, is he wounded and environmental characteristics, like the lighting or the terrain might modify this base target.

Resolving a situation is easy if all criteria for modifier are independent from each other. Computing the final target number becomes an entirely different beast if the modifier interact which each other or might change the target number in way that can't be easily expressed in simple arithmetics.

But it can be said that if certain _criteria_ are met, that the base target is modified or stated differently, that if certain _predicates_ hold true, that certain _functions_ will be applied.  

I propose using one (or more) condition tree that hold values that certain criteria ask for existence or a value. That tree might just be something like a [Trie](http://en.wikipedia.org/wiki/Trie). The idea is to store character (or world) information in a tree. For example a character can look like this:

	character
		- attributes
			- charisma 3
			- willpower 5
			...
		- skills
			- cooking 5

You get the idea. A `SituationResolver` traverses that tree and supplies the `Situation` with all the information it needs.

## Resources ##

- [List of Role-playing game systems](http://en.wikipedia.org/wiki/Role-playing_game_system). Wikipedia.
- [A Treatise on Different Dice-rolling Mechanics in RPGs](http://rpg-design.wikidot.com/evaluation). A qualitative analysis of dice-mechanics for role-playing systems.
- [Troll](http://www.diku.dk/~torbenm/Troll/). A language for specifying dice-roll mechanisms.
