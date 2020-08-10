package com.luv2code.springdemo.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/student")
public class StudentController {

		@RequestMapping("/showForm")
		public String showForm(Model theModel) {
			
			//create new student object 
			Student thestudent = new Student();
			
			//add student object to the model
			theModel.addAttribute("student", thestudent);
			
			return "student-form";
		
	}
		
		@RequestMapping("/processForm")
		public String processForm(@ModelAttribute("student") Student thestudent) {
			
			//log the input data
			System.out.println("theStudent: " + thestudent.getFirstName()
								+ "" + thestudent.getLastName());
			return "student-confirmation";
			
		}
		
}
