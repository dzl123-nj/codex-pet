package com.ruoyi.web.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.web.service.DeepSeekService;

@RestController
@RequestMapping("/chat")
public class ChatController extends BaseController
{
    @Autowired
    private DeepSeekService deepSeekService;

    @PostMapping("/send")
    public AjaxResult send(@RequestBody Map<String, String> params)
    {
        String message = params.get("message");
        if (message == null || message.trim().isEmpty())
        {
            return error("消息内容不能为空");
        }
        try
        {
            String reply = deepSeekService.chat(message);
            if (reply == null)
            {
                return error("AI服务调用失败，请稍后再试");
            }
            return success(reply);
        }
        catch (Exception e)
        {
            logger.error("AI对话异常: {}", e.getMessage(), e);
            return error("AI服务调用失败: " + e.getMessage());
        }
    }
}
