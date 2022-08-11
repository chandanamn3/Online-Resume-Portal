package com.chan.project.resumeportal;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.chan.project.resumeportal.models.Job;
import com.chan.project.resumeportal.models.UserProfile;

@Controller
public class HomeController {
	
@Autowired
UserProfileRepository userProfileRepository;
	@GetMapping("/")
  public String home()
  {
	  
	  Optional<UserProfile> profileOptional = userProfileRepository.findByUserName("einstein");
      profileOptional.orElseThrow(() -> new RuntimeException("Not found "));

      UserProfile profile1 = profileOptional.get();
      Job job1 = new Job();
      job1.setCompany("Company 1");
      job1.setDesignation("Designation");
      job1.setId(1);
      job1.setStartDate(LocalDate.of(2020, 1, 1));
      //job1.setEndDate(LocalDate.of(2020, 3, 1));
      job1.setCurrentJob(true);
      Job job2 = new Job();
      job2.setCompany("Company 2");
      job2.setDesignation("Designation");
      job2.setId(2);
      job2.setStartDate(LocalDate.of(2019, 5, 1));
      job2.setEndDate(LocalDate.of(2020, 1, 1));
      profile1.getJobs().clear();
      profile1.getJobs().add(job1);
      profile1.getJobs().add(job2);

      userProfileRepository.save(profile1);

      return "profile";
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
		System.out.println(userProfile.getJobs());
		 
		  return "profile-templates/"+ userProfile.getTheme()+"/index";
	  }
	
}
