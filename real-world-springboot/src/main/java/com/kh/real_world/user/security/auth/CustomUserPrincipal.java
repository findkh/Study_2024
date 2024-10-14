package com.kh.real_world.user.security.auth;

import java.security.Principal;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomUserPrincipal implements Principal {
	
	private final String email;
	
	@Override
	public String getName() {
		return email;
	}
	
}