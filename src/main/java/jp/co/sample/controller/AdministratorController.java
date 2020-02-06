package jp.co.sample.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.domain.Administrator;
import jp.co.sample.form.InsertAdministratorForm;
import jp.co.sample.form.LoginForm;
import jp.co.sample.service.AdministratorService;

/**
 * 管理者情報の処理制御 /ログイン / ログアウトを行うコントローラ.
 * 
 * @author yuuki
 *
 */
@Controller
@RequestMapping("/")
public class AdministratorController {
	
	@Autowired
	private AdministratorService administratorService;
	
	@Autowired
	private HttpSession session;
	
	/**
	 * 管理者フォームをリクエストスコープに格納.
	 * 
	 * @return 管理者フォームオブジェクト
	 */
	@ModelAttribute
	public InsertAdministratorForm setUpInsertAdministratorForm() {
		return new InsertAdministratorForm();
	}
	
	/**
	 * ログインフォームをリクエストスコープに格納.
	 * 
	 * @return ログインフォームオブジェクト
	 */
	@ModelAttribute
	public LoginForm setUpLoginForm() {
		return new LoginForm();
	}
	
	
	/**
	 * 管理者ログイン画面に遷移.
	 * 
	 * @return 管理者ログイン画面
	 */
	@RequestMapping("/")
	public String toLogin() {
		return "/administrator/login";
	}
	
	/**
	 * 管理者登録画面に遷移.
	 * 
	 * @return 管理者登録画面
	 */
	@RequestMapping("/toInsert")
	public String toInsert() {
		return "/administrator/insert";
	}
	
	/**
	 * 管理者登録を行う.
	 * 
	 * @param form 管理者登録フォームに入力された情報
	 * @return　管理者登録画面
	 */
	@RequestMapping("/insert")
	public String insert(InsertAdministratorForm form) {
		Administrator administrator = new Administrator();
		BeanUtils.copyProperties(form, administrator);
		administratorService.insert(administrator);
		return "redirect:/";
	}
	
	/**
	 * ログイン機能.
	 * 
	 * @param form　ログインフォーム　
	 * @param model　リクエストスコープ
	 * @return 従業員一覧ページ
	 */
	@RequestMapping("/login")
	public String login(@Validated LoginForm form,Model model) {
		Administrator administrator = administratorService.login(form.getMailAddress(), form.getPassword());
		
		if (administrator == null) {
			model.addAttribute("error", "メールアドレスまたはパスワードが不正です");
			return "/administrator/login";
		}
			
		session.setAttribute("administratorName", administrator.getName());
		return "forward:/employee/showList";
	}
	
	/**
	 * ログアウト機能.
	 * 
	 * @return 管理者ログイン画面
	 */
	@RequestMapping("/logout")
	public String logout() {
		session.invalidate();
		return "redirect:/";
	}
	
}
