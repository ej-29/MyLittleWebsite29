package cej.boot.backend.mapper;

import cej.boot.backend.dto.BoardDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
@Mapper
public interface BoardMapper {
    List<BoardDTO> list(BoardDTO boardDTO);

    void write(BoardDTO boardDTO);

    BoardDTO content(HashMap map);

    void update(HashMap map);

    void delete(long bd_num);

    BoardDTO getBoard(long bd_num);
}
