package com.colin.community.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created with IntelliJ IDEA
 * User : HeJianGong
 * Date : 2020/11/28
 * Time : 17:20
 */
@Controller
public class IndexController
{
    @GetMapping("/")
    public String index()
    {
        return "index";
    }
}
