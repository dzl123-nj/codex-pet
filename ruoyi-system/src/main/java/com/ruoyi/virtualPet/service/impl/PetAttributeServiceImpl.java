package com.ruoyi.virtualPet.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.virtualPet.domain.PetAttributeRecord;
import com.ruoyi.virtualPet.domain.PetItem;
import com.ruoyi.virtualPet.domain.VirtualPet;
import com.ruoyi.virtualPet.mapper.VirtualPetMapper;
import com.ruoyi.virtualPet.service.IPetAttributeRecordService;
import com.ruoyi.virtualPet.service.IPetAttributeService;

@Service
public class PetAttributeServiceImpl implements IPetAttributeService
{
    private static final long MIN_ATTR = 0;
    private static final long MAX_ATTR = 100;

    private static final long HUNGER_DECAY_PER_HOUR = 2;
    private static final long ENERGY_DECAY_PER_HOUR = 3;
    private static final long CLEANLINESS_DECAY_PER_HOUR = 2;
    private static final long HAPPINESS_DECAY_PER_HOUR = 1;
    private static final long HEALTH_DECAY_PER_HOUR = 1;

    private static final long HUNGER_LOW_THRESHOLD = 15;
    private static final long CLEANLINESS_LOW_THRESHOLD = 15;
    private static final long ENERGY_LOW_THRESHOLD = 10;
    private static final long HEALTH_SICK_THRESHOLD = 30;

    private static final long FEED_HUNGER_ADD = 30;
    private static final long FEED_HAPPINESS_ADD = 15;
    private static final long FEED_EXP = 10;

    private static final long CLEAN_ADD = 40;
    private static final long CLEAN_HEALTH_ADD = 5;
    private static final long CLEAN_EXP = 10;

    private static final long SLEEP_ENERGY_ADD = 50;
    private static final long SLEEP_HAPPINESS_ADD = 5;
    private static final long SLEEP_EXP = 5;

    private static final long PLAY_HAPPINESS_ADD = 25;
    private static final long PLAY_ENERGY_COST = 15;
    private static final long PLAY_HUNGER_COST = 5;
    private static final long PLAY_CLEAN_COST = 5;
    private static final long PLAY_EXP = 15;

    private static final long CHAT_HAPPINESS_ADD = 5;
    private static final long CHAT_ENERGY_COST = 2;
    private static final long CHAT_EXP = 8;

    @Autowired
    private VirtualPetMapper virtualPetMapper;

    @Autowired
    private IPetAttributeRecordService petAttributeRecordService;

