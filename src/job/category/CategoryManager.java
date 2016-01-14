package job.category;

import model.Category;
import factory.CategoryFactory;

/**
 * Classe permettant de gerer les categories 
 */
public class CategoryManager {

	public static CategoryManager INSTANCE = new CategoryManager();

	/**
	 * Ajoute une categorie a la base de donnees
	 * @param category
	 * @param idHotel
	 */
	public void addCategory(Category category, Long idHotel){
		CategoryFactory.getInstance().addCategory(category.getName(),
                idHotel, category.getCapacity(), category.getPrice());
	}
	
	/**
	 * Supprimer une categorie
	 * @param id
	 */
	public void deleteCategory(Long id){
        CategoryFactory.getInstance().removeCategory(id);
	}

	/**
	 * Modifie la categorie dans la base de donnees
	 * @param category
	 * @param id
	 */
	public void editCategory(Category category, Long id){
		CategoryFactory.getInstance().editCategory(id, category);
	}
}
