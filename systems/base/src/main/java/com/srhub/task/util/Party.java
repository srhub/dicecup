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
package com.srhub.task.util;

/**
 * Defines parties that can be involved in a competion;
 *
 * @author Oliver Schrenk <oliver.schrenk@gmail.com>
 *
 */
public enum Party {

	/**
	 * Doesn't necessarily have to be a player character. Used to define the
	 * attacking side involved in a competing dice roll.
	 */
	ATTACKER,

	/**
	 * Doesn't necessarily have to be a non player character. Used to define the
	 * defending side involved in a competing dice roll.
	 */
	DEFENDER,

	/**
	 * No party.
	 */
	NONE,

	/**
	 * Both parties.
	 */
	BOTH;

}
