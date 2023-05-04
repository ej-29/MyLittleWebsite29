package cej.boot.backend.service;

import cej.boot.backend.dto.BoardDTO;
import cej.boot.backend.dto.MemberDTO;

import java.util.List;

public interface MemberService {

    List<MemberDTO> listS(MemberDTO memberDTO);

    boolean insertS(MemberDTO memberDTO);

    MemberDTO selectByNumS(long mb_num);

    MemberDTO selectByIdUserS(String mb_id);

    boolean drawS(long mb_num);

}
