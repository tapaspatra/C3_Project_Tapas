import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RestaurantTest {
	
    Restaurant restaurant;
    
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
    	restaurant.SetCurrentTime(LocalTime.parse("11:30:01"));
    	assertEquals(true, restaurant.isRestaurantOpen());
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
    	restaurant.SetCurrentTime(LocalTime.parse("22:30:01"));
    	assertEquals(false, restaurant.isRestaurantOpen());
    }

    @BeforeEach
    private void addRestaurantWithMenu() {
    	
    	 LocalTime openingTime = LocalTime.parse("10:30:00");
         LocalTime closingTime = LocalTime.parse("22:00:00");
         restaurant = new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
         restaurant.addToMenu("Sweet corn soup",119);
         restaurant.addToMenu("Vegetable lasagne", 269);
   }

    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
    	assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    
    @Test
    public void calulate_total_price_when_item_selected() {
    	
    	List<String> selectedItems = new ArrayList<String>();
    	selectedItems.add("Sweet corn soup");
    	selectedItems.add("Vegetable lasagne");
    	
    	int price = restaurant.calulatePriceBySelectedItems(selectedItems);
    	assertEquals(388, price);
    }
    
    @Test
    public void calulate_total_price_when_item_not_selected() {
    	
    	List<String> selectedItems = new ArrayList<String>();
    	
    	int price = restaurant.calulatePriceBySelectedItems(selectedItems);
    	assertEquals(0, price);
    }
}