package top.dalongm.controller.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import top.dalongm.bean.Error;
import top.dalongm.dto.URLDto;
import top.dalongm.service.URLService;
import top.dalongm.utils.Time;

import java.util.Date;

@Controller
public class RedirectController {

    @Autowired
    URLService urlService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String createShortUrl(Model model, URLDto urlDto) {

        if (urlDto.getUrl() != null) {
            urlDto = urlService.add(urlDto);
            if (urlDto != null) {
                model.addAttribute("url", urlDto);
                return "/app/result";
            }

        }
        return "/app/index";
    }

    @RequestMapping(value = "/{shorturl}", method = RequestMethod.GET)
    public String goShortUrl(@PathVariable("shorturl") String shortUrl, Model model) {
        URLDto urlDto = urlService.getBySUrl(shortUrl);
        Error e = new Error();
        if (urlDto != null) {
            if (urlDto.getVisitPass() != null && !urlDto.getVisitPass().trim().equals("")) {
                model.addAttribute("sUrl", shortUrl);
                return "/app/enterpass";
            }

            if(urlDto.getValidTimes()<=urlDto.getVisited()){
                e.setMessage("短链接访问次数达上限!");
                model.addAttribute("error", e);
                return "/app/error";
            }

            Double dayDiff = Time.getDateDiff(urlDto.getCreateTime(),new Date());
            if(dayDiff>urlDto.getValidTime()){
                e.setMessage("短链接已过期!");
                model.addAttribute("error", e);
                return "/app/error";
            }

            urlService.incVisitedById(urlDto.getId());
            urlDto.setValidTimes(urlDto.getValidTimes()-urlDto.getVisited());
            urlDto.setValidTime(urlDto.getValidTime()-dayDiff);
            model.addAttribute("url", urlDto);
            return "/app/goto";

//            return "redirect:"+urlDto.getUrl();
        }

        e.setMessage("短链接失效！");
        model.addAttribute("error", e);
        return "/app/error";
    }

    @RequestMapping(value = "/veri", method = RequestMethod.POST)
    public String verifyPass(URLDto urlDto, Model model) {
        String sUrl = urlDto.getsUrl();
        Error e = new Error();
        if (sUrl != null && !sUrl.trim().equals("")) {
            URLDto temp = urlService.getBySUrl(sUrl);
            if (temp != null && urlDto.getVisitPass().equals(temp.getVisitPass())) {
                if(temp.getValidTimes()<=temp.getVisited()){
                    e.setMessage("短链接访问次数达上限!");
                    model.addAttribute("error", e);
                    return "/app/error";
                }


                Double dayDiff = Time.getDateDiff(temp.getCreateTime(),new Date());
                if(dayDiff>temp.getValidTime()){
                    e.setMessage("短链接已过期!");
                    model.addAttribute("error", e);
                    return "/app/error";
                }

                urlService.incVisitedById(temp.getId());
                temp.setValidTimes(temp.getValidTimes()-temp.getVisited());
                temp.setValidTime(temp.getValidTime()-dayDiff);
                model.addAttribute("url", temp);
                return "/app/goto";
            }
        }

        e.setMessage("密码错误或短链接失效!");
        model.addAttribute("error", e);
        return "/app/error";
    }
}
