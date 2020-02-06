package jp.co.sample.form;

import javax.validation.constraints.Pattern;

/**
 * 従業員情報更新用フォーム.
 * 
 * @author yuuki
 *
 */
public class UpdateEmployeeForm {
	
	/** 主キー */
	private String id;
	
	/** 扶養人数 */
	@Pattern(regexp = "^[0-9]+$", message="数字を入力してください")
	private String dependentsCount;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDependentsCount() {
		return dependentsCount;
	}
	public void setDependentsCount(String dependentsCount) {
		this.dependentsCount = dependentsCount;
	}
	
	public Integer getIntId() {
		return Integer.parseInt(id);
	}
	
	public Integer getIntDependentsCount() {
		return Integer.parseInt(dependentsCount);
	}
	
	@Override
	public String toString() {
		return "UpdateEmployeeForm [id=" + id + ", dependentsCount=" + dependentsCount + ", getId()=" + getId()
				+ ", getDependentsCount()=" + getDependentsCount() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}
	
}
