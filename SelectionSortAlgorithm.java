import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.Color;
import javax.swing.JComponent;

public class SelectionSortAlgorithm{
    public SelectionSortAlgorithm(int[] array, JComponent component) {
        this.array = array;
        this.component = component;
        sortStateLock = new ReentrantLock();
    }

    //sorts the elements in the array
    public void sort() throws InterruptedException {
        for (int i = 0; i < this.array.length - 1; i++) {
            int minPos = minimumPosition(i);
            sortStateLock.lock(); // locks the current thread
            try {
                swap(minPos, i); // swaps two elements with one another
                alreadySorted = i;
            } finally {
                sortStateLock.unlock();
            }
            pause(2); // pauses the animation when a swap occurs
        }
    }

    //Finds minimum element in the array and keeps updating minPos when
    // a value smaller than the previous minimum value is found
    private int minimumPosition(int from) throws InterruptedException {
        int minPos = from;
        for (int i = from + 1; i < this.array.length; i++) {
            sortStateLock.lock();
            try {
                if (this.array[i] < this.array[minPos]) minPos = i;
                markedPosition = i;
            } finally {
                sortStateLock.unlock();
            }
            pause(2);
        }
        return minPos;
    }

    //Swaps values of i & j in the array
    private void swap(int i, int j) {
        int temp = this.array[i];
        this.array[i] = this.array[j];
        this.array[j] = temp;
    }

    //Draws the lines rn
    public void draw(Graphics2D g2) {
        int deltaX = component.getWidth() / this.array.length;
        for (int i = 0; i < this.array.length; i++) {
            if (i == alreadySorted && i != markedPosition && i != this.array.length - i) {
                g2.setColor(Color.BLUE);
            } else if (i == markedPosition) {
                g2.setColor(Color.RED);
            } else {
                g2.setColor(Color.BLACK);
            }
            g2.draw(new Line2D.Double(i * deltaX, 0, i * deltaX, this.array[i])); // draws vertical lines
        }
    }

    //Pauses the animation by sleeping
    public void pause(int steps) throws InterruptedException {
        component.repaint();
        Thread.sleep(steps * DELAY);
    }

    private int[] array;
    private JComponent component;
    private Lock sortStateLock;
    private int markedPosition = -1;
    private int alreadySorted = -1;
    private static final int DELAY = 100;
}
