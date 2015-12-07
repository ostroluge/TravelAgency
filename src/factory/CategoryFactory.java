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
	
	public Category create(Long id, int capacity, float price){
		return new Category(id, capacity, price);
	}
	
	public int addCategory(int idHotel, int capacity, float price){
		try {
			preparedStatement = conn.prepareStatement("insert into category " +
			"(capacity, price, id_hotel) values (?,?,?)");
			preparedStatement.clearParameters();
			
			preparedStatement.setInt(1, capacity);
			preparedStatement.setFloat(2, price);
			preparedStatement.setInt(3, idHotel);
			
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
