package ru.ama.inwreaclaste.ignite.dao;

import ru.ama.inwreaclaste.ignite.config.CacheConfig;
import ru.ama.inwreaclaste.ignite.entities.ChannelWithMessages;
import ru.ama.inwreaclaste.ignite.entities.UserWithInfo;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.IgniteDataStreamer;
import org.apache.ignite.cache.CachePartialUpdateException;
import org.apache.ignite.cache.CachePeekMode;
import org.apache.ignite.cache.query.ScanQuery;
import org.apache.ignite.cache.query.SqlFieldsQuery;
import org.apache.ignite.lang.IgniteClosure;
import org.slf4j.Logger;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.cache.Cache.Entry;

@Service
@ConditionalOnBean( CacheConfig.class )
public class ChatCacheDaoImpl<K, V extends ChannelWithMessages> implements CacheDao<K, V> {

    private static final String SYNTHETIC_REQUEST = "SELECT 1 FROM DUAL";
    private static final Logger log = org.slf4j.LoggerFactory.getLogger( ChatCacheDaoImpl.class );

    private final IgniteCache<K, V> cache;
    private final Ignite ignite;

    public ChatCacheDaoImpl( IgniteCache<K, V> cache, Ignite ignite ) {
        this.cache = cache;
        this.ignite = ignite;
    }

    @Override
    public boolean isAvailable() {
        return !cache.isClosed() && !cache.query( new SqlFieldsQuery( SYNTHETIC_REQUEST ) ).getAll()
                                          .isEmpty();
    }

    @Override
    public Long getCacheSize() {
        return cache.sizeLong( CachePeekMode.ALL );
    }

    @Override
    public IgniteDataStreamer<K, V> getDataStreamer() {
        return ignite.dataStreamer( cache.getName() );
    }

    @Override
    public Optional<V> findById( K id ) {
        return Optional.ofNullable( cache.get( id ) );
    }

    @Override
    public List<V> getAll( ScanQuery<K, V> query ) {
        return cache.query( query, Entry::getValue ).getAll();
    }

    @Override
    public List<?> getAll( ScanQuery<K, V> query,
                           IgniteClosure<Entry<K, V>, ?> transformer )
    {
        return cache.query( query, transformer ).getAll();
    }

    @Override
    public void save( V value ) {
        if ( !cache.putIfAbsent( ( K )value.uuid, value ) ) {
            log.info( "Value with key = " + value.uuid + " already exist" );
        }
    }

    @Override
    public void update( V value ) {
        cache.put( ( K )value.uuid, value );
    }

    @Override
    public void removeAll( Set<K> ids ) {
        Set<K> prev = new HashSet<>();
        try {
            cache.removeAll( ids );
        } catch ( CachePartialUpdateException ex ) {
            Set<K> failedKeys = Set.copyOf( ex.failedKeys() );
            if ( !failedKeys.containsAll( prev ) ) {
                prev.addAll( failedKeys );
                removeAll( failedKeys );
            }
            log.error( "Values with ids = " + failedKeys + " not remove" );
        }
    }

    /**
     * Metrics.
     */

    @Override
    public long getMetricsCacheSize() {
        return cache.metrics().getCacheSize();
    }

    @Override
    public float getAverageGetTime() {
        return cache.metrics().getAverageGetTime();
    }

    @Override
    public float getAveragePutTime() {
        return cache.metrics().getAveragePutTime();
    }

    @Override
    public float getAverageRemoveTime() {
        return cache.metrics().getAverageRemoveTime();
    }

    @Override
    public float getCacheGets() {
        return cache.metrics().getCacheGets();
    }

    @Override
    public float getCachePuts() {
        return cache.metrics().getCachePuts();
    }

    @Override
    public float getCacheRemovals() {
        return cache.metrics().getCacheRemovals();
    }
}
