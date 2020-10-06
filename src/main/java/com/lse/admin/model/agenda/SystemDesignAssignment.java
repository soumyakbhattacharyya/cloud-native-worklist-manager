package com.lse.admin.model.agenda;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

import com.lse.admin.config.InputReader;
import com.lse.admin.model.agenda.composite.Agenda.Outcome;
import com.lse.admin.model.lov.Product;

public class SystemDesignAssignment {
  private String description;
  private String system;
  private long duration;

  private SystemDesignAssignment(SystemDesignAssignment.Builder builder) {
    this.description = builder.description;
    this.duration = builder.duration;
    this.system = builder.system;
  }

  public List<Outcome> getOutcome() {
    Outcome outcome = new Outcome();
    outcome.setSegment(this.getClass().getSimpleName());
    outcome.setTask(description);
    outcome.setDuration(duration);
    outcome.setAdditionalInfo(system);
    List<Outcome> outcomes = new ArrayList<>();
    outcomes.add(outcome);
    return outcomes;
  }

  /**
   * Creates builder to build {@link CertificationAssignment}.
   * 
   * @return created builder
   */
  public static SystemDesignAssignment.Builder builder(
      InputReader inputReader) {
    return new Builder(inputReader);
  }

  /**
   * Builder to build {@link CertificationAssignment}.
   */
  public static final class Builder {
    private String description;
    private long duration;
    private String system;
    private InputReader inputReader;

    private Builder(InputReader inputReader) {
      this.inputReader = inputReader;
    }

    public SystemDesignAssignment.Builder withDescription() {
      do {
        String userInput = this.inputReader.prompt(
            "SystemDesignAssignment :: which specific task you would want to accomplish ?");
        if (StringUtils.hasText(userInput)) {
          this.description = userInput;
        }
      } while (!StringUtils.hasText(description));

      return this;
    }

    public SystemDesignAssignment.Builder withDuration() {
      if ("NA".equalsIgnoreCase(this.description))
        return this;
      do {
        String userInput = this.inputReader.prompt(
            "SystemDesignAssignment :: low long would you want to spend ?");
        if (StringUtils.hasText(userInput)) {
          this.duration = Long.parseLong(userInput);
        }
      } while (duration == 0l);

      return this;
    }

    public SystemDesignAssignment.Builder withSeparator() {
      String separator = new String(new char[100]).replace("\0", "*");
      System.out.println(separator);
      return this;
    }

    public SystemDesignAssignment.Builder withSystem() {
      if ("NA".equalsIgnoreCase(this.description))
        return this;

      Map<String, String> products = new HashMap<>();
      products.put("A", Product.RECRUITMENT_MANAGEMENT_SOLUTION.name());
      products.put("B", Product.DEPLOYMENT_PATTERN_SET.name());
      products.put("C", Product.ESTIMATOR_APPLICATION.name());
      products.put("D", Product.FEATURE_FLAG_PATTERN_SET.name());
      products.put("E", Product.PRACTICE_MANAGEMENT_SOLUTION.name());
      products.put("F", Product.TESTING_PATTERN_SET.name());
      products.put("G", Product.SECURITY_PATTERN_SET.name());
      products.put("H", Product.DMS.name());

      String service = inputReader.selectFromList("Products",
          "SystemDesignAssignment :: which system you would like to work upon ?",
          products, true, null);
      Product product = Product.valueOf(products.get(service.toUpperCase()));
      this.system = product.name();
      return this;
    }

    public SystemDesignAssignment build() {
      return new SystemDesignAssignment(this);
    }
  }
}