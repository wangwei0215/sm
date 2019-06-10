package com.sm.business.dao;

import org.apache.commons.lang.time.DateFormatUtils;
import org.iframework.commons.domain.order.Order;
import org.iframework.commons.domain.pager.Pager;
import org.iframework.commons.util.fast.V;
import org.iframework.support.spring.hibernate.dao.BaseHibernateAutoDaoSupport;
import org.springframework.stereotype.Repository;

import com.sm.business.model.BirthDateTime;

import java.util.List;

/**
 * 数据访问层
 * 
 * @author sunhao
 * @email sunhao@myqifa.com
 * @date 2019-05-29 17:46:43
 */
@Repository("birthDateTimeDao")
public class BirthDateTimeDao extends BaseHibernateAutoDaoSupport<BirthDateTime, Long> {
	@Override
	public List<BirthDateTime> findByModel(BirthDateTime model, Order order, Pager pager) {
		model = model == null ? new BirthDateTime() : model;
		StringBuffer hql = new StringBuffer();
		hql.append("from BirthDateTime c where 1=1");
		hql.append(V.isNotEmpty(model.getId()) ? " and c.id=" + model.getId() + " " : "");
		hql.append(V.isNotEmpty(model.getSex()) ? " and c.sex='" + model.getSex() + "' " : "");
		hql.append(V.isNotEmpty(model.getYear()) ? " and c.year='" + model.getYear() + "' " : "");
		hql.append(V.isNotEmpty(model.getMonth()) ? " and c.month='" + model.getMonth() + "' " : "");
		hql.append(V.isNotEmpty(model.getDay()) ? " and c.day='" + model.getDay() + "' " : "");
		hql.append(V.isNotEmpty(model.getHour()) ? " and c.hour='" + model.getHour() + "' " : "");
		hql.append(V.isNotEmpty(model.getDateStart()) && V.isNotEmpty(model.getDateEnd()) ? " and (c.createTime between '" + DateFormatUtils.format(model.getDateStart(), "yyyy-MM-dd HH:mm:ss") + "' and '" + DateFormatUtils.format(model.getDateEnd(), "yyyy-MM-dd HH:mm:ss") + "') " : "");
		hql.append(order != null ? order.toString() : "");
		return this.findByQueryString(hql.toString(),model, pager);
	}
	
}