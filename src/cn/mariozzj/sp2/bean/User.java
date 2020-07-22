package cn.mariozzj.sp2.bean;
import lombok.Data;

@Data
public class User {
    public int id;
    public String username;
    public String password;
    public String email;
    public int userType;//等级，1-4
    //注册时间
//    public int getId(){
//        return id;
//    };
//    public void setId(int id){
//        this.id = id;
//    }
//    public String getUsername(){
//        return username;
//    }
//    public void setUsername(String username){
//        this.username = username;
//    }
//    public String getPassword(){
//        return password;
//    }
//    public void setPassword(String password){
//        this.password = password;
//    }
//    public String getEmail(){
//        return email;
//    }
//    public void setEmail(String email){
//        this.email = email;
//    }
//    public int getUserType(){
//        return userType;
//    }
//    public void setUserType(int userType){
//        this.userType = userType;
//    }

}
