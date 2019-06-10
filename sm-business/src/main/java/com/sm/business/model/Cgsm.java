package com.sm.business.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.iframework.support.spring.hibernate.model.BaseHibernateModelSupport;

/**
 * 称骨算命表
 * 
 * @author sunhao
 * @email sunhao@myqifa.com
 * @date 2019-06-10 16:10:37
 */
@Entity
@Table(name = "tb_cgsm")
@org.hibernate.annotations.Table(appliesTo = "tb_cgsm", comment = "称骨算命表")
//@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Cgsm extends BaseHibernateModelSupport {
	private static final long serialVersionUID = 1L;
	/** 年份 */
	@Column(columnDefinition = "int(4)  comment '年份'")
	private Integer year;
	/** 月份 */
	@Column(columnDefinition = "int(2)  comment '月份'")
	private Integer day;
	/** 日 */
	@Column(columnDefinition = "int(2)  comment '日'")
	private Integer month;
	/** 小时 */
	@Column(columnDefinition = "varchar(20)  comment '小时'")
	private String hour;
	/** 内容 */
	@Column(columnDefinition = "mediumtext  comment '内容'")
	private String content;
	
	/**
	 * 设置：年份
	 * @param year 年份
	 */
	public void setYear(Integer year) {
		this.year = year;
	}
	/**
	 * 获取：年份
	 * @return Integer 返回年份
	 */
	public Integer getYear() {
		return this.year;
	}
	/**
	 * 设置：月份
	 * @param day 月份
	 */
	public void setDay(Integer day) {
		this.day = day;
	}
	/**
	 * 获取：月份
	 * @return Integer 返回月份
	 */
	public Integer getDay() {
		return this.day;
	}
	/**
	 * 设置：日
	 * @param month 日
	 */
	public void setMonth(Integer month) {
		this.month = month;
	}
	/**
	 * 获取：日
	 * @return Integer 返回日
	 */
	public Integer getMonth() {
		return this.month;
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
	/**
	 * 设置：内容
	 * @param content 内容
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取：内容
	 * @return String 返回内容
	 */
	public String getContent() {
		return this.content;
	}

}
