package com.lse.admin.model.agenda.composite;

import lombok.Data;

/**
 * outcome section
 */

@Data
public class Outcome {
  String segment;
  String task;
  Long duration;
  String additionalInfo;

}