    @Override
    public boolean applyTimeDecay(VirtualPet pet)
    {
        if (pet.getStatus() == null || pet.getStatus() == 3L)
        {
            return false;
        }

        long now = System.currentTimeMillis();
        long lastUpdate = pet.getUpdateTime() != null ? pet.getUpdateTime().getTime() : now;
        long elapsedMs = now - lastUpdate;
        double elapsedHours = elapsedMs / (1000.0 * 60 * 60);

        if (elapsedMs < 60 * 1000)
        {
            return false;
        }

        if (elapsedHours > 72)
        {
            elapsedHours = 72;
        }

        boolean isSleeping = pet.getStatus() != null && pet.getStatus() == 0L;
        long energyDecay = isSleeping ? 0 : Math.round(ENERGY_DECAY_PER_HOUR * elapsedHours);
        long energyRecover = isSleeping ? Math.round((ENERGY_DECAY_PER_HOUR + 2) * elapsedHours) : 0;
        long healthRecover = isSleeping ? Math.round(HEALTH_DECAY_PER_HOUR * elapsedHours) : 0;

        long oldHunger = pet.getHunger() != null ? pet.getHunger() : 50;
        long oldEnergy = pet.getEnergy() != null ? pet.getEnergy() : 50;
        long oldCleanliness = pet.getCleanliness() != null ? pet.getCleanliness() : 50;
        long oldHappiness = pet.getHappiness() != null ? pet.getHappiness() : 50;
        long oldHealth = pet.getHealth() != null ? pet.getHealth() : 50;

        pet.setHunger(clamp(oldHunger - Math.round(HUNGER_DECAY_PER_HOUR * elapsedHours)));
        pet.setEnergy(clamp(oldEnergy - energyDecay + energyRecover));
        pet.setCleanliness(clamp(oldCleanliness - Math.round(CLEANLINESS_DECAY_PER_HOUR * elapsedHours)));

        long newHunger = pet.getHunger();
        long newEnergy = pet.getEnergy();
        long newCleanliness = pet.getCleanliness();

        double happinessDecay = HAPPINESS_DECAY_PER_HOUR * elapsedHours;
        if (newHunger <= HUNGER_LOW_THRESHOLD)
        {
            happinessDecay += elapsedHours;
        }
        if (newCleanliness <= CLEANLINESS_LOW_THRESHOLD)
        {
            happinessDecay += elapsedHours;
        }
        if (newEnergy <= ENERGY_LOW_THRESHOLD && !isSleeping)
        {
            happinessDecay += elapsedHours;
        }
        pet.setHappiness(clamp(oldHappiness - Math.round(happinessDecay)));

        double healthDecay = isSleeping ? 0 : HEALTH_DECAY_PER_HOUR * elapsedHours;
        if (newHunger <= HUNGER_LOW_THRESHOLD)
        {
            healthDecay += 3 * elapsedHours;
        }
        if (newCleanliness <= CLEANLINESS_LOW_THRESHOLD)
        {
            healthDecay += 2 * elapsedHours;
        }
        if (newEnergy <= ENERGY_LOW_THRESHOLD && !isSleeping)
        {
            healthDecay += elapsedHours;
        }
        if (pet.getStatus() != null && pet.getStatus() == 2L)
        {
            healthDecay += 3 * elapsedHours;
        }
        pet.setHealth(clamp(oldHealth - Math.round(healthDecay) + healthRecover));

        boolean changed = pet.getHunger() != oldHunger || pet.getEnergy() != oldEnergy
            || pet.getCleanliness() != oldCleanliness || pet.getHappiness() != oldHappiness
            || pet.getHealth() != oldHealth;

        if (changed)
        {
            boolean wasSick = pet.getStatus() != null && pet.getStatus() == 2L;
            recordChange(pet, "hunger", oldHunger, pet.getHunger(), "自然衰减");
            recordChange(pet, "energy", oldEnergy, pet.getEnergy(), isSleeping ? "睡眠恢复" : "自然衰减");
            recordChange(pet, "cleanliness", oldCleanliness, pet.getCleanliness(), "自然衰减");
            recordChange(pet, "happiness", oldHappiness, pet.getHappiness(), "自然衰减");
            recordChange(pet, "health", oldHealth, pet.getHealth(), wasSick ? "生病衰减" : "自然衰减");
        }

        recalculateStatus(pet);

        if (isSleeping && pet.getEnergy() >= MAX_ATTR)
        {
            pet.setStatus(1L);
        }

        return changed;
    }

    @Override
    public Map<String, Object> feed(VirtualPet pet)
    {
        Map<String, Object> result = new HashMap<>();
        if (pet.getStatus() == 3L)
        {
            result.put("success", false);
            result.put("message", "宠物已经不在了，无法喂食... 😢");
            return result;
        }
        if (pet.getStatus() == 0L)
        {
            result.put("success", false);
            result.put("message", "宠物正在睡觉，不要打扰它哦~ 😴");
            return result;
        }

        long oldHunger = pet.getHunger() != null ? pet.getHunger() : 50;
        if (oldHunger > 90)
        {
            result.put("success", false);
            result.put("message", "我已经吃得很饱啦，肚子圆滚滚的~ 再吃就要撑坏了 🫃");
            result.put("petData", pet);
            return result;
        }

        long oldHappiness = pet.getHappiness() != null ? pet.getHappiness() : 50;
        long oldEnergy = pet.getEnergy() != null ? pet.getEnergy() : 50;
        long oldCleanliness = pet.getCleanliness() != null ? pet.getCleanliness() : 50;
        long oldHealth = pet.getHealth() != null ? pet.getHealth() : 50;
        long oldExp = pet.getExperience() != null ? pet.getExperience() : 0;
        long oldLevel = pet.getLevel() != null ? pet.getLevel() : 1;

        pet.setHunger(clamp(oldHunger + FEED_HUNGER_ADD));
        pet.setHappiness(clamp(oldHappiness + FEED_HAPPINESS_ADD));

        recordChange(pet, "hunger", oldHunger, pet.getHunger(), "喂食");
        recordChange(pet, "happiness", oldHappiness, pet.getHappiness(), "喂食");

        recalculateStatus(pet);
        savePet(pet);

        long newHunger = pet.getHunger();

        String msg;
        if (newHunger >= 80)
        {
            msg = "谢谢你给我喂食！我吃得好饱好满足呀~ 🥰";
        }
        else if (newHunger >= 50)
        {
            msg = "谢谢你给我喂食！嗯~ 好好吃！😋";
        }
        else
        {
            msg = "谢谢你给我喂食！还要再吃一点...还是有点饿 🥺";
        }

        result.put("success", true);
        result.put("message", msg);
        result.put("changes", buildChanges(pet, oldHunger, oldHappiness, oldEnergy, oldCleanliness, oldHealth, 0L, oldLevel));
        result.put("petData", pet);
        return result;
    }

