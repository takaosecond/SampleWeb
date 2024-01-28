package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dto.ZipCodeDto;
import com.example.demo.form.ZipCodeForm;
import com.example.demo.service.ZipCodeService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ZipCodeController {

    @Autowired
    ZipCodeService zpcService;

    /**
     * 郵便番号入力フォーム
     * @return "zipcode"
     */
    @GetMapping("/zipcode")
    public String zipcodeForm(HttpSession session, Model model, ZipCodeForm form) {
        return "zipcode";
    }

    /**
     * 郵便番号情報表示 
     * @return "zipcode-confirm"
     */
    @PostMapping(value="/zipcode/confirm")
    public String zipcodeConfirm(HttpSession session, Model model, 
                                 @RequestParam("zipcode") String zipcode){

        // 郵便番号検索APIサービス呼び出し
        ZipCodeDto zipCodeDto = zpcService.service(zipcode);
        // thymeleafでリストを展開して表示する
        model.addAttribute("zipcodeList", zipCodeDto.getResults());
        return "zipcode-confirm";   
    }
}