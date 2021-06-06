package ru.ama.inwreaclaste.ignite.config;

import org.apache.ignite.configuration.DataStorageConfiguration;
import org.apache.ignite.spi.communication.tcp.TcpCommunicationSpi;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties( "ignite" )
public class IgniteConfigProperties {

    private String igniteHome;
    private String consistentId;
    private String igniteInstanceName;
    private TcpCommunicationSpi communicationSpi;
    private DataStorageConfiguration dataStorageConfiguration;

    public IgniteConfigProperties() {
    }

    public String getIgniteHome() {
        return igniteHome;
    }

    public String getConsistentId() {
        return consistentId;
    }

    public String getIgniteInstanceName() {
        return igniteInstanceName;
    }

    public TcpCommunicationSpi getCommunicationSpi() {
        return communicationSpi;
    }

    public DataStorageConfiguration getDataStorageConfiguration() {
        return dataStorageConfiguration;
    }

    public void setIgniteHome( String igniteHome ) {
        this.igniteHome = igniteHome;
    }

    public void setConsistentId( String consistentId ) {
        this.consistentId = consistentId;
    }

    public void setIgniteInstanceName( String igniteInstanceName ) {
        this.igniteInstanceName = igniteInstanceName;
    }

    public void setCommunicationSpi(
            TcpCommunicationSpi communicationSpi )
    {
        this.communicationSpi = communicationSpi;
    }

    public void setDataStorageConfiguration(
            DataStorageConfiguration dataStorageConfiguration )
    {
        this.dataStorageConfiguration = dataStorageConfiguration;
    }
}
