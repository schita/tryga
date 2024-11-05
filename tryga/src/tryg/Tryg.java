package tryg;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.util.Random;
import javax.swing.*;
//import sun.audio.*;

/* @author Vito */
public class Tryg {

    static int s = 0, m = 0;
    static String percorso = System.getProperty("user.dir") + "\\tryga\\src\\image"; //ressettare persorso nel caso in cui viene aseguito su un altro dispositivo

    public static void main(String[] args) {      
        //System.out.println("ok:" + percorso);
        Random R = new Random();
        JFrame finestra = new JFrame();
        JLabel ifinestra = new JLabel();
        JLabel ideath = new JLabel("GAME OVER", SwingConstants.CENTER);
        JLabel bird1 = new JLabel();
        JLabel bird2 = new JLabel("");
        JLabel ball = new JLabel();
        JLabel egg = new JLabel();
        JLabel timer = new JLabel("00:00", SwingConstants.CENTER);
        JLabel score = new JLabel("0", SwingConstants.RIGHT);
        JLabel dscore = new JLabel();
        JLabel heart1 = new JLabel();
        JLabel heart2 = new JLabel();
        JLabel heart3 = new JLabel();
        JPanel bunner = new JPanel();
        JPanel life = new JPanel();
        JButton button = new JButton("EXIT");
        Rectangle Rball = new Rectangle();
        Rectangle Rbird1 = new Rectangle();
        Rectangle Rbird2 = new Rectangle();
        Rectangle Regg = new Rectangle();
        
        Ellipse2D.Double eli = new Ellipse2D.Double();
                
        Toolkit tool = Toolkit.getDefaultToolkit();
        Image img = tool.getImage(percorso+"\\cursor.png");
        Cursor cursor = tool.createCustomCursor(img, ifinestra.getLocation(), "cursor");
        
        finestra.setSize(tool.getScreenSize());
        finestra.setLayout(null);
        finestra.setUndecorated(true);
        finestra.setAlwaysOnTop(true);
        finestra.setResizable(false);
        finestra.setCursor(cursor);
        finestra.add(ifinestra);
        finestra.add(ideath);
        finestra.add(bunner);
        ideath.setSize(finestra.getSize());
        ideath.setFont(new Font("Arial", Font.BOLD, 100));
        ideath.add(dscore);
        ideath.add(button);
        ideath.setVisible(false);
        ifinestra.setIcon(new ImageIcon(percorso + "\\tree.jpg"));
        ifinestra.setSize(finestra.getSize());
        ifinestra.setLocation(0, 150);
        ifinestra.add(ball);
        ifinestra.add(egg);
        ifinestra.add(bird1);
        ifinestra.add(bird2);

        life.setBounds(0, 0, 400, 150);
        life.setLayout(null);
        life.setOpaque(true);
        life.add(heart1);
        life.add(heart2);
        life.add(heart3);
        bunner.setBounds(0, 0, (int) tool.getScreenSize().getWidth(), 150);
        bunner.setOpaque(true);
        bunner.setBackground(Color.green);
        bunner.setLayout(null);
        bunner.add(timer);
        bunner.add(score);
        bunner.add(life);
        score.setBounds((int) tool.getScreenSize().getWidth() - 200, 0, 210, 150);
        score.setFont(new Font("Arial", Font.BOLD, 100));
        score.setForeground(Color.orange);
        score.setOpaque(true);
        score.setBackground(Color.yellow);
        dscore.setBounds((int) tool.getScreenSize().getWidth() / 2 - 100, (int) tool.getScreenSize().getHeight() / 2 + 50, 500, 150);
        dscore.setFont(new Font("Arial", Font.BOLD, 100));
        dscore.setForeground(Color.lightGray);
        timer.setBounds((int) tool.getScreenSize().getWidth() / 2 - 200, 0, 375, 150);
        timer.setFont(new Font("Time New Rome", Font.BOLD, 100));
        timer.setForeground(Color.gray);
        timer.setOpaque(true);
        timer.setBackground(Color.green);
        button.setBounds(dscore.getX(),dscore.getY()+150, 100, 50);
        heart1.setBounds(0, 10, 130, 118);
        heart2.setBounds(135, 10, 130, 118);
        heart3.setBounds(270, 10, 130, 118);
        heart1.setIcon(new ImageIcon(percorso + "\\heart.png"));
        heart2.setIcon(new ImageIcon(percorso + "\\heart.png"));
        heart3.setIcon(new ImageIcon(percorso + "\\heart.png"));
        ball.setSize(140, 140);
        ball.setIcon(new ImageIcon(percorso + "\\king.png"));
        bird1.setSize(156, 95);
        bird2.setSize(156, 95);
        egg.setIcon(new ImageIcon(percorso + "\\egg.png"));
        egg.setBounds(500, 300, 68, 64);
        Rball.setSize(140, 140);
        Rbird1.setSize(75, 95);
        Rbird2.setSize(75, 95);
        Regg.setBounds(egg.getBounds());
        
        //Playsound sound = new Playsound(percorso+"\\music");
        
        Timer t = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                s++;
                if (s > 59) {
                    s = 0;
                    m++;
                }
                if (s < 10) {
                    timer.setText("0" + String.valueOf(m) + ":0" + String.valueOf(s));
                } else {
                    timer.setText("0" + String.valueOf(m) + ":" + String.valueOf(s));
                }
            }
        });
        
        t.start();
        finestra.setVisible(true);

        ifinestra.addMouseMotionListener(new MouseMotionListener() {
            @Override public void mouseDragged(MouseEvent e) {}

            @Override
            public void mouseMoved(MouseEvent e) {
                ball.setLocation(e.getX() - 70, e.getY() - 70);
                Rball.setLocation(e.getX()-70, e.getY()-70);
                eli.setFrame(Rball);
                if (eli.intersects(Regg)) {
                    egg.setLocation(R.nextInt((int) tool.getScreenSize().getWidth() - 200), R.nextInt((int) tool.getScreenSize().getHeight() - 250));
                    Regg.setLocation(egg.getLocation());
                    score.setText(String.valueOf(Integer.parseInt(score.getText()) + 1));
                }
            }
        });
        
        boolean b = true;
        int h = 3;
        while (b) {
            for (int v = R.nextInt(10) + 45, i = -150, r = R.nextInt((int) tool.getScreenSize().getHeight() - 250), j = (int) tool.getScreenSize().getWidth(), s = R.nextInt((int) tool.getScreenSize().getHeight() - 250); i < (int) tool.getScreenSize().getWidth(); i+=v, j-=v) {
                bird1.setLocation(bird1.getX(), bird1.getY()+45);
                bird1.setIcon(new ImageIcon(percorso+"\\bird 1.1.png"));
                bird2.setLocation(bird2.getX(), bird2.getY()+45);
                bird2.setIcon(new ImageIcon(percorso+"\\bird 2.1.png"));
                try {Thread.sleep(60);} catch (Exception e) {};
                bird1.setLocation(bird1.getX(), bird1.getY()-45);
                bird1.setIcon(new ImageIcon(percorso+"\\bird 1_5.png"));
                bird2.setLocation(bird2.getX(), bird2.getY()-45);
                bird2.setIcon(new ImageIcon(percorso+"\\bird 2_5.png"));
                try {Thread.sleep(60);} catch (Exception e) {};
                bird1.setLocation(i, r);
                Rbird1.setLocation(bird1.getX() + 75, bird1.getY());
                bird2.setLocation(j, s);
                Rbird2.setLocation(bird2.getLocation());
                if (Rbird1.intersects(Rball) || Rbird2.intersects(Rball)) {
                    i = -150; j = (int) tool.getScreenSize().getWidth();
                    bird1.setLocation(i, r);
                    bird2.setLocation(j, s);
                    life.setBackground(Color.red);
                    h--;
                    if (h == 2) {heart3.setVisible(false);}
                    else if (h == 1) {heart2.setVisible(false);} 
                    else {b = false; ideath.setVisible(!b);bunner.setVisible(b);ifinestra.setVisible(b);dscore.setText(score.getText() + " " + timer.getText());finestra.setCursor(Cursor.DEFAULT_CURSOR); break;}
                    try {Thread.sleep(1500);} catch (Exception e) {}
               } else {life.setBackground(Color.blue);}}
        }
       button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                finestra.setVisible(false);
                //sound.Stop();
                System.exit(0);
            }
        });
        
    }//main    
      
}//classe
