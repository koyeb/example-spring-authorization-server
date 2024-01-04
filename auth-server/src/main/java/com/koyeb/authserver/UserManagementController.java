// auth-koyeb/auth-server/src/main/java/com/koyeb/authserver/UserManagementController.java
package com.koyeb.authserver;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@PreAuthorize("hasRole('ADMIN')")
@RestController
public class UserManagementController {

  private final JdbcUserDetailsManager userDetailsManager;
  private final PasswordEncoder passwordEncoder;

  public UserManagementController(JdbcUserDetailsManager userDetailsManager, PasswordEncoder passwordEncoder) {
    this.userDetailsManager = userDetailsManager;
    this.passwordEncoder = passwordEncoder;
  }

  @GetMapping("/users/{userName}")
  UserDetails loadUser(@PathVariable String userName) {
    return userDetailsManager.loadUserByUsername(userName);
  }
  
  @GetMapping("/groups")
  List<String> getAllGroups() {
    return userDetailsManager.findAllGroups();
  }
  @GetMapping("/groups/{groupName}/users")
  List<String> getUsersInGroup(@PathVariable String groupName) {
    return userDetailsManager.findUsersInGroup("GROUP_" + groupName);
  }
  
  @GetMapping("/groups/{groupName}/authorities")
  List<GrantedAuthority> getGroupAuthorities(@PathVariable String groupName) {
    return userDetailsManager.findGroupAuthorities("GROUP_" +groupName);
  }

  @GetMapping("/users/create/{userName}:{password}")
  UserDetails createUser(
    @PathVariable String userName, 
    @PathVariable String password
  ) {
    userDetailsManager.createUser(
      User.builder()
        .username(userName)
        .password(passwordEncoder.encode(password))
        .build()
    );
    addUserToGroup("USERS", userName);
    return loadUser(userName);
  }
  
  @GetMapping("/groups/create/{groupName}:{role}")
  List<GrantedAuthority> createGroup(@PathVariable String groupName, @PathVariable String role) {
    userDetailsManager.createGroup(
      "GROUP_" + groupName, 
      AuthorityUtils.createAuthorityList("ROLE_" + role)
    );
    return userDetailsManager.findGroupAuthorities("GROUP_" + groupName);
  }

  @GetMapping("/groups/{groupName}/add/{userName}")
  List<String> addUserToGroup(
    @PathVariable String groupName, 
    @PathVariable String userName
  ) {
    userDetailsManager.addUserToGroup(userName, "GROUP_" + groupName);
    return getUsersInGroup(groupName);
  }

}
