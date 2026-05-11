package com.ruoyi.web.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;

@Service
public class DeepSeekService
{
    private static final Logger logger = LoggerFactory.getLogger(DeepSeekService.class);

    @Value("${deepseek.api-key}")
    private String apiKey;

    @Value("${deepseek.base-url}")
    private String baseUrl;

    @Value("${deepseek.model}")
    private String model;

    public String chat(String userMessage)
    {
        return chat(null, userMessage);
    }

    public String chat(String systemPrompt, String userMessage)
    {
        HttpURLConnection conn = null;
        try
        {
            JSONArray messages = new JSONArray();
            if (systemPrompt != null && !systemPrompt.isEmpty())
            {
                JSONObject systemMsg = new JSONObject();
                systemMsg.put("role", "system");
                systemMsg.put("content", systemPrompt);
                messages.add(systemMsg);
            }
            JSONObject userMsg = new JSONObject();
            userMsg.put("role", "user");
            userMsg.put("content", userMessage);
            messages.add(userMsg);

            JSONObject requestBody = new JSONObject();
            requestBody.put("model", model);
            requestBody.put("messages", messages);

            String jsonBody = requestBody.toJSONString();
            URL url = new URL(baseUrl + "/v1/chat/completions");
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization", "Bearer " + apiKey);
            conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
            conn.setDoOutput(true);
            conn.setConnectTimeout(30000);
            conn.setReadTimeout(60000);

            try (OutputStream os = conn.getOutputStream())
            {
                os.write(jsonBody.getBytes(StandardCharsets.UTF_8));
                os.flush();
            }

            int statusCode = conn.getResponseCode();
            if (statusCode != 200)
            {
                logger.error("DeepSeek API 调用失败, 状态码: {}", statusCode);
                return null;
            }

            StringBuilder responseBuilder = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8)))
            {
                String line;
                while ((line = reader.readLine()) != null)
                {
                    responseBuilder.append(line);
                }
            }

            JSONObject json = JSONObject.parseObject(responseBuilder.toString());
            JSONArray choices = json.getJSONArray("choices");
            if (choices != null && !choices.isEmpty())
            {
                JSONObject message = choices.getJSONObject(0).getJSONObject("message");
                return message.getString("content");
            }
            return null;
        }
        catch (Exception e)
        {
            logger.error("DeepSeek API 调用异常: {}", e.getMessage(), e);
            return null;
        }
        finally
        {
            if (conn != null)
            {
                conn.disconnect();
            }
        }
    }
}
