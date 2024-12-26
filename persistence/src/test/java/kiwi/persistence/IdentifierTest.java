package kiwi.persistence;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;

import org.junit.jupiter.api.Test;

public class IdentifierTest {

  private UUID sampleUUID() {
    return UUID.fromString("a2d9e161-4412-4c65-949e-96bffd600b9b");
  }

  @Test
  void testOf() {
    final UUID uuid = sampleUUID();
    final Identifier<?> identifier = Identifier.of(uuid);
    assertNotNull(identifier);
    assertNotNull(identifier.value());
  }

  @Test
  void ofのnull不許可() {
    assertThrows(NullPointerException.class, () -> Identifier.of(null));
  }

  @Test
  void testRandomUUID() {
    final Identifier<?> identifier = Identifier.randomUUID();
    assertNotNull(identifier);
    assertNotNull(identifier.value());
  }

  @Test
  void testValue() {
    final UUID uuid = sampleUUID();
    final Identifier<?> identifier = Identifier.of(uuid);
    assertEquals(identifier.value(), uuid);
  }

  @Test
  void testEquals() {
    final Identifier<?> identifierA = Identifier.of(UUID.fromString("a2d9e161-4412-4c65-949e-96bffd600b9b"));
    final Identifier<?> identifierA2 = Identifier.of(UUID.fromString("a2d9e161-4412-4c65-949e-96bffd600b9b"));
    final Identifier<?> identifierB = Identifier.of(UUID.fromString("fe358a4c-652d-4ed4-a1bc-1c7b459ca808"));
    assertTrue(identifierA.equals(identifierA2));
    assertFalse(identifierA.equals(identifierB));
  }

  @Test
  void testHashCode() {
    final Identifier<?> identifierA = Identifier.of(UUID.fromString("a2d9e161-4412-4c65-949e-96bffd600b9b"));
    final Identifier<?> identifierA2 = Identifier.of(UUID.fromString("a2d9e161-4412-4c65-949e-96bffd600b9b"));
    assertTrue(identifierA.hashCode() == identifierA2.hashCode());
  }

  @Test
  void testToString() {
    final Identifier<?> identifier = Identifier.randomUUID();
    assertDoesNotThrow(() -> identifier.toString());
  }
}
