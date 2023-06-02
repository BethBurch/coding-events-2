package org.launchcode.codingevents.models.dto;

import org.launchcode.codingevents.models.Event;
import org.launchcode.codingevents.models.Tag;

import javax.validation.constraints.NotNull;

//A data transfer object (or DTO) is an object that enables multiple
// other objects or values to be passed around an application, in a single container
//TODO: A DTO for these two classes is very simple. It contains two fields, a no-arg constructor,
// and accessors for the fields. Each field is annotated with @NotNull because we will use this class
// in conjunction with model binding and form processing.
public class EventTagDTO {

//    . It is a model since it structures data that our application uses.
//    However, it is not persistent (there is no @Entity annotation) because we won’t need to store it in the database
    @NotNull
    private Event event;

    @NotNull
    private Tag tag;

    public EventTagDTO() {}

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }
}

