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
package com.srhub.dicecup.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Static helper methods
 *
 * @author Oliver Schrenk <oliver.schrenk@gmail.com>
 */
public class Lists {

	/**
	 * Count the number of elements in the given list
	 *
	 * @param <T>
	 *            the generic type
	 * @param list
	 *            the list
	 * @param element
	 *            the element
	 * @return the int
	 */
	public static <T> int count(final List<T> list, final T element) {
		return Collections.frequency(list, element);
	}

	/**
	 * The number of elements greater than the given target.
	 *
	 * @param <T>
	 *            the generic type
	 * @param list
	 *            the list
	 * @param target
	 *            the target
	 * @return the list
	 */
	public static <T extends Comparable<T>> List<T> greaterThan(
			final List<T> list, final T target) {

		final List<T> result = new LinkedList<>();

		final Iterator<T> iterator = list.iterator();
		while (iterator.hasNext()) {
			final T next = iterator.next();
			if (next.compareTo(target) > 0) {
				result.add(next);
			}
		}

		return result;
	}

	/**
	 * The number of elements greater than the given target or equal to zero.
	 *
	 * @param <T>
	 *            the generic type
	 * @param list
	 *            the list
	 * @param target
	 *            the target
	 * @return the list
	 */
	public static <T extends Comparable<T>> List<T> greaterThanOrEquals(
			final List<T> list, final T target) {

		final List<T> result = new LinkedList<>();

		final Iterator<T> iterator = list.iterator();
		while (iterator.hasNext()) {
			final T next = iterator.next();
			if (next.compareTo(target) >= 0) {
				result.add(next);
			}
		}

		return result;
	}

	/**
	 * The number of elements lesser than the given target or equal to zero
	 *
	 * @param <T>
	 *            the generic type
	 * @param list
	 *            the list
	 * @param target
	 *            the target
	 * @return the list
	 */
	public static <T extends Comparable<T>> List<T> lesserThanOrEquals(
			final List<T> list, final T target) {

		final List<T> result = new LinkedList<>();

		final Iterator<T> iterator = list.iterator();
		while (iterator.hasNext()) {
			final T next = iterator.next();
			if (next.compareTo(target) <= 0) {
				result.add(next);
			}
		}

		return result;
	}

	/**
	 * The number of elements greater than the given target.
	 *
	 * @param <T>
	 *            the generic type
	 * @param list
	 *            the list
	 * @param target
	 *            the target
	 * @return the list
	 */
	public static <T extends Comparable<T>> List<T> lesserThan(
			final List<T> list, final T target) {

		final List<T> result = new LinkedList<>();

		final Iterator<T> iterator = list.iterator();
		while (iterator.hasNext()) {
			final T next = iterator.next();
			if (next.compareTo(target) < 0) {
				result.add(next);
			}
		}

		return result;
	}

	/**
	 * Prefilled.
	 *
	 * @param <T>
	 *            the generic type
	 * @param count
	 *            the count
	 * @param element
	 *            the element
	 * @return the list
	 */
	public static <T> List<T> prefilled(final int count, final T element) {
		final List<T> list = new ArrayList<>(count);
		for (int i = 0; i < count; i++) {
			list.add(element);
		}
		return list;
	}

	/**
	 * Returns the maximum element of the given collection.
	 *
	 * @param <T>
	 *            the generic type
	 * @param list
	 *            the list
	 * @return the t
	 */
	public static <T extends Comparable<T>> T max(final List<T> list) {
		return Collections.max(list);
	}

	/**
	 * Returns the minimum element of the given collection.
	 *
	 * @param <T>
	 *            the generic type
	 * @param list
	 *            the list
	 * @return the t
	 */
	public static <T extends Comparable<T>> T min(final List<T> list) {
		return Collections.min(list);
	}
}
