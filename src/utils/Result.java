package utils;
import java.util.HashMap;
import java.util.Map;

/**
 * 结果类,用于接收数据库返回的数据
 */
public class Result {
    private Boolean success;    //数据接收结果true or false
    private Integer code;       //数据接收结果状态码
    private String message;     //数据接收结果消息
    private Map<String,Object> data = new HashMap<>();  //数据接收结果数据
    private static Integer SUCCESS = 200;   //成功状态码
    private static Integer ERROR = 201;     //失败状态码

    /**
     * 类的构造方法
     */
    private Result(){}
    /**
     * 默认结果消息
     * @return Result
     */
    public static Result ok() {
        Result r = new Result();
        r.setSuccess(true);
        r.setCode(SUCCESS);
        r.setMessage("成功");
        return r;
    }
    public static Result error() {
        Result r = new Result();
        r.setSuccess(false);
        r.setCode(ERROR);
        r.setMessage("失败");
        return r;
    }
    /**
     * 自定义结果消息
     * @param msg // 自定义的结果信息
     * @return Result
     */
    public static Result ok(String msg) {
        Result r = new Result();
        r.setSuccess(true);
        r.setCode(SUCCESS);
        r.setMessage(msg);
        return r;
    }
    public static Result error(String msg) {
        Result r = new Result();
        r.setSuccess(false);
        r.setCode(ERROR);
        r.setMessage(msg);
        return r;
    }
    /**
     * 设置结果接收的数据
     * @param key
     * @param value
     * @return Result
     */
    public Result data(String key, Object value){
        this.data.put(key,value);
        return this;
    }
    public Result data(Map<String,Object> map){
        this.setData(map);
        return this;
    }
    /**
     * 结果类的get和set方法
     */
    public Boolean getSuccess() {
        return success;
    }
    public void setSuccess(Boolean success) {
        this.success = success;
    }
    public Integer getCode() {
        return code;
    }
    public void setCode(Integer code) {
        this.code = code;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Map<String, Object> getData() {
        return data;
    }
    public void setData(Map<String, Object> data) {
        this.data = data;
    }
    public static Integer getSUCCESS() {
        return SUCCESS;
    }
    public static void setSUCCESS(Integer SUCCESS) {
        Result.SUCCESS = SUCCESS;
    }
    public static Integer getERROR() {
        return ERROR;
    }
    public static void setERROR(Integer ERROR) {
        Result.ERROR = ERROR;
    }
}
