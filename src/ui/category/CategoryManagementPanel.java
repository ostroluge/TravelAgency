package ui.category;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

import job.category.CategoryManager;
import model.Category;
import model.Hotel;
import ui.listener.category.CategorySelectionListener;
import ui.listener.hotel.HotelSelectionListener;
import ui.menu.MainMenuFrame;

@SuppressWarnings("serial")
public class CategoryManagementPanel extends JPanel implements ActionListener, CategorySelectionListener, HotelSelectionListener {

    protected JLabel labelName = new JLabel();
    protected JLabel labelPrice = new JLabel();
    protected JLabel labelCapacity = new JLabel();

    protected JTextField name;
    protected JTextField price;
    protected JTextField capacity;

    protected JButton addButton;
    protected JButton editButton;
    protected JButton deleteButton;
    protected JButton clearButton;
	protected JButton returnButton;

    protected Category categorySelected;
    protected Hotel hotelSelected;
    protected JTable tableSelected;

    public CategoryManagementPanel() {
        CategoryTable.INSTANCE.addListener(this);
        HotelTable.INSTANCE.addListener(this);
        setLabels();
        setTextFields();
        setButtons();
        setPanel();
    }

    private void setLabels() {
        labelName.setText("Nom :");
        labelPrice.setText("Prix :");
        labelCapacity.setText("Capacité");
    }

    private void setTextFields() {
        Document modelTextName = new PlainDocument();
        Document modelTextPrice = new PlainDocument();
        Document modelTextCapacity = new PlainDocument();
        name = new JTextField(modelTextName, "", 10);
        price = new JTextField(modelTextPrice, "", 10);
        capacity = new JTextField(modelTextCapacity, "", 10);
    }

    private void setButtons() {
        addButton = new JButton("Ajouter");
        editButton = new JButton("Modifier");
        clearButton = new JButton("Clear");
        deleteButton = new JButton("Supprimer");
		returnButton = new JButton("Retour");

        addButton.addActionListener(this);
        editButton.addActionListener(this);
        clearButton.addActionListener(this);
        deleteButton.addActionListener(this);
		returnButton.addActionListener(this);
    }

    private void setPanel() {
        setLayout(new GridLayout(3, 4));
        add(labelName);
        add(name);
        add(labelPrice);
        add(price);
        add(labelCapacity);
        add(capacity);
        add(addButton);
        add(editButton);
        add(clearButton);
        add(deleteButton);
        add(returnButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        if (e.getSource() == addButton) {
            if (!name.getText().trim().equals("") && !price.getText().trim().equals("")
                    && !capacity.getText().trim().equals("")) {
                Category categoryToAdd = new Category(name.getText(),
                        Integer.parseInt(capacity.getText()), Float.parseFloat(price.getText()));
                if (hotelSelected != null) {
                    CategoryManager.INSTANCE.addCategory(categoryToAdd, hotelSelected.getId());
                }
                else{
              	  JOptionPane.showMessageDialog(topFrame, "Aucun hôtel sélectionné");
                }
            } else{
          	  JOptionPane.showMessageDialog(topFrame, "Veuillez remplir tous les champs");
            }
        } else if (e.getSource() == editButton) {
            if (categorySelected != null) {
            	if (!name.getText().trim().equals("") && !price.getText().trim().equals("")
                        && !capacity.getText().trim().equals("")) {
                    Category categoryToEdit = new Category(name.getText(),
                            Integer.parseInt(capacity.getText()), Float.parseFloat(price.getText()));
                    categoryToEdit.setId(categorySelected.getId());
                    if (hotelSelected != null) {
                        categoryToEdit.setHotelId(hotelSelected.getId());
                        CategoryManager.INSTANCE.editCategory(categoryToEdit, categorySelected.getId());
                    }
                }
            } else {
            	  JOptionPane.showMessageDialog(topFrame, "Veuillez sélectionner une catégorie");
            }
        } else if (e.getSource() == clearButton) {
            if (categorySelected != null) {
                clearSelection();
            } else {
                name.setText("");
                price.setText("");
                capacity.setText("");
            }
        } else if (e.getSource() == deleteButton) {
            if (categorySelected != null) {
            	CategoryManager.INSTANCE.deleteCategory(categorySelected.getId());
                clearSelection();
            }
            else{
          	  JOptionPane.showMessageDialog(topFrame, "Veuillez sélectionner une catégorie");
            }
        } else if (e.getSource() == returnButton) {
			JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
			currentFrame.dispose();
			MainMenuFrame frame = new MainMenuFrame();
			frame.setVisible(true);
		}
    }

    private void clearSelection() {
        categorySelected = null;
        tableSelected.clearSelection();
        name.setText("");
        price.setText("");
        capacity.setText("");
    }

    @Override
    public void onCategorySelection(Category category, JTable table) {
        if (category != null) {
            categorySelected = category;
            name.setText(category.getName());
            price.setText(category.getPrice()+"");
            capacity.setText(category.getCapacity()+"");
        }
        tableSelected = table;
    }

    @Override
    public void onHotelSelection(Hotel hotel) {
        if (hotel != null) {
            hotelSelected = hotel;
        }
    }
}
