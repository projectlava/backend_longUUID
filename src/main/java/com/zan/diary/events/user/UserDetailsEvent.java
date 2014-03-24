package com.zan.diary.events.user;


import java.util.UUID;

import com.zan.diary.events.ReadEvent;

public class UserDetailsEvent extends ReadEvent {
  private Long id;
  private UserDetails userDetails;

  private UserDetailsEvent() {}

  public UserDetailsEvent(Long id, UserDetails userDetails) {
	this.id = id;
    this.userDetails = userDetails;
  }

  public UserDetails getUserDetails() {
    return userDetails;
  }

  public Long getid() {
    return id;
  }

  public static UserDetailsEvent notFound(Long id) {
    UserDetailsEvent ev = new UserDetailsEvent();
    ev.id = id;
    ev.entityFound=false;
    return ev;
  }
}
