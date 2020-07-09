package az.maqa.project.model;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Component
@ApiModel(description = "All details about the User")
public class User {

	private Long id;
	
	@Size(min = 2 , message = "Name must be more than 2 characters")
	@ApiModelProperty(notes = "Name must be more than 2 characters")
	private String name;
	
	@Past
	@ApiModelProperty(notes = "BirthDate should be in the past")
	private Date birthDate;
	
	public User() {

	}

	public User(Long id, String name, Date birthDate) {
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", birthDate=" + birthDate + "]";
	}

	
	
}
