package com.sm.service.controller;

import com.sm.business.model.Pair;
import com.sm.business.service.PairService;
import com.sm.service.function.PairThread;
import org.iframework.commons.domain.order.Order;
import org.iframework.commons.domain.order.OrderImpl;
import org.iframework.commons.domain.pager.Pager;
import org.iframework.commons.domain.pager.PagerImpl;
import org.iframework.support.spring.context.BaseSpringContextSupport;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
public class PairController extends BaseController{
	@RequestMapping(value = "list", method = { RequestMethod.POST, RequestMethod.GET })
	public void list(HttpServletRequest request, final HttpServletResponse response, Pair pair) throws Exception {


		System.out.print("list。。。");
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtils.isEmpty(pair.getZodiac1()) || StringUtils.isEmpty(pair.getZodiac2())) {
			map.put("CODE","CIP999999");
			map.put("msg","请选择生肖!");
			print(response,map);
			return;
		}

		PairService pairService = (PairService) BaseSpringContextSupport.getApplicationContext().getBean("pairService");
		Pager pager = new PagerImpl(request);
		Order order = new OrderImpl(request);
		List<Pair> pairs = pairService.findByModel(pair, order, pager);
		map.put("CODE","CIP000000");
		map.put("msg","查询结果");
		map.put("data",pairs);
		print(response,map);
	}

	@RequestMapping(value = "fortuneTellers", method = { RequestMethod.POST, RequestMethod.GET })
	public void fortuneTellers(HttpServletRequest request, final HttpServletResponse response) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

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
		map.put("CODE","CIP000000");
		map.put("msg","数据同步成功");
		print(response,map);
	}
}
