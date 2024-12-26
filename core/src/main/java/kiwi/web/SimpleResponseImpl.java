package kiwi.web;

import java.util.Objects;

record SimpleResponseImpl(String message) implements SimpleResponse {
  SimpleResponseImpl {
    Objects.requireNonNull(message);
  }
}
