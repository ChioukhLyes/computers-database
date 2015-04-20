package com.excilys.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.dao.interfaces.UserDao;

// TODO: Auto-generated Javadoc
/**
 * The Class ServiceUserDetails.
 */
@Service
public class ServiceUserDetails implements UserDetailsService {

	/** The user dao. */
	@Autowired
	private UserDao userDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.core.userdetails.UserDetailsService#
	 * loadUserByUsername(java.lang.String)
	 */
	@Transactional(readOnly = true)
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {

		com.excilys.models.User user = userDao.findUserByName(username);
		boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;

		User u = new User(user.getUsername(), user.getPassword(), enabled,
				accountNonExpired, credentialsNonExpired, accountNonLocked,
				getAuthorities(user.getRole().intValue()));
		System.out.println(user.getPassword());
		return u;
	}

	/**
	 * Gets the authorities.
	 *
	 * @param role
	 *            the role
	 * @return the authorities
	 */
	public Collection<? extends GrantedAuthority> getAuthorities(Integer role) {
		List<GrantedAuthority> authList = getGrantedAuthorities(getRoles(role));
		return authList;
	}

	/**
	 * Gets the roles.
	 *
	 * @param role
	 *            the role
	 * @return the roles
	 */
	public List<String> getRoles(Integer role) {

		List<String> roles = new ArrayList<String>();

		if (role.intValue() == 1) {
			roles.add("ROLE_ADMIN");
			roles.add("ROLE_USER");
		} else if (role.intValue() == 2) {
			roles.add("ROLE_USER");
		}
		return roles;
	}

	/**
	 * Gets the granted authorities.
	 *
	 * @param roles
	 *            the roles
	 * @return the granted authorities
	 */
	public static List<GrantedAuthority> getGrantedAuthorities(
			List<String> roles) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		for (String role : roles) {
			authorities.add(new SimpleGrantedAuthority(role));
		}
		return authorities;
	}

}
