package com.hemanth.wallet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
class HomeController {
    public static final Logger Log =  LogManager.getLogger(HomeController.class);
    @RequestMapping("/")
    public String index() {

        Log.info("DONE THROUGH INDEX METHOD");
        Log.trace("Trace");
        Log.warn("WARNING ALERT");
        Log.fatal("FATAL METHOD IS RUNNING");
        Log.error("There is no Error");
        Log.debug("Index file is successfully implemented");

        return "Index.html ";
    }
}
