package ui.booking;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

import job.customer.CustomerManager;

import ui.menu.MainMenuFrame;
import factory.CustomerFactory;

@SuppressWarnings("serial")
public class AuthenticationFrame extends JFrame implements ActionListener {

	JPanel container = new JPanel();
	JLabel labelEnterName; 
	JTextField name;
	JButton logButton;
	JButton returnButton;
	
	public AuthenticationFrame() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(300, 175);
		setResizable(false);
		setTitle("Authentification");
		setLocationRelativeTo(null);
		
		initLayout();
		initContainer();
		setContentPane(container);
	}

	private void initLayout() {
		labelEnterName = new JLabel("Veuillez entrer votre nom : ", SwingConstants.CENTER);
		Document modelName = new PlainDocument();
		name = new JTextField(modelName, "", 10);
		logButton = new JButton("Connexion");
		returnButton = new JButton("Retour");
		logButton.addActionListener(this);
		returnButton.addActionListener(this);
	}
	
	private void initContainer() {
		container.setLayout(new GridLayout(4, 1));
		container.add(labelEnterName);
		container.add(name);
		container.add(logButton);
		container.add(returnButton);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
		if (e.getSource() == logButton) {
			if (!name.getText().trim().equals("")) {
				String nameCustomer = name.getText().trim().toUpperCase();
				if (CustomerManager.INSTANCE.getCustomerByName(nameCustomer) != null) {
					this.dispose();
					BookingFrame bookingFrame = new BookingFrame(nameCustomer);
					bookingFrame.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(topFrame, "Client inconnu");
				}
			} else {
				JOptionPane.showMessageDialog(topFrame, "Veuillez entrer un nom");
			}
		} else if (e.getSource() == returnButton) {
			this.dispose();
			MainMenuFrame frame = new MainMenuFrame();
			frame.setVisible(true);
		}
	}
}
