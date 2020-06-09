package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.ImageIcon;//圖片


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;

public class Score extends JPanel {
    private static JTextArea textArea = new JTextArea();
    public Score(){
        setLayout(null);
		ImageIcon background = new ImageIcon("board.png");
        JLabel bgLabel = new JLabel(background);      // 把背景圖顯示在Label中
		bgLabel.setBounds(-15, -10, 500, 800);
		
		ImageIcon logo = new ImageIcon("score_logo.png");
        JLabel lb = new JLabel(logo); //logo
		lb.setBounds(13, -15, 450, 300);
		

		textArea.setBounds(100, 220, 400, 370);
		textArea.setFont(new Font("標楷體", Font.PLAIN, 30));
		textArea.setColumns(10);
		textArea.setEditable(false);
        textArea.setForeground(Color.white);
		textArea.setOpaque(false);

		JButton btn_staet = new JButton("返回");
		btn_staet.setFont(new Font("微軟正黑體", Font.BOLD, 28));
		btn_staet.setBounds(300, 610, 146, 48);
        btn_staet.setForeground(Color.white);
		btn_staet.setBackground(Color.BLACK);
		btn_staet.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btn_staet.setForeground(new Color(204, 204, 51));
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
				btn_staet.setForeground(Color.white);
			}
		});

		add(btn_staet);
		add(textArea);
        add(lb);
		add(bgLabel);
		showscore();
	}
	
    static void showscore(){
        try
		{
			// read file content from file
			StringBuffer sb = new StringBuffer("");

			FileReader reader = new FileReader("score.txt");
			BufferedReader br = new BufferedReader(reader);

			String str = null;
            String text="";
            int count=0;
			while ((str = br.readLine()) != null && count != 10){
                count++;
				sb.append(str + "\n");
				if(count<10)
					text = text + "#  " + count + "　　　　　　" + str + "\n";
				else
					text = text + "# " + count + "　　　　　　" + str + "\n";
			}
            textArea.setText(text);
			br.close();
			reader.close();
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
        }
    }
}