package factory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Category;
import db.DbManager;
import ui.listener.category.CategoryChangeListener;

/**
 * La classe Fabrique de Category
 */
public class CategoryFactory {

	private static CategoryFactory INSTANCE;
	private Connection conn = DbManager.getInstance().getConnection();
	private PreparedStatement preparedStatement;
	
	private static List<CategoryChangeListener> listeners = new ArrayList<>();

	/**
	 * Récupère l'instance de la CategoryFactory
	 * @return instance of CategoryFactory
	 */
	public static CategoryFactory getInstance(){
		if(INSTANCE == null){
			INSTANCE = new CategoryFactory();
		}
		return INSTANCE;
	}
	
	/**
	 * Fonction de création d'une category
	 * @param id 
	 * @param capacity
	 * @param price
	 * @param name
	 * @return new Category
	 */
	public Category create(Long id, int capacity, float price, String name){
		return new Category(id, capacity, price, name);
	}
	
	/**
	 * Récupère une category à partir de son id
	 * @param id
	 * @return Category
	 */
	public Category getCategoryById(Long id) {
		Category category = null;
		try {
			preparedStatement = conn.prepareStatement(
					"select * from category where id = ?");
			preparedStatement.clearParameters();
			
			preparedStatement.setLong(1, id);
			
			ResultSet resultPreparedStatement = preparedStatement.executeQuery();
			
			while (resultPreparedStatement.next()) {
				Long idCategory = resultPreparedStatement.getLong(1);
				Long idHotel = resultPreparedStatement.getLong(2);
				int capacity = resultPreparedStatement.getInt(3);
				float price = resultPreparedStatement.getFloat(4);
				String name = resultPreparedStatement.getString(5);
				category = new Category(idCategory, idHotel, capacity, price, name);
			}
			
			if (category != null) {
				return category;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Récupère toutes les category d'un hotel à partir de son id
	 * @param id
	 * @return List<Category>
	 */
	public List<Category> getCategoriesByHotelId(Long id) {
		List<Category> categories = new ArrayList<>();
		try {
			preparedStatement = conn.prepareStatement(
					"select * from category where id_hotel = ?");
			preparedStatement.clearParameters();
			
			preparedStatement.setLong(1, id);
			
			ResultSet resultPreparedStatement = preparedStatement.executeQuery();
			
			while (resultPreparedStatement.next()) {
				Long idCategory = resultPreparedStatement.getLong(1);
				Long idHotel = resultPreparedStatement.getLong(2);
				int capacity = resultPreparedStatement.getInt(3);
				float price = resultPreparedStatement.getFloat(4);
				String name = resultPreparedStatement.getString(5);
				categories.add(new Category(idCategory, idHotel, capacity, price, name));
			}
			
			if (!categories.isEmpty()) {
				return categories;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Permet d'ajouter une category à la base de données
	 * @param name
	 * @param idHotel
	 * @param capacity
	 * @param price
	 * @return code retour
	 */
	public int addCategory(String name, Long idHotel, int capacity, float price){
		try {
			preparedStatement = conn.prepareStatement("insert into category " +
			"(name_category, capacity, price, id_hotel) values (?,?,?,?)");
			preparedStatement.clearParameters();
			
			preparedStatement.setString(1, name);
			preparedStatement.setInt(2, capacity);
			preparedStatement.setFloat(3, price);
			preparedStatement.setLong(4, idHotel);
			
			int resultCode = preparedStatement.executeUpdate();

			fireModelChangeEvent();

			return resultCode;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
		
	}
	
	/**
	 * Permet d'éditer une category en base de données
	 * @param id
	 * @param category
	 * @return code retour
	 */
	public int editCategory(Long id, Category category) {
		try {
			preparedStatement = conn.prepareStatement(
					"update category set id_hotel = ?, capacity = ?,"
					+ " price = ?, name_category = ? where id = ?");
			preparedStatement.clearParameters();
			
			preparedStatement.setLong(1, category.getHotelId());
			preparedStatement.setInt(2, category.getCapacity());
			preparedStatement.setFloat(3, category.getPrice());
			preparedStatement.setString(4, category.getName());
			preparedStatement.setLong(5, id);

			int resultCode = preparedStatement.executeUpdate();

			fireModelChangeEvent();

			return resultCode;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	/**
	 * Permet de supprimer une category dans la base de données
	 * @param idCategory
	 * @return code retour
	 */
	public int removeCategory(Long idCategory){
		try {
			PreparedStatement removeCategoryStatement;
			removeCategoryStatement = conn.prepareStatement(
					"delete from category where id = ?");
					
			
			removeCategoryStatement.setLong(1, idCategory);

			int resultCode = removeCategoryStatement.executeUpdate();

			fireModelChangeEvent();
			
			return resultCode;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	/**
	 * Permet d'ajouter un listener à la liste de listeners de la factory
	 * @param listener
	 */
	public void addListener(CategoryChangeListener listener) {
		listeners.add(listener);
	}

	/**
	 * Permet de vérifier si les category ont changés (via les listeners)
	 */
	private static void fireModelChangeEvent() {
		for (CategoryChangeListener listener : listeners) {
			listener.categoryHasChanged();
		}
	}
}
