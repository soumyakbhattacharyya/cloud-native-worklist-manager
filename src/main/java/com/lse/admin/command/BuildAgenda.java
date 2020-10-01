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
import org.springframework.util.StringUtils;

import com.lse.admin.config.InputReader;
import com.lse.admin.model.Agenda;
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

    String description_ = "";
    Long duration_ = 0l;

    do {
      String description = inputReader
          .prompt("which topic you want to study for");
      if (StringUtils.hasText(description)) {
        description_ = description;
      }
    } while (description_ == null);

    do {
      String duration = inputReader.prompt("How long you want to study for");
      if (StringUtils.hasText(duration)) {
        duration_ = Long.parseLong(duration);
      }
    } while (duration_ == 0l);

    // take input:
    Agenda.CertificationAssignment certificationAssignment = new Agenda.CertificationAssignment(
        description_, duration_);
    Agenda agenda = Agenda.builder()
        .withCertificationAssignment(certificationAssignment).build();

    print(agenda.getOutcome());

    return "processed";
  }

  private void print(List<Outcome> result) {
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
