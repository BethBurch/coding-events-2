package org.launchcode.codingevents.controllers;

import org.launchcode.codingevents.data.EventCategoryRepository;
import org.launchcode.codingevents.models.EventCategory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("eventCategories")
public class EventCategoryController {
//    You can use the @RequestMapping annotation to map to "eventCategories".
//    To get our handlers working, we also need a variable of type EventCategoryRepository.
    private EventCategoryRepository eventCategoryRepository;

//TODO: We will be creating 3 handlers in our controller:
// displayAllEvents
// renderCreateEventCategoryForm
// processCreateEventCategoryForm

//    displayAllEvents needs to do the following:
//Use @GetMapping and return "eventCategories/index".
//Add an attribute for the title that uses "All Categories".
//Add an attribute for the categories that uses all of the values in your EventCategoryRepository variable.
@GetMapping("index")
public String displayAllCategories(Model model) {
    model.addAttribute("title", "All Categories");
    model.addAttribute("categories", eventCategoryRepository.findAll());
    return "eventCategories/index";
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

    @PostMapping
    public String processCreateEventCategoryForm(Model model, Errors errors) {
        if(errors.hasErrors()) {
            return "redirect:";
        }
        model.addAttribute("title", "Create Category");
        model.addAttribute(new EventCategory());
        return "eventCategories/create";
    }
}