package kiwi.web;

import java.util.List;
import java.util.Objects;

record ContentsAttachedResponseImpl<T>(String message, List<T> contents)
    implements ContentsAttachedResponse<T> {
  ContentsAttachedResponseImpl {
    Objects.requireNonNull(message);
    Objects.requireNonNull(contents);
  }
}
