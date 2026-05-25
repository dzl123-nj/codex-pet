package com.ruoyi.virtualPet.service;

import java.util.Map;
import com.ruoyi.virtualPet.domain.PetItem;
import com.ruoyi.virtualPet.domain.VirtualPet;

public interface IPetAttributeService
{
    boolean applyTimeDecay(VirtualPet pet);

    Map<String, Object> feed(VirtualPet pet);

    Map<String, Object> clean(VirtualPet pet);

    Map<String, Object> sleep(VirtualPet pet);

    Map<String, Object> wake(VirtualPet pet);

    Map<String, Object> play(VirtualPet pet);

    Map<String, Object> play(VirtualPet pet, long customExp);

    Map<String, Object> chatReward(VirtualPet pet);

    Map<String, Object> useItem(VirtualPet pet, PetItem item);

    void recalculateStatus(VirtualPet pet);

    long calculateExpForLevel(long level);

    void checkLevelUp(VirtualPet pet);
}
