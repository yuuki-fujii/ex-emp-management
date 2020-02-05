package jp.co.sample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.domain.Employee;
import jp.co.sample.service.EmployeeService;

/**
 * 従業員情報の処理制御を行うコントローラ
 * 
 * @author yuuki
 *
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@RequestMapping("/showList")
	public String showList(Model model) {
		List <Employee> employeeList = employeeService.showList();
		model.addAttribute("employeeList",employeeList);
		return "/employee/list";
	}
	
}
