package com.chan.project.resumeportal;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.chan.project.resumeportal.models.UserProfile;

@Controller
public class HomeController {
	
@Autowired
UserProfileRepository userProfileRepository;
	@GetMapping("/")
  public String home()
  {
	  return "hello";
  }
	
	@GetMapping("/edit")
	  public String edit()
	  {
		  return "edit page";
	  }
	@GetMapping("/view/{userId}")
	  public String view(@PathVariable String userId,Model model)
	  {
		Optional<UserProfile> userProfileOptional = userProfileRepository.findByUserName(userId); 
		userProfileOptional.orElseThrow(() -> new RuntimeException("Not found: " + userId));
		UserProfile userProfile = userProfileOptional.get();
		model.addAttribute("userId", userId);
		model.addAttribute("userProfile",userProfile);
		 
		  return "profile-templates/"+ userProfile.getTheme()+"/index";
	  }
	
}
