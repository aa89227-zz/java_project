package Game;
import java.awt.CardLayout;
import javax.swing.*;
import java.awt.event.*;
public class Panelextend extends JFrame{
    public static CardLayout card = new CardLayout();
	public static JPanel container = new JPanel(card);
    
	public Panelextend() {
        setSize(500,800);
        setTitle("雷霆戰機");
        container.add(new Start(), "Start");
        container.add(new Score(), "Score");
        container.add(new Rule(), "Rule");
        container.add(new Option(), "Option");
        container.add(new Over(), "Over");
        add(container);
        card.show(container, "Start");
        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent event) {
            System.exit(0);}
        });
    }

}