package com.javabank.the_java_bank.entity;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String firstName;
	private String lastName;
	private String otherName;
	private String gender;
	private String address;
	private String stateOfOrigin;
	private String accountNumber;
	private BigDecimal accountBalance;
	private String email;
	private String password;
	private Role role;
	
	public User() {
		super();
	}
	public String getPassword() {
		return password;
	}
	public User(Long id, String firstName, String lastName, String otherName, String gender, String address,
			String stateOfOrigin, String accountNumber, BigDecimal accountBalance, String email, String password,
			Role role, String phoneNumber, String alternativePhoneNumber, String status, LocalDateTime createdAt,
			LocalDateTime modifiedAt) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.otherName = otherName;
		this.gender = gender;
		this.address = address;
		this.stateOfOrigin = stateOfOrigin;
		this.accountNumber = accountNumber;
		this.accountBalance = accountBalance;
		this.email = email;
		this.password = password;
		this.role = role;
		this.phoneNumber = phoneNumber;
		this.alternativePhoneNumber = alternativePhoneNumber;
		this.status = status;
		this.createdAt = createdAt;
		this.modifiedAt = modifiedAt;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	private String phoneNumber;
	private String alternativePhoneNumber;
	private String status;
	@CreationTimestamp
	private LocalDateTime createdAt;
	@UpdateTimestamp
	private LocalDateTime modifiedAt;
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
	public String getOtherName() {
		return otherName;
	}
	public void setOtherName(String otherName) {
		this.otherName = otherName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getStateOfOrigin() {
		return stateOfOrigin;
	}
	public void setStateOfOrigin(String stateOfOrigin) {
		this.stateOfOrigin = stateOfOrigin;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public BigDecimal getAccountBalance() {
		return accountBalance;
	}
	public void setAccountBalance(BigDecimal accountBalance) {
		this.accountBalance = accountBalance;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getAlternativePhoneNumber() {
		return alternativePhoneNumber;
	}
	public void setAlternativePhoneNumber(String alternativePhoneNumber) {
		this.alternativePhoneNumber = alternativePhoneNumber;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public LocalDateTime getModifiedAt() {
		return modifiedAt;
	}
	public void setModifiedAt(LocalDateTime modifiedAt) {
		this.modifiedAt = modifiedAt;
	}
	
	
	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", otherName=" + otherName
				+ ", gender=" + gender + ", address=" + address + ", stateOfOrigin=" + stateOfOrigin
				+ ", accountNumber=" + accountNumber + ", accountBalance=" + accountBalance + ", email=" + email
				+ ", password=" + password + ", role=" + role + ", phoneNumber=" + phoneNumber
				+ ", alternativePhoneNumber=" + alternativePhoneNumber + ", status=" + status + ", createdAt="
				+ createdAt + ", modifiedAt=" + modifiedAt + "]";
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return List.of(new SimpleGrantedAuthority(role.name()));
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return email;
	}
	
	
	
	
}
