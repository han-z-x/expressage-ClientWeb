package ts.serviceInterface;

import ts.model.CodeNamePair;
import ts.model.CustomerInfo;
import ts.model.Region;
import ts.model.TransNode;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/Misc")
public interface IMiscService {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getNode/{NodeCode}")
    TransNode getNode(@PathParam("NodeCode") String code);

    @GET
    @Produces(MediaType.APPLICATION_JSON )
    @Path("/getNodesList/{RegionCode}")
    List<TransNode> getNodesList(@PathParam("RegionCode") String regionCode);

    //===============================================================================================
    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("/getCustomerListByName/{name}")
    List<CustomerInfo> getCustomerListByName(@PathParam("name") String name);
    //根据姓名获取顾客信息列表√

    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("/getCustomerListByTelCode/{TelCode}")
    List<CustomerInfo> getCustomerListByTelCode(@PathParam("TelCode") String TelCode);
    //根据电话获取顾客信息列表√

    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("/getCustomerInfo/{id}")
    Response getCustomerInfo(@PathParam("id") String id);
    //根据ID获取顾客信息√

    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("/getUserInfo/{uid}")
    Response getUserInfo(@PathParam("uid") int uid);
    //根据ID获取用户信息√

    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("/deleteCustomerInfo/{id}")
    Response deleteCustomerInfo(@PathParam("id") int id);

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/saveCustomerInfo")
    Response saveCustomerInfo(CustomerInfo obj);

    //===============================================================================================
    @GET
    @Produces({ MediaType.APPLICATION_JSON})
    @Path("/getProvinceList")
    List<CodeNamePair> getProvinceList();
    //获取省份列表√

    @GET
    @Produces({MediaType.APPLICATION_JSON })
    @Path("/getCityList/{prv}")
    List<CodeNamePair> getCityList(@PathParam("prv") String prv);
    //获取地市列表√

    @GET
    @Produces({MediaType.APPLICATION_JSON })
    @Path("/getTownList/{city}")
    List<CodeNamePair> getTownList(@PathParam("city") String city);
    //获取县镇列表√

    @GET
    @Produces({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON })
    @Path("/getRegionString/{id}")
    String getRegionString(@PathParam("id") String id);
    //获取地区的名称√

    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("/getRegion/{id}")
    Region getRegion(@PathParam("id") String id);
    //获取包含省、市、区、地区编号、地区等级等属性的完整的地区对象√


    //===============================================================================================
    void CreateWorkSession(int uid);

    @POST
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("/doLogin/{uid}/{pwd}")
    Response doLogin(@PathParam("uid") int uid, @PathParam("pwd") String pwd);
    //登录√

    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("/doLogOut/{uid}")
    void doLogOut(@PathParam("uid") int uid);

	void RefreshSessionList();
}
