package com.sm.service.controller;

import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
public class BaseController {

	public void print(HttpServletResponse response, Map<String,Object> map) throws Exception{
		JSONObject json = JSONObject.fromObject(map);
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().write(json.toString());
	}
}
