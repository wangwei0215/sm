package com.sm.business.service;

import com.sm.business.model.Birth;
import org.iframework.commons.domain.order.Order;
import org.iframework.commons.domain.pager.Pager;
import org.iframework.support.spring.hibernate.service.IBaseHibernateAutoServiceSupport;

import java.util.List;

/**
 * 服务接口<br>
 * 说明：支持方法查阅实现接口
 * 
 * @author yangrui
 * @date 2019-06-10
 */
public interface BirthService extends IBaseHibernateAutoServiceSupport<Birth, Long> {
    void insert(Birth content);
    List<Birth> findByModel(Birth model, Order order, Pager pager);
}

