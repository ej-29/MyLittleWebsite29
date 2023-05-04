package cej.boot.backend.service;

import cej.boot.backend.dto.BoardDTO;

import java.io.IOException;
import java.util.List;


public interface BoardService {
    List<BoardDTO> listS(BoardDTO boardDTO);

    long writeS(BoardDTO boardDTO) throws IOException;

    BoardDTO contentS(long bd_num);

    long updateS(BoardDTO boardDTO) throws IOException;

    void deleteS(long bd_num);
    BoardDTO getBoard(long bd_num);
}
