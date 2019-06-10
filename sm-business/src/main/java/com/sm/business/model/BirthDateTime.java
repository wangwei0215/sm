package com.sm.business.model;

import javax.persistence.*;

import org.iframework.support.spring.hibernate.model.BaseHibernateAutoModelSupport;
import org.iframework.support.spring.hibernate.model.BaseHibernateModelSupport;

import java.util.Date;

/**
 * 
 * 
 * @author sunhao
 * @email sunhao@myqifa.com
 * @date 2019-05-29 17:46:43
 */
@Entity
@Table(name = "tb_birth_date_time")
@org.hibernate.annotations.Table(appliesTo = "tb_birth_date_time", comment = "")
//@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class BirthDateTime extends BaseHibernateAutoModelSupport {
	private static final long serialVersionUID = 1L;
	/** 性别 */
	@Column(columnDefinition = "int(1)  comment '性别'")
	private Integer sex;
	/** 年份 */
	@Column(columnDefinition = "int(4)  comment '年份'")
	private Integer year;
	/** 月份 */
	@Column(columnDefinition = "int(2)  comment '月份'")
	private Integer month;
	/** 日 */
	@Column(columnDefinition = "int(2)  comment '日'")
	private Integer day;
	/** 小时 */
	@Column(columnDefinition = "varchar(12)  comment '小时'")
	private String hour;
	/**
	 * 设置：性别
	 * @param sex 性别
	 */
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	/**
	 * 获取：性别
	 * @return String 返回性别
	 */
	public Integer getSex() {
		return this.sex;
	}
	/**
	 * 设置：年份
	 * @param year 年份
	 */
	public void setYear(Integer year) {
		this.year = year;
	}
	/**
	 * 获取：年份
	 * @return String 返回年份
	 */
	public Integer getYear() {
		return this.year;
	}
	/**
	 * 设置：月份
	 * @param month 月份
	 */
	public void setMonth(Integer month) {
		this.month = month;
	}
	/**
	 * 获取：月份
	 * @return String 返回月份
	 */
	public Integer getMonth() {
		return this.month;
	}
	/**
	 * 设置：日
	 * @param day 日
	 */
	public void setDay(Integer day) {
		this.day = day;
	}
	/**
	 * 获取：日
	 * @return String 返回日
	 */
	public Integer getDay() {
		return this.day;
	}
	/**
	 * 设置：小时
	 * @param hour 小时
	 */
	public void setHour(String hour) {
		this.hour = hour;
	}
	/**
	 * 获取：小时
	 * @return String 返回小时
	 */
	public String getHour() {
		return this.hour;
	}
}
