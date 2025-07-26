package kiwi.persistence.cqs;

import kiwi.core.identify.Identifiable;
import kiwi.core.identify.Identifier;

/**
 * CQSのCommandです。
 *
 * @param <ID_RESOURCE> IDとするリソースの型
 * @param <RESOURCE>    リソースの型
 */
public interface CqsCommand<ID_RESOURCE, RESOURCE extends Identifiable<ID_RESOURCE>>
    extends AnyKeyCqsCommand<Identifier<ID_RESOURCE>, RESOURCE> {
}
