package com.lse.admin.model.agenda;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.lse.admin.config.InputReader;
import com.lse.admin.model.agenda.composite.Agenda.Outcome;

/*
 * captures the tasks about people management activities
 */
public class PeopleManagementAssignment {
  private String description;
  private String peopleIWouldWantToTalk;
  private long duration;

  private PeopleManagementAssignment(PeopleManagementAssignment.Builder builder) {
    this.description = builder.description;
    this.duration = builder.duration;
    this.peopleIWouldWantToTalk = builder.peopleIWouldWantToTalk;
  }

  public List<Outcome> getOutcome() {
    Outcome outcome = new Outcome();
    outcome.setSegment(this.getClass().getSimpleName());
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
  public static PeopleManagementAssignment.Builder builder(InputReader inputReader) {
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

    public PeopleManagementAssignment.Builder withDescription() {
      do {
        String userInput = this.inputReader.prompt(
            "PeopleManagementAssignment :: people management task you would want to do today ?");
        if (StringUtils.hasText(userInput)) {
          this.description = userInput;
        }
      } while (!StringUtils.hasText(description));

      return this;
    }

    public PeopleManagementAssignment.Builder withDuration() {
      if ("NA".equalsIgnoreCase(this.description)) return this;
      do {
        String userInput = this.inputReader
            .prompt("PeopleManagementAssignment :: low long would you want to spend ?");
        if (StringUtils.hasText(userInput)) {
          this.duration = Long.parseLong(userInput);
        }
      } while (duration == 0l);

      return this;
    }
    
    public PeopleManagementAssignment.Builder withSeparator() {
      String separator = new String(new char[100]).replace("\0", "*");
      System.out.println(separator);
      return this;
    }

    public PeopleManagementAssignment.Builder withPeopleIWouldWantToTalk() {
      if ("NA".equalsIgnoreCase(this.description)) return this;
      do {
        String userInput = this.inputReader.prompt(
            "PeopleManagementAssignment :: who are the people you would want to catch up with (comma separated) ?");
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