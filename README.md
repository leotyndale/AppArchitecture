# AppArchitecture — 移动端架构工程实践指南与随书源码

[![Stars](https://img.shields.io/github/stars/leotyndale/AppArchitecture?style=flat-square&color=f59e0b)](https://github.com/leotyndale/AppArchitecture/stargazers)
[![Book](https://img.shields.io/badge/Book-%E7%A7%BB%E5%8A%A8%E5%BC%80%E5%8F%91%E6%9E%B6%E6%9E%84%E8%AE%BE%E8%AE%A1%E5%AE%9E%E6%88%98-f59e0b?style=flat-square&logo=bookstack&logoColor=white)](https://blog.imuxuan.com/archives/116)
[![License](https://img.shields.io/badge/License-MIT-22c55e?style=flat-square)](LICENSE)
[![Blog](https://img.shields.io/badge/Blog-ForDeer-0ea5e9?style=flat-square)](https://blog.imuxuan.com/)
[![Website](https://img.shields.io/badge/Website-imuxuan.com-10b981?style=flat-square)](https://imuxuan.com/)
[![English](https://img.shields.io/badge/Docs-English-334155?style=flat-square)](README_EN.md)

> **版权与独立性声明（Copyright & Independence Notice）**：
> 本仓库包含两部分内容：
> 1. **`Chapter*` 目录**：《移动开发架构设计实战》（电子工业出版社，ISBN `9787121375071`）官方配套随书工程源码；
> 2. **`playbook/` 目录**：作者于 2026 年全新独立撰写的开源工程实战指南（完全原创，**不包含、不摘录、不替代**出版图书正文内容），按 [MIT License](LICENSE) 开源。如需系统学习原书完整章节与理论体系，请通过下方官方正版渠道购买或前往高校图书馆借阅。

<table>
  <tr>
    <td>
      <a href="https://item.jd.com/12730926.html">
        <img src="https://blog.imuxuan.com/wp-content/uploads/2019/08/901574650976_.pic_.jpg" width="180" alt="《移动开发架构设计实战》" />
      </a>
    </td>
    <td>
      <b>📖《移动开发架构设计实战》随书源码 &amp; 2026 移动端架构开源指南</b><br /><br />
      <ul>
        <li>🛒 正版购买：<a href="https://item.jd.com/12730926.html">京东自营 / 移动开发架构设计实战</a></li>
        <li>⭐ 豆瓣评分与书评：<a href="https://book.douban.com/subject/34872142/">移动开发架构设计实战</a></li>
        <li>📝 勘误与随书说明：<a href="https://blog.imuxuan.com/archives/116">ForDeer 博客</a></li>
        <li>🌐 作者博客：<a href="https://blog.imuxuan.com/">ForDeer</a> · 个人站点：<a href="https://imuxuan.com/">沐暄公益网</a></li>
        <li>🏛️ 学术与馆藏：清华大学、北京大学、中国科学院大学、浙江大学等高校图书馆馆藏 · 河北经贸大学硕士培养方案必读书目（详见下方明细）</li>
      </ul>
    </td>
  </tr>
</table>

---

## 🧭 2026 移动端架构开源工程指南（`playbook/`）

面向 Android、iOS、KMP 与 Flutter 团队的全新开源架构决策手册（与 AI 编程助手技能库 [`mobile-agent-skills`](https://github.com/leotyndale/mobile-agent-skills) 配套使用）：

```mermaid
flowchart LR
    L1["经典客户端分层<br/>MVC · MVP · MVVM"] --> L2["响应式与状态机<br/>Compose/SwiftUI UDF · MVI"]
    L2 --> L3["大规模协同工程<br/>Clean + DDD · :api/:impl 模块化"]
    L3 --> L4["2026 端云协同架构<br/>KMP/Flutter · 端侧 AI · 性能稳定门禁"]
```

| 指南文档 | 核心解决的问题 | 适用场景 |
| --- | --- | --- |
| [**01 · 架构选型决策矩阵**](playbook/01-architecture-decision-matrix.md) | MVC / MVP / MVVM / MVI(UDF) / Clean+DDD 横向对比、ACL 防腐层与模块拆分阈值 | 技术方案评审、老项目重构 |
| [**02 · 2026 跨平台技术选型**](playbook/02-cross-platform-2026.md) | Pure Native vs KMP vs Flutter(Impeller) vs RN(New Arch) 决策树与桥接规范 | 新业务立项、存量 App 跨端演进 |
| [**03 · AI 时代端云协同架构**](playbook/03-ai-era-mobile-architecture.md) | 端侧小模型（LiteRT/CoreML/AICore）路由门禁、温控/OOM 防护与安全边界 | 端侧 AI 落地、Agentic Mobile UX |
| [**04 · 性能与稳定性上线核验表**](playbook/04-performance-stability-checklist.md) | 冷启动 TTID、60/120Hz 掉帧、内存泄漏、ROM 崩溃治理、R8 与包体积门禁 | 发版前自检、线上稳定性治理 |
| [**05 · 架构决策记录（ADR）模板**](playbook/05-adr-template.md) | 标准化移动端技术选型评审文档（含体积/内存/回滚开关评估表） | 团队技术评审、架构归档 |

---

## 📂 随书章节源码索引（`Chapter*`）

- `Chapter3-MVC` · `Chapter5-MVP` · `Chapter7-MVVM` · `Chapter8-Dagger2` · `Chapter9-RxJava2`
- `Chapter10-AAC` · `Chapter11-CBD` · `Chapter12-Clean` · `Chapter12-Viper` · `Chapter13-Fragmentless` · `Chapter14-Conductor` · `Chapter15-Plugin-Framework`

---

## 🏛️ 高校馆藏与培养方案收录

| 机构 / 高校 | 收录类型 | 馆藏地 / 索书号 / 说明 | 核验入口 |
| --- | --- | --- | --- |
| **清华大学图书馆** | 纸本馆藏（在架） | 西馆（逸夫馆）二层科技图书借阅区（西224）· `TN929.53 L324` | [水木搜索](https://discover.lib.tsinghua.edu.cn/entrance/searchEntrance/sortList?query=any,contains,%E7%A7%BB%E5%8A%A8%E5%BC%80%E5%8F%91%E6%9E%B6%E6%9E%84%E8%AE%BE%E8%AE%A1%E5%AE%9E%E6%88%98&tab=default_tab&search_scope=print_scope&vid=86THU) |
| **北京大学图书馆** | 纸本馆藏 | 北大中心馆 · `TP316/1100` | [未名学术搜索](https://pku.summon.serialssolutions.com/#!/search?ho=t&include.ft.matches=f&l=zh-CN&q=9787121375071) |
| **中国科学院大学图书馆** | 纸本馆藏（2 册在架） | 雁栖三层南区流通阅览书库 · `TN929.53/334` | [中科院文献中心](https://www.las.ac.cn/front/book/detail?id=3104950a74f86ba28b92df1c1b567b62) |
| **浙江大学图书馆** | 纸本馆藏（2 册） | 浙江大学图书馆 · `TN929.53/CL105` | 馆藏编目收录 |
| **河北经贸大学** | 硕士培养方案必读书目 | 计算机科学与技术专业硕士学位研究生培养方案必读书目 | [培养方案原文](https://ggw.100xuexi.com/WebSpecF/EnrolDetail.aspx?id=222953) · [百度百科](https://baike.baidu.com/item/%E7%A7%BB%E5%8A%A8%E5%BC%80%E5%8F%91%E6%9E%B6%E6%9E%84%E8%AE%BE%E8%AE%A1%E5%AE%9E%E6%88%98) |
| **更多高校图书馆** | 纸本馆藏 / 新书推荐 | 湖南大学、南京信息工程大学（`TN929.53/920`）、安徽职业技术大学、浙江工商大学、东北林业大学、泉州师范学院、河南城建学院等 | [安职大新书推荐](https://www.uta.edu.cn/tsg/2020/1030/c1800a96515/page.htm) |

---

## 🔗 作者与关联开源项目

| 项目 | 简介 | Stars |
| --- | --- | --- |
| [**mobile-agent-skills**](https://github.com/leotyndale/mobile-agent-skills) | 面向 Claude Code / Cursor / Codex / Antigravity / Gemini CLI 的移动端 AI 技能库 | ![Stars](https://img.shields.io/github/stars/leotyndale/mobile-agent-skills?style=flat-square&color=f59e0b) |
| [**EnFloatingView**](https://github.com/leotyndale/EnFloatingView) | 无需一切权限的应用内悬浮窗，全 ROM 兼容（含 Compose 混编指南） | ![Stars](https://img.shields.io/github/stars/leotyndale/EnFloatingView?style=flat-square&color=f59e0b) |
| [**EnCrashWhiteList**](https://github.com/leotyndale/EnCrashWhiteList) | Android ROM 崩溃白名单异常处理方案 | ![Stars](https://img.shields.io/github/stars/leotyndale/EnCrashWhiteList?style=flat-square&color=f59e0b) |
| [**EnDebugBubble**](https://github.com/leotyndale/EnDebugBubble) | 应用内悬浮调试气泡入口，实时内存监控 | ![Stars](https://img.shields.io/github/stars/leotyndale/EnDebugBubble?style=flat-square&color=f59e0b) |

## 书籍详情

![移动开发架构设计实战](/pic/img1.jpg)
