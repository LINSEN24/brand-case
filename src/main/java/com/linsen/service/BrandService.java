package com.linsen.service;

import com.linsen.pojo.Brand;
import com.linsen.pojo.PageBean;

import java.util.List;

public interface BrandService {
    List<Brand> selectAll();
    void add(Brand brand);

    void updata(Brand brand);
    void delete(Integer id);

    void deleteByIds(Integer[] ids);

    PageBean<Brand> selectByPage(Integer currentPage,Integer pageSize);

    PageBean<Brand> selectByPageAndCondition(Integer currentPage,Integer pageSize,Brand brand);
}
