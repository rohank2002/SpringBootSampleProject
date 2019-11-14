package com.sample.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.sample.demo.dao.AlienRepo;
import com.sample.demo.model.Alien;

//@Controller
@RestController
public class AlienController 
{
	
	@Autowired
	AlienRepo repo;
	
	@RequestMapping("/")
	public String home() {
		return "home.jsp";
	}
	
	@RequestMapping("/addAlien")
	public String addAlien(Alien alien) {
		repo.save(alien);
		return "home.jsp";
	}
	
	@RequestMapping("/getAlien")
	public ModelAndView getAlien(@RequestParam int aid) {
		ModelAndView mv = new ModelAndView("showAlien.jsp");
		Alien alien  = repo.findById(aid).orElse(new Alien());
		mv.addObject(alien);
		return mv;
	}
	@RequestMapping("/deleteAlien")
	public ModelAndView deleteAlien(@RequestParam int aid) {
		ModelAndView mv = new ModelAndView("deleteAlien.jsp");
		Alien alien = repo.findById(aid).orElse(null);
		mv.addObject(alien);
		repo.deleteById(aid);
		return mv;
		}
	@RequestMapping("/updateAlien")
	public String updateAlien(Alien alien) {
		ModelAndView mv = new ModelAndView("updateAlien.jsp");
		repo.save(alien);
		return "updateAlien.jsp";
	}
	
//	// Rest API
//	@RequestMapping("/getAliens")
//	@ResponseBody
//	public String getAliens() {
//		return repo.findAll().toString();
//		
//	}
//	@RequestMapping("/getAliensbyId/{aid}")
//	@ResponseBody
//	public String getAliensbyId(@PathVariable("aid") int aid) {
//		return repo.findById(aid).toString();
//	}
	
	//CRUD With JSON GET
	
	@RequestMapping("/Aliens")
//	@ResponseBody
	public List<Alien> getAliens() {
		return repo.findAll();
		
	}
	
//	@RequestMapping(path="/getAliensbyId",produces= {"xml"}) can specify
	@RequestMapping("/getAliensbyId/{aid}")
//	@ResponseBody
	public Optional<Alien> getAliensbyId(@PathVariable("aid") int aid) {
		
		return repo.findById(aid);
	}
	
	// POST
	@PostMapping (path="/Aliens", consumes="application/json")
//	@ResponseBody
	public Alien AddAliens(@RequestBody Alien alien) {
		repo.save(alien);
		return alien;
		
	}
	//Delete
	
	@DeleteMapping (path="/Aliens/{aid}")
	public String AddAliens(@PathVariable int aid) {
		Alien a =repo.getOne(aid);
		repo.delete(a);
		return "deleted";
		
	}
	
	//Put
	@PutMapping (path="/Aliens", consumes="application/json")
//	@ResponseBody
	public Alien UpdateAliens(@RequestBody Alien alien) {
		repo.save(alien);
		return alien;
		
	}
	
	
	
	
	
	
}
