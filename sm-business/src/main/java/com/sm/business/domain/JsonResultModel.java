package com.sm.business.domain;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.iframework.commons.domain.model.BaseJsonResultModel;
import org.iframework.commons.util.JsonUtil;
import org.iframework.commons.util.fast.V;
import org.springframework.validation.ObjectError;

/**
 * 普通ajax调用返回JSON格式结果模型
 * 
 * @author 
 * 
 */
public class JsonResultModel extends BaseJsonResultModel {

	private static final long serialVersionUID = -6493622383324818635L;

	public JsonResultModel() {
		super();
	}

	public JsonResultModel(String code, String msg) {
		super(code, msg);
	}
	
	public JsonResultModel(String code, String msg, Object data) {
		super(code, msg, data);
	}
	
	public JsonResultModel(String code, String msg, Object data, Object order, Object pager) {
		super(code, msg, data, order, pager);
	}
	
	public JsonResultModel(String code, String msg, Object data, Object order, Object pager,
			List<ObjectError> objectErrors) {
		super(code, msg, data, order, pager, objectErrors);
	}

	public String toSysJsonString() {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		if (V.isNotEmpty(this.code)) {
			map.put("statusCode", this.code);
		}
		if (V.isNotEmpty(this.msg)) {
			map.put("message", this.msg);
		}
		if (V.isNotEmpty(this.objectErrors)) {
			Map<String, String> errors = new HashMap<String, String>();
			for (ObjectError objectError : objectErrors) {
				errors.put(objectError.getCode(), objectError.getDefaultMessage());
			}
			map.put("errors", errors);
		}
		if (V.isNotEmpty(this.data)) {
			map.put("data", data);
		}
		return JsonUtil.toJSONString(map);
	}
}
