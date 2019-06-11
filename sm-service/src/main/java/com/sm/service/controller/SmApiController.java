package com.sm.service.controller;

import com.sm.business.model.BirthDateTime;
import com.sm.business.service.BirthDateTimeService;
import com.sm.service.function.BrithQueryUtil;

import com.sm.service.util.CommonUtils;

import org.iframework.support.spring.context.BaseSpringContextSupport;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 测试
 * 
 * 
 */
@Controller
@RequestMapping("/smapi")
public class SmApiController {
	@RequestMapping(value = "fortuneTellers", method = { RequestMethod.POST, RequestMethod.GET })
	public void fortuneTellers(HttpServletRequest request, final HttpServletResponse response, BirthDateTime content) throws Exception {
		System.out.print("进入fortuneTellers。。。");
		BirthDateTimeService birthDateTimeService = (BirthDateTimeService) BaseSpringContextSupport.getApplicationContext().getBean("birthDateTimeService");
		List<Map<String, Object>> contentList=birthDateTimeService.findContent(content);
		String con="日期不存在";
		Integer sex=content.getSex();
		if(contentList.size()>0){
			con=contentList.get(0).get("content").toString();
		}else{
			content.setSex(1);//男女结果一样，只保存男
			birthDateTimeService.save(content);
 			con=BrithQueryUtil.getBrith(content);
		}
		if(sex==0){//0女1男
			con=con.replace("<strong>男命：</strong>","<strong>女命：</strong>").replace("日0:00-","日23:00-").replace("日2:00-","日1:00-")
					.replace("日4:00-","日3:00-").replace("日6:00-","日5:00-").replace("日8:00-","日7:00-").replace("日10:00-","日9:00-")
					.replace("日12:00-","日11:00-").replace("日14:00-","日13:00-").replace("日16:00-","日15:00-").replace("日18:00-","日17:00-")
					.replace("日20:00-","日19:00-").replace("日22:00-","日21:00-");
		}
		Map<String, String> map = new HashMap<String, String>();
		map.put("data",con);
		CommonUtils.print(response,map);
	}
}
