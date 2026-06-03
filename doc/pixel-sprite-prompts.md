# 像素风宠物 Sprite 生成 Prompt

## 通用格式说明

生成的图片需要满足：
- 格式：PNG，透明背景
- 布局：5行×4列网格，每行一个动作，每列一帧
- 行序：idle → walk → feed → sleep → play
- 建议帧大小：64×64 或 128×128（生成后在 spriteConfig.js 中配置）

---

## 通用 Prompt 模板

```
Pixel art sprite sheet of a {动物}, 5 rows x 4 columns grid layout, transparent background.

Row 1 (idle): cute standing pose, slight breathing animation across 4 frames
Row 2 (walk): walking animation, 4 frames loop
Row 3 (feed): eating/chewing animation, 4 frames
Row 4 (sleep): sleeping pose with Z animation, 4 frames loop
Row 5 (play): playful bouncing/hopping animation, 4 frames loop

Style: 16-bit retro pixel art, vibrant colors, chibi proportions, expressive eyes.
Each frame in a uniform grid cell. Clean pixel edges, no anti-aliasing.
```

---

## 各宠物 Prompt

### Cat (猫)
```
Pixel art sprite sheet of a cute orange tabby cat, 5 rows x 4 columns grid, transparent background.
Row 1: idle standing, tail swaying, 4 frames
Row 2: walking animation, 4 frames loop
Row 3: eating fish, happy expression, 4 frames
Row 4: curled up sleeping, 4 frames loop
Row 5: playing with yarn ball, bouncing, 4 frames
Style: 16-bit pixel art, chibi style, 64x64 per frame.
```

### Dog (狗)
```
Pixel art sprite sheet of a happy golden retriever puppy, 5 rows x 4 columns grid, transparent background.
Row 1: idle standing, tail wagging, 4 frames
Row 2: walking/trotting animation, 4 frames loop
Row 3: eating bone, 4 frames
Row 4: sleeping on side, 4 frames loop
Row 5: fetching ball, excited bounce, 4 frames
Style: 16-bit pixel art, chibi style, 64x64 per frame.
```

### Rabbit (兔子)
```
Pixel art sprite sheet of a fluffy white rabbit, 5 rows x 4 columns grid, transparent background.
Row 1: idle sitting, nose twitching, 4 frames
Row 2: hopping animation, 4 frames loop
Row 3: munching carrot, 4 frames
Row 4: sleeping curled up, 4 frames loop
Row 5: playful jumping, 4 frames
Style: 16-bit pixel art, chibi style, 64x64 per frame.
```

### Hamster (仓鼠)
```
Pixel art sprite sheet of a chubby hamster, 5 rows x 4 columns grid, transparent background.
Row 1: idle standing, cheeks puffing, 4 frames
Row 2: scurrying animation, 4 frames loop
Row 3: stuffing cheeks with seeds, 4 frames
Row 4: sleeping in nest, 4 frames loop
Row 5: running on wheel, 4 frames
Style: 16-bit pixel art, chibi style, 64x64 per frame.
```

### Dragon (龙)
```
Pixel art sprite sheet of a cute baby dragon, green scales, 5 rows x 4 columns grid, transparent background.
Row 1: idle standing, wings flapping gently, 4 frames
Row 2: walking animation, 4 frames loop
Row 3: breathing small fire, 4 frames
Row 4: sleeping curled with tail, 4 frames loop
Row 5: playful flying hop, 4 frames
Style: 16-bit pixel art, chibi style, 64x64 per frame.
```

### Bird (鸟)
```
Pixel art sprite sheet of a small blue bird, 5 rows x 4 columns grid, transparent background.
Row 1: idle perched, head bobbing, 4 frames
Row 2: hopping/flying forward, 4 frames loop
Row 3: pecking at worm, 4 frames
Row 4: sleeping on branch, 4 frames loop
Row 5: flying in circles, 4 frames
Style: 16-bit pixel art, chibi style, 64x64 per frame.
```

### Fish (鱼)
```
Pixel art sprite sheet of a colorful tropical fish, 5 rows x 4 columns grid, transparent background.
Row 1: idle floating, fins moving, 4 frames
Row 2: swimming animation, 4 frames loop
Row 3: eating food pellets, 4 frames
Row 4: sleeping floating still, 4 frames loop
Row 5: playful darting around, 4 frames
Style: 16-bit pixel art, chibi style, 64x64 per frame.
```

### Panda (熊猫)
```
Pixel art sprite sheet of a cute panda bear, 5 rows x 4 columns grid, transparent background.
Row 1: idle sitting, ear wiggle, 4 frames
Row 2: clumsy walking animation, 4 frames loop
Row 3: eating bamboo, 4 frames
Row 4: sleeping against tree, 4 frames loop
Row 5: rolling on ground, 4 frames
Style: 16-bit pixel art, chibi style, 64x64 per frame.
```

### Fox (狐狸)
```
Pixel art sprite sheet of an orange fox, 5 rows x 4 columns grid, transparent background.
Row 1: idle standing, tail swish, 4 frames
Row 2: trotting animation, 4 frames loop
Row 3: eating berries, 4 frames
Row 4: sleeping curled up, 4 frames loop
Row 5: pouncing playfully, 4 frames
Style: 16-bit pixel art, chibi style, 64x64 per frame.
```

---

## 推荐 AI 工具

| 工具 | 适合 | 说明 |
|------|------|------|
| DALL-E 3 | 高质量单图 | ChatGPT Plus，描述越详细越好 |
| Midjourney | 美术风格好 | 需要 Discord，加 --style raw 参数 |
| Stable Diffusion | 免费本地运行 | 用 pixel art 模型效果更好 |
| Aseprite + AI 插件 | 像素专用 | 可以生成后直接编辑像素 |

## 生成后处理

1. 检查帧对齐：每帧是否在均匀的网格中
2. 透明背景：确认无白色/黑色背景
3. 统一帧大小：裁剪到一致的 64×64 或 128×128
4. 命名：cat.png, dog.png, ...
5. 放到 `public/img/pet/` 目录
6. 修改 `spriteConfig.js` 中的 `frameW`/`frameH`
