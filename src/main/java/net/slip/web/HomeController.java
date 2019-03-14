package net.slip.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping("")
	public String home() {
		System.out.println("index 호출");
		return "index";
	}
}
