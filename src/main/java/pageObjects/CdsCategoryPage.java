package pageObjects;

import org.openqa.selenium.WebDriver;

public class CdsCategoryPage extends CategoryPage {
	private static final String ALTERNATIVE_CATEGORY_URL = "/index.php?page=cds&category=cds-alternative";
	private static final String BLUES_CATEGORY_URL = "/index.php?page=cds&category=cds-blues";
	private static final String CHILDRENS_CATEGORY_URL = "/index.php?page=cds&category=cds-childrens-music";
	private static final String CLASSICAL_CATEGORY_URL = "/index.php?page=cds&category=cds-classical";
	private static final String COUNTRY_CATEGORY_URL = "/index.php?page=cds&category=cds-country";
	private static final String DANCE_CATEGORY_URL = "/index.php?page=cds&category=cds-dance-dj";
	private static final String FOLK_CATEGORY_URL = "/index.php?page=cds&category=cds-folk";
	private static final String NEW_AGE_CATEGORY_URL = "/index.php?page=cds&category=cds-new-age";
	private static final String POP_CATEGORY_URL = "/index.php?page=cds&category=cds-pop";
	private static final String SOUL_CATEGORY_URL = "/index.php?page=cds&category=cds-soul";
	private static final String EMERGING_ARTISTS_CATEGORY_URL = "/index.php?page=cds&category=cds-emerging-artists";
	private static final String INTERNATIONAL_CATEGORY_URL = "/index.php?page=cds&category=cds-international";
	private static final String JAZZ_CATEGORY_URL = "/index.php?page=cds&category=cds-jazz";
	private static final String MISCELLANEOUS_CATEGORY_URL = "/index.php?page=cds&category=cds-miscellaneous";
	private static final String VOCAL_CATEGORY_URL = "/index.php?page=cds&category=cds-opera-vocal";
	private static final String RAP_HIPHOP_CATEGORY_URL = "/index.php?page=cds&category=cds-rap-hiphop";
	private static final String RNB_CATEGORY_URL = "/index.php?page=cds&category=cds-rnb";
	private static final String SOUNDTRACK_CATEGORY_URL = "/index.php?page=cds&category=cds-soundtracks";
	private static final String VOCALISTS_BROADWAY_CATEGORY_URL = "/index.php?page=cds&category=cds-vocalists-broadway";
	private static final String WORLD_CATEGORY_URL = "/index.php?page=cds&category=cds-world";
	
	public CdsCategoryPage(WebDriver driver) {
		super(driver);
	}

	@Override
	public GeneralPage open() {
		this.driver.get(configFileReader.getHost() + ALTERNATIVE_CATEGORY_URL);
		return this;
	}

	@Override
	public boolean isOpen() {
		boolean result = false;
		try {
			result = this.pageHeadingTitle.isDisplayed() && this.pageHeadingTitle.getText().equals("CDs » Alternative");
		} catch (Throwable e) {
			System.err.println("Problem while checking if Books Category Page Heading is displayed: " + e.getMessage());
		}
		return result;
	}

	public void openCategory(int categoryNumber){
		this.getSideBarButtons().get(categoryNumber).click();
	}

	public void openCategory(String category){
		switch(category){
			case "Alternative": {
				this.driver.get(configFileReader.getHost() + ALTERNATIVE_CATEGORY_URL);
				break;
			}
			case "Blues": {
				this.driver.get(configFileReader.getHost() + BLUES_CATEGORY_URL);
				break;
			}
			case "Children’s music": {
				this.driver.get(configFileReader.getHost() + CHILDRENS_CATEGORY_URL);
				break;
			}
			case "Classical": {
				this.driver.get(configFileReader.getHost() + CLASSICAL_CATEGORY_URL);
				break;
			}
			case "Country": {
				this.driver.get(configFileReader.getHost() + COUNTRY_CATEGORY_URL);
				break;
			}
			case "Dance & DJ": {
				this.driver.get(configFileReader.getHost() + DANCE_CATEGORY_URL);
				break;
			}
			case "Folk": {
				this.driver.get(configFileReader.getHost() + FOLK_CATEGORY_URL);
				break;
			}
			case "New age": {
				this.driver.get(configFileReader.getHost() + NEW_AGE_CATEGORY_URL);
				break;
			}
			case "Pop": {
				this.driver.get(configFileReader.getHost() + POP_CATEGORY_URL);
				break;
			}
			case "Soul": {
				this.driver.get(configFileReader.getHost() + SOUL_CATEGORY_URL);
				break;
			}
			case "Emerging artists": {
				this.driver.get(configFileReader.getHost() + EMERGING_ARTISTS_CATEGORY_URL);
				break;
			}

			case "International": {
				this.driver.get(configFileReader.getHost() + INTERNATIONAL_CATEGORY_URL);
				break;
			}
			case "Jazz": {
				this.driver.get(configFileReader.getHost() + JAZZ_CATEGORY_URL);
				break;
			}
			case "Miscellaneous": {
				this.driver.get(configFileReader.getHost() + MISCELLANEOUS_CATEGORY_URL);
				break;
			}
			case "Opera & vocal": {
				this.driver.get(configFileReader.getHost() + VOCAL_CATEGORY_URL);
				break;
			}
			case "Rap & hip-hop": {
				this.driver.get(configFileReader.getHost() + RAP_HIPHOP_CATEGORY_URL);
				break;
			}
			case "R&B": {
				this.driver.get(configFileReader.getHost() + RNB_CATEGORY_URL);
				break;
			}
			case "Soundtracks": {
				this.driver.get(configFileReader.getHost() + SOUNDTRACK_CATEGORY_URL);
				break;
			}
			case "Vocalists & Broadway": {
				this.driver.get(configFileReader.getHost() + VOCALISTS_BROADWAY_CATEGORY_URL);
				break;
			}
			case "World": {
				this.driver.get(configFileReader.getHost() + WORLD_CATEGORY_URL);
				break;
			}
		}
	}

