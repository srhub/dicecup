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
package com.srhub.dicecup.core;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Dice roll results.
 *
 * @author Oliver Schrenk <oliver.schrenk@gmail.com>
 */
public class Roll {

	/** The groups. */
	private final Set<Group> groups;

	/** The result. */
	private final Map<Group, List<Integer>> result;

	/**
	 * Instantiates a new roll.
	 *
	 * @param results
	 *            the results
	 */
	Roll(final Map<Group, List<Integer>> results) {
		this.groups = results.keySet();
		this.result = results;
	}

	/**
	 * Returns the dice roll results.
	 *
	 * @return the dice roll results.
	 */
	public List<Integer> all() {
		final List<Integer> all = new LinkedList<>();
		for (final Group group : groups) {
			all.addAll(result.get(group));
		}
		Collections.sort(all);
		return all;
	}

	/**
	 * Return the dice groups
	 *
	 * @return the dice groups
	 */
	public Set<Group> groups() {
		return groups;
	}

	/**
	 * Return dice roll results by {@link Group}
	 *
	 * @param group
	 *            the group
	 * @return the dice roll results.
	 */
	public List<Integer> byGroup(final Group group) {
		return result.get(group);
	}

	/**
	 * Return dice roll results by the group id.
	 *
	 * @param id
	 *            the id
	 * @return the dice roll results.
	 */
	public List<Integer> byId(final String id) {
		final List<Integer> dice = new LinkedList<>();
		for (final Group group : groups) {
			if (group.getId() != null && group.getId().equals(id)) {
				dice.addAll(result.get(group));
			}
		}
		Collections.sort(dice);
		return dice;
	}

	/**
	 * Sum of all dice.
	 *
	 * @return the sum of all dice.
	 */
	public int sum() {
		int sum = 0;
		for (final Group group : groups) {
			final List<Integer> list = result.get(group);
			for (final int i : list) {
				sum += i;

			}
		}
		return sum;
	}

	/**
	 * Number of all dice in the orginary dice cup
	 *
	 * @return the int
	 */
	public int size() {
		int sum = 0;
		for (final Group group : groups) {
			sum += result.get(group).size();
		}
		return sum;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((groups == null) ? 0 : groups.hashCode());
		result = prime * result
				+ ((this.result == null) ? 0 : this.result.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Roll other = (Roll) obj;
		if (groups == null) {
			if (other.groups != null) {
				return false;
			}
		} else if (!groups.equals(other.groups)) {
			return false;
		}
		if (result == null) {
			if (other.result != null) {
				return false;
			}
		} else if (!result.equals(other.result)) {
			return false;
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Roll [result=" + result + "]";
	}

}
