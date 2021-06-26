package com.turing.website.enums;

/**
 * @author Jack
 * @date 2019-07-27-18:07
 */
public enum MyCustomizeErrorCode implements CustomizeErrorCode {
    /**
     * 2001:没有找到成员
     */
    MEMBER_NOT_FOUND(2001,"找不到对应成员, 请检查后重新尝试请求!"),
    /**
     * 2002:没有找到对应奖项
     */
    AWARD_NOT_FOUND(2002,"没有找到对应的奖项,请检查后重新尝试请求!"),
    /**
     * 2003:奖项信息填写不正确!
     */
    AWARD_IS_NOT_CORRECT(2003, "奖项的信息不正确!请检查后重新尝试请求!"),
    /**
     * 2004:历史内容不能为空!
     */
    HISTORY_INFO_IS_NULL(2004, "团队历史介绍信息不能为空!"),
    /**
     * 2005:没有找到这一条历史信息!有人侵入数据库强制删除了!
     */
    HISTORY_NOT_FOUND(2005, "没有找到这一条历史信息!有人侵入数据库强制删除了!"),
    /**
     * 2006:通告信息填写不完整或不正确!
     */
    INFORM_IS_NOT_CORRECT(2006, "通告的信息填写不正确!请检查后重新尝试请求!"),
    /**
     * 2007:通告没有找到
     */
    INFORM_NOT_FOUND(2007, "没有找到对应的通告, 请检查后再尝试!"),
    /**
     *  2008:必须上传图片
     */
    PHOTO_IS_NULL(2008, "必须上传图片!"),
    /**
     * 2009:生活内容填写不正确
     */
    LIVE_IS_NOT_CORRECT(2009, "生活的信息填写不正确!请检查后重新尝试请求!"),
    /**
     * 3001:对应的团队活动记录没有找到
     */
    LIVE_NOT_FOUND(3001, "没有找到对应的团队活动记录, 请检查后再尝试请求"),
    /**
     * 3002:奖项信息填写不正确!
     */
    PROJECT_IS_NOT_CORRECT(3002, "项目的信息不正确!请检查后重新尝试请求!"),
    /**
     * 3003:没有找到对应项目
     */
    PROJECT_NOT_FOUND(3003, "没有找到对应项目, 请重新检查后再尝试请求!"),
    /**
     * 3004:成员信息填写不正确!
     */
    MEMBER_IS_NOT_CORRECT(3004, "成员信息填写不正确!"),
    /**
     * 3005:"密码不正确!"
     */
    PASS_NOT_CORRECT(3005, "密码不正确!请重新尝试!"),
    /**
     * 3006:"尚未登录!"
     */
    NOT_LOGIN(3006, "尚未登录!"),
    /**
     * 404:没有找到对应的请求路径
     */
    PAGE_NOT_FOUND(404, "你要请求的页面好像暂时飘走了...要不试试请求其它页面?"),
    /**
     * 500:服务端异常
     */
    INTERNAL_SERVER_ERROR(500, "服务器冒烟了...要不等它降降温后再来访问?"),
    /**
     * 3007:文件大小超出限制！
     */
    FILE_MAX_SIZE_EXCEPTION(3007, "文件大小超出限制！"),
    /**
     * 3008:没有找到对应老师的信息！
     */
    TEACHER_NOT_FOUND(3008, "没有找到对应老师的信息!"),
    /**
     * 3009：没有权限
     */
    NOT_ALLOWED(3009, "您没有权限访问该区域的内容"),

    LEADINSPECTION_NOT_FOUND(3010, "没有找到领导视察记录"),

    LEADINSPECTION_IS_NOT_FOUND(3011, "领导视察内容填写不正确"),

    RESUME_NOT_FIND(3012, "请先投递简历"),


    RESUME_STOP(3013,"现在还没到投简历的时间"),

    Date_FORMAT_REEOR(3014,"时间格式不对");

    private String message;
    private Integer code;

    @Override
    public Integer getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    MyCustomizeErrorCode(Integer code, String message) {
        this.message = message;
        this.code = code;
    }

}


