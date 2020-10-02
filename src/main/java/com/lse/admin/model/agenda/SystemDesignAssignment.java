package com.lse.admin.model.agenda;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.lse.admin.config.InputReader;
import com.lse.admin.model.agenda.composite.Agenda.Outcome;

public class SystemDesignAssignment {
  private String description;
  private String system;
  private long duration;

  private SystemDesignAssignment(SystemDesignAssignment.Builder builder) {
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
  public static SystemDesignAssignment.Builder builder(InputReader inputReader) {
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

    public SystemDesignAssignment.Builder withDescription() {
      do {
        String userInput = this.inputReader.prompt(
            "SystemDesignAssignment :: which specific task you would want to accomplish ?");
        if (StringUtils.hasText(userInput)) {
          this.description = userInput;
        }
      } while (!StringUtils.hasText(description));

      return this;
    }

    public SystemDesignAssignment.Builder withDuration() {
      do {
        String userInput = this.inputReader
            .prompt("SystemDesignAssignment :: low long would you want to spend ?");
        if (StringUtils.hasText(userInput)) {
          this.duration = Long.parseLong(userInput);
        }
      } while (duration == 0l);

      return this;
    }

    public SystemDesignAssignment.Builder withSystem() {
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