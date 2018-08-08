package org.rbaygildin.geodevice.matchers;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

import java.util.List;
import java.util.function.Predicate;

public class CommonMatchers {
    public static <T>  Matcher<List<T>> anyMatches(Predicate<T> predicate){
        return new BaseMatcher<List<T>>() {
            @Override
            public boolean matches(Object item) {
                List<T> list = (List<T>) item;
                for(T e : list){
                    if(predicate.test(e))
                        return true;
                }
                return false;
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("Elements does not belong to list");
            }
        };
    }
}
