package com.twiiter.base.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.twiiter.base.entity.TwiiterTwiitEntity;
import com.twiiter.base.entity.TwiiterUserEntity;
import com.twiiter.base.form.TwiiterForm;
import com.twiiter.base.service.TwiiterService;

@Controller
public class TwiiterController {
	
	@Autowired
	private TwiiterService twiiterService;
	
	
	// 新規登録への画面推移
	@GetMapping("twiiter/registration")
	public String getRegistration(@Validated TwiiterForm twiiterForm, BindingResult result, Model model) throws ParseException {

		model.addAttribute("form", twiiterForm);

		return "/twiiter/registration";
	}
	
	// ユーザー情報登録
	@GetMapping("twiiter/registration")
	public String registration(@Validated TwiiterForm twiiterForm, BindingResult result, Model model) throws ParseException {

		model.addAttribute("title", "新規登録");
		model.addAttribute("form", twiiterForm);
		
		// TwiiterTwiitEntityインスタンス生成
		TwiiterUserEntity twiiterUserEntity = new TwiiterUserEntity();

		// twiit時DB登録のためにTodoEntityにTodoFormの値をセット
		twiiterUserEntity.setTwiiterUserName(twiiterForm.getTwiiterUserName());
		twiiterUserEntity.setTwiiterPassword(twiiterForm.getTwiiterPassword());

		return "/twiiter/login";
	}
	
	// ログイン画面
//	@PostMapping("/twiiter/login")
//	public String login(@Validated TwiiterForm twiiterForm, BindingResult result, Model model) throws ParseException {
//
//		model.addAttribute("title", "ログイン");
//		model.addAttribute("form", twiiterForm);
//
//
//	    // TwiitUserEntityインスタンス生成
//	    TwiiterUserEntity twiiterUserEntity = new TwiiterUserEntity();
//	    
//
//		// 以下でログイン名とパスワードの照合
//
//		
//	    // 一致した場合twiit画面へ
//		return "twiiter/twiit";
//	}
	
	
	/**
	 * ログイン成功時に呼び出されるメソッド
	 * SecurityContextHolderから認証済みユーザの情報を取得しモデルへ追加する
	 * @param model リクエストスコープ上にオブジェクトを載せるためのmap
	 * @return helloページのViewName
	 */
	@RequestMapping("/twiiter/twiit")
	private String init(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		//Principalからログインユーザの情報を取得
		String userName = auth.getName();
		model.addAttribute("userName", userName);
		return "twiiter/twiit";
		
	}
	
	// twiit画面
	@PostMapping("twiiter/twiit")
	public String twiit(@Validated TwiiterForm twiiterForm, BindingResult result, Model model) throws ParseException {
		
		// TwiiterTwiitEntityインスタンス生成
		TwiiterTwiitEntity twiiterTwiitEntity = new TwiiterTwiitEntity();
		
		Date dateObj = new Date();
	    SimpleDateFormat format = new SimpleDateFormat( "yyyy/MM/dd HH:mm:ss" );
	    // 日時情報を指定フォーマットの文字列で取得
	    String sysDate = format.format( dateObj );

		// twiit時DB登録のためにTodoEntityにTodoFormの値をセット
	    twiiterTwiitEntity.setTwiiterTwiitMessage(twiiterForm.getTwiiterTwiitMessage());
	    twiiterTwiitEntity.setTwiiterTwiitPhoto(twiiterForm.getTwiiterTwiitPhoto());
	    twiiterTwiitEntity.setTwiiterTwiitTime(sysDate);
		
		
		return "twiiter/twiit";
	
	}
	
	// ユーザー情報画面への画面推移
	@GetMapping("twiiter/detail")
	public String getDetail(@Validated TwiiterForm twiiterForm, BindingResult result, Model model) throws ParseException {

		model.addAttribute("form", twiiterForm);

		return "/twiiter/detail";
	}
	
	// 一覧表示処理
	@GetMapping("twiiter/twiit")
	public String displayList(TwiiterForm twiiterForm, Model model) {
		List<TwiiterTwiitEntity> twiitList = twiiterService.getAllTwiit();
	    model.addAttribute("twiitList", twiitList);
		return "twiiter/twiit";
	}
	
	// 削除処理
	@PostMapping("twiiter/delete")
	public String displayDelete(@RequestParam Integer twiiterTwiitId, @ModelAttribute TwiiterForm twiiterForm, Model model) {

		twiiterService.twiitDelete(twiiterTwiitId);

		List<TwiiterTwiitEntity> twiitList = twiiterService.getAllTwiit();
	    model.addAttribute("taskList", twiitList);

		return "twiiter/twiit";
	}
}
