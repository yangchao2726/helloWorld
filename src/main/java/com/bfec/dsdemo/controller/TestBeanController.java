/**
 * @Description:TODO
 * @author:YC
 * @time:2017年1月5日 上午11:57:02
 */
package com.bfec.dsdemo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bfec.dsdemo.model.TestBean;
import com.bfec.dsdemo.service.TestBeanService;

/**
 * @Description:TODO
 * @author:YC
 * @time:2017年1月5日 上午11:57:02
 */
@Controller
@RequestMapping("/testbean")
public class TestBeanController {

	@Autowired
	private TestBeanService testBeanService;

	private Log log = LogFactory.getLog(MUserController.class);
	
	@RequestMapping(value = "/listBean")
	public String listUser(HttpServletRequest request) {
		List<TestBean> list = testBeanService.queryAll();
		request.setAttribute("beanlist", list);
		return "testbean/listBean";
	}
}
