package idusw.springboot.boardlgh.controller;

import idusw.springboot.boardlgh.service.MemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class HomeController {
    @Autowired
    MemoService memoService;  // MemoService 인터페이스의 구현체를 필드 주입

    @GetMapping("/")
    public String goHome() {
        return "/admin/index";
    }   // /admin/

    @GetMapping("/buttons")     //자원을 접근하는 개념
    public String getButtons() {
        return "/admin/buttons";
    }

    @GetMapping("/cards")
    public String getCards() {
        return "/admin/cards";
    }
}
