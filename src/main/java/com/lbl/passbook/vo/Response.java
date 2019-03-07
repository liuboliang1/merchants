package com.lbl.passbook.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response {

    /** 错误码， 成功为0 */
    private Integer errCode = 0;

    /** 错误消息， 成功为空字符串"" */
    private String errMsg = "";

    private Object data;

    /** 正确的响应方式 */
    public Response(Object data) {
        this.data = data;
    }
}
