package com.example.bci.demoApi.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.example.bci.demoApi.models.dto.UserDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User extends BaseEntity<UserDto, User> {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@Column(name = "id", nullable = false, unique = true)
	private String id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "email", nullable = false)
	private String email;

	@Column(name = "token", nullable = false)
	private String token;
	@Column(name = "type_token", nullable = false)
	private String typeToken;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "disabled", nullable = false)
	private Boolean disabled;

	@Column(name = "last_login")
	private LocalDateTime lastLogin;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "user_phones", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
			@JoinColumn(name = "phone_id") })
	private List<Phones> phones = new ArrayList<>();

	@Override
	public UserDto normalizeObj(User entity) {

		UserDto obj = new UserDto();

		obj.setId(entity.getId());
		obj.setEmail(entity.getEmail());
		if (entity.getDisabled()) {
			obj.setIsactive(Boolean.FALSE);
		} else {
			obj.setIsactive(Boolean.TRUE);
		}
		obj.setLast_login(entity.getLastLogin());
		obj.setModified(entity.getModifiedAt());
		obj.setCreated(entity.getCreatedAt());
		obj.setToken(entity.getTypeToken() + " " + entity.getToken());
		obj.setPhones(new Phones().normalizeObjList(entity.getPhones()));
		return obj;
	}

	@Override
	public List<UserDto> normalizeObjList(List<User> entityList) {

		List<UserDto> result = new ArrayList<UserDto>();

		for (User user : entityList) {

			result.add(normalizeObj(user));

		}

		return result;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", token=" + token + ", password=" + password
				+ ", disabled=" + disabled + ", lastLogin=" + lastLogin + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(disabled, email, id, lastLogin, name, password, token);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(disabled, other.disabled) && Objects.equals(email, other.email)
				&& Objects.equals(id, other.id) && Objects.equals(lastLogin, other.lastLogin)
				&& Objects.equals(name, other.name) && Objects.equals(password, other.password)
				&& Objects.equals(token, other.token);
	}

}
