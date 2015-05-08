package com.wzg.dao.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.wzg.bean.Entity;
import com.wzg.dao.IBaseDAO;
import com.wzg.utils.MyBatisUtils;
import com.wzg.utils.MyLog;

// SELECT count(*) num FROM starland.g_users_table where platform_key =
// "91";

public class BaseDao<T extends Entity> implements IBaseDAO<T> {

	private static final MyLog log = MyLog.getLogger(BaseDao.class);

	public void save(T o) {
		log.I("save en Entity ==>> " + o);
		SqlSession session = MyBatisUtils.getSession();
		if (session != null) {
			String sqlName = o.getClass().getName() + ".save";
			log.I("sql name ==>> " + sqlName);
			int result = session.insert(sqlName, o);
			log.I("save change record count -> " + result);
		}
	}

	public int deleteById(T o) {
		return deleteById(o.getClass(), o.getId());
	}

	@Override
	public int deleteById(Class<?> clazz, int id) {
		log.I("delete " + clazz.getName() + " By Id -> " + id);
		int result = 0;
		SqlSession session = MyBatisUtils.getSession();
		if (session != null) {
			String sqlName = clazz.getName() + ".deleteById";
			log.I("sql name ==>> " + sqlName);
			result = session.delete(sqlName, id);
		}
		log.I("delete by id change count -> " + result);
		return result;
	}

	@Override
	public int update(T o) {
		log.I("update an Entity -->> " + o);
		int result = 0;
		if (o != null) {
			SqlSession session = MyBatisUtils.getSession();
			if (session != null) {
				String sqlName = o.getClass().getName() + ".update";
				log.I("sql name ==>> " + sqlName);
				result = session.update(sqlName, o);
			}
		}
		log.I("update change count -> " + result);
		return result;
	}

	@Override
	public T get(Class<T> clazz, int id) {
		log.I("get an Entity -->>" + clazz.getName() + " , by id -> " + id);
		T o = null;
		SqlSession session = MyBatisUtils.getSession();
		if (session != null) {
			String sqlName = clazz.getName() + ".get";
			log.I("sql name ==>> " + sqlName);
			o = session.selectOne(sqlName, id);
		}
		return o;
	}

	@Override
	public List<Entity> findAll(Class<T> clazz) {
		List<Entity> result = null;
		log.I("findAll Entity -->>" + clazz.getName());
		SqlSession session = MyBatisUtils.getSession();
		if (session != null) {
			String sqlName = clazz.getName() + ".findAll";
			log.I("sql name ==>> " + sqlName);
			result = session.selectList(sqlName);
		}
		return result;
	}

	@Override
	public List<Entity> queryForPage(Class<T> clazz, T o, int offset, int limit) {
		List<Entity> result = null;
		log.I("queryForPage Entity -->>" + clazz.getName() + " , offset -> " + offset + " , limit -> " + limit);
		SqlSession session = MyBatisUtils.getSession();
		if (session != null) {
			String sqlName = clazz.getName() + ".queryForPage";
			log.I("sql name ==>> " + sqlName);
			result = session.selectList(sqlName, o, new RowBounds(offset, limit));
		}
		return result;
	}

	@Override
	public int updateForDeleteStatus(Class<T> clazz, int id) {
		log.I("updateForDeleteStatus Entity -->>" + clazz.getName() + " , id -> " + id);
		int result = 0;
		SqlSession session = MyBatisUtils.getSession();
		if (session != null) {
			String sqlName = clazz.getName() + ".updateForDeleteStatus";
			log.I("sql name ==>> " + sqlName);
			result = session.update(sqlName, id);
		}
		log.I("update change count -> " + result);
		return result;
	}

}
