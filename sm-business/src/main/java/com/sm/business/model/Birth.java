package com.sm.business.model;

import org.iframework.support.spring.hibernate.model.BaseHibernateAutoModelSupport;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 
 * 
 * @author yangrui
 * @date 2019-06-10
 */
@Entity
@Table(name = "td_birth")
@org.hibernate.annotations.Table(appliesTo = "td_birth", comment = "")
public class Birth extends BaseHibernateAutoModelSupport {
	private static final long serialVersionUID = 1L;

	@Column(columnDefinition = "int(2)  comment '月份'")
	private int month;
	@Column(columnDefinition = "int(2)  comment '生肖'")
	private int day;
	@Column(columnDefinition = "text  comment '结果'")
	private String content;

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
