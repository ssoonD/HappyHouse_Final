package com.ssafy.guestbook.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ssafy.guestbook.model.FileInfoDto;
import com.ssafy.guestbook.model.GuestBookDto;
import com.ssafy.guestbook.model.MemberDto;
import com.ssafy.guestbook.model.service.GuestBookService;
import com.ssafy.util.PageNavigation;

@Controller
@RequestMapping("/article")
public class GuestBookController {

	private static final Logger logger = LoggerFactory.getLogger(GuestBookController.class);

	@Autowired
	ServletContext servletContext;

	@Autowired
	private GuestBookService guestBookService;

	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String write() {
		return "guestbook/write";
	}

	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String write(GuestBookDto guestBookDto, @RequestParam("upfile") MultipartFile[] files, Model model,
			HttpSession session) throws Exception {
		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
		if (memberDto != null) {
			logger.debug("MultipartFile.isEmpty : {}", files[0].isEmpty());
			if (!files[0].isEmpty()) {
				// 실제 경로 얻어와라
//				String realPath = servletContext.getRealPath("/upload");
				String realPath = servletContext.getRealPath("/resources/img");
				// 오늘 날짜에 해당하는 폴더 만들어서 실제 경로랑 결합해라
				String today = new SimpleDateFormat("yyMMdd").format(new Date());
				String saveFolder = realPath + File.separator + today;
				logger.debug("저장 폴더 : {}", saveFolder);
				File folder = new File(saveFolder);
				// 폴더가 없으면 만들어라
				if (!folder.exists())
					folder.mkdirs();
				// 리스트 하나 만들기
				List<FileInfoDto> fileInfos = new ArrayList<FileInfoDto>();
				// 파일 정보를 가지고 for문 돌리기
				for (MultipartFile mfile : files) {
					FileInfoDto fileInfoDto = new FileInfoDto();
					String originalFileName = mfile.getOriginalFilename();
					// 파일이 비어있지 않다면
					if (!originalFileName.isEmpty()) {
						// 유일값 만들기 + DTO Setting
						String saveFileName = UUID.randomUUID().toString()
								+ originalFileName.substring(originalFileName.lastIndexOf('.'));
						fileInfoDto.setSaveFolder(today);
						fileInfoDto.setOriginFile(originalFileName);
						fileInfoDto.setSaveFile(saveFileName);
						logger.debug("원본 파일 이름 : {}, 실제 저장 파일 이름 : {}", mfile.getOriginalFilename(), saveFileName);
						// 서버에 하나씩 올라감
						mfile.transferTo(new File(folder, saveFileName));
					}
					// 리스트에 담아라
					fileInfos.add(fileInfoDto);
				}
				// 리스트를 DTO에 Setting해라
				guestBookDto.setFileInfos(fileInfos);
			}

			guestBookDto.setUserid(memberDto.getUserid());
			guestBookService.writeArticle(guestBookDto);
			return "guestbook/writesuccess";

		} else {
			model.addAttribute("msg", "로그인 후 사용 가능한 페이지입니다.");
			return "error/error";
		}
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(@RequestParam Map<String, String> map, Model model) throws Exception {
		String spp = map.get("spp");
		map.put("spp", spp != null ? spp : "10");// sizePerPage
//		try {
		List<GuestBookDto> list = guestBookService.listArticle(map);
		PageNavigation pageNavigation = guestBookService.makePageNavigation(map);
		model.addAttribute("articles", list);
		model.addAttribute("navigation", pageNavigation);
		return "guestbook/list";
//		} catch (Exception e) {
//			e.printStackTrace();
//			model.addAttribute("msg", "글목록을 얻어오는 중 문제가 발생했습니다.");
//			return "error/error";
//		}
	}

	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public String modify(@RequestParam("articleno") int articleno, Model model) {
		try {
			GuestBookDto guestBookDto = guestBookService.getArticle(articleno);
			model.addAttribute("article", guestBookDto);
			return "guestbook/modify";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "글수정 처리 중 문제가 발생했습니다.");
			return "error/error";
		}
	}

	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modify(GuestBookDto guestBookDto, Model model, HttpSession session) {
		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
		if (memberDto != null) {
			guestBookDto.setUserid(memberDto.getUserid());
			try {
				guestBookService.modifyArticle(guestBookDto);
				return "guestbook/writesuccess";
			} catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("msg", "글수정 중 문제가 발생했습니다.");
				return "error/error";
			}
		} else {
			model.addAttribute("msg", "로그인 후 사용 가능한 페이지입니다.");
			return "error/error";
		}
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(@RequestParam("articleno") int articleno, Model model) {
		try {
			guestBookService.deleteArticle(articleno);
			return "redirect:list?pg=1&key=&word=";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "글삭제 처리 중 문제가 발생했습니다.");
			return "error/error";
		}
	}

	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public ModelAndView downloadFile(@RequestParam("sfolder") String sfolder, @RequestParam("ofile") String ofile,
			@RequestParam("sfile") String sfile, HttpSession session) {
		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
		if (memberDto != null) {
			Map<String, Object> fileInfo = new HashMap<String, Object>();
			fileInfo.put("sfolder", sfolder);
			fileInfo.put("ofile", ofile);
			fileInfo.put("sfile", sfile);

			return new ModelAndView("fileDownLoadView", "downloadFile", fileInfo);
		} else {
			return new ModelAndView("redirect:/");
		}
	}

}
