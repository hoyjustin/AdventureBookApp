package c301.AdventureBook.Models;

public class testCases {
	/**
	 * This class should be used to write dummyStory Objects; This can help
	 * debug code.
	 * 
	 * @return
	 */
	public static Story initializeStory() {

		Story r = new Story("Cat Story", "This Story is about a Cat", "Minhal Syed",
				"2013-23-3234", null, null);
		Page page = new Page("FirstPage", "What should the cat do?");
		page.addOption(new Option("Choose Jump", new Page("nextPage",
				"You fell off a cliff")));
		r.addPage(page);
		return r;
	}

	public static Story initializeStory2() {

		Story r = new Story("title2", "description2", "author2", "1787823",
				null, null);
		Page page = new Page("PageTitle2", "description2");
		page.addOption(new Option("Option description2", new Page("nextPage2",
				"Nextdescription2")));
		r.addPage(page);
		return r;
	}
}
