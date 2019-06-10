package com.sm.business.service.impl;

import com.sm.business.model.Birth;
import com.sm.business.service.BirthService;
import org.iframework.commons.domain.order.Order;
import org.iframework.commons.domain.pager.Pager;
import org.iframework.support.spring.hibernate.dao.BaseHibernateAutoDaoSupport;
import org.iframework.support.spring.hibernate.service.BaseHibernateAutoServiceSupport;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 服务接口实现类<br>
 * 说明：支持方法查阅实现接口
 * 
 * @author yangrui
 * @date 2019-06-10
 */
@Service("birthService")
public class BirthServiceImpl extends BaseHibernateAutoServiceSupport<Birth, Long> implements BirthService {
	@Resource(name = "birthDao")
	public void setBaseHibernateAutoDaoSupport(@Qualifier("birthDaoImpl") BaseHibernateAutoDaoSupport<Birth, Long> baseHibernateAutoDaoSupport) {
		this.baseHibernateAutoDaoSupport = baseHibernateAutoDaoSupport;
	}

	@Override
	public void insert(Birth birth) {
		this.baseHibernateAutoDaoSupport.save(birth);
	}

	@Override
	public List<Birth> findByModel(Birth model, Order order, Pager pager) {
		return this.baseHibernateAutoDaoSupport.findByModel(model,order,pager);
	}
}