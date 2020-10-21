package com.lse.admin.model.agenda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
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
  private String healthTask;
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
    //this.salesTask = builder.salesTask;
    this.peopleMgmtTask = builder.peopleMgmtTask;
    this.personalProjectTask = builder.personalProjectTask;
    this.operationalTask = builder.operationalTask;
    this.switchTask = builder.switchTask;
    this.healthTask = builder.healthTask;
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
    //tasks.add(this.salesTask);
    tasks.add(this.personalProjectTask);
    tasks.add(this.operationalTask);
    tasks.add(this.switchTask);
    tasks.add(this.healthTask);
    tasks.add(this.financeTask);
    tasks.add(this.socialTask);
    tasks.add(this.avocationalTask);
    tasks.add(this.kidTask);
    tasks.add(this.wifeTask);
    tasks.add(this.extendedFamilyTask);
    
    Queue<String> segments = new LinkedList<String>();
    segments.add("Certification");
    segments.add("Delivery");
    segments.add("Practice-Artifact");
    segments.add("Business-Development");
    segments.add("People-Management");
    segments.add("People-Upskilling");
    segments.add("Personal-Project");
    segments.add("Operational");
    segments.add("Switch");
    segments.add("Health-Chek");
    segments.add("Finance");
    segments.add("Social");
    segments.add("Avocational");
    segments.add("Kid");
    segments.add("Wife");
    segments.add("Extended-Family");
    

    List<Outcome> consolidatedListOfTasks = tasks.stream()
        .map(new Function<String, Outcome>() {

          @Override
          public Outcome apply(String task) {
            Outcome outcome = new Outcome();
            if (null != task && !task.isEmpty()) {
              String[] components = task.split(";");
              for (int i = 0; i < components.length; i++) {
                String string = components[i];
                if (!"na".equalsIgnoreCase(string.trim())) {
                  components[i] = (i + 1) + ". " + string;
                }
              }
              String joined = String.join(System.lineSeparator(),
                  Arrays.asList(components));
              outcome.setTask(joined);
              outcome.setSegment(segments.poll());
            }
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
          "Question :: which task related to professional certification would you like to do today ?");

      this.deliveryExecutionTask = prompt(
          "Question :: which task related to delivery would you like to do today ?");

      this.artifactCreationTask = prompt(
          "Question :: which task related to practice level artifact would you like to do today ?");

      this.busdevTask = prompt(
          "Question :: which task related to business development would you like to do today ?");

      this.peopleUpskillingTask = prompt(
          "Question :: which task related to people upskilling would you like to do today ?");

      // this.salesTask = prompt(
      // "Question :: which task related to upselling practice capability would you want to perform in next two days ?");

      this.peopleMgmtTask = prompt(
          "Question :: which task related to people management would you like to do today ?");

      this.personalProjectTask = prompt(
          "Question :: which task related to personal project would you like to do today ?");

      this.operationalTask = prompt(
          "Question :: which task related to operations would you like to do today ?");

      this.switchTask = prompt(
          "Question :: which task related to switching would you like to do today ?");

      this.healthTask = prompt(
          "Question :: which task related to health would you like to do today ?");

      this.financeTask = prompt(
          "Question :: which task related to finance would you like to do today ?");

      this.socialTask = prompt(
          "Question :: which social contribution would you like to do today ?");

      this.avocationalTask = prompt(
          "Question :: which avocational pursuit would you like to do today ?");

      this.kidTask = prompt(
          "Question :: which parenting task would you like to do today ?");

      this.wifeTask = prompt(
          "Question :: what about wife would would you like to do today ?");

      this.extendedFamilyTask = prompt(
          "Question :: what about the extended family would you like to do today ?");
      
      

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
    private String healthTask;
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