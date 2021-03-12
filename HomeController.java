package com.example.CovidTracker.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.CovidTracker.Services.ServiceCorona;
import com.example.CovidTracker.model.CasesStats;

@Controller
public class HomeController {
@Autowired
private ServiceCorona service;
@RequestMapping
public String Home(Model m) {
	List<CasesStats> stats=service.getUpdated();
	int globalcases=stats.stream().mapToInt(stat-> stat.getCases()).sum();
	int changeincases=stats.stream().mapToInt(stat-> stat.getChangeincases()).sum();
	m.addAttribute("globalcases", globalcases);
	m.addAttribute("results",stats);
	m.addAttribute("changeincases", changeincases);
	return "index";
}
}
