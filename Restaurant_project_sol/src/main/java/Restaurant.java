import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Restaurant {
	private String name;
	private String location;
	private LocalTime openingTime;
	private LocalTime closingTime;
	private List<Item> menu = new ArrayList<Item>();
	private LocalTime time = LocalTime.now();

	public Restaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
		this.name = name;
		this.location = location;
		this.openingTime = openingTime;
		this.closingTime = closingTime;
	}

	public LocalTime getOpeningTime() {
		return openingTime;
	}

	public LocalTime getClosingTime() {
		return closingTime;
	}

	public void setClosingTime(LocalTime closingTime) {
		this.closingTime = closingTime;
	}

	public boolean isRestaurantOpen() {

		boolean restaurantOpen = false;

		if (getCurrentTime().isAfter(getOpeningTime()) && getCurrentTime().isBefore(getClosingTime())) {
			restaurantOpen = true;
		}

		return restaurantOpen;
	}

	public void setOpeningTime(LocalTime openingTime) {
		this.openingTime = openingTime;
	}

	public LocalTime getCurrentTime() {
		return time;
	}

	public LocalTime SetCurrentTime(LocalTime time) {
		return this.time = time;
	}

	public List<Item> getMenu() {
		return menu;
	}

	private Item findItemByName(String itemName) {
		for (Item item : menu) {
			if (item.getName().equals(itemName))
				return item;
		}
		return null;
	}

	public void addToMenu(String name, int price) {
		Item newItem = new Item(name, price);
		menu.add(newItem);
	}

	public void removeFromMenu(String itemName) throws itemNotFoundException {

		Item itemToBeRemoved = findItemByName(itemName);
		if (itemToBeRemoved == null)
			throw new itemNotFoundException(itemName);

		menu.remove(itemToBeRemoved);
	}

	public void displayDetails() {
		System.out.println("Restaurant:" + name + "\n" + "Location:" + location + "\n" + "Opening time:" + openingTime
				+ "\n" + "Closing time:" + closingTime + "\n" + "Menu:" + "\n" + getMenu());

	}

	public String getName() {
		return name;
	}

	public int calulatePriceBySelectedItems(List<String> selectedItems) {

		List<Item> currentMenu = menu;

		int totalPrice = 0;

		List<Item> matched = currentMenu.stream().filter(a -> selectedItems.contains(a.getName()))
				.collect(Collectors.toList());
		for (Item itemData : matched) {
			totalPrice = totalPrice + itemData.getPrice();
		}

		return totalPrice;
	}

}
