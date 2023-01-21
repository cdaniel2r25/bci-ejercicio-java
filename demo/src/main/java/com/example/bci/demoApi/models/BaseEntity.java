package com.example.bci.demoApi.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity<T, ID> implements Serializable {

  private static final long serialVersionUID = 1L;


  /**
   * Returns a multi-line String with key=value pairs.
   * 
   * @return a String representation of this class.
   */
  public abstract String toString();

  /**
   * Compares object equality. When using Hibernate, the primary key should not be a part of this
   * comparison.
   * 
   * @param o object to compare to
   * @return true/false based on equality tests
   */
  public abstract boolean equals(Object o);

  /**
   * When you override equals, you should override hashCode. See "Why are equals() and hashCode()
   * importation" for more information: http://www.hibernate.org/109.html
   * 
   * @return hashCode
   */
  public abstract int hashCode();

  /**
   * Normaliza el objeto con un objeto de la clase a objecto DTO
   * 
   * @param entity
   * @return Object DTO
   */
  public abstract T normalizeObj(ID entity);

  /**
   * Normaliza la lista del objecto de la clase a objecto DTO
   * 
   * @param entityList
   * @return List Object DTO
   */
  public abstract List<T> normalizeObjList(List<ID> entityList);


  @LastModifiedDate
  @Column(name = "created_at")
  private LocalDateTime createdAt;

  @CreatedDate
  @Column(name = "modified_at")
  private LocalDateTime modifiedAt;


}
