package job.category;

import factory.CategoryFactory;
import model.Category;

public class EditCategory {

    public EditCategory(Category category, Long id) {
        CategoryFactory.getInstance().editCategory(id, category);
    }
}
