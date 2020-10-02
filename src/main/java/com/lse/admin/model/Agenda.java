package com.lse.admin.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.util.StringUtils;

import com.lse.admin.config.InputReader;

import lombok.Builder;
import lombok.Data;

@Builder
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

  public static class Task {
    protected String description;
    protected long duration;

    public Task(String description, long duration) {
      super();
      this.description = description;
      this.duration = duration;
    }

    public Task() {
    }

  }

  public static class CertificationAssignment {
    private String description;
    private long duration;

    private CertificationAssignment(Builder builder) {
      this.description = builder.description;
      this.duration = builder.duration;
    }

    public List<Outcome> getOutcome() {
      Outcome outcome = new Outcome();
      outcome.setTask(description);
      outcome.setDuration(duration);
      List<Outcome> outcomes = new ArrayList<>();
      outcomes.add(outcome);
      return outcomes;
    }

    /**
     * Creates builder to build {@link CertificationAssignment}.
     * 
     * @return created builder
     */
    public static Builder builder(InputReader inputReader) {
      return new Builder(inputReader);
    }

    /**
     * Builder to build {@link CertificationAssignment}.
     */
    public static final class Builder {
      private String description;
      private long duration;
      private InputReader inputReader;

      private Builder(InputReader inputReader) {
        this.inputReader = inputReader;
      }

      public Builder withDescription() {
        do {
          String userInput = this.inputReader.prompt(
              "which certification topic you would want to study for ?");
          if (StringUtils.hasText(userInput)) {
            this.description = userInput;
          }
        } while (!StringUtils.hasText(description));

        return this;
      }

      public Builder withDuration() {
        do {
          String userInput = this.inputReader
              .prompt("how long you would want to study ?");
          if (StringUtils.hasText(userInput)) {
            this.duration = Long.parseLong(userInput);
          }
        } while (duration == 0l);

        return this;
      }

      public CertificationAssignment build() {
        return new CertificationAssignment(this);
      }
    }
  }

  @Builder
  public static class BusinessDevelopmentAssignment extends Task {

    public BusinessDevelopmentAssignment() {
    }

    public BusinessDevelopmentAssignment(String description, Long duration) {
      super(description, duration);
    }

  }

  @Builder
  public static class DeliveryOversightAssignment extends Task {

    public DeliveryOversightAssignment(String project) {
      this.project = project;
    }

    private String project;

    public DeliveryOversightAssignment(String project, String description,
        Long duration) {
      super(description, duration);
      this.project = project;
    }

  }

  public static class PeopleManagementAssignment {
    private String description;
    private String peopleIWouldWantToTalk;
    private long duration;

    private PeopleManagementAssignment(Builder builder) {
      this.description = builder.description;
      this.duration = builder.duration;
      this.peopleIWouldWantToTalk = builder.peopleIWouldWantToTalk;
    }

    public List<Outcome> getOutcome() {
      Outcome outcome = new Outcome();
      outcome.setTask(description);
      outcome.setDuration(duration);
      outcome.setAdditionalInfo(peopleIWouldWantToTalk);
      System.out.println(outcome);
      List<Outcome> outcomes = new ArrayList<>();
      outcomes.add(outcome);
      return outcomes;
    }

    /**
     * Creates builder to build {@link CertificationAssignment}.
     * 
     * @return created builder
     */
    public static Builder builder(InputReader inputReader) {
      return new Builder(inputReader);
    }

    /**
     * Builder to build {@link CertificationAssignment}.
     */
    public static final class Builder {
      private String description;
      private long duration;
      private String peopleIWouldWantToTalk;
      private InputReader inputReader;

      private Builder(InputReader inputReader) {
        this.inputReader = inputReader;
      }

      public Builder withDescription() {
        do {
          String userInput = this.inputReader.prompt(
              "what people management task you would want to do today ?");
          if (StringUtils.hasText(userInput)) {
            this.description = userInput;
          }
        } while (!StringUtils.hasText(description));

        return this;
      }

      public Builder withDuration() {
        do {
          String userInput = this.inputReader
              .prompt("low long would you want to spend ?");
          if (StringUtils.hasText(userInput)) {
            this.duration = Long.parseLong(userInput);
          }
        } while (duration == 0l);

        return this;
      }

      public Builder withPeopleIWouldWantToTalk() {
        do {
          String userInput = this.inputReader.prompt(
              "who are the people you would want to catch up with (comma separated) ?");
          if (StringUtils.hasText(userInput)) {
            this.peopleIWouldWantToTalk = userInput;
          }
        } while (!StringUtils.hasText(peopleIWouldWantToTalk));

        return this;
      }

      public PeopleManagementAssignment build() {
        return new PeopleManagementAssignment(this);
      }
    }
  }

  
  
  public static class SystemDesignAssignment {
    private String description;
    private String system;
    private long duration;

    private SystemDesignAssignment(Builder builder) {
      this.description = builder.description;
      this.duration = builder.duration;
      this.system = builder.system;
    }

    public List<Outcome> getOutcome() {
      Outcome outcome = new Outcome();
      outcome.setTask(description);
      outcome.setDuration(duration);
      outcome.setAdditionalInfo(system);
      List<Outcome> outcomes = new ArrayList<>();
      outcomes.add(outcome);
      return outcomes;
    }

    /**
     * Creates builder to build {@link CertificationAssignment}.
     * 
     * @return created builder
     */
    public static Builder builder(InputReader inputReader) {
      return new Builder(inputReader);
    }

    /**
     * Builder to build {@link CertificationAssignment}.
     */
    public static final class Builder {
      private String description;
      private long duration;
      private String system;
      private InputReader inputReader;

      private Builder(InputReader inputReader) {
        this.inputReader = inputReader;
      }

      public Builder withDescription() {
        do {
          String userInput = this.inputReader.prompt(
              "which specific task you would want to accomplish ?");
          if (StringUtils.hasText(userInput)) {
            this.description = userInput;
          }
        } while (!StringUtils.hasText(description));

        return this;
      }

      public Builder withDuration() {
        do {
          String userInput = this.inputReader
              .prompt("low long would you want to spend ?");
          if (StringUtils.hasText(userInput)) {
            this.duration = Long.parseLong(userInput);
          }
        } while (duration == 0l);

        return this;
      }

      public Builder withSystem() {
        do {
          String userInput = this.inputReader.prompt(
              "which system you would like to work upon ?");
          if (StringUtils.hasText(userInput)) {
            this.system = userInput;
          }
        } while (!StringUtils.hasText(system));

        return this;
      }

      public SystemDesignAssignment build() {
        return new SystemDesignAssignment(this);
      }
    }
  }

  /**
   * outcome section
   */

  @Data
  public static class Outcome {
    String task;
    Long duration;
    String additionalInfo;

  }

  public List<Outcome> getOutcome() {
    List<Outcome> resultant = new ArrayList<>();
    resultant.addAll(this.certificationAssignment.getOutcome());
    resultant.addAll(this.peopleManagementAssignment.getOutcome());
   
    return resultant;
  }

}
