package cej.boot.backend.mapper;

import cej.boot.backend.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MemberMapper {

    List<MemberDTO> listM(MemberDTO memberDTO);

    MemberDTO selectByNum(long mb_num);

    MemberDTO selectByIdUser(String mb_id);
    void insert(MemberDTO memberDTO);

    void draw(long mb_num);
}
