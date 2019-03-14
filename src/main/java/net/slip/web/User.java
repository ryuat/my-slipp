package net.slip.web;

import org.springframework.web.bind.annotation.GetMapping;

public class User {

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
}
