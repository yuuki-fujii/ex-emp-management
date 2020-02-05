package jp.co.sample.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.sample.domain.Administrator;

/**
 * @author yuuki
 * administratorsテーブルにアクセスするためのリポジトリ.
 */
@Repository
public class AdministratorRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	/**
	 * Administratorクラスオブジェクトを生成するRowMapper
	 */
	public static final RowMapper<Administrator> ADMINISTRATOR_ROW_MAPPER 
		= (rs, i)->{
			Administrator administrator = new Administrator();
			administrator.setId(rs.getInt("id"));
			administrator.setName(rs.getString("name"));
			administrator.setMailAddress(rs.getString("mail_address"));
			administrator.setPassword(rs.getString("password"));
			return administrator;
		};
	
	/**
	 * 引数に渡された管理者オブジェクトをデータベースに保存する。
	 * 
	 * @param administrator 管理者オブジェクト
	 */
	public void insert(Administrator administrator) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(administrator);
		
		String sql = "INSERT INTO administrators (name,mail_address,password) " +
					 "VALUES (:name, :mailAddress, :password)"; 
		template.update(sql, param);	
	}
	
	/**
	 * メールアドレスとパスワードから管理者情報を取得するメソッド
	 * 戻り値が存在しない場合、nullを返す
	 * 
	 * @param mailAddress メールアドレス
	 * @param password　パスワード
	 * @return　Administrator　管理者オブジェクト
	 */
	public Administrator findByMailAddressAndPassword(String mailAddress, String password) {
		String sql = "SELECT id,name,mail_address,password FROM administrators "
				   + "WHERE mail_address =:mailAddress AND password=:password " ;
		
		SqlParameterSource param = new MapSqlParameterSource().addValue("mailAddress", mailAddress)
									   .addValue("password", password);
		
		List <Administrator> administratorList = template.query(sql, param, ADMINISTRATOR_ROW_MAPPER);
		
		if(administratorList.size() == 0) {
			return null;
		}
		return administratorList.get(0);
	}
	
}
