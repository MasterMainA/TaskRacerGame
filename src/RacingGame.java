import javax.swing.*;
import java.awt.*;

public class RacingGame extends JFrame {
    private StartMenuPanel startMenuPanel;
    private ChooseCarPanel chooseCarPanel;
    private GameplayPanel gameplayPanel;


    public RacingGame() {
        this.startMenuPanel = new StartMenuPanel();
        getContentPane().add(startMenuPanel);
    } 

    public void changePanel(JPanel newPanel) {
        Container contentPane = getContentPane();
        contentPane.removeAll();
        contentPane.add(newPanel);
        contentPane.revalidate();
        contentPane.repaint();
    }

    public static void main(String[] args) {
        RacingGame window = new RacingGame();
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setSize(1200, 825);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}
