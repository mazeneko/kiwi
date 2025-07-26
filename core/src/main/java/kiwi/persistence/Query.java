package kiwi.persistence;

import kiwi.core.identify.Identifiable;
import kiwi.core.identify.Identifier;

/**
 * CQSのQueryです。
 *
 * @param <ID_RESOURCE> IDとするリソースの型
 * @param <RESOURCE>    リソースの型
 */
public interface Query<ID_RESOURCE, RESOURCE extends Identifiable<ID_RESOURCE>>
    extends AnyKeyQuery<Identifier<ID_RESOURCE>, RESOURCE> {
}
