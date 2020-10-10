package com.lse.admin.model.agenda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.util.StringUtils;

import com.lse.admin.config.InputReader;
import com.lse.admin.model.agenda.composite.Outcome;

/**
 * 
 * collects tasks that have to be accomplished daily across various focus area
 * 
 * @author admin
 *
 */
public class CompositeTask {
  private String certificationTask;
  private String deliveryExecutionTask;
  private String artifactCreationTask;
  private String busdevTask;
  private String peopleUpskillingTask;
  private String salesTask;
  private String peopleMgmtTask;
  private String personalProjectTask;
  private String operationalTask;
  private String switchTask;
  private String meetingTask;
  private String financeTask;
  private String socialTask;
  private String avocationalTask;
  private String kidTask;
  private String wifeTask;
  private String extendedFamilyTask;

  private CompositeTask(CompositeTask.Builder builder) {
    this.certificationTask = builder.certificationTask;
    this.deliveryExecutionTask = builder.deliveryExecutionTask;
    this.artifactCreationTask = builder.artifactCreationTask;
    this.busdevTask = builder.busdevTask;
    this.peopleUpskillingTask = builder.peopleUpskillingTask;
    this.salesTask = builder.salesTask;
    this.peopleMgmtTask = builder.peopleMgmtTask;
    this.personalProjectTask = builder.personalProjectTask;
    this.operationalTask = builder.operationalTask;
    this.switchTask = builder.switchTask;
    this.meetingTask = builder.meetingTask;
    this.financeTask = builder.financeTask;
    this.socialTask = builder.socialTask;
    this.avocationalTask = builder.avocationalTask;
    this.kidTask = builder.kidTask;
    this.wifeTask = builder.wifeTask;
    this.extendedFamilyTask = builder.extendedFamilyTask;

  }

  public List<Outcome> getOutcome() {

    List<String> tasks = new ArrayList<>();
    tasks.add(this.certificationTask);
    tasks.add(this.deliveryExecutionTask);
    tasks.add(this.artifactCreationTask);
    tasks.add(this.busdevTask);
    tasks.add(this.peopleMgmtTask);
    tasks.add(this.peopleUpskillingTask);
    tasks.add(this.salesTask);
    tasks.add(this.personalProjectTask);
    tasks.add(this.operationalTask);
    tasks.add(this.switchTask);
    tasks.add(this.meetingTask);
    tasks.add(this.financeTask);
    tasks.add(this.socialTask);
    tasks.add(this.avocationalTask);
    tasks.add(this.kidTask);
    tasks.add(this.wifeTask);
    tasks.add(this.extendedFamilyTask);

    List<Outcome> consolidatedListOfTasks = tasks.stream()
        .map(new Function<String, Outcome>() {

          @Override
          public Outcome apply(String task) {
            Outcome outcome = new Outcome();
            outcome.setTask(task);
            return outcome;
          }
        }).filter(o -> !"na".equalsIgnoreCase(o.getTask()))
        .collect(Collectors.toList());

    return consolidatedListOfTasks;
  }

  /**
   * Creates builder to build {@link CompositeTask}.
   * 
   * @return created builder
   */
  public static CompositeTask.Builder builder(InputReader inputReader) {
    return new Builder(inputReader);
  }

  /**
   * Builder to build {@link CompositeTask}.
   */
  public static final class Builder {
    private InputReader inputReader;

    private Builder(InputReader inputReader) {
      this.inputReader = inputReader;
    }

    public CompositeTask.Builder withSeparator() {
      String separator = new String(new char[100]).replace("\0", "*");
      System.out.println(separator);
      return this;
    }

    public CompositeTask.Builder withTasks() {

      this.certificationTask = prompt(
          "Question :: which task related to professional certification would you want to do in next two days ?");

      this.deliveryExecutionTask = prompt(
          "Question :: which task related to internal/external delivery would you want to perform in next two days ?");

      this.artifactCreationTask = prompt(
          "Question :: which task related to practice level artifact would you want to perform in next two days ?");

      this.busdevTask = prompt(
          "Question :: which task related to business development would you want to perform in next two days ?");

      this.peopleUpskillingTask = prompt(
          "Question :: which task related to people upskilling would you want to perform in next two days ?");

      this.salesTask = prompt(
          "Question :: which task related to upselling practice capability would you want to perform in next two days ?");

      this.peopleMgmtTask = prompt(
          "Question :: which task related to people management would you want to perform in next two days ?");

      this.personalProjectTask = prompt(
          "Question :: which task related to personal project would you want to perform in next two days ?");

      this.operationalTask = prompt(
          "Question :: which task related to operations would you want to perform in next two days ?");

      this.switchTask = prompt(
          "Question :: which task related to switching would you want to perform in next two days ?");

      this.meetingTask = prompt(
          "Question :: which task related to meeting with others would you want to perform in next two days ?");

      this.financeTask = prompt(
          "Question :: which task related to finance would you want to perform in next two days ?");
      
      this.socialTask = prompt(
          "Question :: which social contribution would you want to perform in next two days ?");
      
      this.avocationalTask = prompt(
          "Question :: which avocational pursuit you want to chase in next two days ?");
      
      this.kidTask = prompt(
          "Question :: which parenting task would you want to perform in next two days ?");
      
      this.wifeTask = prompt(
          "Question :: what about wife would you want to perform in next two days ?");
      
      this.extendedFamilyTask = prompt(
          "Question :: what about the extended family you would want to perform in next two days ?");

      return this;
    }

    private String prompt(String question) {

      do {
        String userInput = this.inputReader.prompt(question);
        if (StringUtils.hasText(userInput)) {
          return userInput;
        }
      } while (true);

    }

    private String certificationTask;
    private String deliveryExecutionTask;
    private String artifactCreationTask;
    private String busdevTask;
    private String peopleUpskillingTask;
    private String salesTask;
    private String peopleMgmtTask;
    private String personalProjectTask;
    private String operationalTask;
    private String switchTask;
    private String meetingTask;
    private String financeTask;
    private String socialTask;
    private String avocationalTask;
    private String kidTask;
    private String wifeTask;
    private String extendedFamilyTask;

    public CompositeTask build() {
      return new CompositeTask(this);
    }
  }
}