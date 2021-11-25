package com.netcracker;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

public class MyObjectInputStream<T> extends ObjectInputStream {

    public MyObjectInputStream(InputStream inputStream) throws IOException {
        super(inputStream);
    }

    public Result<T> next() throws ClassNotFoundException {
        try {
            T o = (T) readObject();
            return new Result<T>(o, true);
        } catch (IOException ioException) {
            return new Result<T>(null, false);
        }
    }

    public static class Result<T> {
        private T value;
        private boolean hasNext;

        public Result(T value, boolean hasNext) {
            this.value = value;
            this.hasNext = hasNext;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public boolean isHasNext() {
            return hasNext;
        }

        public void setHasNext(boolean hasNext) {
            this.hasNext = hasNext;
        }

        @Override
        public String toString() {
            return "Result{" +
                    "value=" + value +
                    ", hasNext=" + hasNext +
                    '}';
        }
    }
}
