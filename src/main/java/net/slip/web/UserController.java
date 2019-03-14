package net.slip.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import net.slip.domain.User;
import net.slip.domain.UserRepository;

@Controller
public class UserController {
	private List<User> users = new ArrayList<User>();
		
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping("/create")
	public String create(User user) { // user 객체 생성 후 form의 user 속성들이 매핑
		System.out.println("creat 메서드 호출");
		users.add(user);
		userRepository.save(user);
		
		System.out.println("sing up completed.: " + user);
		return "redirect:/list";
	}
	
	@GetMapping("/list")
	public String list(Model model) {
		model.addAttribute("users", userRepository.findAll());
		return "list";	
	}
}
