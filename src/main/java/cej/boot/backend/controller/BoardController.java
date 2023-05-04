package cej.boot.backend.controller;

import cej.boot.backend.dto.BoardDTO;
import cej.boot.backend.dto.MemberDTO;
import cej.boot.backend.service.BoardService;
import cej.boot.backend.utility.ScriptUtil;
import jdk.jshell.spi.ExecutionControlProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("board")
@Controller
public class BoardController {
    @Autowired
    private BoardService boardService;

    @GetMapping("/list.do")
    public String list(Model model, BoardDTO boardDTO){
        List<BoardDTO> list = boardService.listS(boardDTO);
        model.addAttribute("list", list);
        return "board";
    }

    @GetMapping("/write.do")
    public String write(Model model, HttpSession session) {
        if (session.getAttribute("member") == null) {
            return "redirect:/member/login";
        }
        model.addAttribute("boardDTO", new BoardDTO());
        model.addAttribute("session", session);
        return "board/writeForm";
    }

    @PostMapping("/write_ok.do")
    public String write(BoardDTO boardDTO, HttpSession session) throws IOException {
        MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");
        boardDTO.setBd_id(memberDTO.getMb_id());
        boardService.writeS(boardDTO);
        return "redirect:/board/list.do";
    }


    @GetMapping("/content.do")
    public String content(@RequestParam("bd_num") long bd_num, Model model) throws IOException {
        BoardDTO boardDTO = boardService.contentS(bd_num);
        System.out.println("bd_num: " + bd_num);
        List<BoardDTO> list = boardService.listS(boardDTO);
        model.addAttribute("list", list);
        model.addAttribute("bd_num", bd_num);
        return "content";
    }

    @GetMapping("/update.do")
    public String update(@RequestParam long bd_num, HttpSession session, Model model, HttpServletResponse response) throws IOException {
        MemberDTO member = (MemberDTO) session.getAttribute("member"); // 세션에서 MemberDTO 가져오기
        String mb_id = member.getMb_id(); // mb_id 속성 가져오기
        System.out.println("---------------------------mb_id:   " + mb_id);
        BoardDTO boardDTO = boardService.getBoard(bd_num);

        // 작성자와 로그인한 사용자의 아이디가 같지 않으면 수정 페이지 접근 불가
        if (boardDTO.getBd_id().equals(mb_id)) {
            model.addAttribute("boardDTO", boardDTO);
            String bd_id = boardDTO.getBd_id();
            model.addAttribute("bd_id", bd_id);
            System.out.println("--------------------------bd_id:   " + bd_id);
            model.addAttribute("bd_num", bd_num);
            System.out.println("--------------------------bd_num:   " + bd_num);
            return "update";
        } else {
            ScriptUtil.alertAndBackPage(response, "회원님이 작성한 글이 아닙니다.");
            return "redirect:/content.do";
        }
    }

    @PostMapping("/update_ok.do")
    public String updateOk(BoardDTO boardDTO) throws IOException {
        System.out.println("11111111111111111111111111111:" + boardDTO);
        boardService.updateS(boardDTO);
        return "redirect:/board/list.do";
    }

    @GetMapping("/delete.do")
    public String delete(long bd_num, Model model, HttpSession session, HttpServletResponse response) throws IOException {
        MemberDTO member = (MemberDTO) session.getAttribute("member"); // 세션에서 MemberDTO 가져오기
        String mb_id = member.getMb_id();
        BoardDTO boardDTO = boardService.getBoard(bd_num);
        boardService.deleteS(bd_num);
        if (boardDTO.getBd_id().equals(mb_id)) {
            model.addAttribute("boardDTO", boardDTO);
            String bd_id = boardDTO.getBd_id();
            model.addAttribute("bd_id", bd_id);
            model.addAttribute("bd_num", bd_num);
            return "redirect:/board/list.do";
        } else {
            ScriptUtil.alertAndBackPage(response, "회원님이 작성한 글이 아닙니다.");
            return "redirect:/content.do";
        }
    }


}
