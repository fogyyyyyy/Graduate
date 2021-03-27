package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Member;
import com.itheima.ssm.domain.Orders;
import com.itheima.ssm.domain.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IOrdersDao {

    @Select("select * from orders")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "orderNum" ,column = "orderNum"),
            @Result(property = "orderTime" ,column = "orderTime"),
            @Result(property = "orderStatus" ,column = "orderStatus"),
            @Result(property = "peopleCount" ,column = "peopleCount"),
            @Result(property = "payType" ,column = "payType"),
            @Result(property = "orderDesc" ,column = "orderDesc"),
            @Result(property = "product" ,column = "productId",javaType = Product.class,one =@One(select = "com.itheima.ssm.dao.IProductDao.findById"))
    })
    public List<Orders> findAll() throws  Exception;

    //多表操作
    @Select("select * from orders where id=#{ordersId}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "orderNum" ,column = "orderNum"),
            @Result(property = "orderTime" ,column = "orderTime"),
            @Result(property = "orderStatus" ,column = "orderStatus"),
            @Result(property = "peopleCount" ,column = "peopleCount"),
            @Result(property = "payType" ,column = "payType"),
            @Result(property = "orderDesc" ,column = "orderDesc"),
            @Result(property = "product" ,column = "productId",javaType = Product.class,one =@One(select = "com.itheima.ssm.dao.IProductDao.findById")),
            @Result(property = "member",column = "memberId" ,javaType = Member.class,one =@One(select = "com.itheima.ssm.dao.IMemberDao.findById")),
            @Result(property = "travellers",column = "id",javaType = java.util.List.class,many=@Many(select = "com.itheima.ssm.dao.ITravellerDao.findByOrdersId"))
    })
    public Orders findById(String ordersId) throws Exception;

    @Delete("delete from orders where id=#{id}")
    void deleteById(String id) throws Exception;

    @Delete("delete from order_traveller where orderid=#{id}")
    void deleteOtherId(String id) throws Exception;

    @Insert("insert into orders(orderNum,orderTime,orderStatus,peopleCount,payType,orderDesc) values(#{orderNum},#{orderTime},#{orderStatus},#{peopleCount},#{payType},#{orderDesc})")
    @Results
    void save(Orders orders) throws Exception;

    @Update("update orders set orderStatus=#{orderStatus},peopleCount=#{peopleCount} where id=#{id}")
    void updateOrder( Orders orders) throws Exception;
}
