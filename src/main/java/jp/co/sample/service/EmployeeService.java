package jp.co.sample.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sample.domain.Employee;
import jp.co.sample.repository.EmployeeRepository;

/**
 * 従業員情報に関する業務処理を行う.
 * 
 * @author yuuki
 *
 */
@Service
@Transactional
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	
	/**
	 * 従業員一覧を取得. 
	 * 
	 * @return 従業員リスト
	 */
	public List<Employee> showList(){
		return employeeRepository.findAll();
	}
	
	/**
	 * 1人の従業員の詳細情報を取得.
	 * 
	 * @param id　主キー
	 * @return　従業員オブジェクト
	 */
	public Employee showDetail(Integer id) {
		return employeeRepository.load(id);
	}
	
	/**
	 * 従業員情報の更新.
	 * 
	 * @param employee 従業員オブジェクト
	 */
	public void update(Employee employee) {
		employeeRepository.update(employee);
	}
	
}
