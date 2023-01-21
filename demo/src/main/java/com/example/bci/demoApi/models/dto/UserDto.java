package com.example.bci.demoApi.models.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.example.bci.demoApi.config.annotations.PasswordValid;
import com.example.bci.demoApi.models.Phones;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto implements Serializable {

	private static final long serialVersionUID = 1L;
	private String id;
	@Email(message = "Email Formato Incorrecto. Ej: aaaaaaa@dominio.cl")
	@NotEmpty(message = "Email no puede estar vacio")
	private String email;

	@NotEmpty(message = "Name no puede estar vacio")
	private String name;

	@PasswordValid
	private String password;
	private LocalDateTime created;
	private LocalDateTime modified;
	private LocalDateTime last_login;
	private String token;
	private Boolean isactive;
	
	private List<UserPhonesDto> phones;

	@Override
	public String toString() {
		return "UserDto [id=" + id + ", email=" + email + ", name=" + name + ", password=" + password + ", created="
				+ created + ", modified=" + modified + ", last_login=" + last_login + ", token=" + token + ", isactive="
				+ isactive + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(created, email, id, isactive, last_login, modified, name, password, token);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDto other = (UserDto) obj;
		return Objects.equals(created, other.created) && Objects.equals(email, other.email)
				&& Objects.equals(id, other.id) && isactive == other.isactive
				&& Objects.equals(last_login, other.last_login) && Objects.equals(modified, other.modified)
				&& Objects.equals(name, other.name) && Objects.equals(password, other.password)
				&& Objects.equals(token, other.token);
	}

}
