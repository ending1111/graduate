package com.system.garbageclassification.model;

import java.io.Serializable;
import java.util.List;

/**
 * 垃圾
 */
public class Garbage {

    /**
     * code : 1
     * message : 获取成功
     * result : [{"id":12,"gName":"废油漆桶","gType":4,"function":"有害垃圾投放指导\\n\\n1、有害垃圾指废电池、废灯管、废药品、废油漆及其容器等对人体健康或者自然环境造成直接或者潜在危害的生活废弃物。\\n2、常见包括废电池、废荧光灯管、废灯泡、废水银温度计、废油漆桶、过期药品等。有害有毒垃圾需特殊正确的方法安全处理。"},{"id":13,"gName":"废旧电池","gType":4,"function":"有害垃圾投放指导\\n\\n1、有害垃圾指废电池、废灯管、废药品、废油漆及其容器等对人体健康或者自然环境造成直接或者潜在危害的生活废弃物。\\n2、常见包括废电池、废荧光灯管、废灯泡、废水银温度计、废油漆桶、过期药品等。有害有毒垃圾需特殊正确的方法安全处理。"},{"id":21,"gName":"废铁","gType":3,"function":"可回收物投放指导\\n\\n1、可回收物应轻投轻放，清洁干燥、避免污染；\\n2、废纸尽量平整；\\n3、立体包装物请清空内容物，清洁后扁压投放；\\n4、有尖锐边角的，应包裹后投放。"},{"id":25,"gName":"医疗废水","gType":4,"function":"有害垃圾投放指导\\n\\n1、有害垃圾指废电池、废灯管、废药品、废油漆及其容器等对人体健康或者自然环境造成直接或者潜在危害的生活废弃物。\\n2、常见包括废电池、废荧光灯管、废灯泡、废水银温度计、废油漆桶、过期药品等。有害有毒垃圾需特殊正确的方法安全处理。"}]
     */

    private int code;
    private String message;
    private List<ResultBean> result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean implements Serializable {
        /**
         * id : 12
         * gName : 废油漆桶
         * gType : 4
         * function : 有害垃圾投放指导\n\n1、有害垃圾指废电池、废灯管、废药品、废油漆及其容器等对人体健康或者自然环境造成直接或者潜在危害的生活废弃物。\n2、常见包括废电池、废荧光灯管、废灯泡、废水银温度计、废油漆桶、过期药品等。有害有毒垃圾需特殊正确的方法安全处理。
         */

        private int id;
        private String gName;
        private int gType;
        private String function;

        public ResultBean(String gName, int gType, String function) {
            this.gName = gName;
            this.gType = gType;
            this.function = function;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGName() {
            return gName;
        }

        public void setGName(String gName) {
            this.gName = gName;
        }

        public int getGType() {
            return gType;
        }

        public void setGType(int gType) {
            this.gType = gType;
        }

        public String getFunction() {
            return function;
        }

        public void setFunction(String function) {
            this.function = function;
        }
    }
}
