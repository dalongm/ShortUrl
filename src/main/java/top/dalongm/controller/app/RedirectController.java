package top.dalongm.controller.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import top.dalongm.dto.URLDto;
import top.dalongm.service.URLService;

@Controller
public class RedirectController {

    @Autowired
    URLService urlService;

    @RequestMapping(value="/",method = RequestMethod.POST)
    public String createShortUrl(Model model, URLDto urlDto){

        if(urlDto.getUrl()!=null&&urlService.add(urlDto)){
            urlDto = urlService.getByUrl(urlDto.getUrl());
            model.addAttribute("url",urlDto);
            return "/app/result";
        }
        return "/app/index";
    }

    @RequestMapping(value="/{shorturl}",method = RequestMethod.GET)
    public String goShortUrl(@PathVariable("shorturl") String shortUrl){
        URLDto urlDto = urlService.getBySUrl(shortUrl);
        if(urlDto!=null){
            return "redirect:"+urlDto.getUrl();
        }
        return "redirect:/";
    }
}
