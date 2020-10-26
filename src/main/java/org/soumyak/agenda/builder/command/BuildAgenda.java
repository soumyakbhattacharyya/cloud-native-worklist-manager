package org.soumyak.agenda.builder.command;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.function.Consumer;

import org.jline.reader.LineReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.soumyak.agenda.builder.Todoist;
import org.soumyak.agenda.builder.TodoistException;
import org.soumyak.agenda.builder.config.InputReader;
import org.soumyak.agenda.builder.model.agenda.CompositeTask;
import org.soumyak.agenda.builder.model.agenda.composite.Outcome;
import org.soumyak.agenda.builder.model.helper.TaskRequest;
import org.soumyak.agenda.builder.skin.ShellHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.table.BeanListTableModel;
import org.springframework.shell.table.BorderStyle;
import org.springframework.shell.table.TableBuilder;
import org.springframework.shell.table.TableModel;

@ShellComponent
public class BuildAgenda {

  private static final String TODOIST_KEY = "cef720df32b1e215815c4fdbb96e6f1784b24bdb";
  private static final long TODOIST_PROJECT = 2248430537l;

  @Autowired
  @Lazy
  private LineReader lineReader;

  @Autowired
  ShellHelper shellHelper;

  @Autowired
  InputReader inputReader;

  public static final Logger log = LoggerFactory.getLogger(BuildAgenda.class);

  @ShellMethod(value = "Build Agenda.", key = "build-agenda")
  public String buildAgenda() throws IOException, TodoistException {

    CompositeTask compositeTask = CompositeTask.builder(inputReader).withTasks()
        .withSeparator().build();

    print(compositeTask.getOutcome());

    return "finished processing";
  }

  private void print(List<Outcome> result)
      throws IOException, TodoistException {
    LinkedHashMap<String, Object> headers = new LinkedHashMap<>();
    headers.put("segment", "Segment");
    headers.put("task", "Task");
    TableModel model = new BeanListTableModel<>(result, headers);

    TableBuilder tableBuilder = new TableBuilder(model);
    tableBuilder.addInnerBorder(BorderStyle.oldschool);
    tableBuilder.addHeaderBorder(BorderStyle.oldschool);
    String tableData = tableBuilder.build().render(120);

    write2Console(tableData);
    write2LocalFile(tableData);
    write2Todoist(result);
  }

  private void write2Todoist(List<Outcome> result) throws TodoistException {
    Todoist todoist = new Todoist(TODOIST_KEY);
    result.stream().forEach(new Consumer<Outcome>() {

      @Override
      public void accept(Outcome t) {
        try {
          todoist.createNewTask(t.getFormattedDescription(), TODOIST_PROJECT, 0, null, 1,"today", "", "", "");
        } catch (TodoistException e) {
          e.printStackTrace();
        }

      }
    });

  }

  private void write2Console(String tableData) {
    System.out.println(tableData);
  }

  private void write2LocalFile(String tableData) throws IOException {
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    LocalDateTime now = LocalDateTime.now();
    String fileName = dtf.format(now) + "-daily-agenda.txt";
    String directory = "C:/Soumyak/DAILY_AGENDA/";
    BufferedWriter writer = new BufferedWriter(
        new FileWriter(directory + fileName));
    writer.write(tableData);
    writer.close();
  }
}
