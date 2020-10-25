package org.soumyak.agenda.builder.model.helper;

import java.util.List;

import org.soumyak.agenda.builder.model.task.Activity;

public class ActivityResponse {

    public final int count;
    public final List<Activity> events;

    public ActivityResponse(int count, List<Activity> events) {
        this.count = count;
        this.events = events;
    }
}
