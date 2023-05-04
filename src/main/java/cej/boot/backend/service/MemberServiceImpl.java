package cej.boot.backend.service;

import cej.boot.backend.dto.BoardDTO;
import cej.boot.backend.dto.MemberDTO;
import cej.boot.backend.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService{
    @Autowired
    private MemberMapper mapper;

    @Override
    public List<MemberDTO> listS(MemberDTO memberDTO) {
        return mapper.listM(memberDTO);
    }

    @Override
    public boolean insertS(MemberDTO memberDTO) {
        mapper.insert(memberDTO);
        if(mapper.selectByNum(memberDTO.getMb_num()) != null){
            System.out.println("회원가입 정상");
            return true;
        }else{
            System.out.println("회원가입 실패");
            return false;
        }
    }

    @Override
    public MemberDTO selectByNumS(long mb_num) {
        return mapper.selectByNum(mb_num);
    }

    @Override
    public MemberDTO selectByIdUserS(String mb_id) {
        return mapper.selectByIdUser(mb_id);
    }

    @Override
    public boolean drawS(long mb_num) {
        mapper.draw(mb_num);
        if (mapper.selectByNum(mb_num) != null) {
            System.out.println("회원탈퇴 실패");
            return false;
        } else {
            System.out.println("회원탈퇴 성공");
            return true;
        }
    }



}
