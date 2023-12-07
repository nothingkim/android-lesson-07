package kr.easw.lesson07.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import kr.easw.lesson07.service.AWSService;
import org.springframework.web.bind.annotation.RequestMethod;


// 이 클래스를 컨트롤러로 선언합니다.
@Controller
@RequiredArgsConstructor
public class BaseWebController {
    private final AWSService awsController;
//    @RequestMapping("/")
//    public ModelAndView onIndex() {
//        if (awsController.isInitialized()) {
//            return new ModelAndView("data.html");
//        }
//        return new ModelAndView("request_aws_key.html");
//    }

    @RequestMapping("/login")
    public ModelAndView onLogin() {
        return new ModelAndView("login.html");
    }


    @RequestMapping("/register")
    public ModelAndView onRegister() {
        return new ModelAndView("register.html");
    }

    @RequestMapping("/admin")
    public ModelAndView onAdminDashboard() {
        if (awsController.isInitialized()) {
            return new ModelAndView("data.html");
        }
        return new ModelAndView("request_aws_key.html");
    }


    @RequestMapping("/management")
    public ModelAndView onManagementDashboard() {
        return new ModelAndView("management.html");
    }

    // 이 메서드의 엔드포인트를 /server-error로 설정합니다.
    @RequestMapping("/server-error")
    public ModelAndView onErrorTest() {
        // 에러 페이지로 리다이렉트합니다.
        return new ModelAndView("error.html");
    }


}



