package ru.ama.inwreaclaste.ignite.dao;

import org.apache.ignite.IgniteDataStreamer;
import org.apache.ignite.cache.query.ScanQuery;
import org.apache.ignite.lang.IgniteClosure;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.cache.Cache;

public interface CacheDao<K, V> {

    /**
     * Проверка доступности кеша.
     */
    boolean isAvailable();

    /**
     * Размер кеша.
     */
    Long getCacheSize();

    /**
     * DataStreamer для пакетной вставки.
     */
    IgniteDataStreamer<K, V> getDataStreamer();

    /**
     * Поиск объекта по id и возврат в зависимости от наличия.
     */
    Optional<V> findById( K id );

    /**
     * Получение всех объетов по запросу.
     */
    List<V> getAll( ScanQuery<K, V> query );

    /**
     * Получение всех объетов по запросу, возвращаемое значение трансформируется по переданному
     * трансформеру.
     */
    List getAll( ScanQuery<K, V> query, IgniteClosure<Cache.Entry<K, V>, ?> transformer );

    /**
     * Сохранение объекта в кеше.
     */
    void save( V value );

    /**
     * Обновление объекта в кеше.
     */
    void update( V value );

    /**
     * Удаление объектов из кеша по множеству id.
     */
    void removeAll( Set<K> ids );

    /**
     * Размер кеша по метрикам.
     */
    long getMetricsCacheSize();

    /**
     * Среднее время поиска.
     */
    float getAverageGetTime();

    /**
     * Среднее время вставки.
     */
    float getAveragePutTime();

    /**
     * Среднее время удаления.
     */
    float getAverageRemoveTime();

    /**
     * Общее число запросов поиска.
     */
    float getCacheGets();

    /**
     * Общее число вставок.
     */
    float getCachePuts();

    /**
     * Общее число удалений.
     */
    float getCacheRemovals();
}
