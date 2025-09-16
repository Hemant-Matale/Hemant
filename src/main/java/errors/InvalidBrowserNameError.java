package errors;


public class InvalidBrowserNameError extends Error {

	private String browserName;

	public InvalidBrowserNameError(String browserName) {
		this.browserName = browserName;
	}
	@Override
	public String getMessage() {
		return "Invalid Browser Name is provided in .xml file: "+browserName;
	}
}
