package com.sist.aopboard;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.*;
import com.sist.dao.*;

@Controller
public class BoardController {
	@Autowired
    private BoardDAO dao;
	@RequestMapping("board/list.do")
	public String board_list(String page,Model model)
	{
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		int rowSize=10;
		int start=(curpage*rowSize)-(rowSize-1);
		int end=curpage*rowSize;
		Map map=new HashMap();
		map.put("start", start); // #{start}
		map.put("end", end);
		List<BoardVO> list=dao.boardAllData(map);
		String today=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		for(BoardVO vo:list)
		{
			vo.setDbday(new SimpleDateFormat("yyyy-MM-dd").format(vo.getRegdate()));
		}
		int totalpage=dao.boardTotalPage();
		model.addAttribute("list", list);
		model.addAttribute("today", today);
		model.addAttribute("curpage", curpage);
		model.addAttribute("msg", "관리자가 삭제한 게시물입니다");
		model.addAttribute("totalpage", totalpage);
		return "board/list";
	}
	@RequestMapping("board/insert.do")
	public String board_insert()
	{
		return "board/insert";
	}
	@RequestMapping("board/insert_ok.do")
	public String board_insert_ok(BoardVO vo)
	{
		dao.boardInsert(vo);
		return "redirect:/board/list.do";
	}
	@RequestMapping("board/content.do")
	public String board_content(int no,int page,Model model)
	{
		BoardVO vo = dao.boardContent(no);
		model.addAttribute("dto",vo);
		model.addAttribute("page",page);
		return "board/content";
	}
}








