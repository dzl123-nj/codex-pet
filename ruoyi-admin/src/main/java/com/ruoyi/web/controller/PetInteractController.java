package com.ruoyi.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.virtualPet.domain.VirtualPet;
import com.ruoyi.virtualPet.service.IVirtualPetService;
import com.ruoyi.virtualPet.service.IPetAttributeService;
import com.ruoyi.web.service.DeepSeekService;

@RestController
@RequestMapping("/pet/interact")
public class PetInteractController extends BaseController
{
    @Autowired
    private DeepSeekService deepSeekService;

    @Autowired
    private IVirtualPetService virtualPetService;

    @Autowired
    private IPetAttributeService petAttributeService;

    @PostMapping("/chat")
    public AjaxResult chat(@RequestBody Map<String, Object> params)
    {
        Long petId = Long.valueOf(params.get("petId").toString());
        String message = params.get("message").toString();

        if (message == null || message.trim().isEmpty())
        {
            return error("消息内容不能为空");
        }

        VirtualPet pet = virtualPetService.selectVirtualPetById(petId);
        if (pet == null)
        {
            return error("宠物不存在");
        }

        petAttributeService.applyTimeDecay(pet);

        if (pet.getStatus() == 3L)
        {
            Map<String, Object> data = new HashMap<>();
            data.put("reply", pet.getPetName() + "已经不在了... 它在彩虹桥的另一边等着你 🌈😢");
            data.put("petData", pet);
            return success(data);
        }

        if (pet.getStatus() == 0L)
        {
            Map<String, Object> data = new HashMap<>();
            data.put("reply", "嘘... " + pet.getPetName() + "正在睡觉呢~ 让它好好休息吧 💤🌙");
            data.put("petData", pet);
            return success(data);
        }

        try
        {
            String moodDesc = getMoodDescription(pet);
            String systemPrompt = "你是一只名叫" + pet.getPetName() + "的虚拟宠物。"
                + "你的性格活泼可爱，喜欢和主人互动。"
                + "请用简短、亲切、可爱的语气回复主人的消息，可以适当使用一些可爱的表情符号。"
                + "回复内容控制在100字以内。"
                + "你当前的状态：" + moodDesc + "。"
                + "请根据你当前的状态来调整回复的语气和内容。";

            String reply = deepSeekService.chat(systemPrompt, message);
            if (reply == null)
            {
                return error("AI服务调用失败，请稍后再试");
            }

            long levelBefore = pet.getLevel() != null ? pet.getLevel() : 1;
            petAttributeService.chatReward(pet);

            Map<String, Object> data = new HashMap<>();
            data.put("reply", reply);
            data.put("petData", pet);
            data.put("levelUp", pet.getLevel() > levelBefore);

            return success(data);
        }
        catch (Exception e)
        {
            logger.error("宠物对话异常: {}", e.getMessage(), e);
            return error("AI服务调用失败: " + e.getMessage());
        }
    }

    @PostMapping("/feed")
    public AjaxResult feed(@RequestBody Map<String, Object> params)
    {
        Long petId = Long.valueOf(params.get("petId").toString());
        VirtualPet pet = virtualPetService.selectVirtualPetById(petId);
        if (pet == null)
        {
            return error("宠物不存在");
        }

        petAttributeService.applyTimeDecay(pet);
        Map<String, Object> result = petAttributeService.feed(pet);
        return success(result);
    }

    @PostMapping("/clean")
    public AjaxResult clean(@RequestBody Map<String, Object> params)
    {
        Long petId = Long.valueOf(params.get("petId").toString());
        VirtualPet pet = virtualPetService.selectVirtualPetById(petId);
        if (pet == null)
        {
            return error("宠物不存在");
        }

        petAttributeService.applyTimeDecay(pet);
        Map<String, Object> result = petAttributeService.clean(pet);
        return success(result);
    }

    @PostMapping("/sleep")
    public AjaxResult sleep(@RequestBody Map<String, Object> params)
    {
        Long petId = Long.valueOf(params.get("petId").toString());
        VirtualPet pet = virtualPetService.selectVirtualPetById(petId);
        if (pet == null)
        {
            return error("宠物不存在");
        }

        petAttributeService.applyTimeDecay(pet);
        Map<String, Object> result = petAttributeService.sleep(pet);
        return success(result);
    }

    @PostMapping("/wake")
    public AjaxResult wake(@RequestBody Map<String, Object> params)
    {
        Long petId = Long.valueOf(params.get("petId").toString());
        VirtualPet pet = virtualPetService.selectVirtualPetById(petId);
        if (pet == null)
        {
            return error("宠物不存在");
        }

        petAttributeService.applyTimeDecay(pet);
        Map<String, Object> result = petAttributeService.wake(pet);
        return success(result);
    }

    @PostMapping("/play")
    public AjaxResult play(@RequestBody Map<String, Object> params)
    {
        Long petId = Long.valueOf(params.get("petId").toString());
        VirtualPet pet = virtualPetService.selectVirtualPetById(petId);
        if (pet == null)
        {
            return error("宠物不存在");
        }

        petAttributeService.applyTimeDecay(pet);
        Map<String, Object> result = petAttributeService.play(pet);
        return success(result);
    }

