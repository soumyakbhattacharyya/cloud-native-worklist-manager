package com.lse.admin.model.agenda;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.lse.admin.config.InputReader;
import com.lse.admin.model.agenda.composite.Agenda.Outcome;

/*
 * captures the tasks about certification related studies
 */
public class FamilyOrientedAssignment {
  private String description;
  private long duration;

  private FamilyOrientedAssignment(FamilyOrientedAssignment.Builder builder) {
    this.description = builder.description;
    this.duration = builder.duration;
  }

  public List<Outcome> getOutcome() {
    Outcome outcome = new Outcome();
    outcome.setSegment(this.getClass().getSimpleName());
    outcome.setTask(description);
    outcome.setDuration(duration);
    
    List<Outcome> outcomes = new ArrayList<>();
    outcomes.add(outcome);
    return outcomes;
  }

  /**
   * Creates builder to build {@link FamilyOrientedAssignment}.
   * 
   * @return created builder
   */
  public static FamilyOrientedAssignment.Builder builder(InputReader inputReader) {
    return new Builder(inputReader);
  }

  /**
   * Builder to build {@link FamilyOrientedAssignment}.
   */
  public static final class Builder {
    private String description;
    private long duration;
    private InputReader inputReader;

    private Builder(InputReader inputReader) {
      this.inputReader = inputReader;
    }
    
    public FamilyOrientedAssignment.Builder withSeparator() {
      String separator = new String(new char[100]).replace("\0", "*");
      System.out.println(separator);
      return this;
    }

    public FamilyOrientedAssignment.Builder withDescription() {
      do {
        String userInput = this.inputReader.prompt(
            "FamilyOrientedAssignment :: which family oriented task you would want to complete today ?");
        if (StringUtils.hasText(userInput)) {
          this.description = userInput;
        }
      } while (!StringUtils.hasText(description));

      return this;
    }

    public FamilyOrientedAssignment.Builder withDuration() {
      if ("NA".equalsIgnoreCase(this.description)) return this;
      do {
        String userInput = this.inputReader
            .prompt("FamilyOrientedAssignment :: how long the specific task would take ?");
        if (StringUtils.hasText(userInput)) {
          this.duration = Long.parseLong(userInput);
        }
      } while (duration == 0l);

      return this;
    }

    public FamilyOrientedAssignment build() {
      return new FamilyOrientedAssignment(this);
    }
  }
}