    @Override
    public Map<String, Object> clean(VirtualPet pet)
    {
        Map<String, Object> result = new HashMap<>();
        if (pet.getStatus() == 3L)
        {
            result.put("success", false);
            result.put("message", "宠物已经不在了，无法清洁... 😢");
            return result;
        }
        if (pet.getStatus() == 0L)
        {
            result.put("success", false);
            result.put("message", "宠物正在睡觉，等它醒来再洗吧~ 😴");
            return result;
        }

        long oldHunger = pet.getHunger() != null ? pet.getHunger() : 50;
        long oldCleanliness = pet.getCleanliness() != null ? pet.getCleanliness() : 50;
        long oldHappiness = pet.getHappiness() != null ? pet.getHappiness() : 50;
        long oldEnergy = pet.getEnergy() != null ? pet.getEnergy() : 50;
        long oldHealth = pet.getHealth() != null ? pet.getHealth() : 50;
        long oldExp = pet.getExperience() != null ? pet.getExperience() : 0;
        long oldLevel = pet.getLevel() != null ? pet.getLevel() : 1;

        pet.setCleanliness(clamp(oldCleanliness + CLEAN_ADD));
        pet.setHealth(clamp(oldHealth + CLEAN_HEALTH_ADD));
        pet.setHappiness(clamp(oldHappiness + 5));

        recordChange(pet, "cleanliness", oldCleanliness, pet.getCleanliness(), "清洁");
        recordChange(pet, "health", oldHealth, pet.getHealth(), "清洁");
        recordChange(pet, "happiness", oldHappiness, pet.getHappiness(), "清洁");

        recalculateStatus(pet);
        savePet(pet);

        String msg;
        if (pet.getCleanliness() >= 80)
        {
            msg = "洗完澡浑身香喷喷的！你看我多干净~ ✨🧼";
        }
        else
        {
            msg = "谢谢你帮我洗澡，舒服多啦~ 🛁💕";
        }

        result.put("success", true);
        result.put("message", msg);
        result.put("changes", buildChanges(pet, oldHunger, oldHappiness, oldEnergy, oldCleanliness, oldHealth, 0L, oldLevel));
        result.put("petData", pet);
        return result;
    }

    @Override
    public Map<String, Object> sleep(VirtualPet pet)
    {
        Map<String, Object> result = new HashMap<>();
        if (pet.getStatus() == 3L)
        {
            result.put("success", false);
            result.put("message", "宠物已经不在了... 😢");
            return result;
        }
        if (pet.getStatus() == 0L)
        {
            result.put("success", false);
            result.put("message", "宠物已经在睡觉了哦~ 不要打扰它 💤");
            return result;
        }

        long oldHunger = pet.getHunger() != null ? pet.getHunger() : 50;
        long oldHappiness = pet.getHappiness() != null ? pet.getHappiness() : 50;
        long oldEnergy = pet.getEnergy() != null ? pet.getEnergy() : 50;
        long oldCleanliness = pet.getCleanliness() != null ? pet.getCleanliness() : 50;
        long oldHealth = pet.getHealth() != null ? pet.getHealth() : 50;
        long oldExp = pet.getExperience() != null ? pet.getExperience() : 0;
        long oldLevel = pet.getLevel() != null ? pet.getLevel() : 1;

        pet.setEnergy(clamp(oldEnergy + SLEEP_ENERGY_ADD));
        pet.setHappiness(clamp(oldHappiness + SLEEP_HAPPINESS_ADD));
        pet.setExperience(oldExp + SLEEP_EXP);
        pet.setStatus(0L);

        recordChange(pet, "energy", oldEnergy, pet.getEnergy(), "睡眠");
        recordChange(pet, "happiness", oldHappiness, pet.getHappiness(), "睡眠");

        checkLevelUp(pet);
        savePet(pet);

        String msg;
        long energy = pet.getEnergy();
        if (energy >= 90)
        {
            msg = "精力充沛！睡了一觉感觉好极了~ 🌟";
        }
        else if (energy >= 60)
        {
            msg = "嗯...打了个盹，感觉好多了 😊💤";
        }
        else
        {
            msg = "好困...让我再睡一会儿...Zzz 🌙";
        }

        result.put("success", true);
        result.put("message", msg);
        result.put("changes", buildChanges(pet, oldHunger, oldHappiness, oldEnergy, oldCleanliness, oldHealth, SLEEP_EXP, oldLevel));
        result.put("petData", pet);
        return result;
    }

