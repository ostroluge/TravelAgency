package ui.category.listener;

import model.Category;

import javax.swing.*;

public interface CategorySelectionListener {

    public void onCategorySelection(Category category, JTable table);
}
