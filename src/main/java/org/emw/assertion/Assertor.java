package org.emw.assertion;

import org.emw.assertion.bool.BooleanAssertor;
import org.emw.assertion.collection.CollectionAssertor;
import org.emw.assertion.date.DateAssertor;
import org.emw.assertion.number.NumberAssertor;
import org.emw.assertion.string.StringAssertor;

public interface Assertor extends StringAssertor, CollectionAssertor, BooleanAssertor, DateAssertor, NumberAssertor {
}
