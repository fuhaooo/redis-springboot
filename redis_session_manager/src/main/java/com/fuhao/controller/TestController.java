package com.fuhao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author fuhao
 * @Date 2021/7/15 3:52 下午
 * @Version 1.0
 */
@Controller
@RequestMapping("test")
public class TestController {


    //使用redis的session管理，当session中的数据发生变化时，必须将session中变化的数据同步到redis中

    @RequestMapping("test")
    public void test(HttpServletRequest request, HttpServletResponse response) throws IOException {

        List<String> list = (List<String>) request.getSession().getAttribute("list");

        if(list == null){
            list = new ArrayList<>();
        }

        list.add("xxxx");
        /**
         * 每次session发生变化都要同步session
         */
        request.getSession().setAttribute("list",list);

        response.getWriter().println("size:"+list.size());
        response.getWriter().println("sessionID:"+request.getSession().getId());
    }
}
