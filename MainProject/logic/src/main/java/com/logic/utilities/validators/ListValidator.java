package com.logic.utilities.validators;

import javafx.collections.ObservableList;

/**
 * <h1>List Validator</h1>
 *
 * Class with static methods to valide Lists in the application
 *
 * @author Candidate 730
 * @since 15-04-2019
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