    @GetMapping("/history/{petId}")
    public AjaxResult history(@PathVariable Long petId)
    {
        return success(new HashMap<>());
    }

    @GetMapping("/suggestions/{petId}")
    public AjaxResult suggestions(@PathVariable Long petId)
    {
        VirtualPet pet = virtualPetService.selectVirtualPetById(petId);
        if (pet == null)
        {
            return error("宠物不存在");
        }

        petAttributeService.applyTimeDecay(pet);

        List<Map<String, String>> suggestions = new ArrayList<>();

        if (pet.getStatus() == 0L)
        {
            suggestions.add(createSuggestion("该起床啦~太阳晒屁股了"));
            suggestions.add(createSuggestion("睡得好吗？做什么美梦了"));
            suggestions.add(createSuggestion("小懒虫，快醒醒~"));
        }
        else if (pet.getStatus() == 3L)
        {
            suggestions.add(createSuggestion("我会永远想你的..."));
            suggestions.add(createSuggestion("再见了，好朋友"));
            suggestions.add(createSuggestion("希望你在另一个世界快乐"));
        }
        else if (pet.getStatus() == 2L)
        {
            suggestions.add(createSuggestion("你哪里不舒服呀？"));
            suggestions.add(createSuggestion("别担心，我会照顾你的"));
            suggestions.add(createSuggestion("要不要去看医生呀？"));
        }
        else
        {
            suggestions.add(createSuggestion("你好呀！今天过得怎么样？"));
            suggestions.add(createSuggestion("我们来玩游戏吧！"));
            suggestions.add(createSuggestion("你能给我讲个故事吗？"));

            if (pet.getHunger() != null && pet.getHunger() < 20)
            {
                suggestions.add(createSuggestion("你是不是饿坏了？我马上喂你"));
            }
            else if (pet.getHunger() != null && pet.getHunger() < 40)
            {
                suggestions.add(createSuggestion("你饿不饿呀？吃点东西吧"));
            }
            if (pet.getHappiness() != null && pet.getHappiness() < 30)
            {
                suggestions.add(createSuggestion("我来陪你玩好不好？"));
            }
            else if (pet.getHappiness() != null && pet.getHappiness() > 80)
            {
                suggestions.add(createSuggestion("看你今天心情不错呀！"));
            }
            if (pet.getEnergy() != null && pet.getEnergy() < 20)
            {
                suggestions.add(createSuggestion("你看起来好疲惫，快去休息吧"));
            }
            else if (pet.getEnergy() != null && pet.getEnergy() < 30)
            {
                suggestions.add(createSuggestion("累了就去睡一会儿吧"));
            }
            if (pet.getCleanliness() != null && pet.getCleanliness() < 20)
            {
                suggestions.add(createSuggestion("我来给你洗个澡吧~"));
            }
            else if (pet.getCleanliness() != null && pet.getCleanliness() < 30)
            {
                suggestions.add(createSuggestion("该给你洗洗澡啦~"));
            }
            if (pet.getHealth() != null && pet.getHealth() < 40)
            {
                suggestions.add(createSuggestion("你看起来不太舒服，我来照顾你"));
            }
            if (pet.getLevel() != null && pet.getLevel() >= 5)
            {
                suggestions.add(createSuggestion("你越来越厉害了呢！"));
            }
        }

        return success(suggestions);
    }

    @GetMapping("/pets/{userId}")
    public AjaxResult pets(@PathVariable Long userId)
    {
        return success(new HashMap<>());
    }

    @GetMapping("/reply/{chatId}")
    public AjaxResult reply(@PathVariable Long chatId)
    {
        return success(new HashMap<>());
    }

    private Map<String, String> createSuggestion(String text)
    {
        Map<String, String> suggestion = new HashMap<>();
        suggestion.put("text", text);
        return suggestion;
    }

    private String getMoodDescription(VirtualPet pet)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("等级Lv.").append(pet.getLevel() != null ? pet.getLevel() : 1);
        sb.append("，经验值").append(pet.getExperience() != null ? pet.getExperience() : 0);

        if (pet.getHunger() != null && pet.getHunger() < 20)
        {
            sb.append("，非常饥饿");
        }
        else if (pet.getHunger() != null && pet.getHunger() < 40)
        {
            sb.append("，肚子有点饿");
        }
        else
        {
            sb.append("，吃饱饱的");
        }

        if (pet.getHappiness() != null && pet.getHappiness() > 70)
        {
            sb.append("，心情很好");
        }
        else if (pet.getHappiness() != null && pet.getHappiness() < 30)
        {
            sb.append("，心情低落");
        }

        if (pet.getEnergy() != null && pet.getEnergy() < 20)
        {
            sb.append("，很疲惫");
        }

        if (pet.getCleanliness() != null && pet.getCleanliness() < 30)
        {
            sb.append("，身上脏脏的");
        }

        if (pet.getHealth() != null && pet.getHealth() < 40)
        {
            sb.append("，身体不太舒服");
        }

        return sb.toString();
    }
}
