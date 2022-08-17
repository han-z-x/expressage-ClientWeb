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
    //����������ȡ�˿���Ϣ�б��

    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("/getCustomerListByTelCode/{TelCode}")
    List<CustomerInfo> getCustomerListByTelCode(@PathParam("TelCode") String TelCode);
    //���ݵ绰��ȡ�˿���Ϣ�б��

    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("/getCustomerInfo/{id}")
    Response getCustomerInfo(@PathParam("id") String id);
    //����ID��ȡ�˿���Ϣ��

    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("/getUserInfo/{uid}")
    Response getUserInfo(@PathParam("uid") int uid);
    //����ID��ȡ�û���Ϣ��

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
    //��ȡʡ���б��

    @GET
    @Produces({MediaType.APPLICATION_JSON })
    @Path("/getCityList/{prv}")
    List<CodeNamePair> getCityList(@PathParam("prv") String prv);
    //��ȡ�����б��

    @GET
    @Produces({MediaType.APPLICATION_JSON })
    @Path("/getTownList/{city}")
    List<CodeNamePair> getTownList(@PathParam("city") String city);
    //��ȡ�����б��

    @GET
    @Produces({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON })
    @Path("/getRegionString/{id}")
    String getRegionString(@PathParam("id") String id);
    //��ȡ���������ơ�

    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("/getRegion/{id}")
    Region getRegion(@PathParam("id") String id);
    //��ȡ����ʡ���С�����������š������ȼ������Ե������ĵ��������


    //===============================================================================================
    void CreateWorkSession(int uid);

    @POST
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("/doLogin/{uid}/{pwd}")
    Response doLogin(@PathParam("uid") int uid, @PathParam("pwd") String pwd);
    //��¼��

    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("/doLogOut/{uid}")
    void doLogOut(@PathParam("uid") int uid);

	void RefreshSessionList();
}
