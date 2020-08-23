package life.majiang.community.provider;

import com.alibaba.fastjson.JSON;
import life.majiang.community.dto.AccessTokenDTO;
import life.majiang.community.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author: T.ADOLPH
 * @date: 2020/8/22 13:12
 * Describe:
 */
@Component
public class GithubProvider {
    /**
     * 对github发起请求，并从起响应中获取token
     * @param accessTokenDTO
     * @return String token
     */
    public String getAccessToken(AccessTokenDTO accessTokenDTO) {
        // 创建一个Json类型
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        // 新建一个HttpClient
        OkHttpClient client = new OkHttpClient();
        // 构建一个请求体（requestBody）里面包含了AccessToken
        RequestBody body = RequestBody.create(JSON.toJSONString(accessTokenDTO), mediaType);
        // 组成一个完整的请求
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        // 进行请求，并尝试获取响应
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            response.close();
            // 从响应中把token抽取出来并返回
            String token = string.split("&")[0].split("=")[1];
            return token;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据AccessToken向github发起请求，并获取用户信息
     * @param accessToken
     * @return GitHubUser gitHubUser
     */
    public GithubUser getUser(String accessToken) {
        OkHttpClient client = new OkHttpClient();
        // 根据token新建一个请求
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token=" + accessToken)
                .build();

        try {
            // 发起请求，并从响应中拿到用户信息
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            response.close();
            // 直接将Json中用户的String信息，封装成一个GithubUser类
            GithubUser githubUser = JSON.parseObject(string, GithubUser.class);
            return githubUser;
        } catch (IOException e) {
            return null;
        }
    }
}
