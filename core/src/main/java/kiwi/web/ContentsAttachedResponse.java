package kiwi.web;

import java.util.List;

public interface ContentsAttachedResponse<T> extends SimpleResponse {
  List<T> contents();

  static <T> ContentsAttachedResponse<T> of(String message, List<T> contents) {
    return new ContentsAttachedResponseImpl<>(message, contents);
  }
}
