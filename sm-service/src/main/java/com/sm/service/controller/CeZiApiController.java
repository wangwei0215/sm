package com.sm.service.controller;

import com.sm.service.function.CeZiQuery;
import com.sm.service.util.CommonUtils;
import org.iframework.commons.util.fast.V;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 爬取生辰八字
 * 
 * 
 */
@Controller
@RequestMapping("/csapi")
public class CeZiApiController {
	//提供给前端的api
	@RequestMapping(value = "fortuneTellers", method = { RequestMethod.POST, RequestMethod.GET })
	public void fortuneTellers(HttpServletRequest request, final HttpServletResponse response, String czsm) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		if(V.isEmpty(czsm)||!czsm.matches("^[\u4e00-\u9fa5]+$")||czsm.length()!=2) {
			map.put("data","请输入两个汉字");
			CommonUtils.print(response,map);
			return;
		}
		String con=CeZiQuery.getBrith(czsm);
		map.put("data",con);
		CommonUtils.print(response,map);
	}
}
