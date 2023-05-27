package org.launchcode.codingevents.controllers;

import org.launchcode.codingevents.data.EventCategoryRepository;
import org.launchcode.codingevents.models.EventCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("eventCategories")
public class EventCategoryController {
//    You can use the @RequestMapping annotation to map to "eventCategories".
//    To get our handlers working, we also need a variable of type EventCategoryRepository.
    @Autowired
    private EventCategoryRepository eventCategoryRepository;

//TODO: We will be creating 3 handlers in our controller:
// displayAllEvents
// renderCreateEventCategoryForm
// processCreateEventCategoryForm

//    displayAllEvents needs to do the following:
//Use @GetMapping and return "eventCategories/index".
//Add an attribute for the title that uses "All Categories".
//Add an attribute for the categories that uses all of the values in your EventCategoryRepository variable.
@GetMapping
public String displayEvents(@RequestParam(required = false) Integer categoryId, Model model) {
// @RequestParam(required = false) Integer categoryId >> This allows requests to URLs like /events?categoryId=1.
// By specifying required = false we are telling Spring that it should call this handler for requests to the path
// /events even if no such ID is specified.

    if (categoryId == null) {
        model.addAttribute("title", "All Events");
        model.addAttribute("events", eventCategoryRepository.findAll());
//        If no categoryId is passed in, we carry out the same behavior as before, passing all events into the view

        //        If the conditional check above fails, then we have a non-null categoryId. However, this does not guarantee
//        that an object with the given ID exists in the database. We must manually check for the existence
//        of such an object.
    } else {
        Optional<EventCategory> result = eventCategoryRepository.findById(categoryId);
// queries our repository for a category object with the given ID. Notice that the findById
// method returns not an EventCategory object, but something of type Optional<EventCategory>
//        OPTIONAL is a CRUD Repository method

// returning an Optional<EventCategory> object, the findById method is taking into account that an object with such an
// ID may not exist. An Optional object is a container for the results of our query. If such an object does exist,
// we can get it using Optional.get(). Otherwise, Optional.isEmpty() will return true.

        if (result.isEmpty()) {
            model.addAttribute("title", "Invalid Category ID: " + categoryId);
//             we use this fact to check to see if an EventCategory object was found.
//             If not, we display an error message in the title.
        } else {
            EventCategory category = result.get();
            model.addAttribute("title", "Events in category: " + category.getName());
        //  execute if we know for sure that a category with the given ID exists.
//  In this case, we extract the category from the Optional object result using the get() method.
//  Then we create an appropriate title using the category name,
//  and pass the events belonging to the category into the view.
            model.addAttribute("events", category.getEvents());
//Our category object now has an events field that is populated using the @OneToMany annotation
// that we added near the beginning of this section, allowing the inverse relationship to be established
        }

    }

    return "events/index";
}
//renderCreateEventCategoryForm needs to do the following:
//Use @GetMapping and return "eventCategories/create".
//Add an attribute for the title and assign it "Create Category".
//Add an attribute for a new instance of EventCategory
@GetMapping("create")
    public String renderCreateEventCategoryForm (Model model) {
    model.addAttribute("title", "Create Category");
    model.addAttribute(new EventCategory());
    return "eventCategories/create";
}
//processCreateEventCategoryForm needs to do the following:
//Use @PostMapping.
//Use error validation and the Errors object appropriately.

//Add an attribute for the title and assign it "Create Category".
//Add an attribute for a new instance of EventCategory.
//Either return "eventCategories/create" or "redirect:"

    @PostMapping("create")
    public String processCreateEventCategoryForm(@ModelAttribute EventCategory eventCategory, Model model, Errors errors) {
        if(errors.hasErrors()) {
            model.addAttribute("title", "Create Category");
            model.addAttribute(new EventCategory());
            return "eventCategories/create";
        }
            eventCategoryRepository.save(eventCategory);
        return "redirect:";
    }
}