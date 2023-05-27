package org.launchcode.codingevents.models;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

/**
 * Created by Chris Bay
 */
@Entity
public class Event extends AbstractEntity {
//    //    To create a model to shape event data, we include a field for name.
////    Of course, we’ll also like at least one constructor and some getters and setters.


    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
//     database operation cascades from Event to EventDetails if,
//     when the operation is applied to an Event instance, it is also applied to the associated EventDetails instance
//    Cascascade specifies which ORM operations should cascade from Event to its eventDetails field.
//    CascadeType.ALL specifies that all database operations—including save and delete—should cascade.
    @Valid
// using @Valid on a method parameter in a controller will result in the fields of that method being validated
//    Using @Valid on the eventDetails field ensures that such validation occurs. It makes sure that an Event
//    object will not be considered valid unless it has an EventDetails object that is also valid
//    (i.e. it has valid description and contactEmail fields)
    @NotNull
    private EventDetails eventDetails;
//    TODO: add a new field of type EventDetails to Event and annotate it with @OneToOne.
//     Additionally, add the validation annotations @Valid and @NotNull
    @ManyToOne
    @NotNull(message = "Category is required")
    private EventCategory eventCategory;

    public Event(String name, String description, String contactEmail, EventCategory eventCategory) {
        this.name = name;
        this.eventCategory = eventCategory;
    }

    public Event() {
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }


    public EventCategory getEventCategory() {

        return eventCategory;
    }

    public void setEventCategory(EventCategory eventCategory) {

        this.eventCategory = eventCategory;
    }

    public EventDetails getEventDetails() {
        return eventDetails;
    }

    public void setEventDetails(EventDetails eventDetails) {
        this.eventDetails = eventDetails;
    }

    @Override
    public String toString() {

        return name;
    }


}
