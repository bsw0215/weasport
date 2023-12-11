package com.connect.weasport.controller.board;

import com.connect.weasport.domain.Board;
import com.connect.weasport.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class boardController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/board")
    public String index(Model model, @PageableDefault(size=3, sort="id", direction = Sort.Direction.DESC) Pageable pageable) {
        model.addAttribute("boards", boardService.boardList(pageable));
        return "board";
    }

    @GetMapping("/board/{id}")
    public String findById(@PathVariable int id, Model model) {

        Board board = boardService.boardDetail(id);
        board.setCount(board.getCount()+1);
        boardService.modify(id, board);

        model.addAttribute("board", board);

        return "view";
    }

    @GetMapping("/board/{id}/updateForm")
    public String updateForm(@PathVariable int id, Model model) {
        model.addAttribute("board", boardService.boardDetail(id));
        return "updateForm";
    }

    @GetMapping("/board/saveForm")
    public String saveForm() {
        return "saveForm";
    }
}
