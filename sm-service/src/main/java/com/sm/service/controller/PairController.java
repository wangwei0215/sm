package com.sm.service.controller;

import com.sm.business.model.Pair;
import com.sm.business.service.PairService;
import com.sm.service.function.PairThread;
import net.sf.json.JSONObject;
import org.iframework.support.spring.context.BaseSpringContextSupport;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/pair")
public class PairController {
	@RequestMapping(value = "list", method = { RequestMethod.POST, RequestMethod.GET })
	public void list(HttpServletRequest request, final HttpServletResponse response, Pair pair) throws ServletException, IOException {
		System.out.print("list。。。");
		PairService pairService = (PairService) BaseSpringContextSupport.getApplicationContext().getBean("pairService");
		List<Pair> pairs = pairService.findByModel(pair, null, null);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data",pairs);
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

		List<String> datas = new ArrayList<>();
		datas.add("鼠");
		datas.add("牛");
		datas.add("虎");
		datas.add("兔");
		datas.add("龙");
		datas.add("蛇");
		datas.add("马");
		datas.add("羊");
		datas.add("猴");
		datas.add("鸡");
		datas.add("狗");
		datas.add("猪");
		for (int i = 0; i < datas.size(); i ++) {
			PairThread thread = new PairThread(datas.get(i), i, datas);
			pool.execute(thread);
		}
		response.getWriter().write("成功");
		System.out.print("结束fortuneTellers。。。");
	}
}
