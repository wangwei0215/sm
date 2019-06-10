package com.sm.business.service.impl;


import com.sm.business.model.Haul;
import com.sm.business.service.HaulService;
import org.iframework.support.spring.hibernate.dao.BaseHibernateAutoDaoSupport;
import org.iframework.support.spring.hibernate.service.BaseHibernateAutoServiceSupport;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 服务接口实现类<br>
 * 说明：支持方法查阅实现接口
 * 
 * @author sunhao
 * @email sunhao@myqifa.com
 * @date 2019-05-29 18:25:45
 */
@Service("haulService")
public class HaulServiceImpl extends BaseHibernateAutoServiceSupport<Haul, Long> implements HaulService {
	@Resource(name = "haulDao")
	public void setBaseHibernateAutoDaoSupport(@Qualifier("haulDaoImpl") BaseHibernateAutoDaoSupport<Haul, Long> baseHibernateAutoDaoSupport) {
		this.baseHibernateAutoDaoSupport = baseHibernateAutoDaoSupport;
	}
	//自行添加自定义业务方法
}