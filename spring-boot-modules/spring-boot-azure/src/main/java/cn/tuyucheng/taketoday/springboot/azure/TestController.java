package cn.tuyucheng.taketoday.springboot.azure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static cn.tuyucheng.taketoday.springboot.azure.User.userNamed;

/**
 * @author tuyucheng
 */
@RestController
public class TestController {

	@GetMapping("/hello")
	public String hello() {
		return "hello azure!";
	}

	@Autowired
	private UserRepository userRepository;

	@PostMapping("/user")
	public String register(@RequestParam String name) {
		userRepository.save(userNamed(name));
		return "registered";
	}

	@GetMapping("/user")
	public Iterable<User> userlist() {
		return userRepository.findAll();
	}
}