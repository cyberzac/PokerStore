package com.ongame.pokerstore.common;

/**
 * Thrown to indicate that a credit limit was exceeded, i.e. that there were
 * insufficient funds.
 *
 * @author bwin Games AB
 * @version 2008-05-05
 */
public class InsufficientCreditException extends RuntimeException {
    public InsufficientCreditException() {
    }

    public InsufficientCreditException(String s) {
        super(s);
    }

    public InsufficientCreditException(String message, Throwable cause) {
        super(message, cause);
    }

    public InsufficientCreditException(Throwable cause) {
        super(cause);
    }
}