	/*
	public CdsCategoryPage openNotEmptyCdsCategory(int i) {
		if (i == 0) {
			this.driver.get(configFileReader.getHost() + ALTERNATIVE_CATEGORY_URL);
			this.getSideBarButtons().get(i).click();
		}
		if (i == 1) {
			this.driver.get(configFileReader.getHost() + BLUES_CATEGORY_URL);
			this.getSideBarButtons().get(i).click();
		}
		if (i == 2) {
			this.driver.get(configFileReader.getHost() + CHILDRENS_CATEGORY_URL);
			this.getSideBarButtons().get(i).click();
		}
		if (i == 3) {
			this.driver.get(configFileReader.getHost() + CLASSICAL_CATEGORY_URL);
			this.getSideBarButtons().get(i).click();
		}
		if (i == 4) {
			this.driver.get(configFileReader.getHost() + COUNTRY_CATEGORY_URL);
			this.getSideBarButtons().get(i).click();
		}
		if (i == 5) {
			this.driver.get(configFileReader.getHost() + DANCE_CATEGORY_URL);
			this.getSideBarButtons().get(i).click();
		}
		if (i == 6) {
			this.driver.get(configFileReader.getHost() + FOLK_CATEGORY_URL);
			this.getSideBarButtons().get(i).click();
		}
		if (i == 11) {
			this.driver.get(configFileReader.getHost() + NEW_AGE_CATEGORY_URL);
			this.getSideBarButtons().get(i).click();
		}
		if (i == 13) {
			this.driver.get(configFileReader.getHost() + POP_CATEGORY_URL);
			this.getSideBarButtons().get(i).click();
		}
		if (i == 16) {
			this.driver.get(configFileReader.getHost() + SOUL_CATEGORY_URL);
			this.getSideBarButtons().get(i).click();
		}
		return this;
	}

	public CdsCategoryPage openEmptyCdsCategory(int i) {
		if (i == 7) {
			this.driver.get(configFileReader.getHost() + EMERGING_ARTISTS_CATEGORY_URL);
			this.getSideBarButtons().get(i).click();
		}
		if (i == 8) {
			this.driver.get(configFileReader.getHost() + INTERNATIONAL_CATEGORY_URL);
			this.getSideBarButtons().get(i).click();
		}
		if (i == 9) {
			this.driver.get(configFileReader.getHost() + JAZZ_CATEGORY_URL);
			this.getSideBarButtons().get(i).click();
		}
		if (i == 10) {
			this.driver.get(configFileReader.getHost() + MISCELLANEOUS_CATEGORY_URL);
			this.getSideBarButtons().get(i).click();
		}
		if (i == 12) {
			this.driver.get(configFileReader.getHost() + VOCAL_CATEGORY_URL);
			this.getSideBarButtons().get(i).click();
		}
		if (i == 14) {
			this.driver.get(configFileReader.getHost() + RAP_HIPHOP_CATEGORY_URL);
			this.getSideBarButtons().get(i).click();
		}
		if (i == 15) {
			this.driver.get(configFileReader.getHost() + RNB_CATEGORY_URL);
			this.getSideBarButtons().get(i).click();
		}
		if (i == 17) {
			this.driver.get(configFileReader.getHost() + SOUNDTRACK_CATEGORY_URL);
			this.getSideBarButtons().get(i).click();
		}
		if (i == 18) {
			this.driver.get(configFileReader.getHost() +  VOCALISTS_BROADWAY_CATEGORY_URL);
			this.getSideBarButtons().get(i).click();
		}
		if (i == 19) {
			this.driver.get(configFileReader.getHost() + WORLD_CATEGORY_URL);
			this.getSideBarButtons().get(i).click();
		}
		return this;		
	}
*/
	public int getRandomNotEmptyCdCategoryNumbers() {
		int[] notEmptyCatNumbers = new int[10];
		int rnd = (int)(Math.random()*notEmptyCatNumbers.length);
		
		notEmptyCatNumbers[0] = 0;
		notEmptyCatNumbers[1] = 1;
		notEmptyCatNumbers[2] = 2;
		notEmptyCatNumbers[3] = 3;
		notEmptyCatNumbers[4] = 4;
		notEmptyCatNumbers[5] = 5;
		notEmptyCatNumbers[6] = 6;
		notEmptyCatNumbers[7] = 11;
		notEmptyCatNumbers[8] = 13;
		notEmptyCatNumbers[9] = 16;
		
		return notEmptyCatNumbers[rnd];
	}
}
