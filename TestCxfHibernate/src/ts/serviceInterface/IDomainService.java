package ts.serviceInterface;

import ts.model.ExpressSheet;
import ts.model.TransHistory;
import ts.model.TransPackage;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/Domain")    //业务操作
public interface IDomainService {
    //快件操作访问接口=======================================================================
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("/getExpressList/{Property}/{Restrictions}/{Value}")
    List<ExpressSheet> getExpressList(@PathParam("Property") String property, @PathParam("Restrictions") String restrictions, @PathParam("Value") String value);
    //根据{属性}+{限制}+{值}（如ID like 2022）的三元组筛选快件√

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("/getExpressListInPackage/PackageId/{PackageId}")
    List<ExpressSheet> getExpressListInPackage(@PathParam("PackageId") String packageId);
    //根据包裹ID查询其中的快件√

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/getExpressSheet/{id}")
    Response getExpressSheet(@PathParam("id") String id);
    //根据快件ID获取快件√

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("/newExpressSheet/id/{id}/uid/{uid}")
    Response newExpressSheet(@PathParam("id") String id, @PathParam("uid") int uid);
    //新建快件，设定快件ID、快件揽收用户ID、快件类型（0）、揽收时间（当前时间）、状态0（已创建）√
    //并且把快件移到用户所揽收的包裹内
    //你新建的快件怎么就把揽收用户揽收事件都整好了
    //新建-揽收一条龙


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("/saveExpressSheet")
    Response saveExpressSheet(ExpressSheet obj);
    //保存快件信息，没什么特别的

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("/receiveExpressSheetId/id/{id}/uid/{uid}")
    Response ReceiveExpressSheetId(@PathParam("id") String id, @PathParam("uid") int uid);
    //揽收快件，设定快件揽收用户ID、揽收时间（当前时间）、状态1（已揽收/运输中）√

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("/dispatchExpressSheetId/id/{id}/uid/{uid}")
    Response DispatchExpressSheet(@PathParam("id") String id, @PathParam("uid") int uid);
    //拆包 还没写呢：销毁

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("/deliveryExpressSheetId/id/{id}/uid/{uid}")
    Response DeliveryExpressSheetId(@PathParam("id") String id, @PathParam("uid") int uid);
    //交付包裹  派送包裹的是哪个？
    //为什么要把ReceivePackageID什么的放在UserInfo里面，这也太怪了

    //包裹操作访问接口=======================================================================
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("/getTransPackageList/{Property}/{Restrictions}/{Value}")
    List<TransPackage> getTransPackageList(@PathParam("Property") String property, @PathParam("Restrictions") String restrictions, @PathParam("Value") String value);
    //获取运输快件列表，参数有点模糊诶
    //ID是char，Status是int，光用一个String类型的value来传递参数是不够的    //我不到啊

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getTransPackage/{id}")
    Response getTransPackage(@PathParam("id") String id);
    //返回包裹信息

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/newTransPackage/id/{id}/uid/{uid}")
    Response newTransPackage(@PathParam("id") String id, @PathParam("uid") int uid);
    //新建包裹  这个写完了没？
    //还需要编写的接口：
    //  ①打包：将快件加入包裹；若快件已在包裹内，则调用Move

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/saveTransPackage")
    Response saveTransPackage(TransPackage obj);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getTransHistory/id/{id}")
    List<TransHistory> getTransHistory(@PathParam("id") String id);
    //获取快件跟踪信息

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/addTransHistory/id/{id}/action/{action}")
    Response addTransHistory(@PathParam("id") String id,@PathParam("action") String action);
    //添加快件跟踪信息

    //你把客户忘啦==========================================================================
    /*
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/newCustomer/TelCode/{Tel}")
    Response newCustomer(@PathParam("Tel") String Tel);
     */
}
