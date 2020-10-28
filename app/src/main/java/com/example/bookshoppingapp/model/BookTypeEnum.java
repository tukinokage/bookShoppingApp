package com.example.bookshoppingapp.model;


import com.example.bookshoppingapp.dao.DataBaseHelper;

/*********书本类型*********/
public enum BookTypeEnum {
    POPULAR("popular","热门"),
    SCIENCE("science","科学"),
    COMPUTER("computer", "计算机"),
    NOTE("novel", "小说"),
    ART("art", "艺术"),
    HISTORY("history", "历史");

    private String typeStr;
    private String chineseStr;

    BookTypeEnum(String typeStr, String chineseStr){
        this.typeStr = typeStr;
        this.chineseStr = chineseStr;
    }

    //获取类型名
    public String getTypeStr(){
        return typeStr;
    }


    //获取类型中文名
    public String getTypeChinese(){
        return chineseStr;
    }

    /**根据类型名获取对应enum*
     * @Param str 类型名
     * */
    public static BookTypeEnum getEnumByTypeStr(String str){
        for (BookTypeEnum typeEnum:BookTypeEnum.values()
        ) {
            if(str.equals(typeEnum.getTypeStr())){
                return typeEnum;
            }
        }

        return null;
    }


    /**根据类型中文名获取对应enum*
     * @Param str 中文类型名
     * */
    public static BookTypeEnum getEnumByChinese(String str){
        for (BookTypeEnum typeEnum:BookTypeEnum.values()
        ) {
            if(str.equals(typeEnum.getTypeChinese())){
                return typeEnum;
            }
        }

        return null;
    }


}
