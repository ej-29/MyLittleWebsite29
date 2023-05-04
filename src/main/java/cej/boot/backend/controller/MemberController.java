package cej.boot.backend.controller;

import cej.boot.backend.dto.BoardDTO;
import cej.boot.backend.dto.MemberDTO;
import cej.boot.backend.service.MemberService;
import cej.boot.backend.utility.ScriptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Member;
import java.util.List;

@Controller
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/list.do")
    public String listM(Model model, HttpSession session) {
        List<MemberDTO> memberList = memberService.listS(new MemberDTO());
        model.addAttribute("memberList", memberList);

        // 세션에서 로그인한 사용자 정보를 가져와서 뷰에 전달
        MemberDTO loginMember = (MemberDTO) session.getAttribute("member");
        model.addAttribute("loginMember", loginMember);

        return "board";
    }

    @GetMapping("join.do")
    public String join() {
        return "join";
    }

    @PostMapping("join_ok.do")
    public void joinOk(MemberDTO memberDTO, HttpServletResponse response) throws IOException {
        if (memberService.insertS(memberDTO)) {
            ScriptUtil.alertAndMovePage(response, "회원가입이 완료되었습니다.", "login");
        } else {
            ScriptUtil.alertAndMovePage(response, "회원가입이 실패되었습니다.", "join.do");
        }
    }

    @GetMapping("login.do")
    public String login() {
        return "login";
    }

    @PostMapping("login_ok.do")
    public void loginOk(MemberDTO memberDTO, HttpServletResponse response, HttpSession session) throws IOException{
        MemberDTO member = memberService.selectByIdUserS(memberDTO.getMb_id());
        if(member == null){
            ScriptUtil.alertAndBackPage(response, "아이디가 존재하지 않습니다.");
        }else{
            if(member.getMb_pwd().equals(memberDTO.getMb_pwd())){
                session.setAttribute("member", member); // 회원 정보를 세션에 저장
                if(member.getMb_status() == 0){
                    ScriptUtil.alertAndMovePage(response, "회원님, 어서오세요!", "/board/list.do");
                }else{
                    ScriptUtil.alertAndMovePage(response, "관리자님, 어서오세요!", "/board/list.do");
                }
                System.out.println("로그인 성공:   "+member);
            }else{
                ScriptUtil.alertAndBackPage(response, "비밀번호가 일치하지 않습니다.");
            }
        }
    }

    @GetMapping("logout.do")
    public String logout(HttpSession session, HttpServletResponse response) throws IOException{
        if(session.getAttribute("member") != null){
        session.invalidate();
        ScriptUtil.alertAndMovePage(response,"로그아웃 되었습니다!", "/");
        System.out.println("로그아웃 완료!");
        return "/";
        }
        else{
            return "/board/list.do";
        }
    }

    @GetMapping("/draw.do")
    public void draw(HttpSession session, HttpServletResponse response) throws IOException {
        long mb_num = ((MemberDTO)session.getAttribute("member")).getMb_num();
        System.out.println("mb_num:    " + mb_num);
        if (memberService.drawS(mb_num)) {
            session.invalidate();
            ScriptUtil.alertAndMovePage(response, "회원탈퇴 되었습니다.", "/");
        }
        else{
            ScriptUtil.alertAndBackPage(response, "회원탈퇴를 실패하였습니다. 다시 시도해주세요.");
        }
    }
}
