package com.lse.admin.model.agenda.composite;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.lse.admin.model.agenda.BusinessDevelopmentAssignment;
import com.lse.admin.model.agenda.CertificationAssignment;
import com.lse.admin.model.agenda.DeliveryOversightAssignment;
import com.lse.admin.model.agenda.FamilyOrientedAssignment;
import com.lse.admin.model.agenda.PeopleManagementAssignment;
import com.lse.admin.model.agenda.PersonalAssignment;
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
  private FamilyOrientedAssignment familyOrientedAssignment;
  private PersonalAssignment personalAssignment;

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
    String segment;
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
    resultant.addAll(this.familyOrientedAssignment.getOutcome());
    resultant.addAll(this.personalAssignment.getOutcome());

    List<Outcome> naFreeList = resultant.stream()
        .filter(o -> !"na".equalsIgnoreCase(o.getTask()))
        .collect(Collectors.toList());

    return naFreeList;
  }

}
