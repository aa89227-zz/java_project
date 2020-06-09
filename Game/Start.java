package Game;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.ImageIcon;//圖片


public class Start extends JPanel {
    public Start(){
        setLayout(null);
        ImageIcon background = new ImageIcon("board.png");
        JLabel bgLabel = new JLabel(background);      // 把背景圖顯示在Label中
        bgLabel.setBounds(-15, -10, 500, 800);
        ImageIcon logo = new ImageIcon("start_logo.png");
        JLabel lb = new JLabel(logo); //logo
        lb.setBounds(15, 75, 450, 300);

        JButton bt_start = new JButton("開始遊戲");
        bt_start.setForeground(Color.white);
        bt_start.setBackground(Color.BLACK);
        bt_start.setFont(new Font("微軟正黑體", Font.BOLD, 28));
        bt_start.setBounds(177, 375, 146, 48);
        bt_start.addMouseListener(new MouseAdapter() {
          @Override
          public void mouseEntered(MouseEvent e) {
              bt_start.setForeground(new Color(204, 204, 51));
          }
          @Override
          public void mouseClicked(MouseEvent e) {
            bt_start.setForeground(Color.white);
          }
          @Override
          public void mouseReleased(MouseEvent e) {
            Panelextend.card.show(Panelextend.container, "Option");
          }
          @Override
			    public void mouseExited(MouseEvent e) {
            bt_start.setForeground(Color.white);
		    	}
        });

        JButton bt_score = new JButton("積分表");
        bt_score.setForeground(Color.white);
        bt_score.setBackground(Color.BLACK);
        bt_score.setFont(new Font("微軟正黑體", Font.BOLD, 28));
        bt_score.setBounds(177, 437, 146, 48);
        bt_score.addMouseListener(new MouseAdapter() {
          @Override
          public void mouseEntered(MouseEvent e) {
            bt_score.setForeground(new Color(204, 204, 51));
          }
          @Override
          public void mouseClicked(MouseEvent e) {
            bt_score.setForeground(Color.white);
          }
          @Override
          public void mouseReleased(MouseEvent e) {
            Panelextend.card.show(Panelextend.container, "Score");
          }
          @Override
			    public void mouseExited(MouseEvent e) {
            bt_score.setForeground(Color.white);
		    	}
        });

        JButton bt_option = new JButton("遊戲規則");
        bt_option.setForeground(Color.white);
        bt_option.setBackground(Color.BLACK);
        bt_option.setFont(new Font("微軟正黑體", Font.BOLD, 28));
        bt_option.setBounds(177, 499, 146, 48);
        bt_option.addMouseListener(new MouseAdapter() {
          @Override
          public void mouseEntered(MouseEvent e) {
            bt_option.setForeground(new Color(204, 204, 51));
          }
          @Override
          public void mouseClicked(MouseEvent e) {
          }
          @Override
          public void mouseReleased(MouseEvent e) {
            Panelextend.card.show(Panelextend.container, "Rule");
          }
          @Override
			    public void mouseExited(MouseEvent e) {
            bt_option.setForeground(Color.white);
		    	}
        });
        
        JButton bt_exit = new JButton("結束遊戲");
        bt_exit.setForeground(Color.white);
        bt_exit.setBackground(Color.BLACK);
        bt_exit.setFont(new Font("微軟正黑體", Font.BOLD, 28));
        bt_exit.setBounds(177, 561, 146, 48);
        bt_exit.addMouseListener(new MouseAdapter() {
          @Override
          public void mouseEntered(MouseEvent e) {
            bt_exit.setForeground(new Color(204, 204, 51));
          }
          @Override
          public void mouseClicked(MouseEvent e) {
          }
          @Override
          public void mouseReleased(MouseEvent e) {
            System.exit(0);
          }
          @Override
			    public void mouseExited(MouseEvent e) {
            bt_exit.setForeground(Color.white);
		    	}
        });

        add(bt_start, BorderLayout.CENTER);
        add(bt_score, BorderLayout.CENTER);
        add(bt_option, BorderLayout.CENTER);
        add(bt_exit, BorderLayout.CENTER);
        add(lb);
        add(bgLabel);
    }
}