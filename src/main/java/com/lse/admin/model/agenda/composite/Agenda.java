package com.lse.admin.model.agenda.composite;

import java.util.ArrayList;
import java.util.List;

import com.lse.admin.model.agenda.BusinessDevelopmentAssignment;
import com.lse.admin.model.agenda.CertificationAssignment;
import com.lse.admin.model.agenda.DeliveryOversightAssignment;
import com.lse.admin.model.agenda.PeopleManagementAssignment;
import com.lse.admin.model.agenda.SystemDesignAssignment;

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
    resultant.addAll(this.systemDesignAssignment.getOutcome());
    resultant.addAll(this.businessDevelopmentAssignment.getOutcome());

    return resultant;
  }

}
