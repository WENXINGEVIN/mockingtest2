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
package com.mocking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.mocking.data.EmployeeRepository;
import com.mocking.data.ManagerRepository;
import com.mocking.model.Employee;
import com.mocking.model.Manager;

/**
 * @author Greg Turnquist
 */
// tag::code[]
@Component
public class DatabaseLoader implements CommandLineRunner {

	private final EmployeeRepository employees;
	private final ManagerRepository managers;

	@Autowired
	public DatabaseLoader(EmployeeRepository employeeRepository,
						  ManagerRepository managerRepository) {

		this.employees = employeeRepository;
		this.managers = managerRepository;
	}

	@Override
	public void run(String... strings) throws Exception {
		Manager admin = null;
		if (this.managers.findByName("admin") == null) {
			 admin = this.managers.save(new Manager("admin", "admin",
							"ROLE_MANAGER"));
		}
		
		SecurityContextHolder.getContext().setAuthentication(
			new UsernamePasswordAuthenticationToken("greg", "doesn't matter",
				AuthorityUtils.createAuthorityList("ROLE_MANAGER")));
		
		/*
		this.employees.save(new Employee("Frodo", "Baggins", "ring bearer", admin));
		this.employees.save(new Employee("Bilbo", "Baggins", "burglar", admin));
		this.employees.save(new Employee("Gandalf", "the Grey", "wizard", admin));
	    */

		SecurityContextHolder.clearContext();
	}
}
// end::code[]