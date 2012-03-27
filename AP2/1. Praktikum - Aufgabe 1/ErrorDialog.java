

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This class implements a simple Error Dialog.
 * 
 * @author Erich Ehses
 */
@SuppressWarnings("serial")
public class ErrorDialog extends JDialog {

    /**
     * This method creates a ErrorDialog with the given parent dialog
     * and title.
     *
     * @param message The title to display in the dialog.
     */
    public ErrorDialog(String message) {
        super((JFrame) null, "Error");
        setModal(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        JLabel messageLabel = new JLabel(message);
        messageLabel.setForeground(new Color(0xc00000));
        buttonPanel.add(messageLabel);
        getContentPane().add(buttonPanel, BorderLayout.NORTH);
        
        Action okAction = new AbstractAction("Ok") {
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
            }
        };
        getContentPane().add(new JButton(okAction), BorderLayout.SOUTH);
        pack();
        setVisible(true);
    }
}