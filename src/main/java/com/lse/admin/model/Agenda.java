package com.lse.admin.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Generated;

import lombok.Data;

public class Agenda {
  private CertificationAssignment certificationAssignment;
  private PeopleManagementAssignment peopleManagementAssignment;
  private SystemDesignAssignment systemDesignAssignment;
  private BusinessDevelopmentAssignment businessDevelopmentAssignment;
  private DeliveryOversightAssignment deliveryOversightAssignment;
  private String codingAssignment;
  private List<String> parentingAssignment;
  private List<String> personalAssignment;
  private String cloudServiceToLearn;

  @Generated("SparkTools")
  private Agenda(Builder builder) {
    this.certificationAssignment = builder.certificationAssignment;
    this.peopleManagementAssignment = builder.peopleManagementAssignment;
    this.systemDesignAssignment = builder.systemDesignAssignment;
    this.businessDevelopmentAssignment = builder.businessDevelopmentAssignment;
    this.deliveryOversightAssignment = builder.deliveryOversightAssignment;
    this.codingAssignment = builder.codingAssignment;
    this.parentingAssignment = builder.parentingAssignment;
    this.personalAssignment = builder.personalAssignment;
    this.cloudServiceToLearn = builder.cloudServiceToLearn;
  }

  public static class Task {
    protected String description;
    protected long duration;

    @Generated("SparkTools")
    private Task(Builder builder) {
      this.description = builder.description;
      this.duration = builder.duration;
    }

    public Task(String description, Long duration) {
      super();
      this.description = description;
      this.duration = duration;
    }

    /**
     * Creates builder to build {@link Task}.
     * 
     * @return created builder
     */
    @Generated("SparkTools")
    public static Builder builder() {
      return new Builder();
    }

    /**
     * Builder to build {@link Task}.
     */
    @Generated("SparkTools")
    public static final class Builder {
      private String description;
      private Long duration;

      private Builder() {
      }

      public Builder withDescription(String description) {
        this.description = description;
        return this;
      }

      public Builder withDuration(Long duration) {
        this.duration = duration;
        return this;
      }

      public Task build() {
        return new Task(this);
      }
    }

  }

  public static class CertificationAssignment extends Task {

    public CertificationAssignment(String description, Long duration) {
      super(description, duration);
    }

    public List<Outcome> getOutcome() {
      Outcome outcome = new Outcome();
      outcome.setTask(description);
      outcome.setDuration(duration);
      List<Outcome> outcomes = new ArrayList<>();
      outcomes.add(outcome);
      return outcomes;
    }

  }

  @Data
  public static class Outcome {
    String task;
    Long duration;
    String additionalInfo;

  }

  public static class BusinessDevelopmentAssignment extends Task {

    public BusinessDevelopmentAssignment(String description, Long duration) {
      super(description, duration);
    }

  }

  public static class DeliveryOversightAssignment extends Task {

    private final String project;

    public DeliveryOversightAssignment(String project, String description,
        Long duration) {
      super(description, duration);
      this.project = project;
    }

  }

  public static class PeopleManagementAssignment extends Task {

    private final String whom;

    public PeopleManagementAssignment(String whom, String description,
        Long duration) {
      super(description, duration);
      this.whom = whom;
    }

  }

  public static class SystemDesignAssignment extends Task {

    private final String system;

    public SystemDesignAssignment(String system, String description,
        Long duration) {
      super(description, duration);
      this.system = system;
    }

  }

  /**
   * Creates builder to build {@link Agenda}.
   * 
   * @return created builder
   */
  @Generated("SparkTools")
  public static Builder builder() {
    return new Builder();
  }

  /**
   * Builder to build {@link Agenda}.
   */
  @Generated("SparkTools")
  public static final class Builder {
    private CertificationAssignment certificationAssignment;
    private PeopleManagementAssignment peopleManagementAssignment;
    private SystemDesignAssignment systemDesignAssignment;
    private BusinessDevelopmentAssignment businessDevelopmentAssignment;
    private DeliveryOversightAssignment deliveryOversightAssignment;
    private String codingAssignment;
    private List<String> parentingAssignment = Collections.emptyList();
    private List<String> personalAssignment = Collections.emptyList();
    private String cloudServiceToLearn;

    private Builder() {
    }

    public Builder withCertificationAssignment(
        CertificationAssignment certificationAssignment) {
      this.certificationAssignment = certificationAssignment;
      return this;
    }

    public Builder withPeopleManagementAssignment(
        PeopleManagementAssignment peopleManagementAssignment) {
      this.peopleManagementAssignment = peopleManagementAssignment;
      return this;
    }

    public Builder withSystemDesignAssignment(
        SystemDesignAssignment systemDesignAssignment) {
      this.systemDesignAssignment = systemDesignAssignment;
      return this;
    }

    public Builder withBusinessDevelopmentAssignment(
        BusinessDevelopmentAssignment businessDevelopmentAssignment) {
      this.businessDevelopmentAssignment = businessDevelopmentAssignment;
      return this;
    }

    public Builder withDeliveryOversightAssignment(
        DeliveryOversightAssignment deliveryOversightAssignment) {
      this.deliveryOversightAssignment = deliveryOversightAssignment;
      return this;
    }

    public Builder withCodingAssignment(String codingAssignment) {
      this.codingAssignment = codingAssignment;
      return this;
    }

    public Builder withParentingAssignment(List<String> parentingAssignment) {
      this.parentingAssignment = parentingAssignment;
      return this;
    }

    public Builder withPersonalAssignment(List<String> personalAssignment) {
      this.personalAssignment = personalAssignment;
      return this;
    }

    public Builder withCloudServiceToLearn(String cloudServiceToLearn) {
      this.cloudServiceToLearn = cloudServiceToLearn;
      return this;
    }

    public Agenda build() {
      return new Agenda(this);
    }
  }

  public List<Outcome> getOutcome() {
    return this.certificationAssignment.getOutcome();
  }

}
