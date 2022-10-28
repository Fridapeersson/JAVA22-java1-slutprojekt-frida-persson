package slutprojekt;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Locale;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class GUI {
	
	
	static void createAndPlay() {
		
	JFrame frame = new JFrame("Kalender");
	frame.setSize(1100,700);
	frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
	//bild-icon
	frame.setIconImage(new ImageIcon("kalender.jpg").getImage());
	
	
	JPanel container = new JPanel();
	container.setLayout(new GridLayout());
	
	
		//Skriver ut datum och dag för 7 dagar + panel, textArea, textField, button, label
		for(int i = 1; i <=7; i++) {
			addGroupsOfComponents(getDate(i) + " \n" + DayOfWeek.of(i), container, i);
		}
	
	
		

	
	frame.add(container);
	frame.setVisible(true);
	}
	
	private static void addGroupsOfComponents(String text, JPanel container, int x) {
		LineBorder border = new LineBorder(Color.black, 2, false);
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(4, 1, 0, 0));
		
		JTextArea txtArea = new JTextArea();
		txtArea.setEditable(false);
		txtArea.setBorder(border);
		
		JLabel label = new JLabel(text);
		label.setBorder(border);
		
		JButton btn = new JButton("Add");
		
		JTextField txtField = new JTextField("Add an event");
		txtField.setBorder(border);
		
		
		//btnListener
		addButtonListener(btn, label, txtField, txtArea);
		panel.add(label);
		panel.add(txtArea);
		panel.add(txtField);
		panel.add(btn);
		
		
		//kallar metod chckdate
		checkDate(x, panel);

		container.add(panel);
	}
	//btn listener lägga till label,textField, textArea, btn
	private static void addButtonListener(JButton b, JLabel label, JTextField tf, JTextArea ta) {
		ActionListener bListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				ta.append(tf.getText() + "\n");
				tf.setText("");
				
			}
		};
		b.addActionListener(bListener);
	}
	//Kollar datum och markerar rätt dag
	public static void checkDate(int x, JPanel panel) {     
		LocalDate now = LocalDate.now();
        TemporalField fieldISO = WeekFields.of(Locale.FRANCE).dayOfWeek();
        LocalDate date = now.with(fieldISO, x);
        		
        if(date.equals(LocalDate.now())){
        	panel.setBackground(Color.magenta);
        }
		
	}
	//Metod datum
	public static String getDate(int x) {
        
		 LocalDate now = LocalDate.now();
	        TemporalField fieldISO = WeekFields.of(Locale.FRANCE).dayOfWeek();
	        LocalDate date = now.with(fieldISO, x);
	        String dateString = date.toString();
	        return dateString;
	 }
	
	

}

