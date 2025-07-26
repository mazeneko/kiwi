package kiwi.persistence.cqrs;

/**
 * {@link EventCreationContext}を作成するファクトリ。
 */
public interface EventCreationContextFactory {
  /**
   * {@link EventCreationContext}を作成します。
   * 
   * @return {@link EventCreationContext}
   */
  public EventCreationContext createContext();
}
