package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class DerekUskcs extends JFrame implements ActionListener
{
    JButton  start;

    GameMain myMain;

    public DerekUskcs(GameMain main)
    {
        myMain = main;
        start = new JButton("start game");
        start.addActionListener(this);
        this.add(start);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setTitle("TderekSUckse");
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        myMain.startGame();
        this.dispose();
    }
}
