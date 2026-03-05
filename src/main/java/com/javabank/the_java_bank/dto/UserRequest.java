package com.javabank.the_java_bank.dto;

import com.javabank.the_java_bank.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

	private String firstName;
	private String lastName;
	private String otherName;
	private String gender;
	private String address;
	private String stateOfOrigin;
	private String email;
	private Role role;

	public UserRequest(String firstName, String lastName, String otherName, String gender, String address,
			String stateOfOrigin, String email, Role role, String password, String phoneNumber,
			String alternativePhoneNumber) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.otherName = otherName;
		this.gender = gender;
		this.address = address;
		this.stateOfOrigin = stateOfOrigin;
		this.email = email;
		this.role = role;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.alternativePhoneNumber = alternativePhoneNumber;
	}
	public UserRequest() {
		super();
	}
	
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	private String password;
	private String phoneNumber;
	private String alternativePhoneNumber;
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
	
	
	@Override
	public String toString() {
		return "UserRequest [firstName=" + firstName + ", lastName=" + lastName + ", otherName=" + otherName
				+ ", gender=" + gender + ", address=" + address + ", stateOfOrigin=" + stateOfOrigin + ", email="
				+ email + ", role=" + role + ", password=" + password + ", phoneNumber=" + phoneNumber
				+ ", alternativePhoneNumber=" + alternativePhoneNumber + "]";
	}
	
	
}
