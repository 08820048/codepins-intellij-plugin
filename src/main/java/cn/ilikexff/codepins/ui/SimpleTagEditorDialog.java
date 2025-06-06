package cn.ilikexff.codepins.ui;

import cn.ilikexff.codepins.core.PinEntry;
import cn.ilikexff.codepins.core.PinStorage;
import cn.ilikexff.codepins.i18n.CodePinsBundle;
// 移除不再需要的许可证服务引用
import cn.ilikexff.codepins.utils.IconUtil;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.ui.JBColor;
import com.intellij.ui.components.JBList;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.components.JBTextField;
import com.intellij.util.ui.JBUI;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

/**
 * 简化版标签编辑对话框
 */
public class SimpleTagEditorDialog extends DialogWrapper {

    private final DefaultListModel<String> tagsModel;
    private final JBList<String> tagsList;
    private final JBTextField newTagField;
    private final List<String> currentTags;
    private JPanel tagActionPanel; // 标签操作面板
    private Set<String> existingTags; // 已有的所有标签
    private JPanel existingTagsPanel; // 已有标签面板

    public SimpleTagEditorDialog(Project project, PinEntry pinEntry) {
        super(project);
        this.currentTags = new ArrayList<>(pinEntry.getTags());

        // 获取所有已有标签
        this.existingTags = PinStorage.getAllTags();

        // 创建标签列表模型和列表
        tagsModel = new DefaultListModel<>();
        for (String tag : currentTags) {
            tagsModel.addElement(tag);
        }

        tagsList = new JBList<>(tagsModel);
        tagsList.setCellRenderer(new TagCellRenderer());

        // 创建新标签输入框
        newTagField = new JBTextField();

        // 创建已有标签面板
        existingTagsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));

        // 设置对话框标题和尺寸
        setTitle(CodePinsBundle.message("tag.dialog.title"));
        init();
    }

    @Override
    protected @Nullable JComponent createCenterPanel() {
        // 创建主面板
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(JBUI.Borders.empty(10));

        // 插件现在完全免费开源，不再需要检查标签限制信息

        // 添加说明标签
        String instructionText = CodePinsBundle.message("tag.dialog.instruction");

        JLabel instructionLabel = new JLabel(instructionText);
        instructionLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        instructionLabel.setBorder(JBUI.Borders.emptyBottom(10));

        // 移除升级链接点击事件

        mainPanel.add(instructionLabel);

        // 添加当前标签标签
        JLabel currentTagsLabel = new JLabel(CodePinsBundle.message("tag.current.label"));
        currentTagsLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainPanel.add(currentTagsLabel);

        // 添加标签列表
        tagsList.setFixedCellHeight(50); // 设置固定行高，使标签显示更加一致

        JBScrollPane scrollPane = new JBScrollPane(tagsList);
        scrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
        scrollPane.setPreferredSize(new Dimension(400, 200));
        scrollPane.setMinimumSize(new Dimension(400, 200));
        scrollPane.setMaximumSize(new Dimension(Short.MAX_VALUE, 200));
        scrollPane.setBorder(BorderFactory.createLineBorder(JBColor.border(), 1));
        mainPanel.add(scrollPane);

        // 创建标签操作面板
        tagActionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton editButton = new JButton(CodePinsBundle.message("tag.dialog.edit"));
        tagActionPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        tagActionPanel.setBorder(JBUI.Borders.empty(8, 0, 8, 0));

        // 删除按钮
        JButton removeButton = new JButton(CodePinsBundle.message("tag.dialog.delete"));
        removeButton.setIcon(IconUtil.loadIcon("/icons/trash.svg", getClass()));
        removeButton.addActionListener(e -> removeSelectedTags());
        removeButton.setFocusPainted(false);

        // 编辑按钮
        editButton.setIcon(IconUtil.loadIcon("/icons/edit.svg", getClass()));
        editButton.addActionListener(e -> editSelectedTag());
        editButton.setFocusPainted(false);

        tagActionPanel.add(removeButton);
        tagActionPanel.add(editButton);
        mainPanel.add(tagActionPanel);

        // 添加双击编辑功能
        tagsList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    editSelectedTag();
                }
            }
        });

        // 添加分隔符
        JSeparator separator = new JSeparator();
        separator.setAlignmentX(Component.LEFT_ALIGNMENT);
        separator.setMaximumSize(new Dimension(Short.MAX_VALUE, 1));
        separator.setForeground(JBColor.border());
        mainPanel.add(Box.createVerticalStrut(5));
        mainPanel.add(separator);
        mainPanel.add(Box.createVerticalStrut(15));

        // 添加已有标签区域
        JLabel existingTagsLabel = new JLabel(CodePinsBundle.message("tag.dialog.existing"));
        existingTagsLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainPanel.add(existingTagsLabel);

        // 创建已有标签滚动面板
        JScrollPane existingTagsScrollPane = new JBScrollPane(existingTagsPanel);
        existingTagsScrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
        existingTagsScrollPane.setPreferredSize(new Dimension(400, 100));
        existingTagsScrollPane.setMinimumSize(new Dimension(400, 100));
        existingTagsScrollPane.setMaximumSize(new Dimension(Short.MAX_VALUE, 100));
        existingTagsScrollPane.setBorder(BorderFactory.createLineBorder(JBColor.border(), 1));

        // 填充已有标签
        populateExistingTags();

        mainPanel.add(existingTagsScrollPane);
        mainPanel.add(Box.createVerticalStrut(15));

        // 添加新标签标签
        JLabel newTagLabel = new JLabel(CodePinsBundle.message("tag.dialog.add"));
        newTagLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainPanel.add(newTagLabel);

        // 添加输入框和按钮面板
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.X_AXIS));
        inputPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        // 设置输入框提示
        newTagField.putClientProperty("JTextField.placeholderText", CodePinsBundle.message("tag.dialog.input.placeholder"));
        newTagField.setPreferredSize(new Dimension(300, 32));
        newTagField.setMaximumSize(new Dimension(Short.MAX_VALUE, 32));
        newTagField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(JBColor.border(), 1),
                JBUI.Borders.empty(5, 8)
        ));
        newTagField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    addNewTag();
                }
            }
        });

        JButton addButton = new JButton(CodePinsBundle.message("tag.dialog.add"));
        addButton.setIcon(IconUtil.loadIcon("/icons/plus.svg", getClass()));
        addButton.addActionListener(e -> addNewTag());
        addButton.setFocusPainted(false);

        inputPanel.add(newTagField);
        inputPanel.add(Box.createHorizontalStrut(8));
        inputPanel.add(addButton);

        mainPanel.add(inputPanel);

        return mainPanel;
    }

    /**
     * 添加新标签
     */
    private void addNewTag() {
        String tag = newTagField.getText().trim();
        if (!tag.isEmpty() && !tagsModel.contains(tag)) {
            // 插件现在完全免费，移除所有标签限制检查

            // 添加标签
            tagsModel.addElement(tag);
            currentTags.add(tag);
            newTagField.setText("");

            // 添加按钮动画效果
            AnimationUtil.buttonClickEffect((JButton)tagActionPanel.getComponent(1));

            // 选中新添加的标签
            int newIndex = tagsModel.size() - 1;
            tagsList.setSelectedIndex(newIndex);
            tagsList.ensureIndexIsVisible(newIndex);

            // 刷新已有标签面板
            populateExistingTags();
        }
    }

    /**
     * 删除选中的标签
     */
    private void removeSelectedTags() {
        int[] indices = tagsList.getSelectedIndices();
        if (indices.length > 0) {
            // 删除按钮动画效果
            AnimationUtil.buttonClickEffect((JButton)tagActionPanel.getComponent(0));

            for (int i = indices.length - 1; i >= 0; i--) {
                String tag = tagsModel.getElementAt(indices[i]);
                tagsModel.remove(indices[i]);
                currentTags.remove(tag);
            }

            // 刷新已有标签面板
            populateExistingTags();
        }
    }

    /**
     * 编辑选中的标签
     */
    private void editSelectedTag() {
        int selectedIndex = tagsList.getSelectedIndex();
        if (selectedIndex >= 0) {
            // 编辑按钮动画效果
            // AnimationUtil.buttonClickEffect((JButton)tagActionPanel.getComponent(1));

            String oldTag = tagsModel.getElementAt(selectedIndex);
            String newTag = JOptionPane.showInputDialog(this.getRootPane(),
                    CodePinsBundle.message("tag.dialog.edit"), oldTag);

            if (newTag != null && !newTag.trim().isEmpty() && !newTag.equals(oldTag)) {
                // 检查新标签是否已存在
                if (!tagsModel.contains(newTag.trim())) {
                    // 替换旧标签
                    currentTags.remove(oldTag);
                    currentTags.add(newTag.trim());

                    tagsModel.removeElement(oldTag);
                    tagsModel.addElement(newTag.trim());

                    // 选中新标签
                    tagsList.setSelectedValue(newTag.trim(), true);

                    // 刷新已有标签面板
                    populateExistingTags();
                } else {
                    JOptionPane.showMessageDialog(this.getRootPane(),
                            CodePinsBundle.message("tag.dialog.exists", newTag.trim()),
                            CodePinsBundle.message("tag.dialog.exists.title"),
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        }
    }

    /**
     * 获取当前标签列表
     */
    public List<String> getTags() {
        return currentTags;
    }

    /**
     * 填充已有标签面板
     */
    private void populateExistingTags() {
        existingTagsPanel.removeAll();

        // 如果没有已有标签，显示提示信息
        if (existingTags.isEmpty()) {
            JLabel emptyLabel = new JLabel(CodePinsBundle.message("tag.dialog.empty"));
            emptyLabel.setForeground(JBColor.GRAY);
            existingTagsPanel.add(emptyLabel);
            return;
        }

        // 从当前标签中移除已经添加的标签
        Set<String> availableTags = new HashSet<>(existingTags);
        availableTags.removeAll(currentTags);

        // 如果过滤后没有可用标签，显示提示信息
        if (availableTags.isEmpty()) {
            JLabel emptyLabel = new JLabel(CodePinsBundle.message("tag.dialog.all"));
            emptyLabel.setForeground(JBColor.GRAY);
            existingTagsPanel.add(emptyLabel);
            return;
        }

        // 为每个已有标签创建一个可点击的标签组件
        for (String tag : availableTags) {
            JLabel tagLabel = createClickableTagLabel(tag);
            existingTagsPanel.add(tagLabel);
        }

        existingTagsPanel.revalidate();
        existingTagsPanel.repaint();
    }

    /**
     * 创建可点击的标签标签
     */
    private JLabel createClickableTagLabel(String tag) {
        // 创建标签
        JLabel tagLabel = new JLabel(tag);
        tagLabel.setFont(tagLabel.getFont().deriveFont(Font.PLAIN, 12f));

        // 生成标签颜色
        Color tagColor = ColorUtil.getTagColor(tag);
        Color bgColor = new JBColor(
                new Color(tagColor.getRed(), tagColor.getGreen(), tagColor.getBlue(), 40),
                new Color(tagColor.getRed() / 4, tagColor.getGreen() / 4, tagColor.getBlue() / 4, 80)
        );

        // 设置文本颜色
        boolean isDark = ColorUtil.isDark(tagColor);
        Color textColor = isDark ?
                new JBColor(new Color(40, 40, 40), new Color(220, 220, 220)) :
                new JBColor(new Color(40, 40, 40), new Color(220, 220, 220));

        // 设置边框颜色
        Color borderColor = new JBColor(
                new Color(tagColor.getRed(), tagColor.getGreen(), tagColor.getBlue(), 80),
                new Color(tagColor.getRed() / 2, tagColor.getGreen() / 2, tagColor.getBlue() / 2, 100)
        );

        // 设置标签样式
        tagLabel.setForeground(textColor);
        tagLabel.setBackground(bgColor);
        tagLabel.setOpaque(true);
        tagLabel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(borderColor, 1),
                JBUI.Borders.empty(5, 8)
        ));
        tagLabel.setIcon(IconUtil.loadIcon("/icons/tag-small.svg", getClass()));
        tagLabel.setIconTextGap(6);

        // 添加鼠标事件
        tagLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                // 鼠标悬停效果
                tagLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
                tagLabel.setBackground(tagColor.brighter());
                tagLabel.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(borderColor.darker(), 1),
                        JBUI.Borders.empty(5, 8)
                ));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // 恢复正常效果
                tagLabel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                tagLabel.setBackground(bgColor);
                tagLabel.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(borderColor, 1),
                        JBUI.Borders.empty(5, 8)
                ));
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                // 点击添加标签
                if (!tagsModel.contains(tag)) {
                    tagsModel.addElement(tag);
                    currentTags.add(tag);

                    // 选中新添加的标签
                    int newIndex = tagsModel.size() - 1;
                    tagsList.setSelectedIndex(newIndex);
                    tagsList.ensureIndexIsVisible(newIndex);

                    // 刷新已有标签面板
                    populateExistingTags();
                }
            }
        });

        return tagLabel;
    }

    /**
     * 标签单元格渲染器
     */
    private static class TagCellRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            // 创建标签单元格面板
            JPanel tagPanel = new JPanel(new BorderLayout());

            // 获取标签文本
            String tag = value.toString();

            // 生成标签颜色
            Color tagColor = ColorUtil.getTagColor(tag);
            Color textColor;
            Color borderColor;
            Color bgColor;

            if (isSelected) {
                // 选中状态使用更深的颜色
                bgColor = tagColor.darker();
                textColor = new JBColor(Color.WHITE, Color.WHITE);
                borderColor = new JBColor(new Color(100, 100, 100), new Color(100, 100, 100));
            } else {
                // 非选中状态使用半透明背景
                bgColor = new JBColor(
                        new Color(tagColor.getRed(), tagColor.getGreen(), tagColor.getBlue(), 40),
                        new Color(tagColor.getRed() / 4, tagColor.getGreen() / 4, tagColor.getBlue() / 4, 80)
                );
                boolean isDark = ColorUtil.isDark(tagColor);
                textColor = isDark ?
                        new JBColor(new Color(40, 40, 40), new Color(220, 220, 220)) :
                        new JBColor(new Color(40, 40, 40), new Color(220, 220, 220));
                borderColor = new JBColor(
                        new Color(tagColor.getRed(), tagColor.getGreen(), tagColor.getBlue(), 80),
                        new Color(tagColor.getRed() / 2, tagColor.getGreen() / 2, tagColor.getBlue() / 2, 100)
                );
            }

            // 设置面板样式
            tagPanel.setBackground(bgColor);
            tagPanel.setOpaque(true);

            // 创建左侧的颜色指示器
            JPanel colorIndicator = new JPanel();
            colorIndicator.setBackground(tagColor);
            colorIndicator.setPreferredSize(new Dimension(4, 0));
            tagPanel.add(colorIndicator, BorderLayout.WEST);

            // 创建标签文本和图标
            JPanel contentPanel = new JPanel(new BorderLayout(5, 0));
            contentPanel.setOpaque(false);

            JLabel iconLabel = new JLabel(IconUtil.loadIcon("/icons/tag-small.svg", TagCellRenderer.class));
            iconLabel.setForeground(textColor);

            JLabel textLabel = new JLabel(tag);
            textLabel.setForeground(textColor);
            textLabel.setFont(textLabel.getFont().deriveFont(Font.BOLD, 12f));

            contentPanel.add(iconLabel, BorderLayout.WEST);
            contentPanel.add(textLabel, BorderLayout.CENTER);

            tagPanel.add(contentPanel, BorderLayout.CENTER);

            // 设置边框
            tagPanel.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(borderColor, 1),
                    JBUI.Borders.empty(8, 10, 8, 10)
            ));

            return tagPanel;
        }


    }

    /**
     * 颜色工具类
     */
    private static class ColorUtil {
        /**
         * 判断颜色是否为深色
         */
        public static boolean isDark(Color color) {
            // 使用人眼对不同颜色的敏感度公式
            double brightness = (0.299 * color.getRed() + 0.587 * color.getGreen() + 0.114 * color.getBlue()) / 255;
            return brightness < 0.5;
        }

        /**
         * 根据标签名称生成颜色
         */
        public static Color getTagColor(String tag) {
            // 使用标签的哈希值生成颜色，确保相同标签有相同颜色
            int hash = tag.hashCode();

            // 现代感强的色调
            Color[] lightPalette = {
                    new Color(79, 195, 247),  // 浅蓝
                    new Color(129, 199, 132), // 浅绿
                    new Color(255, 183, 77),  // 浅橙
                    new Color(240, 98, 146),  // 浅红
                    new Color(149, 117, 205), // 浅紫
                    new Color(224, 224, 224), // 浅灰
                    new Color(77, 208, 225),  // 浅青
                    new Color(174, 213, 129)  // 浅黄绿
            };

            Color[] darkPalette = {
                    new Color(41, 121, 255),  // 深蓝
                    new Color(67, 160, 71),   // 深绿
                    new Color(255, 152, 0),   // 深橙
                    new Color(233, 30, 99),   // 深红
                    new Color(103, 58, 183),  // 深紫
                    new Color(117, 117, 117), // 深灰
                    new Color(0, 172, 193),   // 深青
                    new Color(104, 159, 56)   // 深黄绿
            };

            int index = Math.abs(hash) % lightPalette.length;
            return new JBColor(lightPalette[index], darkPalette[index]);
        }
    }
}
