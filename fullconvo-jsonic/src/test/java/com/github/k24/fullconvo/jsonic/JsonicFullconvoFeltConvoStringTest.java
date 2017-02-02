package com.github.k24.fullconvo.jsonic;

import com.github.k24.fullconvo.Fullconvo;
import com.github.k24.fullconvo.convo.Convo;
import com.github.k24.fullconvo.convo.ConvoFailedException;
import com.github.k24.fullconvo.convo.Felt;
import com.github.k24.fullconvo.convo.FeltFailedException;
import com.github.k24.fullconvo.option.*;
import com.github.k24.fullconvo.test.FieldOnly;
import com.github.k24.fullconvo.test.Resources;
import com.github.k24.fullconvo.test.SingleTypesInterface;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.ThrowableAssert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Created by k24 on 2017/01/18.
 */
public class JsonicFullconvoFeltConvoStringTest {

    private Fullconvo fullconvo;

    @Before
    public void setUp() {
        fullconvo = JsonicFullconvo.create();
    }


    @Test
    public void felt_format_notJson() throws Exception {
        try {
            fullconvo.felt("application/xml", "<root></root>").toJson();
            Assertions.fail("Nothing thrown");
        } catch (ConvoFailedException e) {
            // OK
        }

        try {
            fullconvo.felt("application/xml", "<root></root>").toMap();
            Assertions.fail("Nothing thrown");
        } catch (ConvoFailedException e) {
            // OK
        }

        try {
            fullconvo.felt("application/xml", "<root></root>").toObject(Object.class);
            Assertions.fail("Nothing thrown");
        } catch (ConvoFailedException e) {
            // OK
        }

        try {
            fullconvo.felt("application/xml", "<root></root>").felt().output(Felt.FORMAT_JSON);
            Assertions.fail("Nothing thrown");
        } catch (FeltFailedException e) {
            // OK
        }

        try {
            fullconvo.felt("application/xml", "<root></root>").felt().output(Felt.FORMAT_JSON, new StringBuilder());
            Assertions.fail("Nothing thrown");
        } catch (FeltFailedException e) {
            // OK
        }
    }

    @Test
    public void felt_null() throws Exception {
        Convo convo = fullconvo.felt(Felt.FORMAT_JSON, (String) null);
        assertThat(convo.toJson())
                .isNull();
        assertThat(convo.toJson(null))
                .isNull();
        assertThat(convo.toJson(ConvoOptions.DEFAULT))
                .isNull();
        assertThat(convo.toJson(convoOptions("longValue", 9)))
                .isNull();

        assertThat(convo.toMap())
                .isNull();
        assertThat(convo.toMap(null))
                .isNull();
        assertThat(convo.toMap(ConvoOptions.DEFAULT))
                .isNull();
        assertThat(convo.toMap(convoOptions("longValue", 9)))
                .isNull();

        assertThat(convo.toObject(FieldOnly.class))
                .isNull();
        assertThat(convo.toObject(FieldOnly.class, null))
                .isNull();
        assertThat(convo.toObject(FieldOnly.class, ConvoOptions.DEFAULT))
                .isNull();
        assertThat(convo.toObject(FieldOnly.class, convoOptions("longValue", 9)))
                .isNull();

        try {
            convo.felt();
            Assertions.fail("Nothing thrown");
        } catch (UnsupportedOperationException e) {
            // OK
        }
    }

    @Test
    public void felt_empty() throws Exception {
        Convo convo = fullconvo.felt(Felt.FORMAT_JSON, "");
        assertThat(convo.toJson())
                .isEqualTo("{}");
        assertThat(convo.toJson(null))
                .isEqualTo("{}");
        assertThat(convo.toJson(ConvoOptions.DEFAULT))
                .isEqualTo("{}");
        assertThat(convo.toJson(convoOptions("longValue", 9)))
                .isEqualTo("{}");

        assertThat(convo.toMap())
                .isEmpty();
        assertThat(convo.toMap(null))
                .isEmpty();
        assertThat(convo.toMap(ConvoOptions.DEFAULT))
                .isEmpty();
        assertThat(convo.toMap(convoOptions("longValue", 9)))
                .isEmpty();

        assertThat(convo.toObject(FieldOnly.class))
                .isEqualTo(new FieldOnly());
        assertThat(convo.toObject(FieldOnly.class, null))
                .isEqualTo(new FieldOnly());
        assertThat(convo.toObject(FieldOnly.class, ConvoOptions.DEFAULT))
                .isEqualTo(new FieldOnly());
        assertThat(convo.toObject(FieldOnly.class, convoOptions("longValue", 9)))
                .isEqualTo(new FieldOnly());

        assertThat(convo.felt().output(Felt.FORMAT_JSON))
                .isEqualTo("{}");
        StringBuilder buf = new StringBuilder();
        convo.felt().output(Felt.FORMAT_JSON, buf);
        assertThat(buf.toString())
                .isEqualTo("{}");
    }

