package com.srhub.task.api;

public interface OpenTask<T> {

	T evaluate(final int c, final int... count);

}