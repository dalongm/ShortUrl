package top.dalongm.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import top.dalongm.service.URLService;

@Controller
@RequestMapping("/api")
public class ApiController {
    @Autowired
    URLService urlService;
}
