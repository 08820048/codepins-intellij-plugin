package cn.ilikexff.codepins.ui;

import cn.ilikexff.codepins.services.LicenseService;
import cn.ilikexff.codepins.utils.SocialSharingUtil;
import cn.ilikexff.codepins.utils.WatermarkManager;
import com.intellij.openapi.util.IconLoader;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.Messages;
import com.intellij.ui.ColorPanel;
import com.intellij.ui.components.JBLabel;
import com.intellij.ui.components.JBRadioButton;
import com.intellij.util.ui.JBUI;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;

/**
 * 水印设置对话框
 * 用于设置水印选项
 */
public class WatermarkSettingsDialog extends DialogWrapper {

    private final Project project;

    private JRadioButton textWatermarkRadio;
    private JRadioButton noWatermarkRadio;

    private JTextField textField;
    private JComboBox<WatermarkManager.WatermarkPosition> positionComboBox;
    private ColorPanel colorPanel;
    private JSlider opacitySlider;

    private JPanel textPanel;

    /**
     * 构造函数
     *
     * @param project 当前项目
     */
    public WatermarkSettingsDialog(Project project) {
        super(project);
        this.project = project;

        setTitle("水印设置");
        setSize(500, 400);
        init();
    }

    @Override
    protected @Nullable JComponent createCenterPanel() {
        JPanel dialogPanel = new JPanel(new BorderLayout());
        dialogPanel.setBorder(JBUI.Borders.empty(10));

        // 创建水印类型选择面板
        JPanel typePanel = new JPanel(new GridLayout(0, 1));
        typePanel.setBorder(BorderFactory.createTitledBorder("水印类型"));

        // 水印类型选项
        textWatermarkRadio = new JBRadioButton("文本水印");
        noWatermarkRadio = new JBRadioButton("无水印");

        // 默认选择文本水印
        textWatermarkRadio.setSelected(true);

        // 添加到按钮组
        ButtonGroup typeGroup = new ButtonGroup();
        typeGroup.add(textWatermarkRadio);
        typeGroup.add(noWatermarkRadio);

        // 添加到面板
        typePanel.add(textWatermarkRadio);
        typePanel.add(noWatermarkRadio);

        // 创建文本水印设置面板
        textPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        textPanel.setBorder(BorderFactory.createTitledBorder("文本水印设置"));

        // 文本输入
        textPanel.add(new JBLabel("水印文本:"));
        textField = new JTextField("Generated by CodePins - Code Bookmarks");
        textPanel.add(textField);

        // 位置选择
        textPanel.add(new JBLabel("水印位置:"));
        positionComboBox = new JComboBox<>(WatermarkManager.WatermarkPosition.values());
        positionComboBox.setSelectedItem(WatermarkManager.WatermarkPosition.BOTTOM_RIGHT);
        textPanel.add(positionComboBox);

        // 颜色选择
        textPanel.add(new JBLabel("水印颜色:"));
        colorPanel = new ColorPanel();
        colorPanel.setSelectedColor(new Color(128, 128, 128, 128));
        textPanel.add(colorPanel);

        // 透明度滑块
        textPanel.add(new JBLabel("透明度:"));
        opacitySlider = new JSlider(0, 100, 50);
        opacitySlider.setMajorTickSpacing(25);
        opacitySlider.setMinorTickSpacing(5);
        opacitySlider.setPaintTicks(true);
        opacitySlider.setPaintLabels(true);
        textPanel.add(opacitySlider);

        // 添加水印类型选择监听器
        textWatermarkRadio.addActionListener(e -> {
            textPanel.setVisible(true);
        });

        noWatermarkRadio.addActionListener(e -> {
            textPanel.setVisible(false);
        });

        // 插件现在完全免费，移除所有付费功能限制

        // 组装面板
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(typePanel, BorderLayout.NORTH);
        mainPanel.add(textPanel, BorderLayout.CENTER);

        dialogPanel.add(mainPanel, BorderLayout.CENTER);

        return dialogPanel;
    }

    @Override
    protected void doOKAction() {
        try {
            // 检查是否为付费用户
            boolean isPremium = SocialSharingUtil.isPremiumUser();

            // 获取水印类型
            WatermarkManager.WatermarkType type;
            if (textWatermarkRadio.isSelected()) {
                type = WatermarkManager.WatermarkType.TEXT;
            } else if (noWatermarkRadio.isSelected() && isPremium) {
                type = WatermarkManager.WatermarkType.NONE;
            } else {
                // 默认使用文本水印
                type = WatermarkManager.WatermarkType.TEXT;
            }

            // 插件现在完全免费，移除付费功能检查

            // TODO: 保存水印设置到配置

            super.doOKAction();
        } catch (Exception e) {
            Messages.showErrorDialog(
                    project,
                    "保存水印设置失败: " + e.getMessage(),
                    "设置错误"
            );
        }
    }
}
