package kiwi.web;

public interface SimpleResponse {
  String message();

  static SimpleResponse of(String message) {
    return new SimpleResponseImpl(message);
  }
}
