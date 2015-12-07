package ui;

import java.sql.SQLException;
import java.util.List;

import model.City;
import model.Customer;
import model.Hotel;
import factory.CityFactory;
import factory.CustomerFactory;
import factory.HotelFactory;

public class Main {

	public static void main(String[] args) throws SQLException {
		
		List<Customer> customers = CustomerFactory.getInstance().getAllCustomers();
		
		for (Customer customer : customers) {
			System.out.println(customer.getOriginCity());
		}
		
		List<City> cities = CityFactory.getInstance().getAllCity();
		
		for (City city : cities){
			System.out.println(city.getNameCity());
		}
		
		List<Hotel> hotels = HotelFactory.getInstance().getHotelsFromCity(2L);
		
		for (Hotel hotel : hotels) {
			System.out.println(hotel.getName());
		}
	}
}
