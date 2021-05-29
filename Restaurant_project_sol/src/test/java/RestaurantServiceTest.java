import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class RestaurantServiceTest {

    RestaurantService service = new RestaurantService();
    Restaurant restaurant;

    @Test
    public void searching_for_existing_restaurant_should_return_expected_restaurant_object() throws restaurantNotFoundException {
    	
    	//Setting name to pass the test case while i was  testing
    	String restName = "Amelie's cafe";
    	
    	restaurant = service.findRestaurantByName(restName);
    	
    	assertNotNull(restaurant);
    }

    @Test
    public void searching_for_non_existing_restaurant_should_throw_exception() throws restaurantNotFoundException {
    	
    	//Setting name to pass the test case while i was  testing
    	String restName = "Amelie's cafesssssssssss"; 
    	assertNotNull(restName);
    	assertThrows(restaurantNotFoundException.class, () -> {
    		 service.findRestaurantByName(restName);
    	});
    	
    }
    
    @BeforeEach
    private void addRestaurantWithMenu() {
    	
    	 LocalTime openingTime = LocalTime.parse("10:30:00");
         LocalTime closingTime = LocalTime.parse("22:00:00");
         restaurant = service.addRestaurant("Amelie's cafe","Chennai",openingTime,closingTime);
         restaurant.addToMenu("Sweet corn soup",119);
         restaurant.addToMenu("Vegetable lasagne", 269);
    }
    
    
    @Test
    public void remove_restaurant_should_reduce_list_of_restaurants_size_by_1() throws restaurantNotFoundException {
        int initialNumberOfRestaurants = service.getRestaurants().size();
        service.removeRestaurant("Amelie's cafe");
        assertEquals(initialNumberOfRestaurants-1, service.getRestaurants().size());
    }

    @Test
    public void removing_restaurant_that_does_not_exist_should_throw_exception() throws restaurantNotFoundException {
        assertThrows(restaurantNotFoundException.class,()->service.removeRestaurant("Pantry d'or"));
    }

    @Test
    public void add_restaurant_should_increase_list_of_restaurants_size_by_1(){
        int initialNumberOfRestaurants = service.getRestaurants().size();
        service.addRestaurant("Pumpkin Tales","Chennai",LocalTime.parse("12:00:00"),LocalTime.parse("23:00:00"));
        assertEquals(initialNumberOfRestaurants + 1,service.getRestaurants().size());
    }
}