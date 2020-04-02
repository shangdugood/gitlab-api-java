package com.css.gitapi.util.enums;

/**
 * @author shangdu
 * @version 1.0
 * @date 2020/3/31 10:03
 */
public enum HttpCode {

    OK(200, "成功"),
    DELETESUCCESS(204, "删除成功"),
    MARKEDDELETE(202, "已标记为删除"),
    CREATED(201, "创建成功"),
    NOTMODIFIED(304, "请求的资源未被修改"),
    BADREQUEST(400, "参数不全"),
    UNAUTHORIZED(401, "无权访问"),
    FORBIDDEN(403, "禁止访问"),
    NOTFOUND(404, "无法访问"),
    METHODNOTALLOWED(405, "方法错误"),
    CONFILICT(409, "冲突"),
    DENIED(412, "访问被拒绝"),
    UNPROCESSABLE(422, "无法处理的实体"),
    SERVERERROR(500, "服务器错误");

    private Integer code;
    private String description;

    HttpCode(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

}
