package com.sm.service.controller;

import com.sm.business.model.Birth;
import com.sm.business.service.BirthService;
import com.sm.service.function.BirthdayThread;
import net.sf.json.JSONObject;
import org.iframework.support.spring.context.BaseSpringContextSupport;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/birth")
public class BirthController {
	@RequestMapping(value = "list", method = { RequestMethod.POST, RequestMethod.GET })
	public void list(HttpServletRequest request, final HttpServletResponse response, Birth birth) throws ServletException, IOException {
		System.out.print("list。。。");
		BirthService birthService = (BirthService) BaseSpringContextSupport.getApplicationContext().getBean("birthService");
		// Birth model, Order order, Pager pager
		List<Birth> births = birthService.findByModel(birth, null, null);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data",births);
		JSONObject json = JSONObject.fromObject(map);
		String result = "showData" + "(" + json.toString() + ")";
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().write(result);
	}

	@RequestMapping(value = "fortuneTellers", method = { RequestMethod.POST, RequestMethod.GET })
	public void fortuneTellers(HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {

		BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(10);
		ThreadPoolExecutor pool = new ThreadPoolExecutor(10, 20, 60, TimeUnit.MICROSECONDS, queue);

		for (int i = 0; i < 12; i ++) {
			BirthdayThread thread = new BirthdayThread(i);
			pool.execute(thread);
		}

		response.getWriter().write("成功");
		System.out.print("结束fortuneTellers。。。");
	}
}
