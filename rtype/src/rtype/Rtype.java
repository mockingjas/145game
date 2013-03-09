package rtype;

import javax.swing.JFrame;

/**
 *
 * @author joyamendoza
 */
public class Rtype extends JFrame {

    public Rtype() {

        add(new Board());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setLocationRelativeTo(null);
        setTitle("R - Type");
        setResizable(false);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Rtype();
    }
}