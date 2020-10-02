package com.lse.admin.model.agenda;


import lombok.Builder;

@Builder
public class DeliveryOversightAssignment {

  public DeliveryOversightAssignment(String project) {
    this.project = project;
  }

  private String project;

  public DeliveryOversightAssignment(String project, String description,
      Long duration) {

    this.project = project;
  }

}