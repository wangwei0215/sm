package com.sm.business.model;

import org.iframework.support.spring.hibernate.model.BaseHibernateAutoModelSupport;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 
 * 
 * @author sunhao
 * @email sunhao@myqifa.com
 * @date 2019-05-29 18:25:45
 */
@Entity
@Table(name = "tb_haul")
@org.hibernate.annotations.Table(appliesTo = "tb_haul", comment = "")
//@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Haul extends BaseHibernateAutoModelSupport {
	private static final long serialVersionUID = 1L;
	/** 人员id */
	@Column(name = "birth_date_time_id",columnDefinition = "bigint(20)  comment '人员id'")
	private Long birthDateTimeId;
	/** 内容 */
	@Column(columnDefinition = "text  comment '内容'")
	private String content;
	/**
	 * 设置：人员id
	 * @param birthDateTimeId 人员id
	 */
	public void setBirthDateTimeId(Long birthDateTimeId) {
		this.birthDateTimeId = birthDateTimeId;
	}
	/**
	 * 获取：人员id
	 * @return Integer 返回人员id
	 */
	public Long getBirthDateTimeId() {
		return this.birthDateTimeId;
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
