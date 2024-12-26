package kiwi.persistence;

/**
 * 一意に識別可能なオブジェクトであることを表します。
 * 
 * @param <ID_RESOURCE> IDとするリソースの型
 */
public interface Identifiable<ID_RESOURCE> extends AnyKeyIdentifiable<Identifier<ID_RESOURCE>> {
}
