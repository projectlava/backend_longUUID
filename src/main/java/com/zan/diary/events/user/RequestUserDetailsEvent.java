package com.zan.diary.events.user;

import java.util.UUID;

import com.zan.diary.events.RequestReadEvent;

public class RequestUserDetailsEvent extends RequestReadEvent {
  private Long id;

  public RequestUserDetailsEvent(Long id) {
    this.id = id;
  }

  public Long getId() {
    return id;
  }
}