    @Override
    public Map<String, Object> wake(VirtualPet pet)
    {
        Map<String, Object> result = new HashMap<>();
        if (pet.getStatus() == 3L)
        {
            result.put("success", false);
            result.put("message", "宠物已经不在了... 😢");
            return result;
        }
        if (pet.getStatus() != 0L)
        {
            result.put("success", false);
            result.put("message", "宠物没有在睡觉哦~ 它现在很精神！😊");
            return result;
        }

        pet.setStatus(1L);
        recalculateStatus(pet);
        savePet(pet);

        String msg;
        if (pet.getEnergy() >= 70)
        {
            msg = "伸个懒腰~ 我醒啦！今天精力充沛！🌞✨";
        }
        else
        {
            msg = "嗯...被叫醒了...还想再睡一会儿...😪";
        }

        result.put("success", true);
        result.put("message", msg);
        result.put("petData", pet);
        return result;
    }

    @Override
    public Map<String, Object> play(VirtualPet pet)
    {
        return play(pet, PLAY_EXP);
    }

    @Override
    public Map<String, Object> play(VirtualPet pet, long customExp)
    {
        Map<String, Object> result = new HashMap<>();
        if (pet.getStatus() == 3L)
        {
            result.put("success", false);
            result.put("message", "宠物已经不在了，无法玩耍... 😢");
            return result;
        }
        if (pet.getStatus() == 0L)
        {
            result.put("success", false);
            result.put("message", "宠物正在睡觉，等它醒来再玩吧~ 😴");
            return result;
        }
        if ((pet.getEnergy() != null ? pet.getEnergy() : 0) < PLAY_ENERGY_COST)
        {
            result.put("success", false);
            result.put("message", "宠物太累了，让它休息一下吧~ 😩");
            return result;
        }

        long expToAdd = customExp > 0 ? customExp : PLAY_EXP;

        long oldHunger = pet.getHunger() != null ? pet.getHunger() : 50;
        long oldHappiness = pet.getHappiness() != null ? pet.getHappiness() : 50;
        long oldEnergy = pet.getEnergy() != null ? pet.getEnergy() : 50;
        long oldCleanliness = pet.getCleanliness() != null ? pet.getCleanliness() : 50;
        long oldHealth = pet.getHealth() != null ? pet.getHealth() : 50;
        long oldExp = pet.getExperience() != null ? pet.getExperience() : 0;
        long oldLevel = pet.getLevel() != null ? pet.getLevel() : 1;

        pet.setHappiness(clamp(oldHappiness + PLAY_HAPPINESS_ADD));
        pet.setEnergy(clamp(oldEnergy - PLAY_ENERGY_COST));
        pet.setHunger(clamp(oldHunger - PLAY_HUNGER_COST));
        pet.setCleanliness(clamp(oldCleanliness - PLAY_CLEAN_COST));
        pet.setExperience(oldExp + expToAdd);

        recordChange(pet, "happiness", oldHappiness, pet.getHappiness(), "玩耍");
        recordChange(pet, "energy", oldEnergy, pet.getEnergy(), "玩耍");
        recordChange(pet, "hunger", oldHunger, pet.getHunger(), "玩耍");
        recordChange(pet, "cleanliness", oldCleanliness, pet.getCleanliness(), "玩耍");

        checkLevelUp(pet);
        recalculateStatus(pet);
        savePet(pet);

        String msg;
        if (pet.getHappiness() >= 80)
        {
            msg = "和你一起玩好开心呀！我最喜欢你了！🥰🎾";
        }
        else
        {
            msg = "嘿嘿，玩耍真有趣！还要玩还要玩~ 🎉";
        }

        result.put("success", true);
        result.put("message", msg);
        result.put("changes", buildChanges(pet, oldHunger, oldHappiness, oldEnergy, oldCleanliness, oldHealth, expToAdd, oldLevel));
        result.put("petData", pet);
        return result;
    }

