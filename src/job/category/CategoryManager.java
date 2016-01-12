package job.category;

import model.Category;
import factory.CategoryFactory;

public class CategoryManager {

	public static CategoryManager INSTANCE = new CategoryManager();

	public void addCategory(Category category, Long idHotel){
		CategoryFactory.getInstance().addCategory(category.getName(),
                idHotel, category.getCapacity(), category.getPrice());
	}
	
	public void deleteCategory(Long id){
        CategoryFactory.getInstance().removeCategory(id);
	}

	public void editCategory(Category category, Long id){
		CategoryFactory.getInstance().editCategory(id, category);
	}
}
