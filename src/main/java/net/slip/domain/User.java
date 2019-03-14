package net.slip.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.web.bind.annotation.GetMapping;

@Entity
public class User {

		@Id
		@GeneratedValue // auto increment
		private Long id;
		
		@Column(nullable=false, length=20, unique=true)
		private String userId;
		
		
		private String userPassword;
		private String userName;
		private String userEmail;
		
		public void setUserEmail(String userEmail) {
			this.userEmail = userEmail;
		}
		public void setUserId(String userId) {
			this.userId = userId;
		}
		public void setUserName(String userName) {
			this.userName = userName;
		}
		public void setUserPassword(String userPassword) {
			this.userPassword = userPassword;
		}
		public String getUserEmail() {
			return userEmail;
		}
		public String getUserId() {
			return userId;
		}
		public String getUserName() {
			return userName;
		}
		public String getUserPassword() {
			return userPassword;
		}
		@Override
		public String toString() {
			return "User [userId=" + userId + ", userPassword=" + userPassword + ", userName=" + userName + ", userEmail=" + userEmail + "]";
		}
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		
		public void update(User updateUser) {
			this.userPassword = updateUser.userPassword;
			this.userName = updateUser.userName;
			this.userEmail = updateUser.userEmail;
		}
}
