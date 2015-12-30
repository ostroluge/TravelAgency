package factory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
	
	public int addCategory(String name, Long idHotel, Long capacity, float price){
		try {
			preparedStatement = conn.prepareStatement("insert into category " +
			"(name_category, capacity, price, id_hotel) values (?,?,?)");
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
