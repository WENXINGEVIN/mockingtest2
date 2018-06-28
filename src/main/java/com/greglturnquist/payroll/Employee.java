/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.greglturnquist.payroll;

import javax.persistence.*;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

/**
 * @author Greg Turnquist
 */
// tag::code[]
@Data
@Entity
@Table(name = "employee")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, 
allowGetters = true)
public class Employee {

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	private @Id @GeneratedValue Long id;
	private String firstName;
	private String lastName;
	private String description;
	private String password;
	private int uid;
	private String email;
	private String security_q1;
	private String security_q2;
	private String security_a1;
	private String security_a2;
	
	
    private Date created_at;
    
    private Date updated_at;
    
    @PrePersist
    protected void onCreate() {
      created_at = new Date();
    }
    
    @PreUpdate
    protected void onUpdate() {
      updated_at = new Date();
    }
  
	private @Version @JsonIgnore Long version;

	private @ManyToOne Manager manager;

	private Employee() {}

	public Employee(String firstName, String lastName, String description, Manager manager, String password, int uid, String email, String security_q1, String security_q2, String security_a1, String security_a2) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.description = description;
		this.manager = manager;
		this.setPassword(password);
		this.uid=uid;
		this.email=email;
		this.security_q1=security_q1;
		this.security_q2=security_q2;
		this.security_a1=security_a1;
		this.security_a2=security_a2;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSecurity_q1() {
		return security_q1;
	}

	public void setSecurity_q1(String security_q1) {
		this.security_q1 = security_q1;
	}

	public String getSecurity_q2() {
		return security_q2;
	}

	public void setSecurity_q2(String security_q2) {
		this.security_q2 = security_q2;
	}

	public String getSecurity_a1() {
		return security_a1;
	}

	public void setSecurity_a1(String security_a1) {
		this.security_a1 = security_a1;
	}

	public String getSecurity_a2() {
		return security_a2;
	}

	public void setSecurity_a2(String security_a2) {
		this.security_a2 = security_a2;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}
}
// end::code[]