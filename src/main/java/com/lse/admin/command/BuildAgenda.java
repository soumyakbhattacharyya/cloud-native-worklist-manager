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
import com.lse.admin.model.Agenda;
import com.lse.admin.model.Agenda.AgendaBuilder;
import com.lse.admin.model.Agenda.Outcome;
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

    Agenda.CertificationAssignment certificationAssignment = Agenda.CertificationAssignment
        .builder(inputReader).withDescription().withDuration().build();
    Agenda.PeopleManagementAssignment peopleManagementAssignment = Agenda.PeopleManagementAssignment
        .builder(inputReader).withDescription().withDuration()
        .withPeopleIWouldWantToTalk().build();
    Agenda.SystemDesignAssignment systemDesignAssignment = Agenda.SystemDesignAssignment
        .builder(inputReader).withSystem().withDescription().withDuration()
        .build();
    
    
    Agenda agenda = Agenda.builder()
        .certificationAssignment(certificationAssignment)
        .peopleManagementAssignment(peopleManagementAssignment).systemDesignAssignment(systemDesignAssignment).build();
    
    print(agenda.getOutcome());

    return "processed";
  }

  private void print(List<Outcome> result) {
    System.out.println(result);
    LinkedHashMap<String, Object> headers = new LinkedHashMap<>();
    headers.put("task", "Segment");
    headers.put("duration", "Duration");
    headers.put("additionalInfo", "Additional-Info");
    TableModel model = new BeanListTableModel<>(result, headers);

    TableBuilder tableBuilder = new TableBuilder(model);
    tableBuilder.addInnerBorder(BorderStyle.oldschool);
    tableBuilder.addHeaderBorder(BorderStyle.oldschool);
    System.out.println(tableBuilder.build().render(80));
  }
}
