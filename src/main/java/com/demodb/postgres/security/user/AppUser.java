package com.demodb.postgres.security.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Setter
@Getter
@Entity
@NoArgsConstructor
public class AppUser implements UserDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	@Transient
	private Boolean isBuilding;
	private static AppUser currentInstance;
	
	public AppUser(String firstName, String lastName, String email, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}
	
	public static AppUser getInstance() {
		if (currentInstance == null) {
			currentInstance = new AppUser();
		}
		return currentInstance;
	}
	
	private void setIsBuilding(Boolean status) {
		this.isBuilding = status;
	}
	
	public AppUser builder() {
		if (currentInstance == null) return null;
		this.setIsBuilding(true);
		return this;
	}
	
	public AppUser id(Long s) {
		if (isBuilding && id == null) this.id = s;
		return this;
	}
	
	public AppUser firstName(String s) {
		if (isBuilding && firstName == null) this.firstName = s;
		return this;
	}
	
	public AppUser lastName(String s) {
		if (isBuilding && lastName == null) this.lastName = s;
		return this;
	}
	
	public AppUser email(String s) {
		if (isBuilding && email == null) this.email = s;
		return this;
	}
	
	public AppUser password(String s) {
		if (isBuilding && password == null) this.password = s;
		return this;
	}
	
	public AppUser build() {
		this.setIsBuilding(false);
		return this;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singleton(new SimpleGrantedAuthority("USER"));
	}
	
	@Override
	public String getPassword() {
		return password;
	}
	
	@Override
	public String getUsername() {
		return email;
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return UserDetails.super.isAccountNonExpired();
	}
	
	@Override
	public boolean isAccountNonLocked() {
		return UserDetails.super.isAccountNonLocked();
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		return UserDetails.super.isCredentialsNonExpired();
	}
	
	@Override
	public boolean isEnabled() {
		return UserDetails.super.isEnabled();
	}
}
