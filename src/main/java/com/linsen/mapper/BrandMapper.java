package com.linsen.mapper;

import com.linsen.pojo.Brand;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BrandMapper {
    List<Brand> selectAll();
    void add(Brand brand);
    void updata(Brand brand);
    void delete(Integer id);
    void deleteByIds(@Param("ids")Integer[] ids);
    List<Brand> selectByPage(@Param("begin") Integer begin,@Param("size") Integer size);
    Integer selectTotalCount();

    List<Brand> selectByPageAndCondition(@Param("begin") Integer begin,@Param("size") Integer size,@Param("brand") Brand brand);
    Integer selectTotalCountAndCondition(Brand brand);
}