    @Override
    public Map<String, Object> chatReward(VirtualPet pet)
    {
        Map<String, Object> result = new HashMap<>();
        long oldExp = pet.getExperience() != null ? pet.getExperience() : 0;
        long oldHappiness = pet.getHappiness() != null ? pet.getHappiness() : 50;
        long oldEnergy = pet.getEnergy() != null ? pet.getEnergy() : 50;

        pet.setExperience(oldExp + CHAT_EXP);
        pet.setHappiness(clamp(oldHappiness + CHAT_HAPPINESS_ADD));

        if (pet.getStatus() != null && pet.getStatus() != 0L && pet.getStatus() != 3L)
        {
            pet.setEnergy(clamp((pet.getEnergy() != null ? pet.getEnergy() : 50) - CHAT_ENERGY_COST));
        }

        recordChange(pet, "happiness", oldHappiness, pet.getHappiness(), "聊天");
        recordChange(pet, "energy", oldEnergy, pet.getEnergy(), "聊天");

        checkLevelUp(pet);
        savePet(pet);

        result.put("petData", pet);
        return result;
    }

    @Override
    public Map<String, Object> useItem(VirtualPet pet, PetItem item)
    {
        Map<String, Object> result = new HashMap<>();
        if (pet.getStatus() == 3L)
        {
            result.put("success", false);
            result.put("message", "宠物已经不在了，无法使用道具... 😢");
            return result;
        }
        if (pet.getStatus() == 0L)
        {
            result.put("success", false);
            result.put("message", "宠物正在睡觉，等它醒来再用道具吧~ 😴");
            return result;
        }

        long oldHunger = pet.getHunger() != null ? pet.getHunger() : 50;
        long oldHappiness = pet.getHappiness() != null ? pet.getHappiness() : 50;
        long oldEnergy = pet.getEnergy() != null ? pet.getEnergy() : 50;
        long oldCleanliness = pet.getCleanliness() != null ? pet.getCleanliness() : 50;
        long oldHealth = pet.getHealth() != null ? pet.getHealth() : 50;
        long oldExp = pet.getExperience() != null ? pet.getExperience() : 0;
        long oldLevel = pet.getLevel() != null ? pet.getLevel() : 1;

        if (item.getHungerChange() != null)
        {
            pet.setHunger(clamp(oldHunger + item.getHungerChange()));
            recordChange(pet, "hunger", oldHunger, pet.getHunger(), "使用道具-" + item.getItemName());
        }
        if (item.getHappinessChange() != null)
        {
            pet.setHappiness(clamp(oldHappiness + item.getHappinessChange()));
            recordChange(pet, "happiness", oldHappiness, pet.getHappiness(), "使用道具-" + item.getItemName());
        }
        if (item.getEnergyChange() != null)
        {
            pet.setEnergy(clamp(oldEnergy + item.getEnergyChange()));
            recordChange(pet, "energy", oldEnergy, pet.getEnergy(), "使用道具-" + item.getItemName());
        }
        if (item.getCleanlinessChange() != null)
        {
            pet.setCleanliness(clamp(oldCleanliness + item.getCleanlinessChange()));
            recordChange(pet, "cleanliness", oldCleanliness, pet.getCleanliness(), "使用道具-" + item.getItemName());
        }
        if (item.getHealthChange() != null)
        {
            pet.setHealth(clamp(oldHealth + item.getHealthChange()));
            recordChange(pet, "health", oldHealth, pet.getHealth(), "使用道具-" + item.getItemName());
        }
        if (item.getExperienceReward() != null)
        {
            pet.setExperience(oldExp + item.getExperienceReward());
        }

        long expGained = item.getExperienceReward() != null ? item.getExperienceReward() : 0;
        checkLevelUp(pet);
        recalculateStatus(pet);
        savePet(pet);

        result.put("success", true);
        result.put("message", "给" + pet.getPetName() + "使用了" + item.getItemName() + "！效果拔群~ ✨");
        result.put("changes", buildChanges(pet, oldHunger, oldHappiness, oldEnergy, oldCleanliness, oldHealth, expGained, oldLevel));
        result.put("petData", pet);
        return result;
    }