    @Test
    public void felt_emptyObject() throws Exception {
        Convo convo = fullconvo.felt(Felt.FORMAT_JSON, "{}");
        assertThat(convo.toJson())
                .isEqualTo("{}");
        assertThat(convo.toJson(null))
                .isEqualTo("{}");

        assertThat(convo.toMap())
                .isEmpty();
        assertThat(convo.toMap(null))
                .isEmpty();

        assertThat(convo.toObject(FieldOnly.class))
                .isEqualTo(new FieldOnly());
        assertThat(convo.toObject(FieldOnly.class, null))
                .isEqualTo(new FieldOnly());

        assertThat(convo.felt().output(Felt.FORMAT_JSON))
                .isEqualTo("{}");
        StringBuilder buf = new StringBuilder();
        convo.felt().output(Felt.FORMAT_JSON, buf);
        assertThat(buf.toString())
                .isEqualTo("{}");
    }

    @Test
    public void felt_singleTypes() throws Exception {
        String singleTypes = Resources.singleTypesAsString();
        Convo convo = fullconvo.felt(Felt.FORMAT_JSON, singleTypes);
        String compacted = singleTypes.replaceAll("\\s+", "");
        assertThat(convo.toJson())
                .isEqualTo(compacted);
        assertThat(convo.toJson(null))
                .isEqualTo(compacted);
        assertThat(convo.toJson(ConvoOptions.DEFAULT))
                .isEqualTo(compacted);
        assertThat(convo.toJson(convoOptions("longValue", 9)))
                .isNotEqualTo(singleTypes)
                .isNotEqualTo(compacted)
                .doesNotContain("longValue")
                .contains("9");

        assertThat(convo.toMap())
                .hasSize(6)
                .containsEntry("booleanValue", true)
                .containsEntry("doubleValue", BigDecimal.valueOf(1.2))
                .containsEntry("floatValue", BigDecimal.valueOf(3.4))
                .containsEntry("intValue", BigDecimal.valueOf(5))
                .containsEntry("longValue", BigDecimal.valueOf(6L))
                .containsEntry("stringValue", "7");
        assertThat(convo.toMap(null))
                .hasSize(6)
                .containsEntry("booleanValue", true)
                .containsEntry("doubleValue", BigDecimal.valueOf(1.2))
                .containsEntry("floatValue", BigDecimal.valueOf(3.4))
                .containsEntry("intValue", BigDecimal.valueOf(5))
                .containsEntry("longValue", BigDecimal.valueOf(6L))
                .containsEntry("stringValue", "7");
        assertThat(convo.toMap(ConvoOptions.DEFAULT))
                .hasSize(6)
                .containsEntry("booleanValue", true)
                .containsEntry("doubleValue", BigDecimal.valueOf(1.2))
                .containsEntry("floatValue", BigDecimal.valueOf(3.4))
                .containsEntry("intValue", BigDecimal.valueOf(5))
                .containsEntry("longValue", BigDecimal.valueOf(6L))
                .containsEntry("stringValue", "7");
        assertThat(convo.toMap(convoOptions("longValue", 9)))
                .hasSize(5)
                .containsEntry("booleanValue", true)
                .containsEntry("doubleValue", BigDecimal.valueOf(1.2))
                .containsEntry("floatValue", BigDecimal.valueOf(3.4))
                .containsEntry("intValue", 9) // Converted to int
                .doesNotContainKey("longValue")
                .containsEntry("stringValue", "7");

        FieldOnly expected = new FieldOnly();
        expected.booleanValue = true;
        expected.doubleValue = 1.2;
        expected.floatValue = 3.4f;
        expected.intValue = 5;
        expected.longValue = 6L;
        expected.stringValue = "7";
        assertThat(convo.toObject(FieldOnly.class))
                .isEqualTo(expected);
        assertThat(convo.toObject(FieldOnly.class, null))
                .isEqualTo(expected);
        assertThat(convo.toObject(FieldOnly.class, ConvoOptions.DEFAULT))
                .isEqualTo(expected);
        expected.longValue = 0;
        expected.intValue = 9;
        assertThat(convo.toObject(FieldOnly.class, convoOptions("longValue", 9)))
                .isEqualTo(expected);

        SingleTypesInterface singleTypesInterface = convo.toObject(SingleTypesInterface.class);
        assertThat(singleTypesInterface.isBooleanValue()).isEqualTo(true);
        assertThat(singleTypesInterface.getDoubleValue()).isEqualTo(1.2);
        assertThat(singleTypesInterface.getFloatValue()).isEqualTo(3.4f);
        assertThat(singleTypesInterface.getIntValue()).isEqualTo(5);
        assertThat(singleTypesInterface.getLongValue()).isEqualTo(6L);
        assertThat(singleTypesInterface.getStringValue()).isEqualTo("7");

        singleTypesInterface = convo.toObject(SingleTypesInterface.class, convoOptions("longValue", 9));
        assertThat(singleTypesInterface.isBooleanValue()).isEqualTo(true);
        assertThat(singleTypesInterface.getDoubleValue()).isEqualTo(1.2);
        assertThat(singleTypesInterface.getFloatValue()).isEqualTo(3.4f);
        assertThat(singleTypesInterface.getIntValue()).isEqualTo(9);
        assertThat(singleTypesInterface.getLongValue()).isEqualTo(0L);
        assertThat(singleTypesInterface.getStringValue()).isEqualTo("7");

        assertThat(convo.felt().output(Felt.FORMAT_JSON))
                .isEqualTo(compacted);
        StringBuilder buf = new StringBuilder();
        convo.felt().output(Felt.FORMAT_JSON, buf);
        assertThat(buf.toString())
                .isEqualTo(compacted);
    }

