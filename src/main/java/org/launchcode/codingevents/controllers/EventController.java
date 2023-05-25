package org.launchcode.codingevents.controllers;


import org.launchcode.codingevents.data.EventRepository;
import org.launchcode.codingevents.models.Event;
import org.launchcode.codingevents.models.EventType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
//TODO:In the controllers package, create a new controller named EventController.
// Annotate the class with @Controller and @RequestMapping("events").

@Controller
@RequestMapping("events")
public class EventController {




//TODO: In the new controller, create a handler method for GET requests that takes a single parameter, Model model.
// Within the handler, create an empty list and add a few event names to it.
//        List<String> events = new ArrayList<>();
//        events.add("PRIDE In STL");
//        events.add("Pool Day");
//        events.add("Hike 4 Miles");
//        events.add("Kayaking");
//TODO: Add the list to the model by calling model.addAttribute("events", events).
// Then return the template name "events/index".

    @Autowired
    private EventRepository eventRepository;

    @GetMapping
    public String displayAllEvents(Model model) {
//TODO: Comment out your previous code in the displayAllEvents method.
        //TODO:make our events list a HashMap! This enables us to add descriptions to our events.
//        HashMap<String, String> events = new HashMap<>();
//  TODO: Fill your events HashMap with the names and descriptions of 3 coding events around town.
//        events.put("STL PRIDE", "Meet fellow Gays and Celebrate being Out and Proud");
//        events.put("Day at the Pool", "Swimming and Tanning!");
//        events.put("First Hike", "4 Miles! No Big Deal");
        model.addAttribute("title", "All Events");
        model.addAttribute("events", eventRepository.findAll());
//        findAll is from the crudRepository
        return "events/index";
    }

    //lives at /events/create

    //    TODO: The string parameter for GetMapping must be the name of the form template you want to use.
    @GetMapping("create")
    public String displayCreateEventForm(Model model) {
        model.addAttribute("title", "Create Event");
        model.addAttribute("event", new Event());
        model.addAttribute("types", EventType.values());
        return "events/create";
    }

    //     Spring Boot makes an object of type Errors available when a method uses @Valid.
//     As with Model objects, we can access this object by placing it in a method’s parameter list.
    @PostMapping("create")
    public String processCreateEventForm(@ModelAttribute @Valid Event newEvent,
                                         Errors errors, Model model) {
        if(errors.hasErrors()) {
            model.addAttribute("title", "Create Event");
//            model.addAttribute("errorMsg", "Bad data!");
            return "events/create";
        }

        eventRepository.save(newEvent);
        return "redirect:";
    }

    @GetMapping("delete")
    public String displayDeleteEventForm(Model model) {
        model.addAttribute("title", "Delete Events");
        model.addAttribute("events", eventRepository.findAll());
        return "events/delete";
    }

    @PostMapping("delete")
    public String processDeleteEventsForm(@RequestParam(required = false) int[] eventIds) {

        if (eventIds != null) {
            for (int id : eventIds) {
                eventRepository.deleteById(id);
            }
        }

        return "redirect:";
    }

}
