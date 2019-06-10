package com.sm.business.service;

import org.iframework.support.spring.hibernate.service.IBaseHibernateAutoServiceSupport;
import org.iframework.support.spring.hibernate.service.IBaseServiceSupport;

import com.sm.business.model.BirthDateTime;

import java.util.List;
import java.util.Map;

/**
 * 服务接口<br>
 * 说明：支持方法查阅实现接口
 * 
 * @author sunhao
 * @email sunhao@myqifa.com
 * @date 2019-05-29 17:46:43
 */
public interface BirthDateTimeService extends IBaseHibernateAutoServiceSupport<BirthDateTime, Long> {

	//自行添加自定义业务接口方法
    public List<Map<String, Object>> findContent(BirthDateTime content);
    //自行添加自定义业务接口方法
    public List<Map<String, Object>> findBirthDateTime(BirthDateTime content);
    public void deleteById(BirthDateTime content);
}

