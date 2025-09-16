package setup;

public interface Locators {
   //String busJourneySource = "//input[@placeholder=\"Enter Source\"]";//xpath
   String busJourneySource = "#autosuggestBusSRPSrcHome"; //cssSelector";
   //String busJourneyDestination ="//input[@placeholder=\"Enter Destination\"]";
   String busJourneyDestination ="input[placeholder=\"Enter Destination\"]";
   //String suggestionForSource = "//div[@role=\"option\"]/li";
   String suggestionForSource = "div[role=\"option\"]>li";
   //String suggestionForDestination ="//div[@role=\"option\"]/li";
   String suggestionForDestination ="div[role=\"option\"]>li";
   //String singleSuggOfSource = "(//div[@role=\"option\"]/li)[1]";
   String singleSuggOfSource = "(//div[@role=\"option\"]/li)[1]";
   //String inputFieldCalender = "//input[@placeholder=\"Pick a date\"]";
   String inputFieldCalender = "input[placeholder*=\"date\"]";
   //String searchButton = "//button[text()='Search Bus']";
   String searchButton = "button[data-testid=\"searchBusBtn\"]";
  //String searchResultCnt = "//div[@class=\"SortBystyles__LeftWrapDiv-sc-luteww-6 ullym\"]/span[2]";
   String searchResultCnt = ".dOsBbG";
   //String errMsgOnSearchPage = "//div[@class=\"NoResultsstyles__NoResultsCard-sc-g9lvxg-0 bkNanZ\"]";
   String errMsgOnSearchPage = ".dKWUaD";
   String sameSourceAndDestinationErrValidationMsg = ".biXizc .error";
   String monthYearDisplayed = "//p[@class=\"dcalendarstyles__MonthNamePara-sc-r2jz2t-3 gryMsr\"]";
   String suggDays = "//ul[@class=\"dcalendarstyles__DateWrapDiv-sc-r2jz2t-7 gJsKZe\"]/li";
}
