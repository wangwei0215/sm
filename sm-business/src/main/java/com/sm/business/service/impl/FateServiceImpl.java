package com.sm.business.service.impl;

import javax.annotation.Resource;

import org.iframework.support.spring.hibernate.dao.BaseHibernateAutoDaoSupport;
import org.iframework.support.spring.hibernate.dao.BaseHibernateDaoSupport;
import org.iframework.support.spring.hibernate.service.BaseHibernateAutoServiceSupport;
import org.iframework.support.spring.hibernate.service.BaseServiceSupport;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.sm.business.model.Fate;
import com.sm.business.service.FateService;

/**
 * 服务接口实现类<br>
 * 说明：支持方法查阅实现接口
 * 
 * @author sunhao
 * @email sunhao@myqifa.com
 * @date 2019-05-29 18:25:45
 */
@Service("fateService")
public class FateServiceImpl extends BaseHibernateAutoServiceSupport<Fate, Long> implements FateService {
	@Resource(name = "fateDao")
	public void setBaseHibernateAutoDaoSupport(@Qualifier("fateDaoImpl") BaseHibernateAutoDaoSupport<Fate, Long> baseHibernateAutoDaoSupport) {
		this.baseHibernateAutoDaoSupport = baseHibernateAutoDaoSupport;
	}
	//自行添加自定义业务方法
}