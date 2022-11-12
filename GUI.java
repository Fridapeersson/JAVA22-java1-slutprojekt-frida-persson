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

	static void guiCalendar() {
		
		JFrame frame = new JFrame("Kalender");
		frame.setSize(1100,700);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		//bild-icon
		frame.setIconImage(new ImageIcon("kalender.jpg").getImage());
	
		JPanel container = new JPanel();
		container.setLayout(new GridLayout());
	
		//Skriver ut datum och dag för 7 dagar + panel, textArea, textField, button, label
		for(int i = 1; i <=7; i++) {
			contentComponents(getDate(i) + DayOfWeek.of(i), container, i);
		}
		
		frame.add(container);
		frame.setVisible(true);
	}
	
	private static void contentComponents(String dateAndDay, JPanel contentContainer, int dayNumber) {
		LineBorder border = new LineBorder(Color.black, 2, false);
		
		//Skapar en panel med GridLayout med 4 rows som innehåller textArea, label, button & textField 
		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(new GridLayout(4, 0, 0, 0));
		contentPanel.setBorder(border);
		
		//textArea som användaren inte kan ändra/ta bort/skriva i
		JTextArea txtArea = new JTextArea();
		txtArea.setEditable(false);

		JLabel dateLabel = new JLabel(dateAndDay);
		JButton btn = new JButton("Add");
		JTextField txtField = new JTextField("Add an event");
		
		
		contentPanel.add(dateLabel);
		contentPanel.add(txtArea);
		contentPanel.add(txtField);
		contentPanel.add(btn);
		
		addButtonListener(btn, txtField, txtArea);
		
		checkDate(dayNumber, contentPanel);

		contentContainer.add(contentPanel);
	}
	
	private static void addButtonListener(JButton btnListenerButton, JTextField btnListenerTextField, JTextArea btnListenerTextArea) {
		ActionListener buttonListener = new ActionListener() {
			//Vad som ska hända när man trycker på "add"
			@Override
			public void actionPerformed(ActionEvent e) {
				//Lägger till texten från btnListenerTextField till btnListenerTextArea och hoppar ner en rad
				btnListenerTextArea.append(btnListenerTextField.getText() + "\n");
				
				//btnListenerTextField "Add event" blir tom efter man lagt till en händelse
				btnListenerTextField.setText("");
			}
		};
		//ActionListener läggs till i knappen
		btnListenerButton.addActionListener(buttonListener);
	}
	
	//Kollar datum och markerar dag
	public static void checkDate(int dayNumber, JPanel showDate) {    
		LocalDate now = LocalDate.now();
        TemporalField dayOfWeek = WeekFields.of(Locale.FRANCE).dayOfWeek();
        LocalDate date = now.with(dayOfWeek, dayNumber);
        		
        if(date.equals(LocalDate.now())){
        	showDate.setBackground(Color.magenta);
        }
	}
	//Metod datum
	public static String getDate(int dayNumber) {
		 LocalDate now = LocalDate.now();
	     TemporalField dayOfWeek = WeekFields.of(Locale.FRANCE).dayOfWeek();
	     LocalDate date = now.with(dayOfWeek, dayNumber);
	     String dateString = date.toString();
	     return dateString;
	 }
}
