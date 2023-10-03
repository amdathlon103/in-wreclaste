package ru.ama.inwreaclaste.database.entities;

import ru.ama.inwreaclaste.User;

import java.util.Objects;

/**
 * @author AmaJohny, amdathlon103@gmail.com
 * created 06.06.2021
 */
public class UserWithInfo {

    public String id;

    public String login;

    public String password;

    public String email;

    public User.Role role;

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

    public UserWithInfo( String id, String login, String password, String email, User.Role role, String name,
                         String status, Boolean block1, String dob, String htown, Boolean block2, String instagram,
                         String vk, String url1, String urlname1, String url2, String urlname2, Boolean block3,
                         String music, String about ) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.email = email;
        this.role = role;
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

    public UserWithInfo( String id, String login, String password, String email, User.Role role ) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.email = email;
        this.role = role;
        this.name = "";
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
        this.block1 = !( dob.isEmpty() && htown.isEmpty() );
        this.block2 = !( instagram.isEmpty() && vk.isEmpty() && url1.isEmpty() && url2.isEmpty() );
        this.block3 = !( music.isEmpty() && about.isEmpty() );
    }

    public UserWithInfo() {

    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( !( o instanceof UserWithInfo ) ) return false;
        if ( !super.equals( o ) ) return false;
        UserWithInfo that = (UserWithInfo) o;
        return id.equals( that.id ) &&
               Objects.equals( login, that.login ) &&
               Objects.equals( email, that.email );
    }

    @Override
    public int hashCode() {
        return Objects.hash( id, login, email );
    }

    @Override public String toString() {
        return "UserWithInfo{" +
               "id=" + id +
               ", login='" + login + '\'' +
               ", password='" + password + '\'' +
               ", email='" + email + '\'' +
               ", role=" + role +
               ", name='" + name + '\'' +
               ", status='" + status + '\'' +
               ", block1=" + block1 +
               ", dob='" + dob + '\'' +
               ", htown='" + htown + '\'' +
               ", block2=" + block2 +
               ", instagram='" + instagram + '\'' +
               ", vk='" + vk + '\'' +
               ", url1='" + url1 + '\'' +
               ", urlname1='" + urlname1 + '\'' +
               ", url2='" + url2 + '\'' +
               ", urlname2='" + urlname2 + '\'' +
               ", block3=" + block3 +
               ", music='" + music + '\'' +
               ", about='" + about + '\'' +
               '}';
    }
}
