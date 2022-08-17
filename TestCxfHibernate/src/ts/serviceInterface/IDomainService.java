package ts.serviceInterface;

import ts.model.ExpressSheet;
import ts.model.TransHistory;
import ts.model.TransPackage;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/Domain")    //ҵ�����
public interface IDomainService {
    //����������ʽӿ�=======================================================================
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("/getExpressList/{Property}/{Restrictions}/{Value}")
    List<ExpressSheet> getExpressList(@PathParam("Property") String property, @PathParam("Restrictions") String restrictions, @PathParam("Value") String value);
    //����{����}+{����}+{ֵ}����ID like 2022������Ԫ��ɸѡ�����

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("/getExpressListInPackage/PackageId/{PackageId}")
    List<ExpressSheet> getExpressListInPackage(@PathParam("PackageId") String packageId);
    //���ݰ���ID��ѯ���еĿ����

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/getExpressSheet/{id}")
    Response getExpressSheet(@PathParam("id") String id);
    //���ݿ��ID��ȡ�����

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("/newExpressSheet/id/{id}/uid/{uid}")
    Response newExpressSheet(@PathParam("id") String id, @PathParam("uid") int uid);
    //�½�������趨���ID����������û�ID��������ͣ�0��������ʱ�䣨��ǰʱ�䣩��״̬0���Ѵ�������
    //���Ұѿ���Ƶ��û������յİ�����
    //���½��Ŀ����ô�Ͱ������û������¼���������
    //�½�-����һ����


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("/saveExpressSheet")
    Response saveExpressSheet(ExpressSheet obj);
    //��������Ϣ��ûʲô�ر��

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("/receiveExpressSheetId/id/{id}/uid/{uid}")
    Response ReceiveExpressSheetId(@PathParam("id") String id, @PathParam("uid") int uid);
    //���տ�����趨��������û�ID������ʱ�䣨��ǰʱ�䣩��״̬1��������/�����У���

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("/dispatchExpressSheetId/id/{id}/uid/{uid}")
    Response DispatchExpressSheet(@PathParam("id") String id, @PathParam("uid") int uid);
    //��� ��ûд�أ�����

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("/deliveryExpressSheetId/id/{id}/uid/{uid}")
    Response DeliveryExpressSheetId(@PathParam("id") String id, @PathParam("uid") int uid);
    //��������  ���Ͱ��������ĸ���
    //ΪʲôҪ��ReceivePackageIDʲô�ķ���UserInfo���棬��Ҳ̫����

    //�����������ʽӿ�=======================================================================
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("/getTransPackageList/{Property}/{Restrictions}/{Value}")
    List<TransPackage> getTransPackageList(@PathParam("Property") String property, @PathParam("Restrictions") String restrictions, @PathParam("Value") String value);
    //��ȡ�������б������е�ģ����
    //ID��char��Status��int������һ��String���͵�value�����ݲ����ǲ�����    //�Ҳ�����

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getTransPackage/{id}")
    Response getTransPackage(@PathParam("id") String id);
    //���ذ�����Ϣ

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/newTransPackage/id/{id}/uid/{uid}")
    Response newTransPackage(@PathParam("id") String id, @PathParam("uid") int uid);
    //�½�����  ���д����û��
    //����Ҫ��д�Ľӿڣ�
    //  �ٴ��������������������������ڰ����ڣ������Move

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/saveTransPackage")
    Response saveTransPackage(TransPackage obj);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getTransHistory/id/{id}")
    List<TransHistory> getTransHistory(@PathParam("id") String id);
    //��ȡ���������Ϣ

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/addTransHistory/id/{id}/action/{action}")
    Response addTransHistory(@PathParam("id") String id,@PathParam("action") String action);
    //��ӿ��������Ϣ

    //��ѿͻ�����==========================================================================
    /*
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/newCustomer/TelCode/{Tel}")
    Response newCustomer(@PathParam("Tel") String Tel);
     */
}
