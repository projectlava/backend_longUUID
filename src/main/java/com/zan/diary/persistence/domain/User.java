package com.zan.diary.persistence.domain;

import javax.persistence.*;

import com.zan.diary.events.user.UserDetails;
import com.zan.diary.persistence.domain.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "ZAN_USERS")
public class User {
	
  @Id
  @Column(name = "USER_ID")
  //private UUID id;
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  
  @Column(name = "USER_NAME")
  private String name;
    
  @Column(name = "USER_PASS")
  private String pass;
    
  @Column(name = "USER_CODE")
  private String code;
    
  @Column(name = "USER_LOC")
  private String loc;
  
  @Column(name = "CREATION_DATETIME")
  private Date dateTimeOfCreation;
  
  @ElementCollection(fetch = FetchType.EAGER, targetClass = java.lang.String.class)
  @JoinTable(name="DIARY_ITEMS", joinColumns=@JoinColumn(name="ID"))
  @MapKeyColumn(name="DIARY_ID")
  @Column(name="DIARIES")
  private List<String> userDiaries; 
  
  public void setDateTimeOfCreation(Date dateTimeOfCreation) {this.dateTimeOfCreation = dateTimeOfCreation;	}
  public Date getDateTimeOfCreation() {	return dateTimeOfCreation;	}
  public void setid(Long id) { this.id = id;}
  public Long getid() {  return id;  }
  public void setName(String name) { this.name = name;}
  public String getName() {  return name;  }
  public void setLoc(String loc) { this.loc = loc;}
  public String getLoc() {  return loc;  }
  public void setPass(String pass) { this.pass = pass;}
  public String getPass() {  return pass;  }
  public void setCode(String code) { this.code = code;}
  public String getCode() {  return code;  }	
	

  public void addUserDiary(String userDiary) {
	  if (userDiaries == null || userDiaries.isEmpty()) {
		  this.userDiaries = new ArrayList<String>();
		  this.userDiaries.add(userDiary);
	  } else {
		  this.userDiaries.add(userDiary);
	  }
  }  

  public void setUserDiaries(List<String> userDiaries) {
	  if (userDiaries == null) {
		  this.userDiaries = Collections.emptyList();
	  } else {
		  this.userDiaries = Collections.unmodifiableList(userDiaries);
	  }
  }

  public List<String> getUserDiaries() {
	  return userDiaries;
  }


  public List<String> getUserDiaries(int start, int end) {
	  return userDiaries.subList(start, end);
  }

  public void deleteUserDiary(String userDiary) {
	  userDiaries.remove(userDiary);
  }

  public UserDetails toUserDetails() {
	  UserDetails userDetails = new UserDetails();

	  userDetails.setid(this.id);
	  userDetails.setName(this.name);
	  userDetails.setDateTimeOfCreation(this.dateTimeOfCreation);
	  userDetails.setLoc(this.loc);
	  userDetails.setPass(this.pass);
	  userDetails.setCode(this.code);  
	  userDetails.setUserDiaries(this.userDiaries);
	  return userDetails;
  }

  public static User fromUserDetails(UserDetails userDetails) {
	  User user = new User();

	  user.setid(userDetails.getid());
	  user.setCode(userDetails.getCode());
	  user.setLoc(userDetails.getLoc());
	  user.setName(userDetails.getName());
	  user.setPass(userDetails.getPass());
	  user.setDateTimeOfCreation(userDetails.getDateTimeOfCreation());
	  user.setUserDiaries(userDetails.getUserDiaries());

	  return user;
  }  
}