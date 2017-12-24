package cn.campus.platfrom.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

public class AppClient implements java.io.Serializable {

    private static final long serialVersionUID = 723364631613037503L;

    @TableId(type = IdType.UUID)
    private String id;
    private String name;
    private String secret;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = "wxf"+id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}