    @Override
    public void recalculateStatus(VirtualPet pet)
    {
        if (pet.getStatus() == 3L)
        {
            return;
        }

        if (pet.getHealth() != null && pet.getHealth() <= 0)
        {
            pet.setStatus(3L);
            pet.setHealth(0L);
            return;
        }

        if (pet.getStatus() == 0L)
        {
            return;
        }

        if (pet.getHunger() != null && pet.getHunger() <= HUNGER_LOW_THRESHOLD)
        {
            pet.setStatus(2L);
            return;
        }
        if (pet.getCleanliness() != null && pet.getCleanliness() <= CLEANLINESS_LOW_THRESHOLD)
        {
            pet.setStatus(2L);
            return;
        }

        long health = pet.getHealth() != null ? pet.getHealth() : 50;
        long hunger = pet.getHunger() != null ? pet.getHunger() : 50;
        long cleanliness = pet.getCleanliness() != null ? pet.getCleanliness() : 50;
        long energy = pet.getEnergy() != null ? pet.getEnergy() : 50;

        double sickChance = 0;
        if (health < 40)
        {
            sickChance = 0.7 + (40 - health) * 0.01;
        }
        else if (health < 60)
        {
            sickChance = 0.15 + (60 - health) * 0.0125;
        }
        else if (health < 80)
        {
            sickChance = (80 - health) * 0.005;
        }

        if (hunger < 30)
        {
            sickChance += 0.1;
        }
        if (cleanliness < 30)
        {
            sickChance += 0.1;
        }
        if (energy < 15)
        {
            sickChance += 0.1;
        }

        sickChance = Math.min(1.0, sickChance);

        long timeSeed = System.currentTimeMillis() / (30 * 60 * 1000);
        Random random = new Random(pet.getId() * 31 + timeSeed);
        double roll = random.nextDouble();

        boolean wasSick = pet.getStatus() != null && pet.getStatus() == 2L;
        if (wasSick)
        {
            if (roll >= sickChance * 1.5)
            {
                pet.setStatus(1L);
            }
        }
        else
        {
            if (roll < sickChance)
            {
                pet.setStatus(2L);
            }
        }
    }

    @Override
    public long calculateExpForLevel(long level)
    {
        return level * 100 + 50;
    }

    @Override
    public void checkLevelUp(VirtualPet pet)
    {
        if (pet.getLevel() == null)
        {
            pet.setLevel(1L);
        }
        if (pet.getExperience() == null)
        {
            pet.setExperience(0L);
        }

        long currentLevel = pet.getLevel();
        long currentExp = pet.getExperience();
        long expNeeded = calculateExpForLevel(currentLevel);

        while (currentExp >= expNeeded && currentLevel < 100)
        {
            currentExp -= expNeeded;
            currentLevel++;
            pet.setLevel(currentLevel);
            expNeeded = calculateExpForLevel(currentLevel);
        }
        pet.setExperience(currentExp);
    }

    private long clamp(long value)
    {
        return Math.max(MIN_ATTR, Math.min(MAX_ATTR, value));
    }

    private void recordChange(VirtualPet pet, String attributeType, long oldValue, long newValue, String reason)
    {
        if (oldValue == newValue)
        {
            return;
        }
        PetAttributeRecord record = new PetAttributeRecord();
        record.setPetId(pet.getId());
        record.setAttributeType(attributeType);
        record.setOldValue(oldValue);
        record.setNewValue(newValue);
        record.setChangeAmount(newValue - oldValue);
        record.setChangeReason(reason);
        record.setChangeTime(new Date());
        petAttributeRecordService.insertPetAttributeRecord(record);
    }

    private void savePet(VirtualPet pet)
    {
        pet.setUpdateTime(new Date());
        virtualPetMapper.updateVirtualPet(pet);
    }

    private Map<String, Long> buildChanges(VirtualPet pet, long hungerOld, long happinessOld, long energyOld,
                                           long cleanlinessOld, long healthOld, long expGained, long levelOld)
    {
        Map<String, Long> changes = new HashMap<>();
        changes.put("hungerOld", hungerOld);
        changes.put("hungerNew", pet.getHunger() != null ? pet.getHunger() : 0);
        changes.put("happinessOld", happinessOld);
        changes.put("happinessNew", pet.getHappiness() != null ? pet.getHappiness() : 0);
        changes.put("energyOld", energyOld);
        changes.put("energyNew", pet.getEnergy() != null ? pet.getEnergy() : 0);
        changes.put("cleanlinessOld", cleanlinessOld);
        changes.put("cleanlinessNew", pet.getCleanliness() != null ? pet.getCleanliness() : 0);
        changes.put("healthOld", healthOld);
        changes.put("healthNew", pet.getHealth() != null ? pet.getHealth() : 0);
        changes.put("expGained", expGained);
        changes.put("levelOld", levelOld);
        changes.put("levelNew", pet.getLevel() != null ? pet.getLevel() : 1);
        return changes;
    }
}
