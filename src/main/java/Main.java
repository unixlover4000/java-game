import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public final class Main {
    private Main() {
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame window = new JFrame("Coin Chaser");
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setResizable(false);
            window.add(new Game());
            window.pack();
            window.setLocationRelativeTo(null);
            window.setVisible(true);
        });
    }
}
