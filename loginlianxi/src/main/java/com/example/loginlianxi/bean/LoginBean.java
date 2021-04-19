package com.example.loginlianxi.bean;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.List;

public class LoginBean implements Serializable {

    /**
     * data : {"admin":false,"chapterTops":[],"coinCount":0,"collectIds":[14254,14241,14227,14210,14188,14187,14135,14106,14083,14073,14037,14028,13996,13957,13945,13928,13918,13917,13896,13865,13813,13812,13769,13753,13743,13720,13690,13689,13655,13638,14257,14258,14259,14242,9359,7987,7445,5871,3008,3423,13975,13974,14284,13976,13740,13675,13458,10690,14332,14362,14354,14369,14618,15077,15176,12554,926,15724],"email":"","icon":"","id":122,"nickname":"123123","password":"","publicName":"123123","token":"","type":0,"username":"123123"}
     * errorCode : 0
     * errorMsg :
     */

    private DataBean data;
    private int errorCode;
    private String errorMsg;

    public static LoginBean objectFromData(String str) {

        return new Gson().fromJson(str, LoginBean.class);
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public static class DataBean implements Serializable {
        /**
         * admin : false
         * chapterTops : []
         * coinCount : 0
         * collectIds : [14254,14241,14227,14210,14188,14187,14135,14106,14083,14073,14037,14028,13996,13957,13945,13928,13918,13917,13896,13865,13813,13812,13769,13753,13743,13720,13690,13689,13655,13638,14257,14258,14259,14242,9359,7987,7445,5871,3008,3423,13975,13974,14284,13976,13740,13675,13458,10690,14332,14362,14354,14369,14618,15077,15176,12554,926,15724]
         * email :
         * icon :
         * id : 122
         * nickname : 123123
         * password :
         * publicName : 123123
         * token :
         * type : 0
         * username : 123123
         */

        private boolean admin;
        private int coinCount;
        private String email;
        private String icon;
        private int id;
        private String nickname;
        private String password;
        private String publicName;
        private String token;
        private int type;
        private String username;
        private List<?> chapterTops;
        private List<Integer> collectIds;

        public static DataBean objectFromData(String str) {

            return new Gson().fromJson(str, DataBean.class);
        }

        public boolean isAdmin() {
            return admin;
        }

        public void setAdmin(boolean admin) {
            this.admin = admin;
        }

        public int getCoinCount() {
            return coinCount;
        }

        public void setCoinCount(int coinCount) {
            this.coinCount = coinCount;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getPublicName() {
            return publicName;
        }

        public void setPublicName(String publicName) {
            this.publicName = publicName;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public List<?> getChapterTops() {
            return chapterTops;
        }

        public void setChapterTops(List<?> chapterTops) {
            this.chapterTops = chapterTops;
        }

        public List<Integer> getCollectIds() {
            return collectIds;
        }

        public void setCollectIds(List<Integer> collectIds) {
            this.collectIds = collectIds;
        }
    }
}
