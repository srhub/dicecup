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
package com.srhub.task.api;

import com.srhub.task.core.Party;

/**
 * A glitch is an extraordinary event when rolling dice, it can either symbolize
 * a critical hit or a critical miss.
 *
 * @author Oliver Schrenk <oliver.schrenk@gmail.com>
 */
public interface Glitch {

	/**
	 * Return the id of the glitch
	 *
	 * @return the id if the glitch or <code>null</code> if none was given
	 */
	public String id();

	/**
	 * The absolute number of dice responsible for the glitch
	 *
	 * @return the number of dice responsible for the glitch
	 */
	public int absolute();

	/**
	 * The percentage of dice responsible for the glitch
	 *
	 * @return the percentage of dice responsible for the glitch
	 */
	public double percentage();

	/**
	 * The {@link Party} that is responsible for the glitch
	 *
	 * @return the party
	 */
	public Party party();

}
