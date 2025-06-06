---
title: CodePins 插件使用教程
---

# 📌 CodePins – IntelliJ 插件使用教程

本教程旨在详细介绍 CodePins 插件的所有功能，帮助您快速上手并高效使用。  
（注：本文档中示例截图请替换为您的图床链接。）

## ✨ 插件简介

CodePins 是一款轻量级图钉系统插件，支持将任意代码行或代码块"📌"固定为标记，并支持备注、跳转、搜索、删除、清空、导入导出、分享、排序、拖放排序、标签筛选、复制、刷新、动画效果、自定义菜单、空状态面板、图钉计数、自定义 UI 等丰富功能，适用于临时笔记、错误定位与 TODO 跟踪。

## 💫 版本说明

### 免费版功能
- 基础图钉功能（添加、删除、搜索、跳转）
- 基础标签功能（每个图钉最多 5 个标签，最多创建 10 种不同标签）
- 基础备注功能
- 基础排序功能
- 基础导入导出功能
- 基础代码预览功能

### 专业版功能
- 无限标签数量
- 高级分享功能（支持多种分享方式）
- 高级排序功能（支持多种排序方式）
- 高级导入导出功能（支持更多格式）
- 高级代码预览功能（支持更多文件类型）
- 自定义 UI 主题
- 批量操作功能
- 团队协作功能
- 优先技术支持

## 🚀 快速开始

1. 安装插件（通过 JetBrains Marketplace 或手动安装 .zip 文件）。
2. 在任意代码中右键点击 → 📌 Pin This Line（或使用快捷键 Alt+Shift+P）即可添加图钉。
3. 打开左侧工具栏" CodePins"查看图钉列表。
4. 双击跳转、右键操作、顶部支持搜索与清空、拖放排序、标签筛选、排序、批量删除、导入导出、分享、复制、刷新、动画效果、自定义菜单、空状态面板、图钉计数、自定义 UI 等。
5. 使用标签（如 #bug、#todo）组织、筛选图钉。

## 📌 图钉的添加

