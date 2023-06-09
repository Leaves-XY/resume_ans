package com.yxy.resume.common;
import com.alibaba.fastjson.TypeReference;
import java.util.List;

/**
 * @author YeXingyi
 * @version 1.0
 * @date 2023/6/9 13:04
 */
public class StringTypeHandler extends ListTypeHandler<String> {
    // 将ListTypeHandler<T>（T为任意对象），具体为特定的对象String
    @Override
    protected TypeReference<List<String>> specificType() {
        return new TypeReference<List<String>>() {
        };
    }
}
