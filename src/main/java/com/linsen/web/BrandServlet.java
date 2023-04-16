package com.linsen.web;

import com.alibaba.fastjson.JSON;
import com.linsen.pojo.Brand;
import com.linsen.pojo.PageBean;
import com.linsen.service.BrandService;
import com.linsen.service.impl.BrandServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet("/brand/*")
public class BrandServlet extends BaseServlet {
    private BrandService brandService = new BrandServiceImpl();

    public void selectAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Brand> brands = brandService.selectAll();

        String jsonString = JSON.toJSONString(brands);

        resp.setContentType("text/json");
        resp.setCharacterEncoding("utf-8");
        resp.getWriter().write(jsonString);
    }

    public void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        String params = reader.readLine();

        String patternzu = "\"ordered\":\"(.*?)\"";
        Pattern patternzzu = Pattern.compile(patternzu);
        Matcher matcherzu = patternzzu.matcher(params);
        if (matcherzu.find()) {
            String group = matcherzu.group(1);
            boolean matches = group.matches("\\d+");
            if (!matches) {
                resp.getWriter().write("fail");
                return;
            }
        }

        Brand brand = JSON.parseObject(params, Brand.class);

        brandService.add(brand);

        resp.getWriter().write("success");
    }

    public void updata(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        String params = reader.readLine();

        String patternzu = "\"ordered\":\"(.*?)\"";
        Pattern patternzzu = Pattern.compile(patternzu);
        Matcher matcherzu = patternzzu.matcher(params);
        if (matcherzu.find()) {
            String group = matcherzu.group(1);
            boolean matches = group.matches("\\d+");
            if (!matches) {
                resp.getWriter().write("fail");
                return;
            }
        }

        Brand brand = JSON.parseObject(params, Brand.class);

        brandService.updata(brand);

        resp.getWriter().write("success");
    }

    public void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        String params = reader.readLine();

        Integer id = JSON.parseObject(params, Integer.class);

        brandService.delete(id);

        resp.getWriter().write("success");
    }

    public void deleteByIds(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        String params = reader.readLine();

        Integer[] ids = JSON.parseObject(params, Integer[].class);

        if (ids.length == 0) {
            resp.getWriter().write("not selected");
            return;
        }

        brandService.deleteByIds(ids);

        resp.getWriter().write("success");
    }

    public void selectByPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String _currentPage = req.getParameter("currentPage");
        String _pageSize = req.getParameter("pageSize");
        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);

        PageBean<Brand> pageBean = brandService.selectByPage(currentPage, pageSize);

        String jsonString = JSON.toJSONString(pageBean);

        resp.setContentType("text/json");
        resp.setCharacterEncoding("utf-8");
        resp.getWriter().write(jsonString);
    }

    public void selectByPageAndCondition(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String _currentPage = req.getParameter("currentPage");
        String _pageSize = req.getParameter("pageSize");
        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);

        BufferedReader reader = req.getReader();
        String params = reader.readLine();

        Brand brand = JSON.parseObject(params, Brand.class);

        PageBean<Brand> pageBean = brandService.selectByPageAndCondition(currentPage, pageSize, brand);

        String jsonString = JSON.toJSONString(pageBean);

        System.out.println(jsonString);

        resp.setContentType("text/json");
        resp.setCharacterEncoding("utf-8");
        resp.getWriter().write(jsonString);
    }
}
