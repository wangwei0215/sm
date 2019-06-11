package com.sm.business.dao;

import com.sm.business.model.Birth;
import org.apache.commons.lang.time.DateFormatUtils;
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
@Repository("birthDao")
public class BirthDao extends BaseHibernateAutoDaoSupport<Birth, Long> {

	@Override
	public List<Birth> findByModel(Birth model, Order order, Pager pager) {
		model = model == null ? new Birth() : model;
		StringBuffer hql = new StringBuffer();
		hql.append("from Birth c where 1=1");
		hql.append(V.isNotEmpty(model.getId()) ? " and c.id=" + model.getId() + " " : "");
		hql.append(V.isNotEmpty(model.getMonth()) ? " and c.month=" + model.getMonth() + " " : "");
		hql.append(V.isNotEmpty(model.getDay()) ? " and c.day=" + model.getDay() + " " : "");
		hql.append(order != null ? order.toString() : "");
		System.out.println("===============" + hql.toString());
		return this.findByQueryString(hql.toString(),model, pager);
	}
	
}