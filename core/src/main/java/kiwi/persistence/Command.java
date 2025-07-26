package kiwi.persistence;

import kiwi.core.identify.Identifiable;
import kiwi.core.identify.Identifier;

/**
 * CQSのCommandです。
 *
 * @param <ID_RESOURCE> IDとするリソースの型
 * @param <RESOURCE>    リソースの型
 * 
 * @deprecated {@link kiwi.persistence.cqs.CqsCommand}を使用してください。
 */
@Deprecated
public interface Command<ID_RESOURCE, RESOURCE extends Identifiable<ID_RESOURCE>>
    extends AnyKeyCommand<Identifier<ID_RESOURCE>, RESOURCE> {
}
