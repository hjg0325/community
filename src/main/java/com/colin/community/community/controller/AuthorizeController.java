package com.colin.community.community.controller;

import com.colin.community.community.dao.AccessToken;
import com.colin.community.community.dao.GithubUser;
import com.colin.community.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created with IntelliJ IDEA
 * User : HeJianGong
 * Date : 2020/11/28
 * Time : 20:06
 */
@Controller
public class AuthorizeController
{
    @Autowired
    private GithubProvider githubProvider;

    @Value("${github.client.id}")
    private String clientId;

    @Value("${github.client.secret}")
    private String clientSecret;

    @Value("${github.redirect.uri}")
    private String redirectUri;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state)
    {
        AccessToken accessToken = new AccessToken();
        accessToken.setClient_id(clientId);
        accessToken.setClient_secret(clientSecret);
        accessToken.setCode(code);
        accessToken.setRedirect_uri(redirectUri);
        accessToken.setState(state);
        String accessToken1 = githubProvider.getAccessToken(accessToken);
        GithubUser user = githubProvider.getUser(accessToken1);
        System.out.println(user.getName());
        return "index";
    }
}
