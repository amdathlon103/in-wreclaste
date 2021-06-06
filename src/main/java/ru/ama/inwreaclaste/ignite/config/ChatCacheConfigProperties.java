package ru.ama.inwreaclaste.ignite.config;

import org.apache.ignite.cache.CacheAtomicityMode;
import org.apache.ignite.cache.CacheMode;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author AmaJohny, amdathlon103@gmail.com
 * created 06.06.2021
 */
@Configuration
@ConfigurationProperties( "cache.chat" )
public class ChatCacheConfigProperties {

    private String name;
    private CacheMode mode;
    private CacheAtomicityMode atomicityMode;
    private int sqlIndexMaxInlineSize;
    private boolean statisticsEnabled;

    public ChatCacheConfigProperties() {
    }

    public String getName() {
        return name;
    }

    public CacheMode getMode() {
        return mode;
    }

    public CacheAtomicityMode getAtomicityMode() {
        return atomicityMode;
    }

    public int getSqlIndexMaxInlineSize() {
        return sqlIndexMaxInlineSize;
    }

    public boolean isStatisticsEnabled() {
        return statisticsEnabled;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public void setMode( CacheMode mode ) {
        this.mode = mode;
    }

    public void setAtomicityMode( CacheAtomicityMode atomicityMode ) {
        this.atomicityMode = atomicityMode;
    }

    public void setSqlIndexMaxInlineSize( int sqlIndexMaxInlineSize ) {
        this.sqlIndexMaxInlineSize = sqlIndexMaxInlineSize;
    }

    public void setStatisticsEnabled( boolean statisticsEnabled ) {
        this.statisticsEnabled = statisticsEnabled;
    }
}
