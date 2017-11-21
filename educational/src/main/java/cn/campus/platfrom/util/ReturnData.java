package cn.campus.platfrom.util;

public class ReturnData implements java.io.Serializable {

    private static final long serialVersionUID = -8265051199512275378L;

    private Object data;
    private String msg;
    private String code;
    private boolean flag;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
