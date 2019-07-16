package com.sm.service.controller;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.iframework.commons.domain.order.Order;
import org.iframework.commons.domain.order.OrderImpl;
import org.iframework.commons.domain.pager.Pager;
import org.iframework.commons.domain.pager.PagerImpl;
import org.iframework.commons.util.fast.L;
import org.iframework.commons.util.fast.V;
import org.iframework.support.spring.controller.BaseSysControllerSupport;
import org.iframework.support.spring.controller.BaseWebControllerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sm.business.domain.JsonResultModel;
import com.sm.business.domain.StatusCode;
import com.sm.business.model.Birth;
import com.sm.business.model.Cgsm;
import com.sm.business.service.CgsmService;
import com.sm.service.function.BirthdayThread;
import com.sm.service.function.CgsmQueryUtil;

/**
 * 称骨算命表控制器
 * 
 * @author sunhao
 * @email sunhao@myqifa.com
 * @date 2019-06-10 16:10:37
 */
@Controller
@RequestMapping("/cgsm")
public class CgsmController extends BaseWebControllerSupport {
	@Autowired CgsmService cgsmService;
	
	@RequestMapping(value = "list", method = { RequestMethod.POST, RequestMethod.GET })
	public void list(HttpServletRequest request, final HttpServletResponse response, Cgsm cgsm) throws Exception {
		if(V.isEmpty(cgsm) || V.isEmpty(cgsm.getYear()) || V.isEmpty(cgsm.getMonth()) || V.isEmpty(cgsm.getDay()) || V.isEmpty(cgsm.getHour())) {
			renderJson(new JsonResultModel(StatusCode.FAILURE.getDesc(), "缺少必填参数").toJsonString(), response);
		}
		List<Cgsm> findByModel = cgsmService.findByModel(cgsm, null, null);
		//如果数据库查出的结果为空，去网页爬取，并入库
		if(V.isEmpty(findByModel)){
			CgsmQueryUtil.getContent(cgsm);
			findByModel = cgsmService.findByModel(cgsm, null, null);
		}
		
		renderJson(new JsonResultModel(StatusCode.SUCCESS.getDesc(), "成功",findByModel).toJsonString(), response);
	}
	
	@RequestMapping(value = "spider", method = { RequestMethod.POST, RequestMethod.GET })
	public void fortuneTellers(final HttpServletResponse response) throws Exception {
		L.i("begin cgsm");
		Integer[] year = {5,6,7,8,9,10,11,12,13,14,15,16};
		Integer[] month = {5,6,7,8,9,15,16,18};
		Integer[] day = {5,6,7,8,9,10,15,16,17,18};
		Integer[] hour = {6,7,8,9,10,16};
		for (int i = 0; i < year.length; i++) {
			for (int j = 0; j < month.length; j++) {
				for (int j2 = 0; j2 < day.length; j2++) {
					for (int k = 0; k < hour.length; k++) {
						Cgsm cgsm = new Cgsm();
						cgsm.setYear(year[i]);
						cgsm.setMonth(month[j]);
						cgsm.setDay(day[j2]);
						cgsm.setHour(String.valueOf(hour[k]));
						CgsmQueryUtil.getContent(cgsm);
					}
				}
			}
		}

//		Map<String, Object> map = new HashMap<>();
//		map.put("CODE","CIP000000");
//		map.put("msg","数据同步成功");
//		print(response,map);
	}

}
