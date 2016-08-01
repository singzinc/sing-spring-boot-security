package com.singplayground.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.singplayground.model.Question;

@Controller
public class MvcExampleController {

	@RequestMapping(value = "mvcexample1", method = RequestMethod.GET)
	public ModelAndView mvcExample1Controller() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("event/home");
		return mav;
	}

	@RequestMapping(value = "mvcexample2", method = RequestMethod.GET)
	public ModelAndView mvcExample2Controller() {
		ModelAndView mav = new ModelAndView();
		Question question = new Question();
		question.setTitle("this is title");
		question.setQuestionContent("this is content");
		mav.addObject("question", question);
		mav.setViewName("event/detail");
		return mav;
	}

}
