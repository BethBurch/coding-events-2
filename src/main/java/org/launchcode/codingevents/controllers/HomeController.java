package org.launchcode.codingevents.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by Chris Bay
 */
@Controller
public class HomeController {
//    TODO:Within the new package, create a new controller class named HomeController. Annotate the class with @Controller.
////TODO: Add a single GET handler method that returns the name templated index,
// which will be the name of the template we create in the next step.

    @GetMapping
    public String index() {
        return "index";
    }

}
