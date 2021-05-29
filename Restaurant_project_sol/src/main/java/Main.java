import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Main {

	public static void main(String[] args) {
		
		RestaurantService restaurantService = new RestaurantService();
		
		//Add restaurant
		Restaurant restaurant1 = restaurantService.addRestaurant("Restaurant 1", "Bangalore", LocalTime.of(9, 00, 00), LocalTime.of(23, 00, 00));
		restaurant1.addToMenu("chicken", 200);
		restaurant1.addToMenu("Goat", 500);
		restaurant1.addToMenu("Prawn", 300);
		
		Restaurant restaurant2 = restaurantService.addRestaurant("Restaurant 2", "Chennai", LocalTime.of(9, 00, 00), LocalTime.of(20, 00, 00));
		restaurant2.addToMenu("Govi", 100);
		restaurant2.addToMenu("Corn", 200);
		restaurant2.addToMenu("Paneer", 300);
		
		Restaurant restaurant3 = restaurantService.addRestaurant("Restaurant 3", "Hyderabad", LocalTime.of(10, 00, 00), LocalTime.of(23, 00, 00));
		restaurant3.addToMenu("chicken Tikka", 250);
		restaurant3.addToMenu("chicken Hariyali", 300);
		restaurant3.addToMenu("Fish fry", 400);
		
		//Total restaurant size
		_log.info("Total number of resaturants:-"+restaurantService.getRestaurants().size());
		
		try {
			//Find restaurant by name
			Restaurant restaurant = restaurantService.findRestaurantByName("Restaurant 1");
			_log.info("Restaurant name found is :-"+restaurant.getName());
			
			//Check restaurant is open or close
			if(restaurant.isRestaurantOpen()) {
				_log.info("The Restaurant is open now");
				restaurant.displayDetails();
				
				List<String> selectedItems = new ArrayList<String>();
				selectedItems.add("chicken");
				selectedItems.add("Goat");
				selectedItems.add("Mashroom");
				
				_log.info("Total price:-"+restaurant.calulatePriceBySelectedItems(selectedItems));
			}else {
				_log.info("The Restaurant is closed now");
			}
		}catch (restaurantNotFoundException e) {
			_log.info("Restuarant not found for name :-"+e.getMessage());
		}
	}

	private static Logger _log = Logger.getLogger(Main.class.getName());
	
}
