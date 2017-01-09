/**
 * @Description:TODO
 * @author:YC
 * @time:2017��1��5�� ����11:57:02
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
import com.bfec.dsdemo.utils.StringUtils;

/**
 * @Description:TODO
 * @author:YC
 * @time:2017��1��5�� ����11:57:02
 */
@Controller
@RequestMapping("/testbean")
public class TestBeanController {

	@Autowired
	private TestBeanService testBeanService;

	private Log log = LogFactory.getLog(MUserController.class);
	
	@RequestMapping(value = "/listBean")
	public String listBean(HttpServletRequest request) {
		List<TestBean> list = testBeanService.reportAll();
		request.setAttribute("beanlist", list);
		return "testbean/listBean";
	}
	
	@RequestMapping("/addBean")
	public String addBean(TestBean bean) {
		// ȥ���ַ����Ŀհ��ַ� (Spring��������ͬ������)
		bean.setName(StringUtils.trimAllWhitespace(bean.getName()));
		log.info("TestBeanController.addBean()");
		testBeanService.insert(bean);
		return "redirect:/testbean/listBean.do";
	}
}
