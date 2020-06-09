package Game;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.ImageIcon;//圖片

public class Option extends JPanel{
   private ImageIcon img;
   private JLabel lbl;

   public Option() {
      setLayout(null);
      JPanel pn = new JPanel();

      ImageIcon background = new ImageIcon("board.png");
      JLabel bgLabel = new JLabel(background);      // 把背景圖顯示在Label中
      bgLabel.setBounds(-15, -10, 500, 800);

      ImageIcon logo = new ImageIcon("option_logo.png");
      JLabel lb = new JLabel(logo); //logo
      lb.setBounds(13, -15, 450, 300);
      Graphics g = getGraphics();


      img=new ImageIcon("picture/1.png");
      Image image = img.getImage(); // transform it 
      Image newimg = image.getScaledInstance(300, 300,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
      img = new ImageIcon(newimg);  // transform it back

      lbl = new JLabel(img);

      ButtonGroup buttonGroup = new ButtonGroup();

      JRadioButton rdb1 = new JRadioButton("1");
      rdb1.setSelected(true);  // 預選
      buttonGroup.add(rdb1);
      rdb1.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            img=new ImageIcon("picture/1.png");
            Image image = img.getImage(); // transform it 
            Image newimg = image.getScaledInstance(300, 300,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
            img = new ImageIcon(newimg);  // transform it back
            lbl.setIcon(img);
         }
      });


      JRadioButton rdb2 = new JRadioButton("2");
      buttonGroup.add(rdb2);
      rdb2.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            img=new ImageIcon("picture/2.png");
            Image image = img.getImage(); // transform it 
            Image newimg = image.getScaledInstance(300, 300,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
            img = new ImageIcon(newimg);  // transform it back
            lbl.setIcon(img);
         }
      });

      JRadioButton rdb3 = new JRadioButton("3");
      buttonGroup.add(rdb3);
      rdb3.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            img=new ImageIcon("picture/3.png");
            Image image = img.getImage(); // transform it 
            Image newimg = image.getScaledInstance(300, 300,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
            img = new ImageIcon(newimg);  // transform it back
            lbl.setIcon(img);
         }
      });

      JRadioButton rdb4 = new JRadioButton("4");
      buttonGroup.add(rdb4);
      rdb4.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            img=new ImageIcon("picture/4.png");
            Image image = img.getImage(); // transform it 
            Image newimg = image.getScaledInstance(300, 300,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
            img = new ImageIcon(newimg);  // transform it back
            lbl.setIcon(img);
         }
      });
     
      pn.add(rdb1); 
      pn.add(rdb2);
      pn.add(rdb3); 
      pn.add(rdb4);
      pn.add(lbl);
      pn.setBounds(35, 250, 400, 350);
      pn.setOpaque(false);
     
      JButton btn_start = new JButton("遊戲開始");
		btn_start.setFont(new Font("微軟正黑體", Font.BOLD, 28));
      btn_start.setBounds(300, 610, 146, 48);
      btn_start.setForeground(Color.white);
      btn_start.setBackground(Color.BLACK);
      btn_start.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btn_start.setForeground(new Color(204, 204, 51));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
            
         }
			@Override
			public void mouseReleased(MouseEvent e) {
				Game.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btn_start.setForeground(Color.white);
			}
		  });

      JButton btnReturn = new JButton("返回");
		btnReturn.setFont(new Font("微軟正黑體", Font.BOLD, 28));
		btnReturn.setBounds(54, 610, 146, 48);
      btnReturn.setForeground(Color.white);
		btnReturn.setBackground(Color.BLACK);
		btnReturn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnReturn.setForeground(new Color(204, 204, 51));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				Panelextend.card.show(Panelextend.container, "Start");
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnReturn.setForeground(Color.white);
			}
		  });

      add(pn);
      add(btnReturn);
      add(btn_start);
      add(lb);
		add(bgLabel);
   }
}