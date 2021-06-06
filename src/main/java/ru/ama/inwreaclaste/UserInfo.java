package ru.ama.inwreaclaste;

import ru.ama.inwreaclaste.rest.dto.UserInfoDto;

/**
 * @author AmaJohny, amdathlon103@gmail.com
 * created 06.06.2021
 */
public class UserInfo {

    public String name;

    public String status;

    public Boolean block1;

    public String dob;

    public String htown;

    public Boolean block2;

    public String instagram;

    public String vk;

    public String url1;

    public String urlname1;

    public String url2;

    public String urlname2;

    public Boolean block3;

    public String music;

    public String about;

    public UserInfo( String name, String status, Boolean block1, String dob, String htown, Boolean block2,
                     String instagram, String vk, String url1, String urlname1, String url2, String urlname2,
                     Boolean block3, String music, String about ) {
        this.name = name;
        this.status = status;
        this.block1 = block1;
        this.dob = dob;
        this.htown = htown;
        this.block2 = block2;
        this.instagram = instagram;
        this.vk = vk;
        this.url1 = url1;
        this.urlname1 = urlname1;
        this.url2 = url2;
        this.urlname2 = urlname2;
        this.block3 = block3;
        this.music = music;
        this.about = about;
    }

    public UserInfo( String name ) {
        this.name = name;
        this.status = "";
        this.dob = "";
        this.htown = "";
        this.instagram = "";
        this.vk = "";
        this.url1 = "";
        this.urlname1 = "";
        this.url2 = "";
        this.urlname2 = "";
        this.music = "";
        this.about = "";
    }

    //TODO сделать красиво, это плохо.
    public UserInfo( UserInfoDto userInfoDto ) {
        this.name = userInfoDto.name;
        this.status = userInfoDto.status;
        this.dob = userInfoDto.dob;
        this.htown = userInfoDto.htown;
        this.instagram = userInfoDto.instagram;
        this.vk = userInfoDto.vk;
        this.url1 = userInfoDto.url1;
        this.urlname1 = userInfoDto.urlname1;
        this.url2 = userInfoDto.url2;
        this.urlname2 = userInfoDto.urlname2;
        this.music = userInfoDto.music;
        this.about = userInfoDto.about;
        this.block1 = !( dob.isEmpty() && htown.isEmpty() );
        this.block2 = !( instagram.isEmpty() && vk.isEmpty() && url1.isEmpty() && url2.isEmpty() );
        this.block3 = !( music.isEmpty() && about.isEmpty() );
    }
}
