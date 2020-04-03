package com.css.gitapi.util.model;

/**
 * @author shangdu
 * @version 1.0
 * @date 2020/3/30 13:30
 */
public class CreateUserParams {
    /**
     * 用户名
     */
    String name;
    /**
     * 账号
     */
    String username;
    /**
     * 邮箱
     */
    String email;
    /**
     * 密码
     */
    String password;
    /**
     * 最大项目数
     */
    Integer projects_limit;
    /**
     * 是否可以创建群组
     */
    boolean can_create_group;
    /**
     * 是否可以创建项目
     */
    boolean can_create_project;
    /**
     * 是否是管理员
     */
    boolean is_admin;
    /**
     * 是否是外部用户
     * 外部用户无法看到内部或私人项目除非显式授予访问。另外,外部用户无法创建项目、组或个人片段。
     */
    boolean is_external;
    /**
     * skype
     */
    String skype;
    /**
     * linkedin
     */
    String linkedin;
    /**
     * twitter
     */
    String twitter;
    /**
     * website_url
     */
    String website_url;

    /**
     * bio 个人简介，不超过250字符
     */
    String bio;

    /**
     * 用户的配色方案，只能选择以下几个
     * 1、White
     * 2、Dark
     * 3、Solarized light
     * 4、Solarized dark
     * 5、Monokai
     */
    int color_scheme_id;

    /**
     * extern_uid 外部id
     */
    String extern_uid;

    /**
     * extra_shared_runners_minutes_limit
     */
    String extra_shared_runners_minutes_limit;

    /**
     * force_random_password 设置一个随机密码
     */
    boolean force_random_password;

    /**
     * group_id_for_saml
     */
    String group_id_for_saml;

    /**
     * location 用户的地点
     */
    String location;

    /**
     * organization
     */
    String organization;

    /**
     * User's profile is private - true, false (default), or null (will be converted to false)
     */
    String private_profile;

    /**
     * External provider name
     */
    String provider;

    /**
     * public_email
     */
    String public_email;

    /**
     * Send user password reset link - true or false(default)
     */
    boolean reset_password;

    /**
     * Pipeline minutes quota for this user (STARTER)
     */
    String shared_runners_minutes_limit;

    /**
     * Skip confirmation - true or false (default)
     */
    boolean skip_confirmation;

    /**
     * theme_id
     * 1:Indigo
     * 2:Light Indigo
     * 3:Blue
     * 4:Light Blue
     * 5:Green
     * 6:Light Green
     * 7:Red
     * 8:Light Red
     * 9:Dark
     * 10:Light
     */
    Integer theme_id;

    public CreateUserParams(String name, String username, String email, String password) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.projects_limit = 100;
        this.can_create_group = false;
        this.can_create_project = false;
        this.is_admin = false;
        this.is_external = false;
        this.skype = "";
        this.linkedin = "";
        this.twitter = "";
        this.website_url = "";
        this.bio = null;
        this.color_scheme_id = 1;
        this.extern_uid = "";
        this.extra_shared_runners_minutes_limit = "";
        this.force_random_password = false;
        this.group_id_for_saml = "";
        this.location = null;
        this.organization = null;
        this.private_profile = "";
        this.provider = "root";
        this.public_email = "";
        this.reset_password = false;
        this.shared_runners_minutes_limit = "";
        this.skip_confirmation = true;
        this.theme_id = 1;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Integer getProjects_limit() {
        return projects_limit;
    }

    public void setProjects_limit(Integer projects_limit) {
        this.projects_limit = projects_limit;
    }

    public boolean getCan_create_group() {
        return can_create_group;
    }

    public void setCan_create_group(boolean can_create_group) {
        this.can_create_group = can_create_group;
    }

    public boolean getCan_create_project() {
        return can_create_project;
    }

    public void setCan_create_project(boolean can_create_project) {
        this.can_create_project = can_create_project;
    }

    public boolean getIs_admin() {
        return is_admin;
    }

    public void setIs_admin(boolean is_admin) {
        this.is_admin = is_admin;
    }

    public boolean getIs_external() {
        return is_external;
    }

    public void setIs_external(boolean is_external) {
        this.is_external = is_external;
    }

    public String getSkype() {
        return skype;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getWebsite_url() {
        return website_url;
    }

    public void setWebsite_url(String website_url) {
        this.website_url = website_url;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public int getColor_scheme_id() {
        return color_scheme_id;
    }

    public void setColor_scheme_id(int color_scheme_id) {
        this.color_scheme_id = color_scheme_id;
    }

    public String getExtern_uid() {
        return extern_uid;
    }

    public void setExtern_uid(String extern_uid) {
        this.extern_uid = extern_uid;
    }

    public String getExtra_shared_runners_minutes_limit() {
        return extra_shared_runners_minutes_limit;
    }

    public void setExtra_shared_runners_minutes_limit(String extra_shared_runners_minutes_limit) {
        this.extra_shared_runners_minutes_limit = extra_shared_runners_minutes_limit;
    }

    public boolean getForce_random_password() {
        return force_random_password;
    }

    public void setForce_random_password(boolean force_random_password) {
        this.force_random_password = force_random_password;
    }

    public String getGroup_id_for_saml() {
        return group_id_for_saml;
    }

    public void setGroup_id_for_saml(String group_id_for_saml) {
        this.group_id_for_saml = group_id_for_saml;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getPrivate_profile() {
        return private_profile;
    }

    public void setPrivate_profile(String private_profile) {
        this.private_profile = private_profile;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getPublic_email() {
        return public_email;
    }

    public void setPublic_email(String public_email) {
        this.public_email = public_email;
    }

    public boolean getReset_password() {
        return reset_password;
    }

    public void setReset_password(boolean reset_password) {
        this.reset_password = reset_password;
    }

    public String getShared_runners_minutes_limit() {
        return shared_runners_minutes_limit;
    }

    public void setShared_runners_minutes_limit(String shared_runners_minutes_limit) {
        this.shared_runners_minutes_limit = shared_runners_minutes_limit;
    }

    public boolean getSkip_confirmation() {
        return skip_confirmation;
    }

    public void setSkip_confirmation(boolean skip_confirmation) {
        this.skip_confirmation = skip_confirmation;
    }

    public Integer getTheme_id() {
        return theme_id;
    }

    public void setTheme_id(Integer theme_id) {
        this.theme_id = theme_id;
    }
}
