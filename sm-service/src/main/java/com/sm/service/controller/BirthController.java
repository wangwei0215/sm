package com.sm.service.controller;

import com.sm.business.model.Birth;
import com.sm.business.service.BirthService;
import com.sm.service.function.BirthdayThread;
import org.iframework.commons.domain.order.Order;
import org.iframework.commons.domain.order.OrderImpl;
import org.iframework.commons.domain.pager.Pager;
import org.iframework.commons.domain.pager.PagerImpl;
import org.iframework.support.spring.context.BaseSpringContextSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/birth")
public class BirthController extends BaseController{
	Logger logger = LoggerFactory.getLogger(BirthController.class);

	@RequestMapping(value = "list", method = { RequestMethod.POST, RequestMethod.GET })
	public void list(HttpServletRequest request, final HttpServletResponse response, Birth birth) throws Exception {

		logger.debug("Enter method BirthController list().");
		Map<String, Object> map = new HashMap<>();
		if (birth.getMonth() == 0) {
			map.put("CODE","CIP999999");
			map.put("msg","请选择月份!");
			print(response,map);
			return;
		}

		if (birth.getDay() == 0) {
			map.put("CODE","CIP999999");
			map.put("msg","请选择日期!");
			print(response,map);
			return;
		}

		BirthService birthService = (BirthService) BaseSpringContextSupport.getApplicationContext().getBean("birthService");
		Pager pager = new PagerImpl(request);
		Order order = new OrderImpl(request);
		List<Birth> births = birthService.findByModel(birth, order, pager);
		map.put("CODE","CIP000000");
		map.put("msg","查询结果");
		map.put("data",births);
		print(response,map);
	}

	@RequestMapping(value = "fortuneTellers", method = { RequestMethod.POST, RequestMethod.GET })
	public void fortuneTellers(final HttpServletResponse response) throws Exception {
		logger.debug("Enter method BirthController fortuneTellers().");
		BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(10);
		ThreadPoolExecutor pool = new ThreadPoolExecutor(10, 20, 60, TimeUnit.MICROSECONDS, queue);

		for (int i = 0; i < 12; i ++) {
			BirthdayThread thread = new BirthdayThread(i);
			pool.execute(thread);
		}

		Map<String, Object> map = new HashMap<>();
		map.put("CODE","CIP000000");
		map.put("msg","数据同步成功");
		print(response,map);
	}
}
