package telran.employees.dto;

import java.io.Serializable;

public class UpdateDepartmentData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public long id;
	public String department;
	public UpdateDepartmentData(long id, String department) {
		super();
		this.id = id;
		this.department = department;
	}
	

}
