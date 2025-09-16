package excepetions;

public class NoSuchLocatorTypeException extends RuntimeException{

	public NoSuchLocatorTypeException(String locatorType) {
		super("Invalid Locator type: "+locatorType);
	}
}
