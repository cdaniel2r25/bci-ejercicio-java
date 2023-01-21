package com.example.bci.demoApi.models;

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
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.example.bci.demoApi.models.dto.UserPhonesDto;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "phones")
public class Phones extends BaseEntity<UserPhonesDto, Phones> {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@Column(name = "id", nullable = false, unique = true)
	private String id;

	@Column(name = "number", nullable = false)
	private String number;

	@Column(name = "city_code", nullable = false)
	private String cityCode;

	@Column(name = "country_code", nullable = false)
	private String countryCode;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "phones")
	@JsonIgnore
	private Set<User> users = new HashSet<>();

	@Override
	public UserPhonesDto normalizeObj(Phones entity) {

		UserPhonesDto obj = new UserPhonesDto();

		obj.setCitycode(entity.getCityCode());
		obj.setContrycode(entity.getCountryCode());
		obj.setNumber(entity.getNumber());
		return obj;
	}

	@Override
	public List<UserPhonesDto> normalizeObjList(List<Phones> entityList) {

		List<UserPhonesDto> result = new ArrayList<UserPhonesDto>();

		for (Phones user : entityList) {

			result.add(normalizeObj(user));

		}

		return result;
	}

	@Override
	public String toString() {
		return "UserPhones [id=" + id + ", number=" + number + ", cityCode=" + cityCode + ", countryCode=" + countryCode
				+ "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Phones other = (Phones) obj;
		return Objects.equals(cityCode, other.cityCode) && Objects.equals(countryCode, other.countryCode)
				&& Objects.equals(id, other.id) && Objects.equals(number, other.number);
	}

	@Override
	public int hashCode() {
		return Objects.hash(cityCode, countryCode, id, number);
	}

	public Phones(String number, String cityCode, String countryCode) {
		super();
		this.number = number;
		this.cityCode = cityCode;
		this.countryCode = countryCode;
	}

}
