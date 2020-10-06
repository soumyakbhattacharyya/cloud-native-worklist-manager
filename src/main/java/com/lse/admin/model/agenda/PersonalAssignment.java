package com.lse.admin.model.agenda;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

import com.lse.admin.config.InputReader;
import com.lse.admin.model.agenda.composite.Agenda.Outcome;
import com.lse.admin.model.lov.Personal;
import com.lse.admin.model.lov.Product;

public class PersonalAssignment {
  private String description;
  private String personalStuff;
  private long duration;

  private PersonalAssignment(PersonalAssignment.Builder builder) {
    this.description = builder.description;
    this.duration = builder.duration;
    this.personalStuff = builder.personalStuff;
  }

  public List<Outcome> getOutcome() {
    Outcome outcome = new Outcome();
    outcome.setSegment(this.getClass().getSimpleName());
    outcome.setTask(description);
    outcome.setDuration(duration);
    outcome.setAdditionalInfo(personalStuff);
    List<Outcome> outcomes = new ArrayList<>();
    outcomes.add(outcome);
    return outcomes;
  }

  /**
   * Creates builder to build {@link CertificationAssignment}.
   * 
   * @return created builder
   */
  public static PersonalAssignment.Builder builder(
      InputReader inputReader) {
    return new Builder(inputReader);
  }

  /**
   * Builder to build {@link CertificationAssignment}.
   */
  public static final class Builder {
    private String description;
    private long duration;
    private String personalStuff;
    private InputReader inputReader;

    private Builder(InputReader inputReader) {
      this.inputReader = inputReader;
    }

    public PersonalAssignment.Builder withDescription() {
      do {
        String userInput = this.inputReader.prompt(
            "PersonalAssignment :: which specific task you would want to accomplish ?");
        if (StringUtils.hasText(userInput)) {
          this.description = userInput;
        }
      } while (!StringUtils.hasText(description));

      return this;
    }

    public PersonalAssignment.Builder withDuration() {
      if ("NA".equalsIgnoreCase(this.description))
        return this;
      do {
        String userInput = this.inputReader.prompt(
            "PersonalAssignment :: low long would you want to spend ?");
        if (StringUtils.hasText(userInput)) {
          this.duration = Long.parseLong(userInput);
        }
      } while (duration == 0l);

      return this;
    }

    public PersonalAssignment.Builder withSeparator() {
      String separator = new String(new char[100]).replace("\0", "*");
      System.out.println(separator);
      return this;
    }

    public PersonalAssignment.Builder withPersonalStuff() {
      if ("NA".equalsIgnoreCase(this.description))
        return this;

      Map<String, String> personalStuff = new HashMap<>();
      personalStuff.put("A", Personal.DO_MEDITATE.name());
      personalStuff.put("B", Personal.DO_READ.name());
      personalStuff.put("C", Personal.DO_WATCH_MOVIE.name());
      personalStuff.put("D", Personal.DO_WRITE.name());
      personalStuff.put("E", Personal.DO_WALK.name());
      
      String service = inputReader.selectFromList("Personal Stuff",
          "PersonalAssignment :: which topic you want to focus upon ?",
          personalStuff, true, null);
      Personal personal = Personal.valueOf(personalStuff.get(service.toUpperCase()));
      this.personalStuff = personal.name();
      return this;
    }

    public PersonalAssignment build() {
      return new PersonalAssignment(this);
    }
  }
}