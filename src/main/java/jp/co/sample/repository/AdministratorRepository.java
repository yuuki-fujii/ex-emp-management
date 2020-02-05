package jp.co.sample.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.sample.domain.Administrator;

@Repository
public class AdministratorRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	public static final RowMapper<Administrator> REPOSITORY_ROW_MAPPER 
		= (rs, i)->{
			Administrator administrator = new Administrator();
			administrator.setId(rs.getInt("id"));
			administrator.setName(rs.getString("name"));
			administrator.setMailAddress(rs.getString("mail_address"));
			administrator.setPassword(rs.getString("password"));
			return administrator;
		};
	
	public void insert(Administrator administrator) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(administrator);
		
		String sql = "INSERT INTO administrators (name,mail_address,password) " +
					 "VALUES (:name, :mailAddress, :password)"; 
		template.update(sql, param);	
	}
	
	public Administrator findByMail
	
}
