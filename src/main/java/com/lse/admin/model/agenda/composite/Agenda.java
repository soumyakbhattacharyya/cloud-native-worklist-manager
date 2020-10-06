package com.lse.admin.model.agenda.composite;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;



import lombok.Builder;
import lombok.Data;

@Builder
public class Agenda {

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

 
}
