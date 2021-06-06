package ru.ama.inwreaclaste.rest.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * @author AmaJohny, amdathlon103@gmail.com
 * created 07.06.2021
 */
public class UserInfoDto implements Serializable {

    public final String name;
    public final String status;
    public final String dob;
    public final String htown;
    public final String instagram;
    public final String vk;
    public final String url1;
    public final String urlname1;
    public final String url2;
    public final String urlname2;
    public final String music;
    public final String about;

    @JsonCreator
    public UserInfoDto( @JsonProperty( "name" ) String name,
                        @JsonProperty( "status" ) String status,
                        @JsonProperty( "dob" ) String dob,
                        @JsonProperty( "htown" ) String htown,
                        @JsonProperty( "instagram" ) String instagram,
                        @JsonProperty( "vk" ) String vk,
                        @JsonProperty( "url1" ) String url1,
                        @JsonProperty( "urlname1" ) String urlname1,
                        @JsonProperty( "url2" ) String url2,
                        @JsonProperty( "urlname2" ) String urlname2,
                        @JsonProperty( "music" ) String music,
                        @JsonProperty( "about" ) String about ) {
        this.name = name == null ? "" : name;
        this.status = status == null ? "" : status;
        this.dob = dob == null ? "" : dob;
        this.htown = htown == null ? "" : htown;
        this.instagram = instagram == null ? "" : instagram;
        this.vk = vk == null ? "" : vk;
        this.url1 = url1 == null ? "" : url1;
        this.urlname1 = urlname1 == null ? "" : urlname1;
        this.url2 = url2 == null ? "" : url2;
        this.urlname2 = urlname2 == null ? "" : urlname2;
        this.music = music == null ? "" : music;
        this.about = about == null ? "" : about;
    }

    @Override public String toString() {
        return "UserInfoDto{" +
               "name='" + name + '\'' +
               ", status='" + status + '\'' +
               ", dob='" + dob + '\'' +
               ", htown='" + htown + '\'' +
               ", instagram='" + instagram + '\'' +
               ", vk='" + vk + '\'' +
               ", url1='" + url1 + '\'' +
               ", urlname1='" + urlname1 + '\'' +
               ", url2='" + url2 + '\'' +
               ", urlname2='" + urlname2 + '\'' +
               ", music='" + music + '\'' +
               ", about='" + about + '\'' +
               '}';
    }
}
