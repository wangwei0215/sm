package com.sm.business.dao;


import com.sm.business.model.Haul;
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
 * @author sunhao
 * @email sunhao@myqifa.com
 * @date 2019-05-29 18:25:45
 */
@Repository("haulDao")
public class HaulDao extends BaseHibernateAutoDaoSupport<Haul, Long> {
	@Override
	public List<Haul> findByModel(Haul model, Order order, Pager pager) {
		model = model == null ? new Haul() : model;
		StringBuffer hql = new StringBuffer();
		hql.append("from Haul c where 1=1");
		hql.append(V.isNotEmpty(model.getId()) ? " and c.id=" + model.getId() + " " : "");
		hql.append(V.isNotEmpty(model.getBirthDateTimeId()) ? " and c.birthDateTimeId=" + model.getBirthDateTimeId() + " " : "");
		hql.append(V.isNotEmpty(model.getContent()) ? " and c.content='" + model.getContent() + "' " : "");
		hql.append(V.isNotEmpty(model.getDateStart()) && V.isNotEmpty(model.getDateEnd()) ? " and (c.createTime between '" + DateFormatUtils.format(model.getDateStart(), "yyyy-MM-dd HH:mm:ss") + "' and '" + DateFormatUtils.format(model.getDateEnd(), "yyyy-MM-dd HH:mm:ss") + "') " : "");
		hql.append(order != null ? order.toString() : "");
		return this.findByQueryString(hql.toString(),model, pager);
	}
	
}