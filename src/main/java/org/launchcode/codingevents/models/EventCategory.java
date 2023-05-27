package org.launchcode.codingevents.models;

import javax.persistence.OneToMany;
import javax.validation.constraints.Size;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class EventCategory extends AbstractEntity {
    @Size(min = 3, message = "Name must be at least 3 characters long")
    private String name;
    @OneToMany(mappedBy = "eventCategory")
//    in the event model there is a many to one annotation for event category
    private final List<Event> events = new ArrayList<>();
// The events field will be initialized when an EventCategory object is created. It is marked final to
// ensure that the collection isn’t deleted or replaced. Since it is final, only a getter is needed;
// add one near the bottom of the class.
//when a collection is marked final its contents are allowed to change, but the collection object itself may not be
// changed. In other words, we may add or remove items from the events list, but we may not reassign the value of events.

    public EventCategory(@Size(min = 3, message = "Name must be at least 3 characters long") String name) {
        this.name = name;
    }

    public EventCategory() {
    }

    public List<Event> getEvents() {
        return events;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    @Override
    public String toString() {
        return "EventCategory{" +
                "name='" + name + '\'' +
                '}';
    }


}

