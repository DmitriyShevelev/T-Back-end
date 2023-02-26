package telran.courses.api.dto;

import javax.validation.constraints.*;
import static telran.courses.api.ApiConstants.*;

import java.io.Serializable;
public class Course implements Serializable{

	private static final long serialVersionUID = 1L;
	public Integer id;
	@NotEmpty
	public String courseName;
	@NotEmpty
	public String lecturerName;
	@Min(MIN_HOURS) @Max(MAX_HOURS)
	public int hoursNum;
	 @Min(MIN_COST) @Max(MAX_COST)
	public int cost;
	public CourseType type;
	@NotNull @Size(min = 1, max = 2)
	public String[] dayEvening;
	@Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}.*")
	public String startDate;
	
	
}
