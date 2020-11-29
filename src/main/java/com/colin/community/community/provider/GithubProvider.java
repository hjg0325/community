package com.colin.community.community.provider;

import com.alibaba.fastjson.JSON;
import com.colin.community.community.dao.AccessToken;
import com.colin.community.community.dao.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA
 * User : HeJianGong
 * Date : 2020/11/28
 * Time : 20:11
 */
@Component
public class GithubProvider
{
    public String getAccessToken(AccessToken accessToken)
    {
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessToken));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute())
        {
            String string = response.body().string();
            String token = string.split("&")[0].split("=")[1];
            return token;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public GithubUser getUser(String accessToken)
    {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token=" + accessToken)
                .build();
        try
        {
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            GithubUser githubUser = JSON.parseObject(string, GithubUser.class);
            return githubUser;
        }
        catch (IOException e)
        {
        }
        return null;
    }
}
