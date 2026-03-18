package org.example.springbootapi.Pojo;

/**
 * 性别枚举
 */
public enum Gender {
    MALE("男"),
    FEMALE("女"),
    UNKNOWN("保密");

    private final String desc;

    Gender(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    // 可选：根据中文描述获取枚举值（方便接口传参转换）
    public static Gender getByDesc(String desc) {
        for (Gender gender : values()) {
            if (gender.desc.equals(desc)) {
                return gender;
            }
        }
        return UNKNOWN; // 未匹配时默认保密
    }
}