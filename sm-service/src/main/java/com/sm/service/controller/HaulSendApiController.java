package com.sm.service.controller;


import com.sm.business.model.BirthDateTime;
import com.sm.business.service.BirthDateTimeService;
import com.sm.service.function.HaulQueryUtil;
import org.iframework.support.spring.context.BaseSpringContextSupport;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 测试
 * 
 * 
 */
@Controller
@RequestMapping("/haulsendapi")
public class HaulSendApiController {
	@RequestMapping(value = "fortuneTellers", method = { RequestMethod.POST, RequestMethod.GET })
	public void fortuneTellers(HttpServletRequest request, final HttpServletResponse response, BirthDateTime content) throws ServletException, IOException {
		System.out.print("进入fortuneTellers。。。");
		BirthDateTimeService birthDateTimeService = (BirthDateTimeService) BaseSpringContextSupport.getApplicationContext().getBean("birthDateTimeService");
		List<BirthDateTime> contentList=birthDateTimeService.findByModel(content,null,null);
		for(BirthDateTime list:contentList){
			String con= HaulQueryUtil.getBrith(list);
			if("日期不存在".equals(con)) {
				birthDateTimeService.delete(list);//删除主表信息
			}
		}
		System.out.print("结束fortuneTellers。。。"+contentList.size());
	}
}
