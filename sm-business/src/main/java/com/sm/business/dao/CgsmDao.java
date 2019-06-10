package com.sm.business.dao;

import java.util.List;

import org.apache.commons.lang.time.DateFormatUtils;
import org.iframework.commons.domain.order.Order;
import org.iframework.commons.domain.pager.Pager;
import org.iframework.commons.util.fast.V;
import org.iframework.support.spring.hibernate.dao.BaseHibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.sm.business.model.Cgsm;

/**
 * 称骨算命表数据访问层
 * 
 * @author sunhao
 * @email sunhao@myqifa.com
 * @date 2019-06-10 16:10:37
 */
@Repository("cgsmDao")
public class CgsmDao extends BaseHibernateDaoSupport<Cgsm, String> {
	@Override
	public List<Cgsm> findByModel(Cgsm model, Order order, Pager pager) {
		model = model == null ? new Cgsm() : model;
		StringBuffer hql = new StringBuffer();
		hql.append("from Cgsm c where 1=1");
		hql.append(V.isNotEmpty(model.getId()) ? " and c.id='" + model.getId() + "' " : "");
		hql.append(V.isNotEmpty(model.getYear()) ? " and c.year=" + model.getYear() + " " : "");
		hql.append(V.isNotEmpty(model.getDay()) ? " and c.day=" + model.getDay() + " " : "");
		hql.append(V.isNotEmpty(model.getMonth()) ? " and c.month=" + model.getMonth() + " " : "");
		hql.append(V.isNotEmpty(model.getHour()) ? " and c.hour='" + model.getHour() + "' " : "");
		hql.append(V.isNotEmpty(model.getContent()) ? " and c.content='" + model.getContent() + "' " : "");
		hql.append(V.isNotEmpty(model.getState()) ? " and c.state='" + model.getState() + "' " : "");
		hql.append(V.isNotEmpty(model.getVersions()) ? " and c.versions=" + model.getVersions() + " " : "");
		hql.append(V.isNotEmpty(model.getDateStart()) && V.isNotEmpty(model.getDateEnd()) ? " and (c.createTime between '" + DateFormatUtils.format(model.getDateStart(), "yyyy-MM-dd HH:mm:ss") + "' and '" + DateFormatUtils.format(model.getDateEnd(), "yyyy-MM-dd HH:mm:ss") + "') " : "");
		hql.append(order != null ? order.toString() : "");
		return this.findByQueryString(hql.toString(),model, pager);
	}
	
}