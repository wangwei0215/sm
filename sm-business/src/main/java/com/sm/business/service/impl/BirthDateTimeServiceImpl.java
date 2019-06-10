package com.sm.business.service.impl;

import javax.annotation.Resource;

import org.iframework.commons.util.fast.V;
import org.iframework.support.spring.hibernate.dao.BaseHibernateAutoDaoSupport;
import org.iframework.support.spring.hibernate.dao.BaseHibernateDaoSupport;
import org.iframework.support.spring.hibernate.service.BaseHibernateAutoServiceSupport;
import org.iframework.support.spring.hibernate.service.BaseServiceSupport;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.sm.business.model.BirthDateTime;
import com.sm.business.model.Fate;
import com.sm.business.service.BirthDateTimeService;

import java.util.List;
import java.util.Map;

/**
 * 服务接口实现类<br>
 * 说明：支持方法查阅实现接口
 * 
 * @author sunhao
 * @email sunhao@myqifa.com
 * @date 2019-05-29 17:46:43
 */
@Service("birthDateTimeService")
public class BirthDateTimeServiceImpl extends BaseHibernateAutoServiceSupport<BirthDateTime, Long> implements BirthDateTimeService {
	@Resource(name = "birthDateTimeDao")
	public void setBaseHibernateAutoDaoSupport(@Qualifier("birthDateTimeDaoImpl") BaseHibernateAutoDaoSupport<BirthDateTime, Long> baseHibernateAutoDaoSupport) {
		this.baseHibernateAutoDaoSupport = baseHibernateAutoDaoSupport;
	}
	//自行添加自定义业务方法
	public List<Map<String, Object>> findContent(BirthDateTime content){

		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT b.content FROM tb_birth_date_time a,tb_fate b WHERE 1=1");
			sql.append(" and a.id=b.birth_date_time_id ");
		sql.append(V.isNotEmpty(content.getYear()) ? " and a.`year`='" + content.getYear() + "'" : "");
		//男女同一时间结果相同，数据库中只保存了一种性别，所以注释掉
//		sql.append(V.isNotEmpty(content.getSex()) ? " and a.`sex`='" + content.getSex() + "'" : "");
		sql.append(V.isNotEmpty(content.getMonth()) ? " and a.`month`='" + content.getMonth() + "'" : "");
		sql.append(V.isNotEmpty(content.getDay()) ? " and a.`day`='" + content.getDay() + "'" : "");
		sql.append(V.isNotEmpty(content.getHour()) ? " and a.`hour`='" + content.getHour() + "'" : "");
		sql.append(" LIMIT 1");
		return this.querySQL(sql.toString());

	}
	//自行添加自定义业务方法
	public List<Map<String, Object>> findBirthDateTime(BirthDateTime content){

		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT a.id,a.sex,a.year,a.day,a.month,a.hour FROM tb_birth_date_time a left join tb_fate b on a.id=b.birth_date_time_id WHERE 1=1 and b.birth_date_time_id is null");
		sql.append(V.isNotEmpty(content.getYear()) ? " and a.`year`='" + content.getYear() + "'" : "");
		sql.append(V.isNotEmpty(content.getSex()) ? " and a.`sex`='" + content.getSex() + "'" : "");
		sql.append(V.isNotEmpty(content.getMonth()) ? " and a.`month`='" + content.getMonth() + "'" : "");
		sql.append(V.isNotEmpty(content.getDay()) ? " and a.`day`='" + content.getDay() + "'" : "");
		sql.append(V.isNotEmpty(content.getDay()) ? " and a.`hour`='" + content.getHour() + "'" : "");
		return this.querySQL(sql.toString());

	}
	//自行添加自定义业务方法
	public List<Map<String, Object>> findBirthDateTimeHaul(BirthDateTime content){

		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT a.id,a.sex,a.year,a.day,a.month,a.hour FROM tb_birth_date_time a left join tb_haul b on a.id=b.birth_date_time_id WHERE 1=1 and b.birth_date_time_id is null");
		sql.append(V.isNotEmpty(content.getYear()) ? " and a.`year`='" + content.getYear() + "'" : "");
		sql.append(V.isNotEmpty(content.getSex()) ? " and a.`sex`='" + content.getSex() + "'" : "");
		sql.append(V.isNotEmpty(content.getMonth()) ? " and a.`month`='" + content.getMonth() + "'" : "");
		sql.append(V.isNotEmpty(content.getDay()) ? " and a.`day`='" + content.getDay() + "'" : "");
		sql.append(V.isNotEmpty(content.getDay()) ? " and a.`hour`='" + content.getHour() + "'" : "");
		return this.querySQL(sql.toString());

	}
	//自行添加自定义业务方法
	public void deleteById(BirthDateTime content){
		StringBuffer sql = new StringBuffer();
		sql.append("DELETE FROM tb_birth_date_time WHERE id='" + content.getId() + "'");
		this.baseHibernateAutoDaoSupport.executeSQL(sql.toString());
	}
}