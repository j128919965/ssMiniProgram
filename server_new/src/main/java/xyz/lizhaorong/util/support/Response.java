package xyz.lizhaorong.util.support;

public class Response<T> {

    private static final String OK = "ok";
    private static final String ERROR = "error";

    private boolean success;
    private String message;
    private T data;

    private static final Response<Boolean> succInstance = new Response<Boolean>().success(true);

    public static Response<Boolean> staticSuccess(){
        return succInstance;
    }

    public Response<T> success(){
        message=OK;
        success=true;
        return this;
    }

    public Response<T> success(T data){
        message=OK;
        success=true;
        this.data=data;
        return this;
    }

    public Response<T> failure(){
        message=ERROR;
        success=false;
        return this;
    }

    public Response<T> failure(String message){
        this.message=message;
        success=false;
        return this;
    }

    public Response<T> failure(String message,T data){
        this.message=message;
        this.data=data;
        success=false;
        return this;
    }


    public static Response<Integer> failure(ErrorCode errorCode){
        Response<Integer> response = new Response<>();
        response.message=errorCode.message();
        response.data = errorCode.code();
        response.success=false;
        return response;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }

    @Override
    public String toString() {
        return "Response{" +
                "success=" + success +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}

