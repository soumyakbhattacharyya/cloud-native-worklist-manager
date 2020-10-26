package org.soumyak.agenda.builder.model.task;

import com.squareup.moshi.Json;

public class Section {

    public final long id;
    public final String name;
    public final int order;
    @Json(name = "project_id") public final long projectId;

    public Section(long id, String name, int order, long projectId) {
        this.id = id;
        this.name = name;
        this.order = order;
        this.projectId = projectId;
    }

    @Override
    public String toString() {
        return "Section{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", order=" + order +
                '}';
    }
}