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

	public BooksCategoryPage openNotEmptyBookCategory(int i) {
		if (i == 0) {
			this.driver.get(configFileReader.getHost() + ART_CATEGORY_URL);
			this.getSideBarButtons().get(i).click();
		}
		if (i == 1) {
			this.driver.get(configFileReader.getHost() + BIOGRAPHY_CATEGORY_URL);
			this.getSideBarButtons().get(i).click();
		}
		if (i == 2) {
			this.driver.get(configFileReader.getHost() + CHILDRENS_CATEGORY_URL);
			this.getSideBarButtons().get(i).click();
		}
		if (i == 3) {
			this.driver.get(configFileReader.getHost() + FINANCE_CATEGORY_URL);
			this.getSideBarButtons().get(i).click();
		}
		if (i == 4) {
			this.driver.get(configFileReader.getHost() + COMPUTERS_CATEGORY_URL);
			this.getSideBarButtons().get(i).click();
		}
		if (i == 5) {
			this.driver.get(configFileReader.getHost() + COOKING_FOOD_WINE_CATEGORY_URL);
			this.getSideBarButtons().get(i).click();
		}
		if (i == 6) {
			this.driver.get(configFileReader.getHost() + ENTERTAINMENT_CATEGORY_URL);
			this.getSideBarButtons().get(i).click();
		}
		if (i == 7) {
			this.driver.get(configFileReader.getHost() + MIND_BODY_CATEGORY_URL);
			this.getSideBarButtons().get(i).click();
		}
		if (i == 9) {
			this.driver.get(configFileReader.getHost() + HOBBIES_CATEGORY_URL);
			this.getSideBarButtons().get(i).click();
		}
		if (i == 10) {
			this.driver.get(configFileReader.getHost() + HOME_GARDEN_CATEGORY_URL);
			this.getSideBarButtons().get(i).click();
		}
		if (i == 18) {
			this.driver.get(configFileReader.getHost() + SCIENCE_NATURE_CATEGORY_URL);
			this.getSideBarButtons().get(i).click();
		}
		if (i == 19) {
			this.driver.get(configFileReader.getHost() + SCIENCE_FICTION_CATEGORY_URL);
			this.getSideBarButtons().get(i).click();
		}
		return this;
	}

	public BooksCategoryPage openEmptyBookCategory(int i) {
		if (i == 8) {
			this.driver.get(configFileReader.getHost() + HISTORY_CATEGORY_URL);
			this.getSideBarButtons().get(i).click();
		}
		if (i == 11) {
			this.driver.get(configFileReader.getHost() + HORROR_CATEGORY_URL);
			this.getSideBarButtons().get(i).click();
		}
		if (i == 12) {
			this.driver.get(configFileReader.getHost() + LITERATURE_FICTION_CATEGORY_URL);
			this.getSideBarButtons().get(i).click();
		}
		if (i == 13) {
			this.driver.get(configFileReader.getHost() + MISTERY_THRILLERS_CATEGORY_URL);
			this.getSideBarButtons().get(i).click();
		}
		if (i == 14) {
			this.driver.get(configFileReader.getHost() + NON_FICTION_CATEGORY_URL);
			this.getSideBarButtons().get(i).click();
		}
		if (i == 15) {
			this.driver.get(configFileReader.getHost() + PROFESSIONAL_TECHNICAL_CATEGORY_URL);
			this.getSideBarButtons().get(i).click();
		}
		if (i == 16) {
			this.driver.get(configFileReader.getHost() + REFERENCE_CATEGORY_URL);
			this.getSideBarButtons().get(i).click();
		}
		if (i == 17) {
			this.driver.get(configFileReader.getHost() + RELIGION_SPIRITUALITY_CATEGORY_URL);
			this.getSideBarButtons().get(i).click();
		}
		if (i == 20) {
			this.driver.get(configFileReader.getHost() + SPORTS_OUTDOORS_CATEGORY_URL);
			this.getSideBarButtons().get(i).click();
		}
		if (i == 21) {
			this.driver.get(configFileReader.getHost() + TRAVEL_CATEGORY_URL);
			this.getSideBarButtons().get(i).click();
		}
		return this;
	}
	
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
