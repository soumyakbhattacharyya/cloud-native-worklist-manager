package com.lse.admin.command;

import java.util.LinkedHashMap;
import java.util.List;

import org.jline.reader.LineReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.table.BeanListTableModel;
import org.springframework.shell.table.BorderStyle;
import org.springframework.shell.table.TableBuilder;
import org.springframework.shell.table.TableModel;

import com.lse.admin.config.InputReader;
import com.lse.admin.model.agenda.BusinessDevelopmentAssignment;
import com.lse.admin.model.agenda.CertificationAssignment;
import com.lse.admin.model.agenda.FamilyOrientedAssignment;
import com.lse.admin.model.agenda.PeopleManagementAssignment;
import com.lse.admin.model.agenda.PersonalAssignment;
import com.lse.admin.model.agenda.SystemDesignAssignment;
import com.lse.admin.model.agenda.composite.Agenda;
import com.lse.admin.model.agenda.composite.Agenda.Outcome;
import com.lse.admin.skin.ShellHelper;

@ShellComponent
public class BuildAgenda {

  @Autowired
  @Lazy
  private LineReader lineReader;

  @Autowired
  ShellHelper shellHelper;

  @Autowired
  InputReader inputReader;

  public static final Logger log = LoggerFactory.getLogger(BuildAgenda.class);

  @ShellMethod(value = "Build Agenda.", key = "build-agenda")
  public String buildAgenda() {

    // // take input:
    // Agenda.CertificationAssignment certificationAssignment = new Agenda.CertificationAssignment(
    // description_, duration_);
    // Agenda agenda = Agenda.builder()
    // .withCertificationAssignment(certificationAssignment).build();
    //
    // print(agenda.getOutcome());

    CertificationAssignment certificationAssignment = CertificationAssignment
        .builder(inputReader).withDescription().withDuration().withSeparator()
        .build();
    PeopleManagementAssignment peopleManagementAssignment = PeopleManagementAssignment
        .builder(inputReader).withDescription().withDuration()
        .withPeopleIWouldWantToTalk().withSeparator().build();
    SystemDesignAssignment systemDesignAssignment = SystemDesignAssignment
        .builder(inputReader).withSystem().withDescription().withDuration()
        .withSeparator().build();
    BusinessDevelopmentAssignment businessDevelopmentAssignment = BusinessDevelopmentAssignment
        .builder(inputReader).withDescription().withTopic().withDuration()
        .withSeparator().build();
    FamilyOrientedAssignment familyOrientedAssignment = FamilyOrientedAssignment
        .builder(inputReader).withDescription().withDuration().withSeparator()
        .build();
    PersonalAssignment personalAssignment = PersonalAssignment
        .builder(inputReader).withDescription().withDuration()
        .withPersonalStuff().withSeparator().build();

    Agenda agenda = Agenda.builder()
        .certificationAssignment(certificationAssignment)
        .peopleManagementAssignment(peopleManagementAssignment)
        .businessDevelopmentAssignment(businessDevelopmentAssignment)
        .systemDesignAssignment(systemDesignAssignment)
        .familyOrientedAssignment(familyOrientedAssignment)
        .personalAssignment(personalAssignment).build();

    print(agenda.getOutcome());

    return "finished processing";
  }

  private void print(List<Outcome> result) {
    LinkedHashMap<String, Object> headers = new LinkedHashMap<>();
    headers.put("segment", "Segment");
    headers.put("task", "Task");
    headers.put("duration", "Duration");
    headers.put("additionalInfo", "Additional-Info");
    TableModel model = new BeanListTableModel<>(result, headers);

    TableBuilder tableBuilder = new TableBuilder(model);
    tableBuilder.addInnerBorder(BorderStyle.oldschool);
    tableBuilder.addHeaderBorder(BorderStyle.oldschool);
    System.out.println(tableBuilder.build().render(80));
  }
}
