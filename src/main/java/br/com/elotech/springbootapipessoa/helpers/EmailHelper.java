package br.com.elotech.springbootapipessoa.helpers;

import java.io.Serializable;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class EmailHelper implements Serializable {

	private static final long serialVersionUID = 1L;

	public static boolean isValid(String email) {
		try {
			new InternetAddress(email).validate();
			return true;
		} catch (AddressException e) {
			return false;
		}
	}
}