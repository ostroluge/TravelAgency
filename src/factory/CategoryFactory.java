package factory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Category;
import db.DbManager;

public class CategoryFactory {

	private static CategoryFactory INSTANCE;
	private Connection conn = DbManager.getInstance().getConnection();
	private PreparedStatement preparedStatement;
	
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
	
	public int addCategory(String name, Long idHotel, Long capacity, float price){
		try {
			preparedStatement = conn.prepareStatement("insert into category " +
			"(name_category, capacity, price, id_hotel) values (?,?,?,?)");
			preparedStatement.clearParameters();
			
			preparedStatement.setString(1, name);
			preparedStatement.setLong(2, capacity);
			preparedStatement.setFloat(3, price);
			preparedStatement.setLong(4, idHotel);
			
			int resultCode = preparedStatement.executeUpdate();
			
			return resultCode;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
		
	}
	
	public int editCategory(Long id, Category category) {
		try {
			preparedStatement = conn.prepareStatement(
					"update category set capacity = ?,"
					+ "set price = ?, set name_category = ? where id = ?");
			preparedStatement.clearParameters();
			
			preparedStatement.setInt(1, category.getCapacity());
			preparedStatement.setFloat(2, category.getPrice());
			preparedStatement.setString(3, category.getName());
			preparedStatement.setLong(4, id);
			
			int resultCode = preparedStatement.executeUpdate();
			
			return resultCode;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int removeCategory(int idCategory, int idHotel){
		try {
			preparedStatement = conn.prepareStatement("delete from category " +
			"where id = ? and id_hotel = ?");
			preparedStatement.clearParameters();
			
			preparedStatement.setInt(1, idCategory);
			preparedStatement.setInt(2, idHotel);
			
			int resultCode = preparedStatement.executeUpdate();
			
			return resultCode;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return 0;
	}
}
