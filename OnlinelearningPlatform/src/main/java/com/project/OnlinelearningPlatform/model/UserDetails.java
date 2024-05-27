package com.project.OnlinelearningPlatform.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "userDetails")
@AllArgsConstructor
@NoArgsConstructor
public class UserDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE )
	@Column(name = "userId")
	private Long userId;
	
	@Column(name = "userName") 
	private String userName;
	
	@Column(name = "password")
	private String password;
	
    private String email;
    
    private Long phoneNumber;

	

}
