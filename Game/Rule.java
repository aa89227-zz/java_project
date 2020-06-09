package Game;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.ImageIcon;//圖片

public class Rule extends JPanel {

	private JTextArea textArea = new JTextArea();

    public Rule() {
		setLayout(null);
		ImageIcon background = new ImageIcon("board.png");
        JLabel bgLabel = new JLabel(background);      // 把背景圖顯示在Label中
		bgLabel.setBounds(-15, -10, 500, 800);
		
		ImageIcon logo = new ImageIcon("rule_logo.png");
        JLabel lb = new JLabel(logo); //logo
		lb.setBounds(13, -15, 450, 300);
		

		textArea.setBounds(100, 250, 400, 350);
		textArea.setFont(new Font("標楷體", Font.PLAIN, 30));
		textArea.setColumns(10);
		textArea.setEditable(false);
		textArea.setText("控制方式：滑鼠移動\n自動射擊\n每1000分給一次大絕(不累加)，空白鍵觸發\n每500分提升一階速度\n生命值歸零時，結束遊戲");
        textArea.setForeground(Color.white);
		textArea.setOpaque(false);

		JButton btnReturn = new JButton("返回");
		btnReturn.setFont(new Font("微軟正黑體", Font.BOLD, 32));
		btnReturn.setBounds(300, 610, 146, 48);
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
			
		add(textArea);
		add(btnReturn);
        add(lb);
		add(bgLabel);
    }
}