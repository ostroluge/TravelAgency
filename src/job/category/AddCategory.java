package job.category;

import factory.CategoryFactory;
import model.Category;

public class AddCategory {

    public AddCategory(Category category, Long idHotel) {
        CategoryFactory.getInstance().addCategory(category.getName(),
                idHotel, category.getCapacity(), category.getPrice());
    }
}
