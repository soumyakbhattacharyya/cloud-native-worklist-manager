package com.lse.admin.model.agenda;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.lse.admin.config.InputReader;
import com.lse.admin.model.agenda.composite.Agenda.Outcome;

public class CertificationAssignment {
  private String description;
  private long duration;

  private CertificationAssignment(CertificationAssignment.Builder builder) {
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
  public static CertificationAssignment.Builder builder(InputReader inputReader) {
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

    public CertificationAssignment.Builder withDescription() {
      do {
        String userInput = this.inputReader.prompt(
            "CertificationAssignment :: which certification topic you would want to study for ?");
        if (StringUtils.hasText(userInput)) {
          this.description = userInput;
        }
      } while (!StringUtils.hasText(description));

      return this;
    }

    public CertificationAssignment.Builder withDuration() {
      do {
        String userInput = this.inputReader
            .prompt("CertificationAssignment :: how long you would want to study ?");
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