- 在任意代码行上，右键点击 → 📌 Pin This Line（或使用快捷键 Alt+Shift+P）即可添加图钉。
- 弹出对话框，输入备注（可选）后，点击"确定"即可。  
  ![添加图钉示例](https://your-image-url-add-pin.png)  
- 如果勾选"代码块"选项，则图钉将固定为代码块（即多行）图钉，并自动保存偏移量范围。

## ✏️ 图钉备注与标签

- 在添加图钉时，弹出对话框输入备注（可选），备注支持任意文本，也可添加标签（例如 #bug、#todo、#important 等）。
- 在"图钉"面板中，右键点击图钉 → "修改备注"可重新编辑备注；右键 → "编辑标签"可打开标签编辑对话框，添加、删除或修改标签。  
  ![编辑备注与标签示例](https://your-image-url-edit-note-tag.png)  
- 标签可用于快速筛选图钉，在"图钉"面板中，点击"标签筛选"面板，勾选对应标签即可。

## 🔄 拖放排序

- 在"图钉"面板中，图钉列表支持拖放排序。  
  ![拖放排序示例](https://your-image-url-drag-drop.png)  
- 拖动图钉到目标位置，松开鼠标即可完成排序，并自动更新持久化存储。

## 🔍 搜索图钉

- 在"图钉"面板顶部，使用搜索框输入关键字（支持按文件路径、备注内容或标签）即可实时过滤图钉列表。  
  ![搜索图钉示例](https://your-image-url-search.png)  
- 搜索框支持模糊匹配，输入关键字后，图钉列表将实时更新。

## 🧭 图钉跳转

- 在"图钉"面板中，双击任意图钉即可跳转到对应代码行（或代码块）。  
  ![跳转示例](https://your-image-url-jump.png)  
- 也可使用快捷键 Alt+Shift+LEFT（上一个图钉）或 Alt+Shift+RIGHT（下一个图钉）在图钉间导航。

## 🗑 删除与清空

- 在"图钉"面板中，右键点击图钉 → "删除本钉"即可删除该图钉。  
  ![删除图钉示例](https://your-image-url-delete.png)  
- 在"图钉"面板中，点击顶部"清空图钉"按钮，弹出确认对话框，确认后即可清空所有图钉记录。  
  ![清空图钉示例](https://your-image-url-clear.png)  
- 在"图钉"面板中，选中多个图钉后，点击"批量删除"按钮，弹出确认对话框，确认后即可批量删除选中的图钉。

## 📋 导入与导出

- 在"图钉"面板中，点击顶部"导出图钉"按钮，弹出导出对话框，选择导出文件路径后即可将图钉导出到文件。  
  ![导出图钉示例](https://your-image-url-export.png)  
- 在"图钉"面板中，点击顶部"导入图钉"按钮，弹出导入对话框，选择导入文件后即可从文件导入图钉。  
  ![导入图钉示例](https://your-image-url-import.png)  
- 导入导出功能支持团队共享图钉数据。

## 📤 分享图钉

- 在"图钉"面板中，选中一个或多个图钉后，点击"分享图钉"按钮，弹出分享对话框，选择分享方式（例如复制到剪贴板、生成分享链接等）即可分享图钉。  
  ![分享图钉示例](https://your-image-url-share.png)  
- 分享功能支持团队协作，方便快速传递图钉信息。

## 🔄 排序图钉

- 在"图钉"面板中，点击顶部"排序"按钮，弹出排序菜单，选择排序方式（例如"按创建时间（新→旧）"、"按创建时间（旧→新）"、"按文件名"、"按备注"）即可对图钉进行排序。  
  ![排序图钉示例](https://your-image-url-sort.png)  
- 排序后，图钉列表将实时更新，并弹出提示消息。

## 📋 复制图钉

- 在"图钉"面板中，右键点击图钉 → "复制图钉"即可复制该图钉（复制后，新图钉将复制原图钉的所有属性，并添加到图钉列表中）。  
  ![复制图钉示例](https://your-image-url-copy.png)  
- 复制功能适用于快速创建相似图钉。

## 🔄 刷新图钉

- 在"图钉"面板中，右键点击图钉 → "刷新"即可刷新该图钉（刷新后，图钉将重新读取文件内容，并更新 RangeMarker）。  
  ![刷新图钉示例](https://your-image-url-refresh.png)  
- 刷新功能适用于文件内容变更后，确保图钉位置正确。

## 🎨 动画效果与自定义 UI

- 在"图钉"面板中，图钉列表支持动画效果（例如拖放排序、点击、刷新等操作时，图钉列表将播放动画效果）。  
  ![动画效果示例](https://your-image-url-animation.png)  
- 图钉列表采用自定义的现代卡片式渲染器，支持悬停效果、自定义菜单、空状态面板、图钉计数、自定义 UI 等，提升用户体验。

## 💾 持久化与自动保存

- 图钉数据（包括备注、标签、排序、代码块偏移量等）将自动持久化保存（项目级），重启 IDE 后仍可恢复。  
  ![持久化示例](https://your-image-url-persist.png)  
- 持久化存储采用 XML 文件（默认位于项目 .idea 目录下 codepins.xml），方便备份与迁移。

## 📋 常见问题

- **Q: 如何快速添加图钉？**  
  A: 在任意代码行上，右键点击 → 📌 Pin This Line（或使用快捷键 Alt+Shift+P）即可。  
- **Q: 免费版和专业版有什么区别？**  
  A: 免费版提供基础功能，包括图钉的添加、删除、搜索、跳转等核心功能。专业版提供更多高级功能，如无限标签、高级分享、自定义 UI 等。具体区别请参考"版本说明"部分。  
- **Q: 如何升级到专业版？**  
  A: 在插件设置中点击"升级到专业版"按钮，按照提示完成升级流程。  
- **Q: 如何批量删除图钉？**  
  A: 在"图钉"面板中，选中多个图钉后，点击"批量删除"按钮，弹出确认对话框，确认后即可批量删除。  
- **Q: 如何导入或导出图钉？**  
  A: 在"图钉"面板中，点击顶部"导出图钉"或"导入图钉"按钮，弹出对话框，选择文件路径后即可。  
- **Q: 如何分享图钉？**  
  A: 在"图钉"面板中，选中一个或多个图钉后，点击"分享图钉"按钮，弹出分享对话框，选择分享方式即可。注意：部分分享功能需要专业版。  
- **Q: 如何排序图钉？**  
  A: 在"图钉"面板中，点击顶部"排序"按钮，弹出排序菜单，选择排序方式即可。  
- **Q: 如何复制图钉？**  
  A: 在"图钉"面板中，右键点击图钉 → "复制图钉"即可。  
- **Q: 如何刷新图钉？**  
  A: 在"图钉"面板中，右键点击图钉 → "刷新"即可。  
- **Q: 如何拖放排序图钉？**  
  A: 在"图钉"面板中，拖动图钉到目标位置，松开鼠标即可。  
- **Q: 如何搜索图钉？**  
  A: 在"图钉"面板顶部，使用搜索框输入关键字即可。  
- **Q: 如何跳转到图钉？**  
  A: 在"图钉"面板中，双击图钉即可跳转，或使用快捷键 Alt+Shift+LEFT/RIGHT 在图钉间导航。  
- **Q: 如何删除或清空图钉？**  
  A: 在"图钉"面板中，右键点击图钉 → "删除本钉"即可删除；点击顶部"清空图钉"按钮，弹出确认对话框，确认后即可清空。  
- **Q: 如何编辑图钉备注与标签？**  
  A: 在"图钉"面板中，右键点击图钉 → "修改备注"或"编辑标签"即可。  
- **Q: 如何查看代码块图钉？**  
  A: 在"图钉"面板中，右键点击代码块图钉 → "查看代码块"即可弹出代码预览对话框。  
- **Q: 如何切换"图钉"面板显示状态？**  
  A: 使用快捷键（例如 Alt+Shift+P）或点击 IDE 左侧工具栏" CodePins"即可。  
- **Q: 如何自定义"图钉"面板 UI？**  
  A: 专业版用户可以在设置中自定义 UI 主题。免费版用户可以使用默认主题。  
- **Q: 如何持久化保存图钉数据？**  
  A: 图钉数据（包括备注、标签、排序、代码块偏移量等）将自动持久化保存（项目级），重启 IDE 后仍可恢复。  
- **Q: 如何备份或迁移图钉数据？**  
  A: 持久化存储采用 XML 文件（默认位于项目 .idea 目录下 codepins.xml），方便备份与迁移。  

## 💡 使用技巧

1. **标签管理**
   - 使用有意义的标签（如 #bug、#todo、#important）来组织图钉
   - 标签支持搜索，可以快速找到相关图钉
   - 专业版用户可以使用无限标签，更好地组织代码

2. **快捷键使用**
   - Alt+Shift+P：添加图钉
   - Alt+Shift+LEFT/RIGHT：在图钉间导航
   - 熟练使用快捷键可以显著提高效率

3. **代码块标记**
   - 选中多行代码后添加图钉，可以标记整个代码块
   - 代码块图钉会显示行号范围，方便定位

4. **搜索技巧**
   - 支持按文件路径、备注内容、标签进行搜索
   - 使用标签前缀（如 #）可以快速筛选特定类型的图钉

5. **团队协作**
   - 使用导入导出功能在团队成员间共享图钉
   - 专业版支持更多团队协作功能

## 📞 技术支持

如果您在使用过程中遇到任何问题，或有任何建议，请通过以下方式联系我们：

- 在 JetBrains Marketplace 插件页面提交反馈
- 发送邮件至我们的支持邮箱

我们会尽快回复您的问题。 