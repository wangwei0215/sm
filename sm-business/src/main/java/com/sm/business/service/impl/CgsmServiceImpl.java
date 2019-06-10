package com.sm.business.service.impl;

import javax.annotation.Resource;
import org.iframework.support.spring.hibernate.dao.BaseHibernateDaoSupport;
import org.iframework.support.spring.hibernate.service.BaseServiceSupport;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.sm.business.model.Cgsm;
import com.sm.business.service.CgsmService;

/**
 * 称骨算命表服务接口实现类<br>
 * 说明：支持方法查阅实现接口
 * 
 * @author sunhao
 * @email sunhao@myqifa.com
 * @date 2019-06-10 16:10:37
 */
@Service("cgsmService")
public class CgsmServiceImpl extends BaseServiceSupport<Cgsm, String> implements CgsmService {
	@Resource(name = "cgsmDao")
	public void setBaseHibernateDaoSupport(@Qualifier("cgsmDaoImpl") BaseHibernateDaoSupport<Cgsm, String> baseHibernateDaoSupport) {
		this.baseHibernateDaoSupport = baseHibernateDaoSupport;
	}
	
	//自行添加自定义业务方法
}