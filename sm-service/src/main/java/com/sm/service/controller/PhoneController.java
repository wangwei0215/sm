package com.sm.service.controller;

import com.sm.service.function.PhoneUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/phone")
public class PhoneController extends BaseController{

	private Logger logger = LoggerFactory.getLogger(PhoneController.class);

	@RequestMapping(value = "phone", method = { RequestMethod.POST, RequestMethod.GET })
	public void list(HttpServletRequest request, final HttpServletResponse response, String phone) throws Exception {
		Map<String, Object> result = new HashMap<>();
		if (!Pattern.matches("1([38]\\d|5[0-35-9]|7[3678])\\d{8}",phone)) {
			result.put("CODE","CIP000000");
			result.put("MSG","请检查手机号");
			result.put("data","");
			print(response,result);
			return;
		}
		result = PhoneUtil.getResult(phone);
		print(response,result);
	}

}
