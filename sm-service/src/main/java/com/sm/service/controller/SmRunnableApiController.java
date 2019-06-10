package com.sm.service.controller;

import com.sm.business.model.BirthDateTime;
import com.sm.service.function.CustomTask;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 测试
 * 
 * 
 */
@Controller
@RequestMapping("/smrunnableapi")
public class SmRunnableApiController {
	@RequestMapping(value = "fortuneTellers", method = { RequestMethod.POST, RequestMethod.GET })
	public void fortuneTellers(HttpServletRequest request, final HttpServletResponse response, BirthDateTime content) throws ServletException, IOException {

		BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(10);
		ThreadPoolExecutor pool = new ThreadPoolExecutor(10, 20, 60, TimeUnit.MICROSECONDS, queue);
		for (int i=1; i<13; i++){
			Runnable task = new CustomTask(i);
			pool.execute(task);
		}
		response.getWriter().write("成功");
		System.out.print("结束fortuneTellers。。。");
	}
}
