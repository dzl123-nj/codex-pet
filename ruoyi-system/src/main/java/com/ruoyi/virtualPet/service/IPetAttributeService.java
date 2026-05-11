package com.ruoyi.virtualPet.service;

import java.util.Map;
import com.ruoyi.virtualPet.domain.VirtualPet;

public interface IPetAttributeService
{
    void applyTimeDecay(VirtualPet pet);

    Map<String, Object> feed(VirtualPet pet);

    Map<String, Object> clean(VirtualPet pet);

    Map<String, Object> sleep(VirtualPet pet);

    Map<String, Object> wake(VirtualPet pet);

    Map<String, Object> play(VirtualPet pet);

    Map<String, Object> chatReward(VirtualPet pet);

    void recalculateStatus(VirtualPet pet);

    long calculateExpForLevel(long level);

    void checkLevelUp(VirtualPet pet);
}
