package net.codejava.contact.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import net.codejava.contact.dao.ContactDAO;
import net.codejava.contact.model.Contact;

@Controller
public class MainController<HttpServltRequest> {
	
	@Autowired
	private ContactDAO contactDAO;
	
	
	@RequestMapping(value = "/")
	public ModelAndView listContact(ModelAndView model) {
		List<Contact>listContact = contactDAO.list();
		model.addObject("listContact", listContact);
		model.setViewName("index");
		
		return model;
	}
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public ModelAndView newContact(ModelAndView model) {
		Contact newContact = new Contact();
		model.addObject("contact", newContact);
		model.setViewName("contact_form");
		
		
		return model;
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView saveContact(@ModelAttribute Contact contact) {
		if (contact.getId() == null) {
			contactDAO.save(contact);
		} else {
			contactDAO.update(contact);
		}
		
		return new ModelAndView("redirect:/");
		
	}
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView editContact(HttpServletRequest request) {
		Integer id = Integer.parseInt(request.getParameter("id"));
		Contact contact = contactDAO.get(id);
		
		ModelAndView model = new ModelAndView("contact_form");
		
		model.addObject("contact", contact);

		return model;
	}
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView deleteContact(@RequestParam Integer id) {
		
		contactDAO.delete(id);
		
		return new ModelAndView("redirect:/");
		
	}

}
