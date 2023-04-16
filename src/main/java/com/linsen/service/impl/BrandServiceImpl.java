package com.linsen.service.impl;

import com.linsen.mapper.BrandMapper;
import com.linsen.pojo.Brand;
import com.linsen.pojo.PageBean;
import com.linsen.service.BrandService;
import com.linsen.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class BrandServiceImpl implements BrandService {
    SqlSessionFactory factory=SqlSessionFactoryUtils.getSqlSessionFactory();

    public List<Brand> selectAll(){
        SqlSession sqlSession = factory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        List<Brand> brands = mapper.selectAll();

        sqlSession.close();

        return brands;
    }

    @Override
    public void add(Brand brand) {
        SqlSession sqlSession = factory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        mapper.add(brand);

        sqlSession.commit();

        sqlSession.close();
    }

    public void updata(Brand brand){
        SqlSession sqlSession = factory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        mapper.updata(brand);

        sqlSession.commit();

        sqlSession.close();
    }

    public void delete(Integer id){
        SqlSession sqlSession = factory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        mapper.delete(id);

        sqlSession.commit();

        sqlSession.close();
    }

    public void deleteByIds(Integer[] ids){
        SqlSession sqlSession = factory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        mapper.deleteByIds(ids);

        sqlSession.commit();

        sqlSession.close();
    }

    public PageBean<Brand> selectByPage(Integer currentPage, Integer pageSize){
        SqlSession sqlSession = factory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        int begin=(currentPage-1)*pageSize;
        int size=pageSize;

        List<Brand> brands = mapper.selectByPage(begin, size);
        Integer totalCount = mapper.selectTotalCount();

        PageBean<Brand> pageBean = new PageBean<>();
        pageBean.setRows(brands);
        pageBean.setTotalCount(totalCount);

        sqlSession.close();

        return pageBean;
    }

    public PageBean<Brand> selectByPageAndCondition(Integer currentPage,Integer pageSize,Brand brand){
        SqlSession sqlSession = factory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        int begin=(currentPage-1)*pageSize;
        int size=pageSize;

        String brandName=brand.getBrandName();
        if(brandName!=null&&brandName.length()>0){
            brand.setBrandName("%"+brandName+"%");
        }
        String companyName=brand.getCompanyName();
        if(companyName!=null&&companyName.length()>0){
            brand.setCompanyName("%"+companyName+"%");
        }

        List<Brand> brands=mapper.selectByPageAndCondition(begin,size,brand);
        Integer totalCount = mapper.selectTotalCountAndCondition(brand);

        PageBean<Brand> pageBean = new PageBean<>();
        pageBean.setRows(brands);
        pageBean.setTotalCount(totalCount);

        sqlSession.close();

        return pageBean;
    }
}
