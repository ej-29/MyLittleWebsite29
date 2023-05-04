package cej.boot.backend;

import cej.boot.backend.service.BoardService;
import oracle.jdbc.proxy.annotation.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

    @Autowired
    private BoardService service;

    @GetMapping("/")
    public String index(Model model){
        return "index";
    }

    @GetMapping("/board")
    public String board(Model model){
        return "board";
    }

    @GetMapping("/write.do")
    public String write(Model model){
        return "board";
    }

    @PostMapping("/write_ok.do")
    public String writeOk(Model model){
        return "redirect:/board/list.do";
    }

    @GetMapping("/content.do")
    public String content(Model model){
        return "content";
    }

    @GetMapping("/update.do")
    public String update(Model model){
        return "update";
    }

    @GetMapping("/selfIntroduce")
    public String selfintroduce (Model model){
        return "selfIntroduce";
    }

    @GetMapping("/selfIntroduce/cej")
    public String selfintroduceCej (Model model){
        return "selfIntroduce/cej";
    }

    @GetMapping("/selfIntroduce/school")
    public String selfintroduceSchool (Model model){
        return "selfIntroduce/school";
    }

    @GetMapping("/selfIntroduce/career")
    public String selfintroduceCareer (Model model){
        return "selfIntroduce/career";
    }

    @GetMapping("/selfIntroduce/class")
    public String selfintroduceClass (Model model){
        return "selfIntroduce/class";
    }

    @GetMapping("/selfIntroduce/portfolio")
    public String selfintroducePortfolio (Model model){
        return "selfIntroduce/portfolio";
    }

    @GetMapping("/selfIntroduce/stWk")
    public String selfintroducestWk (Model model){
        return "selfIntroduce/stWk";}

    @GetMapping("/login")
    public String login (Model model){
        return "login";}

    @GetMapping("/join")
    public String join (Model model){
        return "join";}

    @GetMapping("/draw")
    public String draw (Model model){
        return "draw";}

}
