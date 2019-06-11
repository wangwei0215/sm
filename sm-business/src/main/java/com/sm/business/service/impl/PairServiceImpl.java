package com.sm.business.service.impl;

import com.sm.business.model.Pair;
import com.sm.business.service.PairService;
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
@Service("pairService")
public class PairServiceImpl extends BaseHibernateAutoServiceSupport<Pair, Long> implements PairService {
	@Resource(name = "pairDao")
	public void setBaseHibernateAutoDaoSupport(@Qualifier("pairDaoImpl") BaseHibernateAutoDaoSupport<Pair, Long> baseHibernateAutoDaoSupport) {
		this.baseHibernateAutoDaoSupport = baseHibernateAutoDaoSupport;
	}

	@Override
	public void insert(Pair pair) {
		this.baseHibernateAutoDaoSupport.save(pair);
	}

	@Override
	public List<Pair> findByModel(Pair model, Order order, Pager pager) {
		return this.baseHibernateAutoDaoSupport.findByModel(model,order,pager);
	}
}