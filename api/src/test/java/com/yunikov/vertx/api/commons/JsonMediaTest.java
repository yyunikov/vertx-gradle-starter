package com.yunikov.vertx.api.commons;

import com.yunikov.vertx.domain.commons.Media;
import com.yunikov.vertx.domain.commons.MediaPrintable;
import org.junit.Assert;
import org.junit.Test;

public class JsonMediaTest {

    private static final class FakeRegular implements MediaPrintable {
        private final String field1;
        private final Integer field2;

        private FakeRegular(final String field1, final Integer field2) {
            this.field1 = field1;
            this.field2 = field2;
        }

        @Override
        public Media print(final Media media) {
            return media.with("field1", field1)
                    .with("field2", field2);
        }
    }

    private static final class FakeEmbedded implements MediaPrintable {
        private final String field1;
        private final MediaPrintable field2;

        private FakeEmbedded(final String field1, final MediaPrintable field2) {
            this.field1 = field1;
            this.field2 = field2;
        }

        @Override
        public Media print(final Media media) {
            return media.with("field1", field1)
                    .with("field2", field2);
        }
    }

    @Test
    public void printsRegular() {
        Assert.assertEquals("{\"field1\":\"one\",\"field2\":2}", fakeRegular().printString(new JsonMedia()));
    }

    @Test
    public void printsEmbedded() {
        final FakeEmbedded embedded = fakeEmbedded();
        Assert.assertEquals("{\"field1\":\"one\",\"field2\":{\"field1\":\"one\",\"field2\":2}}", embedded.printString(new JsonMedia()));
    }

    @Test
    public void printsDeeplyEmbedded() {
        final FakeEmbedded embedded = fakeDeeplyEmbedded();
        Assert.assertEquals(
                "{\"field1\":\"one\",\"field2\":{\"field1\":\"one\",\"field2\":{\"field1\":\"one\",\"field2\":2}}}",
                embedded.printString(new JsonMedia()));
    }

    private FakeEmbedded fakeDeeplyEmbedded() {
        return new FakeEmbedded("one", fakeEmbedded());
    }

    private FakeEmbedded fakeEmbedded() {
        return new FakeEmbedded("one", fakeRegular());
    }

    private FakeRegular fakeRegular() {
        return new FakeRegular("one", 2);
    }
}