    @Test
    public void felt_errorCausedByInvalid() throws Exception {
        final Convo convo = fullconvo.felt(Felt.FORMAT_JSON, "{invalid");
        assertThatThrownBy(new ThrowableAssert.ThrowingCallable() {
            @Override
            public void call() throws Throwable {
                convo.toJson();
            }
        });
        assertThatThrownBy(new ThrowableAssert.ThrowingCallable() {
            @Override
            public void call() throws Throwable {
                convo.toJson(convoOptions("longValue", 9));
            }
        });

        assertThatThrownBy(new ThrowableAssert.ThrowingCallable() {
            @Override
            public void call() throws Throwable {
                convo.toMap();
            }
        });
        assertThatThrownBy(new ThrowableAssert.ThrowingCallable() {
            @Override
            public void call() throws Throwable {
                convo.toMap(convoOptions("longValue", 9));
            }
        });

        assertThatThrownBy(new ThrowableAssert.ThrowingCallable() {
            @Override
            public void call() throws Throwable {
                convo.toObject(FieldOnly.class);
            }
        });
        assertThatThrownBy(new ThrowableAssert.ThrowingCallable() {
            @Override
            public void call() throws Throwable {
                convo.toObject(FieldOnly.class, convoOptions("longValue", 9));
            }
        });

        assertThatThrownBy(new ThrowableAssert.ThrowingCallable() {
            @Override
            public void call() throws Throwable {
                convo.felt().output(Felt.FORMAT_JSON);
            }
        });
        assertThatThrownBy(new ThrowableAssert.ThrowingCallable() {
            @Override
            public void call() throws Throwable {
                convo.felt().output(Felt.FORMAT_JSON, new StringBuilder());
            }
        });
    }

    @Test
    public void felt_errorCausedByFilterException() throws Exception {
        final Convo convo = fullconvo.felt(Felt.FORMAT_JSON, "{\"name\":0}");
        assertThatThrownBy(new ThrowableAssert.ThrowingCallable() {
            @Override
            public void call() throws Throwable {
                convo.toJson(convoOptionsForNameException());
            }
        });
        assertThatThrownBy(new ThrowableAssert.ThrowingCallable() {
            @Override
            public void call() throws Throwable {
                convo.toJson(convoOptionsForValueException());
            }
        });

        assertThatThrownBy(new ThrowableAssert.ThrowingCallable() {
            @Override
            public void call() throws Throwable {
                convo.toMap(convoOptionsForNameException());
            }
        });
        assertThatThrownBy(new ThrowableAssert.ThrowingCallable() {
            @Override
            public void call() throws Throwable {
                convo.toMap(convoOptionsForValueException());
            }
        });

        assertThatThrownBy(new ThrowableAssert.ThrowingCallable() {
            @Override
            public void call() throws Throwable {
                convo.toObject(FieldOnly.class, convoOptionsForNameException());
            }
        });
        assertThatThrownBy(new ThrowableAssert.ThrowingCallable() {
            @Override
            public void call() throws Throwable {
                convo.toObject(FieldOnly.class, convoOptionsForValueException());
            }
        });
    }

    private static ConvoOptions convoOptions(final String ignoreName, final int newIntValue) {
        return new ConvoOptions(new NameOption() {
            @Override
            public String filterName(String name) throws FilterNameException {
                // Ignore long.+
                return name.equals(ignoreName) ? null : name;
            }
        }, new ValueOption() {
            @Override
            public Object filterValue(Object value) throws FilterValueException {
                if (String.valueOf(value).equals("5")) {
                    return newIntValue;
                }
                return value;
            }
        });
    }

    private static ConvoOptions convoOptionsForNameException() {
        return new ConvoOptions(new NameOption() {
            @Override
            public String filterName(String name) throws FilterNameException {
                throw new FilterNameException("Name");
            }
        }, null);
    }

    private static ConvoOptions convoOptionsForValueException() {
        return new ConvoOptions(null, new ValueOption() {
            @Override
            public Object filterValue(Object value) throws FilterValueException {
                throw new FilterValueException("Value");
            }
        });
    }
}
