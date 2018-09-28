package top.dalongm.controller.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController {
	/**
	 * 前台界面
	 */
	@RequestMapping
	public String init() {
		return "/app/index";
	}
}
