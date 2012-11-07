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

import java.util.Arrays;

import com.srhub.dicecup.api.Dice;
import com.srhub.dicecup.api.Feature;

/**
 * A group of dice.
 *
 * @author Oliver Schrenk <oliver.schrenk@gmail.com>
 */
public class Group {

	/** The id. */
	private final String id;

	/** The dice. */
	private final Dice dice;

	/** The features. */
	private final Feature[] features;

	/**
	 * Instantiates a new group.
	 *
	 * @param id
	 *            the id
	 * @param dice
	 *            the dice
	 * @param features
	 *            the features
	 */
	Group(final String id, final Dice dice, final Feature[] features) {
		this.id = id;
		this.dice = dice;
		this.features = features;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Gets the dice.
	 *
	 * @return the dice
	 */
	public Dice getDice() {
		return dice;
	}

	/**
	 * Gets the features.
	 *
	 * @return the features
	 */
	public Feature[] getFeatures() {
		return features;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dice == null) ? 0 : dice.hashCode());
		result = prime * result + Arrays.hashCode(features);
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

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
		final Group other = (Group) obj;
		if (dice == null) {
			if (other.dice != null) {
				return false;
			}
		} else if (!dice.equals(other.dice)) {
			return false;
		}
		if (!Arrays.equals(features, other.features)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Group ["
				+ (id != null ? "id=" + id + ", " : "")
				+ (dice != null ? "dice=" + dice + ", " : "")
				+ (features != null ? "features=" + Arrays.toString(features)
						: "") + "]";
	}

}
