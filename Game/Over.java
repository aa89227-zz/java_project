package Game;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.ImageIcon;//圖片


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class Over extends JPanel {
    static private JTextArea textArea = new JTextArea();
    public Over(){
        setLayout(null);
		ImageIcon background = new ImageIcon("board.png");
        JLabel bgLabel = new JLabel(background);      // 把背景圖顯示在Label中
		bgLabel.setBounds(-15, -10, 500, 800);
		
		ImageIcon logo = new ImageIcon("gameover.png");
        JLabel lb = new JLabel(logo); //logo
		lb.setBounds(13, -15, 450, 300);
		

		textArea.setBounds(100, 220, 400, 370);
		textArea.setFont(new Font("標楷體", Font.PLAIN, 30));
		textArea.setColumns(10);
		textArea.setEditable(false);
        textArea.setForeground(Color.white);
		textArea.setOpaque(false);

		JButton btn_staet = new JButton("重新開始");
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

		JButton btnReturn = new JButton("結束遊戲");
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
				System.exit(0);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnReturn.setForeground(Color.white);
			}
		});
		add(btn_staet);
		add(textArea);
		add(btnReturn);
        add(lb);
		add(bgLabel);
		showscore();
	}
	

    static void write(int k){
        try
		{
			// read file content from file
			StringBuffer sb = new StringBuffer("");
			FileReader reader = new FileReader("score.txt");
			BufferedReader br = new BufferedReader(reader);

			String str = null;
			String text = null;
			Boolean change = false;
			while ((str = br.readLine()) != null){
				if(Integer.parseInt(str) < k && !change){
					change=true;
					if(text!=null)
						sb.append(text + "\n");
					sb.append(k + "\n");
					System.out.println(k);
				}
				else{
					if(text != null)
						sb.append(text + "\n");
				}
				text = str;
			}
			if(Integer.parseInt(text) > k){
				sb.append(text + "\n");
				sb.append(k + "\n");
			}
			else{
				sb.append(text + "\n");
			}
			
			br.close();
			reader.close();
			//System.out.println(sb);
			// write string to file
			FileWriter writer = new FileWriter("score.txt");
			BufferedWriter bw = new BufferedWriter(writer);
			bw.write(sb.toString());

			bw.close();
			writer.close();
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
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
                //System.out.println(str);
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
    public static void main(String[] args){
        //Over s=new Over();
       // s.showscore();
        JFrame frm = new JFrame();
		frm.setSize(500,800);
		//frm.add(s);
		frm.setVisible(true);  // 顯示視窗
    }
}