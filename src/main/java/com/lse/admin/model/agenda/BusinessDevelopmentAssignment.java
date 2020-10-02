package com.lse.admin.model.agenda;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.lse.admin.config.InputReader;
import com.lse.admin.model.agenda.composite.Agenda.Outcome;

public class BusinessDevelopmentAssignment {

  private String description;
  private long duration;

  private BusinessDevelopmentAssignment(
      BusinessDevelopmentAssignment.Builder builder) {
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
  public static BusinessDevelopmentAssignment.Builder builder(
      InputReader inputReader) {
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

    public BusinessDevelopmentAssignment.Builder withDescription() {
      do {
        String userInput = this.inputReader
            .prompt("BusinessDevelopmentAssignment :: which task related to developing new business would you like to perform ?");
        if (StringUtils.hasText(userInput)) {
          this.description = userInput;
        }
      } while (!StringUtils.hasText(description));

      return this;
    }

    public BusinessDevelopmentAssignment.Builder withDuration() {
      do {
        String userInput = this.inputReader
            .prompt("BusinessDevelopmentAssignment :: how long would you want to spend on this ?");
        if (StringUtils.hasText(userInput)) {
          this.duration = Long.parseLong(userInput);
        }
      } while (duration == 0l);

      return this;
    }

    public BusinessDevelopmentAssignment build() {
      return new BusinessDevelopmentAssignment(this);
    }
  }
}