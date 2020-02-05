package jp.co.sample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sample.domain.Administrator;
import jp.co.sample.repository.AdministratorRepository;

/**
 * 管理者情報を登録する業務処理を行う.
 * 
 * @author yuuki
 */
@Service
@Transactional
public class AdministratorService {
	
	@Autowired
	private AdministratorRepository administratorRepository;
	
	/**
	 * 管理者情報を挿入する
	 * @param administrator 管理者オブジェクト
	 */
	public void insert (Administrator administrator){
		administratorRepository.insert(administrator);
	}

}