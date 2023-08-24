import java.awt.Graphics2D;
import javax.swing.JComponent;
import java.awt.Graphics;


    public class SelectionSortAnimation extends JComponent {

        public SelectionSortAnimation() {
            int[] values = ArrayUtil.randomNumberGeneratedArray(30, 300);
            sorter = new SelectionSortAlgorithm(values, this);
        }

        public void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D)g;
            sorter.draw(g2);
        }

        public void startAnimation() {
            class AnimationRunnable implements Runnable {
                public void run() {
                    try {
                        sorter.sort();
                    }
                    catch (InterruptedException exception) {}
                }
            }

            Runnable r = new AnimationRunnable();
            Thread t = new Thread(r);
            t.start();
        }

        private SelectionSortAlgorithm sorter;
    }
