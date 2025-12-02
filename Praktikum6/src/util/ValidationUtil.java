package util;

import error.ValidationException;
import model.User;

public class ValidationUtil {
	public static void validate(User user) throws ValidationException, NullPointerException {
		if(user.getEmail() == null) {
			throw new NullPointerException("Username is Null");
		}
		else if(user.getEmail().isBlank()) {
			throw new ValidationException("Username is Blank");
		}
		else if(user.getPassword() == null) {
			throw new NullPointerException("Password is Null");
		}
		else if(user.getPassword().isBlank()) {
			throw new ValidationException("Password is Blank");
		}
	}
}