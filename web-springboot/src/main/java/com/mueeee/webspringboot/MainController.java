package com.mueeee.webspringboot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

/**
 * Controller
 */
@Controller
public class MainController {

    @GetMapping("/")
    public String foo() {
        return "index";
    }

    @ResponseBody
    @GetMapping("/hello/{name}")
    public Object foo(@PathVariable String name) {
        return new HashMap<String, Object>() {{
            put("code", 0);
            put("message", "Hello " + name);
        }};
    }

}
