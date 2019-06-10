package com.sm.service.controller;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.iframework.commons.domain.order.Order;
import org.iframework.commons.domain.order.OrderImpl;
import org.iframework.commons.domain.pager.Pager;
import org.iframework.commons.domain.pager.PagerImpl;
import org.iframework.support.spring.controller.BaseSysControllerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sm.business.model.Cgsm;
import com.sm.business.service.CgsmService;

/**
 * 称骨算命表控制器
 * 
 * @author sunhao
 * @email sunhao@myqifa.com
 * @date 2019-06-10 16:10:37
 */
@Controller
@RequestMapping("/cgsm")
public class CgsmController extends BaseSysControllerSupport<Cgsm> {

	@Autowired
	private CgsmService cgsmService;

	/**
	 * 进入列表页面<br>
	 * 
	 * @param request
	 *            请求对象
	 * @param response
	 *            相应对象
	 * @param model
	 *            模型对象
	 * @return ModelAndView 模型视图对象
	 */
	@RequestMapping(value = "index", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response, Cgsm model) {
		// 自动生成代码 业务逻辑自行添加
		ModelAndView mView = new ModelAndView("/cgsm/index");
		Pager pager = new PagerImpl(request);
		Order order = new OrderImpl(request);
		List<Cgsm> models = this.cgsmService.findByModel(model, order, pager);
		mView.addObject("models", models);
		mView.addObject("page", pager);
		mView.addObject("search", model);
		return mView;
	}

}
