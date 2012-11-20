# Contributing	 #

You need

- Java 1.7
- Maven

It it recommended but not necessary to use Eclipse as your IDE. Once you have your environment setup fork the repo using Github, then

	git clone git://github.com/<username>/dicecup.git
	cd dicecup

Do an initial build to pull all the dependencies to your local machine

	mvn clean test install

## Style ##

I use Eclipse's built in formatter (`Java > Code Style > Formatter`) but with `@formatter` tags enabled.

## Low Hanging Fruit ##

## Dice Systems ##

It would be a nice test of strength for the _dicecup_ API to implement the dice mechanics for various systems and see if and how they can be realized.

Wikipedia supplies a [List of Role-playing game systems](http://en.wikipedia.org/wiki/Role-playing_game_system) but it is even better if you just go ahead and implement your favorite game system.

The most important systems in my personal opinion.

- Dnd
- Sr3
- Sr4
- d20
- D6
- Gurps
- Fudge