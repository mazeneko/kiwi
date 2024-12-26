package kiwi.persistence;

/**
 * CQSのCommandです。
 *
 * @param <ID_RESOURCE> IDとするリソースの型
 * @param <RESOURCE>    リソースの型
 */
public interface Command<ID_RESOURCE, RESOURCE extends Identifiable<ID_RESOURCE>>
    extends AnyKeyCommand<Identifier<ID_RESOURCE>, RESOURCE> {
}
