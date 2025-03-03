package it.company.noname.controller;

import java.util.List;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.company.noname.domain.helpboardVO;
import it.company.noname.service.helpService;
import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@RequestMapping("/help/*")
public class helpController {

	@Autowired
	private helpService helpservice;
	private JavaMailSender mailSender;	
	
	
	@GetMapping("/board")
	public String list(Model model){
		log.info("write() 호출됨...");
		log.warn("주의 메시지");
		List<helpboardVO> boardList = helpservice.getBoards();
		model.addAttribute("boardList", boardList);
		return "help/helpboard";
	}

	@GetMapping("/email")
	public String email() {
		return "help/helpemail";
	}
	
	//mailSending 코드
	@RequestMapping(value = "mailSending.do")
	public String mailSending(HttpServletRequest request) {

		String setfrom = "nonameMovie";
		String tomail = request.getParameter("tomail"); // 받는 사람 이메일
		String title = request.getParameter("title"); // 제목
		String content = request.getParameter("content"); // 내용

		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");

			messageHelper.setFrom(setfrom); // 보내는사람 생략하면 정상작동을 안함
			messageHelper.setTo(tomail); // 받는사람 이메일
			messageHelper.setSubject(title); // 메일제목은 생략이 가능하다
			messageHelper.setText(content); // 메일 내용

			mailSender.send(message);
		} catch (Exception e) {
			System.out.println(e);
		}

		return "home";
	}
	
	
}
