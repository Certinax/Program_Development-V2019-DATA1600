package com.logic.utilities.validators;

import javafx.collections.ObservableList;

/**
 * <h1>List Validator</h1>
 *
 * Class with static methods to valide Lists in the application
 *
 * @author Fredrik Pedersen
 * @since 15-04-2019
 *
 * NB! NB! MB! NB! NB! NB! NB!
 * Tried to make the return type of these a list so the methods could be used in the same
 * way as i.e the StringValidator-methods. Couldn't get that to work, so revise these before
 * hand-in!
 */

public class ListValidator {

    private ListValidator() { //Private constructor to deter initialization
    }

    public static <T extends Object> ObservableList<T> requireNonNullObservable(ObservableList<T> list) {
        for (T element : list) {
            if (element == null) {
                throw new NullPointerException();
            }
        }
        return list;
    }
}
