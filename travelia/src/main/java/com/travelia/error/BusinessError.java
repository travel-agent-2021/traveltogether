package com.travelia.error;

public enum  BusinessError implements CommonError {

    /* 100XX 参数类型错误码 */
    PARAMETER_VALIDATION_ERROR(10001, "参数不合法"),
    PARAMETER_MISMATCH_ERROR(10002, "参数类型不正确，请重试"),
    UNKNOWN_ERROR(10002, "未知错误"),

    /* 200XX 用户类型错误码 */
    USER_NOT_EXIST(20001, "用户不存在"),
    USER_LOGIN_FAIL(20002, "用户名或密码错误"),
    USER_NOT_LOGIN(20003, "用户未登录"),
    USER_TELEPHONE_NOT_EMPTY(20004, "手机号不能为空"),
    USER_NOT_FOUND(20005, "用户不存在"),

    /* 300XX商品错误码 */
    ITEM_DELETE_FAIL(30001, "商品不存在！"),
    ITEM_NOT_FOUND(30002, "未查询到商品信息！"),


    /* 400XX 管理员类型错误码 */
    ADMIN_NOT_EXIST(40001, "管理员账号不存在"),
    ADMIN_LOGIN_FAIL(40002, "账号或密码错误"),
    ADMIN_NOT_LOGIN(40003, "管理员未登录"),


    /* 500XX 城市类型错误码 */
    CITY_NOT_FOUND(40003, "城市不存在");


    private int errCode;
    private String errMsg;

    private BusinessError(int errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    @Override
    public int getErrorCode() {
        return this.errCode;
    }

    @Override
    public String getErrorMsg() {
        return this.errMsg;
    }

    @Override
    public CommonError setErrorMsg(String errorMsg) {
        this.errMsg = errorMsg;
        return this;
    }

}
