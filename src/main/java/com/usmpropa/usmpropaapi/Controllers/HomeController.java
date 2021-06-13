package com.usmpropa.usmpropaapi.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

<<<<<<< HEAD

@Controller
public class HomeController
{
    private static final String INDEX="index";
    @GetMapping("/")
    public String index(Model model) {
        return INDEX;
=======
@Controller
public class HomeController {
    
    private static final String HOME_INDEX ="index"; 

    @GetMapping("/")
    public String index(Model model) {
        return HOME_INDEX;
>>>>>>> c69e7ef7b9e2f1b07f22b88feac158223a455d31
    }
}
