package uk.co.chrisbibby.sync.services.common.tables;

public enum Files {
    FILENAME(1),
    PATH(2),
    CHECKSUM(3),
    SIZE(4);

    private final int value;

  Files(final int value) {
      this.value = value;
    }

    public int getValue() {
      return value;
    }

}
