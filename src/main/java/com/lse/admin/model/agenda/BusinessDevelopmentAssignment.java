package com.lse.admin.model.agenda;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

import com.lse.admin.config.InputReader;
import com.lse.admin.model.agenda.composite.Agenda.Outcome;
import com.lse.admin.model.lov.BD;
import com.lse.admin.model.lov.Product;

public class BusinessDevelopmentAssignment {

  private String description;
  private long duration;
  private String topic;

  private BusinessDevelopmentAssignment(
      BusinessDevelopmentAssignment.Builder builder) {
    this.description = builder.description;
    this.duration = builder.duration;
    this.topic = builder.topic;
  }

  public List<Outcome> getOutcome() {
    Outcome outcome = new Outcome();
    outcome.setSegment(this.getClass().getSimpleName());
    outcome.setTask(description);
    outcome.setDuration(duration);
    outcome.setAdditionalInfo(topic);    
    
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
    private String topic;
    private InputReader inputReader;

    private Builder(InputReader inputReader) {
      this.inputReader = inputReader;
    }

    public BusinessDevelopmentAssignment.Builder withDescription() {
      do {
        String userInput = this.inputReader.prompt(
            "BusinessDevelopmentAssignment :: which task related to developing new business would you like to perform ?");
        if (StringUtils.hasText(userInput)) {
          this.description = userInput;
        }
      } while (!StringUtils.hasText(description));

      return this;
    }

    public BusinessDevelopmentAssignment.Builder withDuration() {
      if ("NA".equalsIgnoreCase(this.description)) return this;
      do {
        String userInput = this.inputReader.prompt(
            "BusinessDevelopmentAssignment :: how long would you want to spend on this ?");
        if (StringUtils.hasText(userInput)) {
          this.duration = Long.parseLong(userInput);
        }
      } while (duration == 0l);

      return this;
    }
    
    public BusinessDevelopmentAssignment.Builder withTopic() {
      if ("NA".equalsIgnoreCase(this.description))
        return this;

      Map<String, String> topics = new HashMap<>();
      topics.put("A", BD.PROPOSAL.name());
      topics.put("B", BD.BLOG.name());
      topics.put("C", BD.EOI.name());
      topics.put("D", BD.ESTIMATION.name());
      topics.put("E", BD.RFP.name());
     
      String service = inputReader.selectFromList("Topic",
          "BusinessDevelopmentAssignment :: which topic you would be working upon ?",
          topics, true, null);
      BD bd = BD.valueOf(topics.get(service.toUpperCase()));
      this.topic = bd.name();
      return this;
    }

    public BusinessDevelopmentAssignment.Builder withSeparator() {
      String separator = new String(new char[100]).replace("\0", "*");
      System.out.println(separator);
      return this;
    }

    public BusinessDevelopmentAssignment build() {
      return new BusinessDevelopmentAssignment(this);
    }
  }
}