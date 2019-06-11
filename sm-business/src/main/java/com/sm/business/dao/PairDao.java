package com.sm.business.dao;

import com.sm.business.model.Birth;
import com.sm.business.model.Pair;
import org.iframework.commons.domain.order.Order;
import org.iframework.commons.domain.pager.Pager;
import org.iframework.commons.util.fast.V;
import org.iframework.support.spring.hibernate.dao.BaseHibernateAutoDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 数据访问层
 * 
 * @author yangrui
 * @date 2019-06-10
 */
@Repository("pairDao")
public class PairDao extends BaseHibernateAutoDaoSupport<Pair, Long> {

	@Override
	public List<Pair> findByModel(Pair model, Order order, Pager pager) {
		model = model == null ? new Pair() : model;
		StringBuffer hql = new StringBuffer();
		hql.append("from Pair c where 1=1");
		hql.append(V.isNotEmpty(model.getId()) ? " and c.id=" + model.getId() + " " : "");
		hql.append(V.isNotEmpty(model.getZodiac1()) ? " and c.zodiac1='" + model.getZodiac1() + "' " : "");
		hql.append(V.isNotEmpty(model.getZodiac2()) ? " and c.zodiac2='" + model.getZodiac2() + "' " : "");
		hql.append(order != null ? order.toString() : "");
		System.out.println("===============" + hql.toString());
		return this.findByQueryString(hql.toString(),model, pager);
	}
	
}