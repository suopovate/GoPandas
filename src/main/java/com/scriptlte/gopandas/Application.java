/*
 * Copyright 2014-2018 the original author or authors.
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
package com.scriptlte.gopandas;

import com.scriptlte.gopandas.models.EmployeeRepository;
import com.scriptlte.gopandas.models.ItemRepository;
import com.scriptlte.gopandas.security.config.SecurityConstant;
import com.scriptlte.gopandas.security.config.pwencoder.Md5PasswordEncoder;
import com.scriptlte.gopandas.security.dao.grant.OrgGrantRepository;
import com.scriptlte.gopandas.security.dao.relation.OrgRel_X_GrantRepository;
import com.scriptlte.gopandas.security.dao.relation.OrgRel_X_RoleRepository;
import com.scriptlte.gopandas.security.dao.role.OrgRoleRepository;
import com.scriptlte.gopandas.security.dao.user.OrgUserRepository;
import com.scriptlte.gopandas.security.pojo.grant.OrgGrant;
import com.scriptlte.gopandas.security.pojo.relation.OrgRel_X_Grant;
import com.scriptlte.gopandas.security.pojo.relation.OrgRel_X_Role;
import com.scriptlte.gopandas.security.pojo.role.OrgRole;
import com.scriptlte.gopandas.security.pojo.user.OrgUser;
import com.scriptlte.gopandas.security.service.OrgRoleService;
import com.scriptlte.gopandas.security.service.OrgUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This example shows various ways to secure Spring Data REST applications using Spring Security
 *
 * @author Greg Turnquist
 */

@SpringBootApplication
public class Application {

	@Autowired
    ItemRepository itemRepository;
	@Autowired
    EmployeeRepository employeeRepository;

	@Autowired
	OrgUserService orgUserService;
	@Autowired
	OrgRoleService orgRoleService;
	@Autowired
	OrgUserRepository orgUserRepository;
	@Autowired
	OrgRoleRepository orgRoleRepository;
	@Autowired
    OrgGrantRepository orgGrantRepository;
	@Autowired
	OrgRel_X_GrantRepository orgRel_x_grantRepository;
	@Autowired
	OrgRel_X_RoleRepository orgRel_x_roleRepository;

	public static void main(String[] args) {
	    SpringApplication.run(Application.class);
	}

	/**
	 * Pre-load the system with employees and items.
	 */
	public @PostConstruct void init() {
		addUserRole();
//		employeeRepository.save(new Employee("Bilbo", "Baggins", "thief"));
//		employeeRepository.save(new Employee("Frodo", "Baggins", "ring bearer"));
//		employeeRepository.save(new Employee("Gandalf", "the Wizard", "servant of the Secret Fire"));

		/**
		 * Due to method-level protections on {@link example.company.ItemRepository}, the security context must be loaded
		 * with an authentication token containing the necessary privileges.
		 */
//		SecurityUtils.runAs("system", "system", "ROLE_ADMIN");

//		itemRepository.save(new Item("Sting"));
//		itemRepository.save(new Item("the one ring"));

	}

	public void addUserRole(){
//		OrgUser userAdmin = orgUserService.getUserByUserName("admin");
//		if (userAdmin==null)
//			userAdmin = new OrgUser();
//		userAdmin.setUsername("admin");
//		userAdmin.setPassword("123");
//		userAdmin.setStatus(SecurityConstant.USER_STATUS_ENABLE);
//		orgUserService.saveOrUpdate(userAdmin);
		OrgUser user1 = orgUserService.getUserByUserName("testuser");
		if (user1 == null){
			user1 = new OrgUser();
		}
		user1.setUsername("testuser");
		user1.setPassword(Md5PasswordEncoder.getInstance().encode("123"));
		user1.setStatus(SecurityConstant.USER_STATUS_ENABLE);
		orgUserService.saveOrUpdate(user1);
		OrgUser user = orgUserService.getUserByUserName("testuser2");
		if (user == null){
			user = new OrgUser();
		}
		user.setUsername("testuser2");
		user.setPassword(Md5PasswordEncoder.getInstance().encode("123"));
		user.setStatus(SecurityConstant.USER_STATUS_ENABLE);
		orgUserService.saveOrUpdate(user);

		OrgRole role = orgRoleService.getRoleByName("TESTROLE");
		if (role == null){
			role = new OrgRole();
			role.setRoleName("TESTROLE");
		}
        orgRoleRepository.save(role);
		OrgGrant orgGrant1 = orgGrantRepository.findOrgGrantByGrantName("testGrant1");
		if (orgGrant1 == null) orgGrant1 = new OrgGrant();
		orgGrant1.setGrantName("testGrant1");
		orgGrantRepository.save(orgGrant1);
		OrgGrant orgGrant2 = orgGrantRepository.findOrgGrantByGrantName("testGrant2");
		if (orgGrant2 == null) orgGrant2 = new OrgGrant();
        orgGrant2.setGrantName("testGrant2");
        orgGrantRepository.save(orgGrant2);
		OrgGrant orgGrant3 = orgGrantRepository.findOrgGrantByGrantName("testGrant3");
		if (orgGrant3 == null) orgGrant3 = new OrgGrant();
        orgGrant3.setGrantName("testGrant3");
        orgGrantRepository.save(orgGrant3);

		//保存用户和角色的对应
//		orgRel_x_roleRepository.save(new OrgRel_X_Role(user1.getId(),role.getId(),SecurityConstant.OBJECT_TYPE_USER));
		//保存角色和权限的对应
//		orgRel_x_grantRepository.save(new OrgRel_X_Grant(role.getId(),orgGrant1.getId(),SecurityConstant.OBJECT_TYPE_ROLE));
//		orgRel_x_grantRepository.save(new OrgRel_X_Grant(role.getId(),orgGrant2.getId(),SecurityConstant.OBJECT_TYPE_ROLE));
		//保存用户和权限的对应
//		orgRel_x_grantRepository.save(new OrgRel_X_Grant(user1.getId(),orgGrant3.getId(),SecurityConstant.OBJECT_TYPE_USER));
//		orgRel_x_grantRepository.save(new OrgRel_X_Grant(user.getId(),orgGrant3.getId(),SecurityConstant.OBJECT_TYPE_USER));



	}
}
