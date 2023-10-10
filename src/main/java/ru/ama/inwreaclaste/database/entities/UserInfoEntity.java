package ru.ama.inwreaclaste.database.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.ZonedDateTime;
import java.util.List;

@Entity
public class UserInfoEntity {

    @Id
    public String userId;

    public String name;
    public String status;
    public String dateOfBirth;
    public String homeTown;
    public String instagramLink;
    public String vkLink;
    /** Список url-ов в формате name:url
     *  Пример: "Deviant Art:https://www.deviantart.com/"
     */
    public List<String> customUrls;
    public String music;
    public String about;
    public ZonedDateTime lastUpdated;

    public UserInfoEntity() {
    }

    public UserInfoEntity( String userId, String name, String status, String dateOfBirth, String homeTown,
                           String instagramLink, String vkLink, List<String> customUrls,
                           String music, String about, ZonedDateTime lastUpdated ) {
        this.userId = userId;
        this.name = name;
        this.status = status;
        this.dateOfBirth = dateOfBirth;
        this.homeTown = homeTown;
        this.instagramLink = instagramLink;
        this.vkLink = vkLink;
        this.customUrls = customUrls;
        this.music = music;
        this.about = about;
        this.lastUpdated = lastUpdated;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId( String userId ) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus( String status ) {
        this.status = status;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth( String dateOfBirth ) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getHomeTown() {
        return homeTown;
    }

    public void setHomeTown( String homeTown ) {
        this.homeTown = homeTown;
    }

    public String getInstagramLink() {
        return instagramLink;
    }

    public void setInstagramLink( String instagramLink ) {
        this.instagramLink = instagramLink;
    }

    public String getVkLink() {
        return vkLink;
    }

    public void setVkLink( String vkLink ) {
        this.vkLink = vkLink;
    }

    public List<String> getCustomUrls() {
        return customUrls;
    }

    public void setCustomUrls( List<String> customUrls ) {
        this.customUrls = customUrls;
    }

    public String getMusic() {
        return music;
    }

    public void setMusic( String music ) {
        this.music = music;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout( String about ) {
        this.about = about;
    }

    public ZonedDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated( ZonedDateTime lastUpdated ) {
        this.lastUpdated = lastUpdated;
    }
}
