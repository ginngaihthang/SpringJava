package com.mmit.beans;

public interface MyRepository<T> {

	public void save(T t);
	public T findBuId(int id);
}
