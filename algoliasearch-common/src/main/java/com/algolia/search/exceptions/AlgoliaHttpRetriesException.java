package com.algolia.search.exceptions;

import java.util.List;
import java.util.stream.Collectors;

public class AlgoliaHttpRetriesException extends AlgoliaException {

  /**
   * List of exception if all retries failed
   */
  private List<AlgoliaIOException> ioExceptionList;

  public AlgoliaHttpRetriesException(String message, List<AlgoliaIOException> ioExceptionList) {
    super(message + ", exceptions: [" + String.join(",", ioExceptionList.stream().map(Throwable::getMessage).collect(Collectors.toList())) + "]");
    this.ioExceptionList = ioExceptionList;
  }

  @SuppressWarnings("unused")
  public List<AlgoliaIOException> getIoExceptionList() {
    return ioExceptionList;
  }
}
