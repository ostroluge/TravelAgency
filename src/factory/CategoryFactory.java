package factory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Category;
import db.DbManager;
import ui.category.listener.CategoryChangeListener;

public class CategoryFactory {

	private static CategoryFactory INSTANCE;
	private Connection conn = DbManager.getInstance().getConnection();
	private PreparedStatement preparedStatement;

	private static List<CategoryChangeListener> listeners = new ArrayList<>();

	public static CategoryFactory getInstance(){
		if(INSTANCE == null){
			INSTANCE = new CategoryFactory();
		}
		return INSTANCE;
	}
	
	public Category create(Long id, int capacity, float price, String name){
		return new Category(id, capacity, price, name);
	}
	
	public Category getCategoryById(int id) {
		Category category = null;
		try {
			preparedStatement = conn.prepareStatement(
					"select * from category where id = ?");
			preparedStatement.clearParameters();
			
			preparedStatement.setInt(1, id);
			
			ResultSet resultPreparedStatement = preparedStatement.executeQuery();
			
			while (resultPreparedStatement.next()) {
				Long idCategory = resultPreparedStatement.getLong(1);
				int capacity = resultPreparedStatement.getInt(3);
				float price = resultPreparedStatement.getFloat(4);
				String name = resultPreparedStatement.getString(5);
				category = new Category(idCategory, capacity, price, name);
			}
			
			if (category != null) {
				return category;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
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
	
	public int removeCategory(Long idCategory){
		try {
			PreparedStatement removeCategoryStatement;
			removeCategoryStatement = conn.prepareStatement(
					"delete from category where id = ?");
					
			
			removeCategoryStatement.setLong(1, idCategory);

			fireModelChangeEvent();

			int resultCode = removeCategoryStatement.executeUpdate();
			
			return resultCode;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	public void addListener(CategoryChangeListener listener) {
		listeners.add(listener);
	}

	private static void fireModelChangeEvent() {
		for (CategoryChangeListener listener : listeners) {
			listener.categoryHasChanged();
		}
	}
}
