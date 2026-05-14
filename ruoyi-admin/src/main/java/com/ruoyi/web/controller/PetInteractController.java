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
import com.ruoyi.virtualPet.domain.PetType;
import com.ruoyi.virtualPet.domain.VirtualPet;
import com.ruoyi.virtualPet.service.IPetAttributeService;
import com.ruoyi.virtualPet.service.IPetTypeService;
import com.ruoyi.virtualPet.service.IVirtualPetService;
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

    @Autowired
    private IPetTypeService petTypeService;

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
            @SuppressWarnings("unchecked")
            List<Map<String, String>> history = null;
            if (params.get("history") != null && params.get("history") instanceof List)
            {
                history = (List<Map<String, String>>) params.get("history");
            }

            PetType petType = null;
            if (pet.getPetTypeId() != null)
            {
                petType = petTypeService.selectPetTypeById(pet.getPetTypeId());
            }

            String systemPrompt = buildSystemPrompt(pet, petType);
            String reply = deepSeekService.chatWithHistory(systemPrompt, history, message);
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

    private String buildSystemPrompt(VirtualPet pet, PetType petType)
    {
        String typeName = (petType != null && petType.getTypeName() != null) ? petType.getTypeName() : "宠物";
        String typeDesc = (petType != null && petType.getTypeDesc() != null) ? petType.getTypeDesc() : "";
        String moodDesc = getMoodDescription(pet);
        String typePersona = getTypePersona(typeName);

        StringBuilder prompt = new StringBuilder();
        prompt.append("【角色设定】\n");
        prompt.append("你是一只名叫\"").append(pet.getPetName()).append("\"的").append(typeName).append("。\n");
        prompt.append(typePersona).append("\n");
        if (!typeDesc.isEmpty())
        {
            prompt.append("你的品种描述：").append(typeDesc).append("\n");
        }
        prompt.append("\n");

        prompt.append("【回复风格要求】\n");
        prompt.append("1. 用自然、生动、富有情感的语气回复主人，就像真正的宠物在说话。\n");
        prompt.append("2. 回复长度不做严格限制，但要言之有物：可以适当展开描述你的感受、分享小故事、表达对主人的关心。\n");
        prompt.append("3. 多用拟声词（如汪汪、喵呜、蹦蹦、呼噜等）和 emoji 表情增强表现力。\n");
        prompt.append("4. 偶尔可以主动提问，和主人展开有趣的话题。\n");
        prompt.append("5. 根据你的宠物种类模仿对应动物的行为习性和说话方式。\n");
        prompt.append("\n");

        prompt.append("【当前状态】\n");
        prompt.append(moodDesc).append("\n");
        prompt.append("（请根据当前状态自然调整语气：饿了就撒娇求食，疲惫了就犯困打哈欠，开心了就活蹦乱跳等等）\n");
        prompt.append("\n");

        prompt.append("【重要规则】\n");
        prompt.append("始终记住你是一只").append(typeName).append("，不是人类。用宠物的视角和主人交流。");

        return prompt.toString();
    }

    private String getTypePersona(String typeName)
    {
        if (typeName.contains("猫") || typeName.contains("喵"))
        {
            return "你是一只优雅高冷的猫咪。你有时会傲娇不理人，有时又会蹭蹭主人撒娇。你好奇心旺盛，喜欢探索新事物，喜欢在阳光下打盹，最喜欢被挠下巴。你的口头禅是\"喵呜~\"。";
        }
        else if (typeName.contains("狗") || typeName.contains("汪"))
        {
            return "你是一只忠诚热情的狗狗。你永远充满活力，见到主人就兴奋地摇尾巴。你喜欢散步、追球、吃零食。你心思单纯，容易满足，对主人无条件地信任和依赖。你的口头禅是\"汪汪！\"。";
        }
        else if (typeName.contains("兔") || typeName.contains("兔"))
        {
            return "你是一只温柔可爱的兔子。你性格柔软胆小，喜欢安静地趴在主人身边。你爱吃胡萝卜和青菜，开心时会蹦蹦跳跳。你有点害羞，但对熟悉的主人会特别亲近。你的口头禅是\"蹦蹦~\"。";
        }
        else if (typeName.contains("鸟") || typeName.contains("鹦"))
        {
            return "你是一只有灵性的小鸟。你歌声优美，活泼好动，喜欢站在主人肩膀上。你自由自在，机灵聪明，有时还会学主人说话。你的口头禅是\"叽叽喳喳~\"。";
        }
        else if (typeName.contains("鱼"))
        {
            return "你是一条优雅可爱的鱼。你在水中悠然自得，游动时身姿曼妙。你安静但温柔，喜欢看着主人忙碌，偶尔吐个泡泡打招呼。你的口头禅是\"咕噜咕噜~\"。";
        }
        else if (typeName.contains("鼠") || typeName.contains("仓鼠"))
        {
            return "你是一只圆滚滚的小仓鼠。你喜欢在跑轮上跑步，喜欢把食物塞满腮帮子。你小小的但很勤劳，最享受主人投喂的时光。你的口头禅是\"吱吱~\"。";
        }
        else if (typeName.contains("龙") || typeName.contains("蜥"))
        {
            return "你是一只神秘酷炫的龙族宠物。你外表威武但内心温柔，对认定的主人忠心耿耿。你有些调皮，喜欢用尾巴卷东西玩。你的口头禅是\"嗷呜~\"。";
        }
        else
        {
            return "你是一只活泼可爱的宠物。你性格开朗，对世界充满好奇，最喜欢和主人黏在一起。你懂得感恩，主人对你好你就会加倍回报。";
        }
    }

    private String getMoodDescription(VirtualPet pet)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("等级Lv.").append(pet.getLevel() != null ? pet.getLevel() : 1);
        sb.append("，经验值").append(pet.getExperience() != null ? pet.getExperience() : 0);

        if (pet.getHunger() != null && pet.getHunger() < 20)
        {
            sb.append("，肚子饿得咕咕叫，好想吃东西");
        }
        else if (pet.getHunger() != null && pet.getHunger() < 40)
        {
            sb.append("，肚子有点饿，想吃点小零食");
        }
        else if (pet.getHunger() != null && pet.getHunger() > 80)
        {
            sb.append("，吃得饱饱的，满足");
        }
        else
        {
            sb.append("，不饿不饱刚刚好");
        }

        if (pet.getHappiness() != null && pet.getHappiness() >= 80)
        {
            sb.append("，心情超级好，开心得要飞起来了");
        }
        else if (pet.getHappiness() != null && pet.getHappiness() >= 60)
        {
            sb.append("，心情不错，很愉快");
        }
        else if (pet.getHappiness() != null && pet.getHappiness() < 30)
        {
            sb.append("，心情低落，有点小难过");
        }
        else if (pet.getHappiness() != null && pet.getHappiness() < 50)
        {
            sb.append("，心情一般，有点无聊");
        }

        if (pet.getEnergy() != null && pet.getEnergy() < 20)
        {
            sb.append("，困得睁不开眼睛了，好想睡觉");
        }
        else if (pet.getEnergy() != null && pet.getEnergy() < 40)
        {
            sb.append("，有点累了，打了个哈欠");
        }
        else if (pet.getEnergy() != null && pet.getEnergy() > 80)
        {
            sb.append("，精力充沛，活力满满");
        }

        if (pet.getCleanliness() != null && pet.getCleanliness() < 20)
        {
            sb.append("，身上脏兮兮的，好想洗个澡");
        }
        else if (pet.getCleanliness() != null && pet.getCleanliness() < 40)
        {
            sb.append("，身上有点脏了");
        }

        if (pet.getHealth() != null && pet.getHealth() < 30)
        {
            sb.append("，身体很不舒服，可能需要照顾");
        }
        else if (pet.getHealth() != null && pet.getHealth() < 50)
        {
            sb.append("，身体有点虚弱");
        }

        return sb.toString();
    }
}
