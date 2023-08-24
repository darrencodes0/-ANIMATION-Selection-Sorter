import java.awt.BorderLayout;
import javax.swing.JFrame;

public class SelectionSortTester{
    public static void main(String[] args) {

        JFrame frame = new JFrame();

        final int FRAME_WIDTH = 300;
        final int FRAME_HEIGHT = 400;

        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Creates a Graphical User Interface that displays the animation
        final SelectionSortAnimation window = new SelectionSortAnimation();
        frame.add(window, BorderLayout.CENTER);

        frame.setVisible(true);
        window.startAnimation();
    }
}