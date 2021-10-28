package exception;

public class UserNotFound extends Exception {

	private static final long serialVersionUID = -7048346048650728946L;

	public UserNotFound(final String code, final String msg) {
		super(msg);
	}

}
