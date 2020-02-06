package jp.co.sample.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import jp.co.sample.domain.Employee;
import jp.co.sample.form.UpdateEmployeeForm;
import jp.co.sample.service.EmployeeService;

/**
 * 従業員情報の処理制御を行うコントローラ.
 * 
 * @author yuuki
 *
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private HttpSession session;
	
	/**
	 * UpdateEmployeeFormをリクエストスコープに格納.
	 * 
	 * @return 扶養人数更新フォーム
	 */
	@ModelAttribute
	public UpdateEmployeeForm setUpUpdateEmployeeForm() {
		return new UpdateEmployeeForm();
	}
	
	
	/**
	 * 従業員一覧を取得.
	 * 
	 * @param model リクエストスコープ
	 * @return　従業員一覧ページ
	 */
	@RequestMapping("/showList")
	public String showList(Model model) {
		if (session.getAttribute("administratorName") == null) {
			return "redirect:/";
		}
		
		List <Employee> employeeList = employeeService.showList();
		model.addAttribute("employeeList",employeeList);
		return "/employee/list";
	}
	
	
	/**
	 * 1人の従業員詳細情報を取得.
	 * 
	 * @param id　主キー
	 * @param model　リクエストスコープ
	 * @return　従業員詳細ページ
	 */
	@RequestMapping("/showDetail")
	public String showDetail(String id ,Model model) {
		Integer intId = Integer.parseInt(id);
		Employee employee = employeeService.showDetail(intId);
		model.addAttribute("employee", employee);
		return "/employee/detail";
	}
	
	/**
	 * 従業員情報を更新.
	 * 
	 * @return 従業員一覧ページ
	 */
	@RequestMapping("/update")
	public String update(@Validated UpdateEmployeeForm form, BindingResult result, Model model) {
		
		if (result.hasErrors()) {
			return showDetail(form.getId(), model);
		}
		
		Employee employee = new Employee();
		employee.setId(form.getIntId());
		employee.setDependentsCount(form.getIntDependentsCount());
		employeeService.update(employee);
		return "redirect:/employee/showList";
	}
	
}
