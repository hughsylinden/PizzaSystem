package com.hughsy.security;

import java.util.Set;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.hughsy.domain.Authorities;
import com.hughsy.domain.Customer;

public class CustomUserDetails extends Customer implements UserDetails {

	private static final long serialVersionUID = -615817106996771870L;

	public CustomUserDetails() {
	}

	public CustomUserDetails(Customer customer, BCryptPasswordEncoder encoder) {
		super(customer, encoder);
	}

	@Override
	public Set<Authorities> getAuthorities() {
		return super.getAuthorities();
	}

	@Override
	public String getPassword() {
		return super.getPassword();
	}

	@Override
	public String getUsername() {
		return super.getEmailAddress();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
