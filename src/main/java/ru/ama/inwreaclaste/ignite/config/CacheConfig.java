package ru.ama.inwreaclaste.ignite.config;

import ru.ama.inwreaclaste.ignite.entities.ChannelWithMessages;
import ru.ama.inwreaclaste.ignite.entities.UserWithInfo;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;
import org.apache.ignite.configuration.CacheConfiguration;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author AmaJohny, amdathlon103@gmail.com
 * created 06.06.2021
 */
@Configuration
@EnableConfigurationProperties( {IgniteConfigProperties.class, UserCacheConfigProperties.class,
                                 ChatCacheConfigProperties.class} )
@PropertySource( "classpath:igniteStorage.properties" )
public class CacheConfig {

    private static final Logger log = LoggerFactory.getLogger( CacheConfig.class );

    @Bean
    public Ignite ignite( IgniteConfigProperties igniteConfigProperties ) {
        log.debug( "Ignite start" );
        IgniteConfiguration igniteCfg = new IgniteConfiguration();
        String igniteHome = igniteConfigProperties.getIgniteHome();
        if ( igniteHome != null )
            igniteCfg.setIgniteHome( igniteHome );
        igniteCfg.setConsistentId( igniteConfigProperties.getConsistentId() );
        igniteCfg.setIgniteInstanceName( igniteConfigProperties.getIgniteInstanceName() );
        igniteCfg.setCommunicationSpi( igniteConfigProperties.getCommunicationSpi() );
        igniteCfg.setDataStorageConfiguration( igniteConfigProperties.getDataStorageConfiguration() );
        return Ignition.start( igniteCfg );
    }

    @Bean
    public IgniteCache<String, UserWithInfo> usersCache(
            Ignite ignite, UserCacheConfigProperties userCacheConfigProperties ) {
        log.debug( "Cache users start" );
        var sessionCfg = new CacheConfiguration<String, UserWithInfo>();
        sessionCfg.setCacheMode( userCacheConfigProperties.getMode() );
        sessionCfg.setAtomicityMode( userCacheConfigProperties.getAtomicityMode() );
        sessionCfg.setName( userCacheConfigProperties.getName() );
        sessionCfg.setSqlSchema( userCacheConfigProperties.getName() );
        sessionCfg.setSqlIndexMaxInlineSize( userCacheConfigProperties.getSqlIndexMaxInlineSize() );
        sessionCfg.setStatisticsEnabled( userCacheConfigProperties.isStatisticsEnabled() );
        sessionCfg.setIndexedTypes( String.class, UserWithInfo.class );
        return ignite.createCache( sessionCfg );
    }

    @Bean
    public IgniteCache<String, ChannelWithMessages> chatCache(
            Ignite ignite, ChatCacheConfigProperties chatCacheConfigProperties ) {
        log.debug( "Cache chat start" );
        var sessionCfg = new CacheConfiguration<String, ChannelWithMessages>();
        sessionCfg.setCacheMode( chatCacheConfigProperties.getMode() );
        sessionCfg.setAtomicityMode( chatCacheConfigProperties.getAtomicityMode() );
        sessionCfg.setName( chatCacheConfigProperties.getName() );
        sessionCfg.setSqlSchema( chatCacheConfigProperties.getName() );
        sessionCfg.setSqlIndexMaxInlineSize( chatCacheConfigProperties.getSqlIndexMaxInlineSize() );
        sessionCfg.setStatisticsEnabled( chatCacheConfigProperties.isStatisticsEnabled() );
        sessionCfg.setIndexedTypes( String.class, ChannelWithMessages.class );
        return ignite.createCache( sessionCfg );
    }
}
