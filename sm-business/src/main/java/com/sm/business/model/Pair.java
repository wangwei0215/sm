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
@Table(name = "td_pair")
@org.hibernate.annotations.Table(appliesTo = "td_pair", comment = "")
public class Pair extends BaseHibernateAutoModelSupport {
	private static final long serialVersionUID = 1L;
	@Column(columnDefinition = "char(2)  comment '生肖'")
	private String zodiac1;
	@Column(columnDefinition = "char(2)  comment '生肖'")
	private String zodiac2;
	@Column(columnDefinition = "text  comment '结果'")
	private String content;
	
	public String getZodiac1() {
		return zodiac1;
	}

	public void setZodiac1(String zodiac1) {
		this.zodiac1 = zodiac1;
	}

	public String getZodiac2() {
		return zodiac2;
	}

	public void setZodiac2(String zodiac2) {
		this.zodiac2 = zodiac2;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
