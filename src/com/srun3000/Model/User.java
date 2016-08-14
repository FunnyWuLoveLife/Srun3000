package com.srun3000.Model;

/**
 * Created by xbc922 on 2016/8/13.
 */
public class User {
    private String username;
    private String password;

    /**
     * 有参构造函数
     *
     * @param username
     * @param password
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * 无参构造函数
     */
    public User() {
    }

    /**
     * 用户加密算法，用户名，密码
     */
    public void userEncode() {
        setUsername(User.userNameEncode(this.getUsername()));
        setPassword(User.passwordEncode(this.getPassword()));
    }

    /**
     * 用户名加密算法
     *
     * @param username 用户名
     * @return 加密后的用户名
     */
    public static String userNameEncode(String username) {

        String rtn = "{SRUN3}\r\n";
        char[] usr_arr = username.toCharArray();
        for (int i = 0; i < usr_arr.length; ++i) {
            rtn += (char) ((int) usr_arr[i] + 4);
        }
        return rtn;
    }

    /**
     * 密码加密算法
     *
     * @param pwd 密码
     * @return
     */
    public static String passwordEncode(String pwd) {
        final String encoding_key = "1234567890";
        String pe = "";
        for (int i = 0; i < pwd.length(); i++) {
            int ki = encoding_key.charAt(encoding_key.length() - i % encoding_key.length() - 1) ^ pwd.charAt(i);
            char _l = (char) ((ki & 0x0F) + 0x36);
            char _h = (char) ((ki >> 4 & 0x0F) + 0x63);
            if (i % 2 == 0) pe += String.valueOf(_l) + String.valueOf(_h);
            else pe += String.valueOf(_h) + String.valueOf(_l);
        }
        return pe;
    }

    /**
     * 覆写equals方法
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        User user = (User) obj;
        if (this == obj) return true;
        if (obj == null) return false;
        if (this.getClass() != obj.getClass()) return false;//判断是否属于同一个类
        return this.username.equals(user.getUsername()) && this.password.equals(user.getPassword());

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
