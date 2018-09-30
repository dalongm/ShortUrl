package top.dalongm.controller.api;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import top.dalongm.dto.APIURLDto;
import top.dalongm.dto.URLDto;
import top.dalongm.service.URLService;
import top.dalongm.utils.ErrorType;
import top.dalongm.utils.Time;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
@RequestMapping("/api")
public class ApiController {
    @Autowired
    URLService urlService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public APIURLDto add(HttpServletRequest request, URLDto urlDto) {
        String basePath = request.getScheme() + "://" + request.getServerName();
        if (request.getServerPort() != 80) {
            basePath += ":" + request.getServerPort();
        }
        basePath += request.getContextPath()+"/";
        APIURLDto apiurlDto = new APIURLDto();
        apiurlDto.setBasePath(basePath);
        if (urlDto.getUrl() != null) {
            urlDto = urlService.add(urlDto);
            if (urlDto != null) {
                BeanUtils.copyProperties(urlDto, apiurlDto);
                apiurlDto.setId(null);
                apiurlDto.setError(ErrorType.SUCCESS);
                return apiurlDto;
            }
            apiurlDto.setError(ErrorType.ADD_FAIL_SHORT_REPEAT);
            return apiurlDto;
        }
        apiurlDto.setError(ErrorType.ADD_FAIL);
        return apiurlDto;
    }

    @RequestMapping(value = "/tran", method = RequestMethod.POST)
    public APIURLDto trans2url(HttpServletRequest request, URLDto urlDto) {
        String basePath = request.getScheme() + "://" + request.getServerName();
        if (request.getServerPort() != 80) {
            basePath += ":" + request.getServerPort();
        }
        basePath += request.getContextPath()+"/";
        APIURLDto apiurlDto = new APIURLDto();
        apiurlDto.setBasePath(basePath);
        if (urlDto.getsUrl() != null && !urlDto.getsUrl().trim().equals("")) {
            URLDto urlDtoTemp = urlService.getBySUrl(urlDto.getsUrl());

            if (urlDtoTemp.getValidTimes() <= urlDtoTemp.getVisited()) {
                BeanUtils.copyProperties(urlDto, apiurlDto);
                apiurlDto.setError(ErrorType.TIMES_INVALID);
                return apiurlDto;
            }

            Double dayDiff = Time.getDateDiff(urlDtoTemp.getCreateTime(), new Date());
            if (dayDiff > urlDtoTemp.getValidTime()) {
                BeanUtils.copyProperties(urlDto, apiurlDto);
                apiurlDto.setError(ErrorType.TIME_INVALID);
                return apiurlDto;
            }
            if (urlDtoTemp.getVisitPass() == null || urlDtoTemp.getVisitPass().equals("") || urlDto.getVisitPass() == null && urlDtoTemp.getVisitPass() == null ||
                    urlDto.getVisitPass() != null && urlDtoTemp.getVisitPass() !=null
                    &&urlDto.getVisitPass().equals(urlDtoTemp.getVisitPass())) {

                BeanUtils.copyProperties(urlDtoTemp, apiurlDto);
                urlService.incVisitedById(apiurlDto.getId());
                apiurlDto.setValidTimes(apiurlDto.getValidTimes() - apiurlDto.getVisited());
                apiurlDto.setValidTime(apiurlDto.getValidTime() - dayDiff);
                apiurlDto.setId(null);
                apiurlDto.setError(ErrorType.SUCCESS);
                return apiurlDto;
            } else {
                BeanUtils.copyProperties(urlDto, apiurlDto);
                apiurlDto.setError(ErrorType.PASS_ERROR);
                return apiurlDto;
            }
        }
        BeanUtils.copyProperties(urlDto, apiurlDto);
        apiurlDto.setError(ErrorType.URL_INVALID);
        return apiurlDto;
    }

}
