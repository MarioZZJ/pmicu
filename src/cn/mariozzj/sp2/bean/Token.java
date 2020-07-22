package cn.mariozzj.sp2.bean;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Token {
    public int id;
    public String tokenname;
    public String username;
}
