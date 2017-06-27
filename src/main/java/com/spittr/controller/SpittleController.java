package com.spittr.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.spittr.Spittle;
import com.spittr.data.SpittleRepository;


@Controller
@RequestMapping("/spittles")
	public class SpittleController {
	
		private SpittleRepository spittleRepository;
		private static final String MAX_LONG_AS_STRING = Long.toString(Long.MAX_VALUE);
		
		@Autowired
		public SpittleController(SpittleRepository spittleRepository) 
		{
			this.spittleRepository = spittleRepository;
		}
		
		//Match get request such as /spittles?max=123&count=20
		@RequestMapping(method=RequestMethod.GET)
		public List<Spittle> spittles(@RequestParam(value="max,defaultValue=MAX_LONG_AS_STRING") long max, 
				@RequestParam(value="count",defaultValue="20") int count) {
			return spittleRepository.findSpittles(max, count);
		}
		
		//will match get request such as /spittles/12345
		@RequestMapping(value="/{spittleId}",method=RequestMethod.GET)
		public String showSpittle(@PathVariable("spittleId") long spittleId, Model model)
		{
			model.addAttribute(spittleRepository.findOne(spittleId));
			return "spittle";
		}
		
		@RequestMapping(value="/register",method=RequestMethod.GET)
		public String showRegistrationForm()
		{
			return "registerForm";
		}
}
