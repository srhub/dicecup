package com.srhub.task.api;

public interface SuccessTask<T> {

	T evaluate(final int target, final int c, final int... count);

}