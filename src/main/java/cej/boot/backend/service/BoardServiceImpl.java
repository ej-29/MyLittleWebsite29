package cej.boot.backend.service;

import cej.boot.backend.dto.BoardDTO;
import cej.boot.backend.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@Service
public class BoardServiceImpl implements BoardService{

    @Autowired
    BoardMapper mapper;

    @Override
    public List<BoardDTO> listS(BoardDTO boardDTO) {
        return mapper.list(boardDTO);
    }

//    @Override
//    public long writeS(BoardDTO boardDTO) throws IOException{
//        HashMap<String, Object> map = new HashMap<>();
//        map.put("bd_id", boardDTO.getBd_id());
//        map.put("bd_title", boardDTO.getBd_title());
//        map.put("bd_content", boardDTO.getBd_content());
//        mapper.write(map);
//        long bd_num= (long) map.get("bd_num");
//        return bd_num;
//    }

    @Override
    public long writeS(BoardDTO boardDTO) throws IOException {
        String mb_id = boardDTO.getBd_id();
        boardDTO.setBd_id(mb_id);
        mapper.write(boardDTO);
        return boardDTO.getBd_num();
    }


    @Override
    public BoardDTO contentS(long bd_num){
       HashMap<String, Object> map = new HashMap<>();
       map.put("bd_num", bd_num);
       return mapper.content(map);
    }

    @Override
    public long updateS(BoardDTO boardDTO) throws IOException {
        HashMap<String, Object> map = new HashMap<>();
        map.put("bd_num", boardDTO.getBd_num());
        map.put("bd_id", boardDTO.getBd_id());
        map.put("bd_title", boardDTO.getBd_title());
        map.put("bd_content", boardDTO.getBd_content());
        map.put("bd_udate", boardDTO.getBd_udate());
        mapper.update(map);
        long bd_num = (long) map.get("bd_num");
        return bd_num;
    }

    @Override
    public void deleteS(long bd_num) {
        mapper.delete(bd_num);
    }

    @Override
    public BoardDTO getBoard(long bd_num) {
        return mapper.getBoard(bd_num);
    }


}
