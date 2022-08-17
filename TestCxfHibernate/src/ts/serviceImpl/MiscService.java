package ts.serviceImpl;


import ts.daoImpl.CustomerInfoDao;
import ts.daoImpl.RegionDao;
import ts.daoImpl.TransNodeDao;
import ts.daoImpl.UserInfoDao;
import ts.model.*;
import ts.serviceInterface.IMiscService;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

public class MiscService implements IMiscService {
    //TransNodeCatalog nodes;	//自己做的缓存和重定向先不要了,用Hibernate缓存对付一下，以后加上去
    //RegionCatalog regions;
    private TransNodeDao transNodeDao;
    private RegionDao regionDao;
    private CustomerInfoDao customerInfoDao;
    private UserInfoDao userInfoDao;

    public TransNodeDao getTransNodeDao() {
        return transNodeDao;
    }

    public void setTransNodeDao(TransNodeDao dao) {
        this.transNodeDao = dao;
    }

    public RegionDao getRegionDao() {
        return regionDao;
    }

    public void setRegionDao(RegionDao dao) {
        this.regionDao = dao;
    }

    public CustomerInfoDao getCustomerInfoDao() {
        return customerInfoDao;
    }

    public void setCustomerInfoDao(CustomerInfoDao dao) {
        this.customerInfoDao = dao;
    }

    public UserInfoDao getUserInfoDao() {
        return userInfoDao;
    }

    public void setUserInfoDao(UserInfoDao dao) {
        this.userInfoDao = dao;
    }


    @Override
    public TransNode getNode(String code) {
        return transNodeDao.get(code);
    }

    @Override
    public List<TransNode> getNodesList(String regionCode) {
        //return transNodeDao.findByRegionCode(regionCode);
        List<TransNode> transNodeList = new ArrayList<>();
        transNodeList = transNodeDao.findBy("regionCode", regionCode, "regionCode", true);
        return transNodeList;
        //return transNodeDao.findBy("ID", true, Restrictions.eq("RegionCode", regionCode), Restrictions.eq("NodeType", type));
    }

    @Override
    public List<CustomerInfo> getCustomerListByName(String name) {
//		List<CustomerInfo> listci = customerInfoDao.findByName(name);
//		List<CodeNamePair> listCN = new ArrayList<CodeNamePair>();
//		for(CustomerInfo ci : listci){
//			CodeNamePair cn = new CodeNamePair(String.valueOf(ci.getID()),ci.getName());
//			listCN.add(cn);
//		}
//		return listCN;
        return customerInfoDao.findByName(name);
    }

    @Override
    public List<CustomerInfo> getCustomerListByTelCode(String TelCode) {
//		List<CustomerInfo> listci = customerInfoDao.findByTelCode(TelCode);
//		List<CodeNamePair> listCN = new ArrayList<CodeNamePair>();
//		for(CustomerInfo ci : listci){
//			CodeNamePair cn = new CodeNamePair(String.valueOf(ci.getID()),ci.getName());
//			listCN.add(cn);
//		}
//		return listCN;
        return customerInfoDao.findByTelCode(TelCode);
    }

    @Override
    public Response getCustomerInfo(String id) {
        CustomerInfo cstm = customerInfoDao.get(Integer.parseInt(id));
//		try{
//			cstm.setRegionString(regionDao.getRegionNameByID(cstm.getRegionCode()));	//这部分功能放到DAO里去了
//		}catch(Exception e){}
        return Response.ok(cstm).header("EntityClass", "CustomerInfo").build();
    }

    @Override
    public Response getUserInfo(int uid) {
        UserInfo userInfo = userInfoDao.get(uid);
        return Response.ok(userInfo).header("EntityClass", "UserInfo").build();
    }

    @Override
    public Response deleteCustomerInfo(int id) {
        customerInfoDao.removeById(id);
        return Response.ok("Deleted").header("EntityClass", "D_CustomerInfo").build();
    }

    @Override
    public Response saveCustomerInfo(CustomerInfo obj) {
        try {
            customerInfoDao.save(obj);
            return Response.ok(obj).header("EntityClass", "R_CustomerInfo").build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @Override
    public List<CodeNamePair> getProvinceList() {
        List<Region> listrg = regionDao.getProvinceList();
        List<CodeNamePair> listCN = new ArrayList<>();
        for (Region rg : listrg) {
            CodeNamePair cn = new CodeNamePair(rg.getORMID(), rg.getPrv());
            listCN.add(cn);
        }
        return listCN;
    }


    @Override
    public List<CodeNamePair> getCityList(String prv) {
        List<Region> listrg = regionDao.getCityList(prv);
        List<CodeNamePair> listCN = new ArrayList<CodeNamePair>();
        for (Region rg : listrg) {
            CodeNamePair cn = new CodeNamePair(rg.getORMID(), rg.getCty());
            listCN.add(cn);
        }
        return listCN;
    }

    @Override
    public List<CodeNamePair> getTownList(String city) {
        List<Region> listrg = regionDao.getTownList(city);
        List<CodeNamePair> listCN = new ArrayList<CodeNamePair>();
        for (Region rg : listrg) {
            CodeNamePair cn = new CodeNamePair(rg.getORMID(), rg.getTwn());
            listCN.add(cn);
        }
        return listCN;
    }

    @Override
    public String getRegionString(String code) {
        return regionDao.getRegionNameByID(code);
    }

    @Override
    public Region getRegion(String code) {
        return regionDao.getFullNameRegionByID(code);
    }

    @Override
    public void CreateWorkSession(int uid) {
        // TODO Auto-generated method stub

    }

    @Override
    public Response doLogin(int uid, String pwd) {
        /*
		UserInfo userInfo = userInfoDao.get(uid);
		try{
			if(pwd.equals(userInfo.getPWD())) {
				return Response.ok(userInfo).header("EntityClass","T_UserInfo").build();
			}
			else {
				return Response.ok("UID或密码错误！").header("EntityClass", "F_UserInfo").build();
			}
		}
		catch (Exception e) {
			return Response.serverError().entity(e.getMessage()).build();
		}*/
        UserInfo userInfo = userInfoDao.login(uid, pwd);
        if (userInfo != null) {
            return Response.ok(userInfo).header("EntityClass", "T_UserInfo").build();
        }
        return Response.ok("UID或密码错误！").header("EntityClass", "F_UserInfo").build();
    }

    @Override
    public void doLogOut(int uid) {
        // TODO Auto-generated method stub

    }

    @Override
    public void RefreshSessionList() {
        // TODO Auto-generated method stub

    }
}
