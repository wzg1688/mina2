package com.wzg.dao;


import java.util.List;

import com.wzg.bean.Entity;

public interface IBaseDAO<T extends Entity> {

	public void save(T o);

	public int deleteById(T o);

	public int update(T o);

	public T get(Class<T> clazz, int id);

	public List<Entity> findAll(Class<T> clazz);

	public List<Entity> queryForPage(Class<T> clazz, T o, int offset, int limit);

	int deleteById(Class<?> clazz, int id);

	int updateForDeleteStatus(Class<T> clazz, int id);
}
