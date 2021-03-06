package pageObjects;

import org.openqa.selenium.WebDriver;

public class BooksCategoryPage extends CategoryPage {
	private static final String ART_CATEGORY_URL = "/index.php?page=books&category=books-art";
	private static final String BIOGRAPHY_CATEGORY_URL = "/index.php?page=books&category=books-biographies";
	private static final String CHILDRENS_CATEGORY_URL = "/index.php?page=books&category=books-childrens";
	private static final String FINANCE_CATEGORY_URL = "/index.php?page=books&category=books-finance";
	private static final String COMPUTERS_CATEGORY_URL = "/index.php?page=books&category=books-computers";
	private static final String COOKING_FOOD_WINE_CATEGORY_URL = "/index.php?page=books&category=books-cooking-food-wine";
	private static final String ENTERTAINMENT_CATEGORY_URL = "/index.php?page=books&category=books-entertainment";
	private static final String MIND_BODY_CATEGORY_URL = "/index.php?page=books&category=books-mind-body";
	private static final String HOBBIES_CATEGORY_URL = "/index.php?page=books&category=books-mind-hobbies";
	private static final String HOME_GARDEN_CATEGORY_URL = "/index.php?page=books&category=books-home-garden";
	private static final String SCIENCE_NATURE_CATEGORY_URL = "/index.php?page=books&category=books-science-nature";
	private static final String SCIENCE_FICTION_CATEGORY_URL = "/index.php?page=books&category=books-science-fiction";
	private static final String HISTORY_CATEGORY_URL = "/index.php?page=books&category=books-history";
	private static final String HORROR_CATEGORY_URL = "/index.php?page=books&category=books-horror";
	private static final String LITERATURE_FICTION_CATEGORY_URL = "/index.php?page=books&category=books-literature-fiction";
	private static final String MISTERY_THRILLERS_CATEGORY_URL = "/index.php?page=books&category=books-mystery-thrillers";
	private static final String NON_FICTION_CATEGORY_URL = "/index.php?page=books&category=books-non-fiction";
	private static final String PROFESSIONAL_TECHNICAL_CATEGORY_URL = "/index.php?page=books&category=books-professional-technical";
	private static final String REFERENCE_CATEGORY_URL = "/index.php?page=books&category=books-reference";
	private static final String RELIGION_SPIRITUALITY_CATEGORY_URL = "/index.php?page=books&category=books-religion-spirituality";
	private static final String SPORTS_OUTDOORS_CATEGORY_URL = "/index.php?page=books&category=books-sports-outdoors";
	private static final String TRAVEL_CATEGORY_URL = "/index.php?page=books&category=books-travel";
	
	public BooksCategoryPage(WebDriver driver) {
		super(driver);
	}

	/*
	 Implementation from Home page abstract methods
	  */
	@Override
	public BooksCategoryPage open() {
		this.driver.get(configFileReader.getHost() + ART_CATEGORY_URL);
		return this;
	}

	@Override
	public boolean isOpen() {
		boolean result = false;
		try {
			result = this.pageHeadingTitle.isDisplayed() && this.pageHeadingTitle.getText().equals("Books » Art");
		} catch (Throwable e) {
			System.err.println("Problem while checking if Books Category Page Heading is displayed: " + e.getMessage());
		}
		return result;
	}

	/*
	Text getters from Web Elements
	 */

	/*
	Actions in this page
	 */
	public void openCategory(int categoryNumber){
		this.getSideBarButtons().get(categoryNumber).click();
	}

	public void openCategory(String category){
		switch (category){
			case "Art": {
				this.driver.get(configFileReader.getHost() + ART_CATEGORY_URL);
				break;
			}
			case "Biographies": {
				this.driver.get(configFileReader.getHost() + BIOGRAPHY_CATEGORY_URL);
				break;
			}
			case "Children’s books": {
				this.driver.get(configFileReader.getHost() + CHILDRENS_CATEGORY_URL);
				break;
			}
			case "Finance": {
				this.driver.get(configFileReader.getHost() + FINANCE_CATEGORY_URL);
				break;
			}
			case "Computers": {
				this.driver.get(configFileReader.getHost() + COMPUTERS_CATEGORY_URL);
				break;
			}
			case "Cooking, food & wine": {
				this.driver.get(configFileReader.getHost() + COOKING_FOOD_WINE_CATEGORY_URL);
				break;
			}
			case "Entertainment": {
				this.driver.get(configFileReader.getHost() + ENTERTAINMENT_CATEGORY_URL);
				break;
			}
			case "Mind & body": {
				this.driver.get(configFileReader.getHost() + MIND_BODY_CATEGORY_URL);
				break;
			}
			case "Hobbies": {
				this.driver.get(configFileReader.getHost() + HOBBIES_CATEGORY_URL);
				break;
			}
			case "Home & garden": {
				this.driver.get(configFileReader.getHost() + HOME_GARDEN_CATEGORY_URL);
				break;
			}
			case "Science & nature": {
				this.driver.get(configFileReader.getHost() + SCIENCE_NATURE_CATEGORY_URL);
				break;
			}
			case "Science fiction": {
				this.driver.get(configFileReader.getHost() + SCIENCE_FICTION_CATEGORY_URL);
				break;
			}
			case "History": {
				this.driver.get(configFileReader.getHost() + HISTORY_CATEGORY_URL);
				break;
			}
			case "Horror": {
				this.driver.get(configFileReader.getHost() + HORROR_CATEGORY_URL);
				break;
			}
			case "Literature & fiction": {
				this.driver.get(configFileReader.getHost() + LITERATURE_FICTION_CATEGORY_URL);
				break;
			}
			case "Mystery & thrillers": {
				this.driver.get(configFileReader.getHost() + MISTERY_THRILLERS_CATEGORY_URL);
				break;
			}
			case "Non-fiction": {
				this.driver.get(configFileReader.getHost() + NON_FICTION_CATEGORY_URL);
				break;
			}
			case "Professional & technical": {
				this.driver.get(configFileReader.getHost() + PROFESSIONAL_TECHNICAL_CATEGORY_URL);
				break;
			}
			case "Reference": {
				this.driver.get(configFileReader.getHost() + REFERENCE_CATEGORY_URL);
				break;
			}
			case "Religion & spirituality": {
				this.driver.get(configFileReader.getHost() + RELIGION_SPIRITUALITY_CATEGORY_URL);
				break;
			}
			case "Sports & outdoors": {
				this.driver.get(configFileReader.getHost() + SPORTS_OUTDOORS_CATEGORY_URL);
				break;
			}
			case "Travel": {
				this.driver.get(configFileReader.getHost() + TRAVEL_CATEGORY_URL);
				break;
			}
		}
	}

	/*
	Checks for certain images, buttons if they are displayed
	 */

	/*
	Helper functions
	 */
	public int getRandomNotEmptyBookCategoryNumbers() {
		int[] notEmptyCatNumbers = new int[12];
		int rnd = (int)(Math.random()*notEmptyCatNumbers.length);
		
		notEmptyCatNumbers[0] = 0;
		notEmptyCatNumbers[1] = 1;
		notEmptyCatNumbers[2] = 2;
		notEmptyCatNumbers[3] = 3;
		notEmptyCatNumbers[4] = 4;
		notEmptyCatNumbers[5] = 5;
		notEmptyCatNumbers[6] = 6;
		notEmptyCatNumbers[7] = 7;
		notEmptyCatNumbers[8] = 9;
		notEmptyCatNumbers[9] = 10;
		notEmptyCatNumbers[10] = 18;
		notEmptyCatNumbers[11] = 19;
		
		return notEmptyCatNumbers[rnd];
	}
}
