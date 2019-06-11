package com.sm.service.controller;

import com.sm.business.model.BirthDateTime;
import com.sm.business.service.BirthDateTimeService;
import com.sm.service.function.BaZiQuery;
import com.sm.service.function.HaulQuery;
import com.sm.service.function.HaulTask;
import com.sm.service.util.CommonUtils;
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

/**
 * 爬取运程
 * 
 * 
 */
@Controller
@RequestMapping("/haulapi")
public class HaulApiController {
	//提供给前端的api
	@RequestMapping(value = "fortuneTellers", method = { RequestMethod.POST, RequestMethod.GET })
	public void fortuneTellers(HttpServletRequest request, final HttpServletResponse response, BirthDateTime content) throws Exception {
		System.out.print("进入fortuneTellers。。。");
		BirthDateTimeService birthDateTimeService = (BirthDateTimeService) BaseSpringContextSupport.getApplicationContext().getBean("birthDateTimeService");
		List<Map<String, Object>> contentList=birthDateTimeService.findContent(content);
		String con="日期不存在";
		Integer sex=content.getSex();
		if(contentList.size()>0){
			con=contentList.get(0).get("content").toString();
		}else{
			content.setSex(1);//男女结果一样，只保存男
			birthDateTimeService.save(content);
 			con= BaZiQuery.getBrith(content);
		}
		if(sex==0){//0女1男
			con=CommonUtils.replace(con);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data",con);
		CommonUtils.print(response,map);
	}
	//多线程爬出
	@RequestMapping(value = "runnable", method = { RequestMethod.POST, RequestMethod.GET })
	public void runnable(HttpServletRequest request, final HttpServletResponse response, BirthDateTime content) throws ServletException, IOException {

		BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(10);
		ThreadPoolExecutor pool = new ThreadPoolExecutor(10, 20, 60, TimeUnit.MICROSECONDS, queue);
		for (int i=1; i<13; i++){
			Runnable task = new HaulTask(i);
			pool.execute(task);
		}
		response.getWriter().write("成功");
		System.out.print("结束fortuneTellers。。。");
	}
	//单个爬出对应的值
	@RequestMapping(value = "single", method = { RequestMethod.POST, RequestMethod.GET })
	public void single(HttpServletRequest request, final HttpServletResponse response, BirthDateTime content) throws ServletException, IOException {
		System.out.print("进入fortuneTellers。。。");
		BirthDateTimeService birthDateTimeService = (BirthDateTimeService) BaseSpringContextSupport.getApplicationContext().getBean("birthDateTimeService");
		List<BirthDateTime> contentList=birthDateTimeService.findByModel(content,null,null);
		for(BirthDateTime list:contentList){
			String con= HaulQuery.getBrith(list);
			if("日期不存在".equals(con)) {
				birthDateTimeService.delete(list);//删除主表信息
			}
		}
		System.out.print("结束fortuneTellers。。。"+contentList.size());
	}
}
