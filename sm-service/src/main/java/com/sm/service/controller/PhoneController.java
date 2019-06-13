package com.sm.service.controller;

import com.sm.service.function.PhoneUtil;
import com.sm.service.util.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/phone")
public class PhoneController {

	private Logger logger = LoggerFactory.getLogger(PhoneController.class);

	@RequestMapping(value = "phone", method = { RequestMethod.POST, RequestMethod.GET })
	public void list(HttpServletRequest request, final HttpServletResponse response, String phone) throws Exception {

	    logger.info("====================={}",phone);
		Map<String, Object> result = new HashMap<>();
		if (StringUtils.isEmpty(phone)||  !Pattern.matches("1([38]\\d|5[0-35-9]|7[3678])\\d{8}",phone) ) {
			result.put("CODE","CIP000000");
			result.put("MSG","请检查手机号");
			result.put("data","");
			CommonUtils.print(response,result);
			return;
		}
		result = PhoneUtil.getResult(phone);
		CommonUtils.print(response,result);
	}

}
