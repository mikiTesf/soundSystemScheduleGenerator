package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileWriter;
import java.io.IOException;

import controller.SettingInitializer;

class PreferenceForm extends JFrame {
    private JPanel panel1;
    private JCheckBox controlCounterCheckbox;
    private JCheckBox control2ndHallChooserCheckbox;
    private JLabel preferenceDetailsLabel;
    private JLabel currentSettingsLabel;

    PreferenceForm(JFrame frame) {
        setTitle(TitlesAndLabels.PREFERENCES_FRAME_TITLE);
        preferenceDetailsLabel.setText(TitlesAndLabels.PREFERENCES_DETAILS_LABEL);
        currentSettingsLabel.setText(TitlesAndLabels.CURRENT_SETTINGS_LABEL);
        controlCounterCheckbox.setText(TitlesAndLabels.COUNT_PREFERENCE_CHECKBOX_LABEL);
        control2ndHallChooserCheckbox.setText(TitlesAndLabels.HALL2_PREFERENCE_CHECK_BOX_LABEL);

        boolean countAllAppearances       = SettingInitializer.settings.getBoolean(SettingInitializer.KEY_COUNT_FROM_ALL);
        boolean choose2ndHallFrom1stRound = SettingInitializer.settings.getBoolean(SettingInitializer.KEY_CHOOSE_FROM_1ST_ROUND);

        controlCounterCheckbox.setSelected(countAllAppearances);
        control2ndHallChooserCheckbox.setSelected(choose2ndHallFrom1stRound);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.setEnabled(true);
                final boolean COUNT_FROM_ALL_NEW_VALUE        = controlCounterCheckbox.isSelected();
                final boolean CHOOSE_FROM_1ST_ROUND_NEW_VALUE = control2ndHallChooserCheckbox.isSelected();

                SettingInitializer.settings.remove(SettingInitializer.KEY_COUNT_FROM_ALL);
                SettingInitializer.settings.remove(SettingInitializer.KEY_CHOOSE_FROM_1ST_ROUND);
                SettingInitializer.settings.put(SettingInitializer.KEY_COUNT_FROM_ALL, COUNT_FROM_ALL_NEW_VALUE);
                SettingInitializer.settings.put(SettingInitializer.KEY_CHOOSE_FROM_1ST_ROUND, CHOOSE_FROM_1ST_ROUND_NEW_VALUE);

                try (FileWriter writer = new FileWriter(SettingInitializer.settingsFile)) {
                    writer.write(SettingInitializer.settings.toString());
                } catch (IOException _e) { _e.printStackTrace(); }
            }
        });

        setContentPane(panel1);
        setAlwaysOnTop(true);
        pack();
        setResizable(false);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }
}
