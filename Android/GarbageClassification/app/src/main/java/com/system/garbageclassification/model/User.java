package com.system.garbageclassification.model;

import java.io.Serializable;

/**
 * 用户
 */
public class User {

    /**
     * code : 1
     * message : 获取成功
     * result : {"id":1,"username":"zhouli","password":"zl1234","nickname":"lilyChow","phone":"15695522635","avatar":null}
     */

    private int code;
    private String message;
    private ResultBean result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean implements Serializable {
        /**
         * id : 1
         * username : zhouli
         * password : zl1234
         * nickname : lilyChow
         * phone : 15695522635
         * avatar : null
         */

        private int id;
        private String username;
        private String password;
        private String nickname;
        private String phone;
        private String avatar;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }
    }
}
