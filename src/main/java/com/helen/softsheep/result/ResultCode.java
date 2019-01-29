package com.helen.softsheep.result;

/**
 * 返回码结构定义, 框架和所有业务可以继承实现自己的CODE码
 * 
 */

public interface ResultCode {

    /**
     * 系统CODE码
     * 
     * @return
     */
    int code();

    /**
     * 提示信息
     * 
     * @return
     */
    String msg();

}
