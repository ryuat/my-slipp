package net.slip.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.slip.domain.User;
import net.slip.domain.UserRepository;

@Controller
@RequestMapping("/users")
public class UserController {
	private List<User> users = new ArrayList<User>();
		
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/form")
	public String form() {
		System.out.println("form 호출 회원가입 뷰 생성");
		return "/user/form";
	}
	
	@PostMapping("")
	public String create(User user) { // user 객체 생성 후 form의 user 속성들이 매핑
		System.out.println("creat 메서드 호출");
		users.add(user);
		userRepository.save(user);
		
		System.out.println("sing up completed.: " + user);
		return "redirect:/users"; // 아래 메서드로 매핑
	}
	
	@GetMapping("")
	public String list(Model model) {
		System.out.println("users get 호출");
		model.addAttribute("users", userRepository.findAll());
		return "/user/list";	// templates/user/list.html 
	}
	
	@GetMapping("/{id}/form")
	public String updateForm(@PathVariable Long id, Model model, HttpSession session) { // path에서 id를 취하겠다. 겟매핑 {{id}}와 변수명이 같아야한다.
		Object tempUser = session.getAttribute("sessionuser");
		
		if(tempUser == null) {
			return "redirect:/users/form";
		}
		
		User sessionUser = (User)tempUser;
		if(!id.equals(sessionUser.getId())) {
			throw new IllegalStateException("자신의 정보만 수정할 수 있습니다."); 
		}
		
		System.out.println("sessionid: " + sessionUser.getId());
		User user = userRepository.findById(sessionUser.getId()).get();
			
		model.addAttribute("user", user);
		System.out.println("수정 요청한 userNo: " + id);
		return "/user/updateForm";
	}
	
	@PostMapping("/{id}/update")
	public String update(@PathVariable Long id, User updateUser, HttpSession session) {
		
		Object tempUser = session.getAttribute("sessionuser");
		if(tempUser == null) {
			return "redirect:/users/form";
		}
		User sessionUser = (User)tempUser;
//		if(!id.equals(sessionUser.getId())) {
//			throw new IllegalStateException("자신의 정보만 수정할 수 있습니다."); 
//		}
		User user = userRepository.findById(id).get();
		user.update(updateUser);
		userRepository.save(user);
		return "redirect:/users"; // update 후 user list 화면으로
	}

	
	@GetMapping("/login")
	public String login() {
		return "/user/login";
	}
	
	@PostMapping("/login")
	public String login(String userId, String userPassword, HttpSession session) {
		User user = userRepository.findByUserId(userId);
		if(user == null) {
			System.out.println("invalid userid");
			return "redirect:login";
		}
		
		if(!user.getUserPassword().equals(userPassword)) {	
			System.out.println("invalid password");
			return "redirect:login";
		}
		
		session.setAttribute("sessionuser", user);
		System.out.println(session);
		return "redirect:/";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("sessionuser");
		return "redirect:/";
	}
	
}
