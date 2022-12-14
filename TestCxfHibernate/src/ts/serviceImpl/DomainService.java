package ts.serviceImpl;

import ts.daoImpl.*;
import ts.model.*;
import ts.serviceInterface.IDomainService;

import javax.ws.rs.core.Response;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DomainService implements IDomainService {

    private ExpressSheetDao expressSheetDao;
    private TransPackageDao transPackageDao;
    private TransHistoryDao transHistoryDao;
    private TransPackageContentDao transPackageContentDao;

    private UsersPackageDao usersPackageDao;
    private UserInfoDao userInfoDao;

    public ExpressSheetDao getExpressSheetDao() {
        return expressSheetDao;
    }

    public void setExpressSheetDao(ExpressSheetDao dao) {
        this.expressSheetDao = dao;
    }

    public TransPackageDao getTransPackageDao() {
        return transPackageDao;
    }

    public void setTransPackageDao(TransPackageDao dao) {
        this.transPackageDao = dao;
    }

    public TransHistoryDao getTransHistoryDao() {
        return transHistoryDao;
    }

    public void setTransHistoryDao(TransHistoryDao dao) {
        this.transHistoryDao = dao;
    }

    public TransPackageContentDao getTransPackageContentDao() {
        return transPackageContentDao;
    }

    public void setTransPackageContentDao(TransPackageContentDao dao) {
        this.transPackageContentDao = dao;
    }

    public UserInfoDao getUserInfoDao() {
        return userInfoDao;
    }

    public void setUserInfoDao(UserInfoDao dao) {
        this.userInfoDao = dao;
    }

    public UsersPackageDao getUsersPackageDao() {
        return usersPackageDao;
    }

    public void setUsersPackageDao(UsersPackageDao dao) {
        this.usersPackageDao = dao;
    }

    public Date getCurrentDate() {
        //产生一个不带毫秒的时间,不然,SQL时间和JAVA时间格式不一致
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date tm = new Date();
        try {
            tm = sdf.parse(sdf.format(new Date()));
        } catch (ParseException e1) {
            e1.printStackTrace();
        }
        return tm;
    }

    @Override
    public List<ExpressSheet> getExpressList(String property,
                                             String restrictions, String value) {
        List<ExpressSheet> list = new ArrayList<ExpressSheet>();
        switch (restrictions.toLowerCase()) {
            case "eq":
                list = expressSheetDao.findBy(property, value, "ID", true);
                break;
            case "like":
                list = expressSheetDao.findLike(property, value + "%", "ID", true);
                break;
        }
        return list;
    }
//	@Override
//	public List<ExpressSheet> getExpressList(String property,
//			String restrictions, String value) {
//		Criterion cr1;
//		Criterion cr2 = Restrictions.eq("Status", 0);
//
//		List<ExpressSheet> list = new ArrayList<ExpressSheet>();
//		switch(restrictions.toLowerCase()){
//		case "eq":
//			cr1 = Restrictions.eq(property, value);
//			break;
//		case "like":
//			cr1 = Restrictions.like(property, value);
//			break;
//		default:
//			cr1 = Restrictions.like(property, value);
//			break;
//		}
//		list = expressSheetDao.findBy("ID", true,cr1,cr2);
//		return list;
//	}

    @Override
    public List<ExpressSheet> getExpressListInPackage(String packageId) {
        List<ExpressSheet> list;
        list = expressSheetDao.getListInPackage(packageId);
        return list;
    }

    @Override
    public Response getExpressSheet(String id) {
        ExpressSheet es = expressSheetDao.get(id);
        return Response.ok(es).header("EntityClass", "ExpressSheet").build();
    }

    @Override
    public Response newExpressSheet(String id, int uid) {
        ExpressSheet es = null;
        try {
            es = expressSheetDao.get(id);
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        if (es != null) {
//			if(es.getStatus() != 0)
//				return Response.ok(es).header("EntityClass", "L_ExpressSheet").build(); //已经存在,且不能更改
//			else
            return Response.ok("快件运单信息已经存在!\n无法创建!").header("EntityClass", "E_ExpressSheet").build(); //已经存在
        }
        try {
            String pkgId = userInfoDao.get(uid).getReceivePackageID();
            ExpressSheet nes = new ExpressSheet();
            nes.setID(id);
            nes.setType(0);
            nes.setAccepter(String.valueOf(uid));
            nes.setAcceptTime(getCurrentDate());
            nes.setStatus(ExpressSheet.STATUS.STATUS_CREATED);
//			TransPackageContent pkg_add = new TransPackageContent();
//			pkg_add.setPkg(transPackageDao.get(pkgId));
//			pkg_add.setExpress(nes);
//			nes.getTransPackageContent().add(pkg_add);
            expressSheetDao.save(nes);
            //放到收件包裹中
            MoveExpressIntoPackage(nes.getID(), pkgId);
            return Response.ok(nes).header("EntityClass", "ExpressSheet").build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @Override
    public Response saveExpressSheet(ExpressSheet obj) {
        try {
            //ExpressSheet nes = expressSheetDao.get(obj.getID());
            if (obj.getStatus() != ExpressSheet.STATUS.STATUS_CREATED) {
                return Response.ok("快件运单已付运!无法保存更改!").header("EntityClass", "E_ExpressSheet").build();
            }
            expressSheetDao.save(obj);
            return Response.ok(obj).header("EntityClass", "R_ExpressSheet").build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @Override
    public Response ReceiveExpressSheetId(String id, int uid) {
        try {
            ExpressSheet nes = expressSheetDao.get(id);
            if (nes.getStatus() != ExpressSheet.STATUS.STATUS_CREATED) {
                return Response.ok("快件运单状态错误!无法收件!").header("EntityClass", "E_ExpressSheet").build();
            }
            nes.setAccepter(String.valueOf(uid));
            nes.setAcceptTime(getCurrentDate());
            nes.setStatus(ExpressSheet.STATUS.STATUS_TRANSPORT);
            //燮华：把这个快件从用户的揽收任务虚包裹里面移走！  //还是等打包完再移走吧，而且在这之前还需要完善快件信息
            //RemoveExpressFromPackage(id,userInfoDao.get(uid).getReceivePackageID());
            expressSheetDao.save(nes);
            return Response.ok(nes).header("EntityClass", "ExpressSheet").build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @Override
    public Response DispatchExpressSheet(String id, int uid) {
        // TODO Auto-generated method stub
        return null;
    }

    public boolean MoveExpressIntoPackage(String id, String targetPkgId) {
        TransPackage targetPkg = transPackageDao.get(targetPkgId);
        if ((targetPkg.getStatus() > 0) && (targetPkg.getStatus() < 3)) {    //包裹的状态快点定义,打开的包裹或者货篮才能操作==================================================================
            return false;
        }

        TransPackageContent pkg_add = new TransPackageContent();
        pkg_add.setPkg(targetPkg);
        pkg_add.setExpress(expressSheetDao.get(id));
        pkg_add.setStatus(TransPackageContent.STATUS.STATUS_ACTIVE);
        transPackageContentDao.save(pkg_add);
        return true;
    }

    public boolean RemoveExpressFromPackage(String id, String sourcePkgId) {
        TransPackage sourcePkg = transPackageDao.get(sourcePkgId);
        if ((sourcePkg.getStatus() > 0) && (sourcePkg.getStatus() < 3)) {
            return false;
        }

        TransPackageContent pkg_add = transPackageContentDao.get(id, sourcePkgId);
        pkg_add.setStatus(TransPackageContent.STATUS.STATUS_OUTOF_PACKAGE);
        transPackageContentDao.save(pkg_add);
        return true;
    }

    public void MoveExpressBetweenPackage(String id, String sourcePkgId, String targetPkgId) {
        //需要加入事务机制
        RemoveExpressFromPackage(id, sourcePkgId);
        MoveExpressIntoPackage(id, targetPkgId);
    }

    @Override
    public Response DeliveryExpressSheetId(String id, int uid) {
        try {
            String pkgId = userInfoDao.get(uid).getDelivePackageID();
            ExpressSheet nes = expressSheetDao.get(id);
            if (nes.getStatus() != ExpressSheet.STATUS.STATUS_TRANSPORT) {
                return Response.ok("快件运单状态错误!无法交付").header("EntityClass", "E_ExpressSheet").build();
            }

            if (transPackageContentDao.getSn(id, pkgId) == 0) {
                //找不到包裹里的快件
                //临时的一个处理方式,断路了包裹的传递过程,自己的货篮倒腾一下
                MoveExpressBetweenPackage(id, userInfoDao.get(uid).getReceivePackageID(), pkgId);
                return Response.ok("快件运单状态错误!\n快件信息没在您的派件包裹中!").header("EntityClass", "E_ExpressSheet").build();
            }

            nes.setDeliver(String.valueOf(uid));
            nes.setDeliveTime(getCurrentDate());
            nes.setStatus(ExpressSheet.STATUS.STATUS_DELIVERIED);
            expressSheetDao.save(nes);
            //从派件包裹中删除
            RemoveExpressFromPackage(nes.getID(), pkgId);
            //快件没有历史记录,很难给出收件和交付的记录
            return Response.ok(nes).header("EntityClass", "ExpressSheet").build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @Override
    public List<TransPackage> getTransPackageList(String property,
                                                  String restrictions, String value) {
        List<TransPackage> list = new ArrayList<TransPackage>();
        switch (restrictions.toLowerCase()) {
            case "eq":
                list = transPackageDao.findBy(property + "", 0, "ID", true);
                //list = transPackageDao.findBy(property, value, "ID", true);
                break;
            case "like":
                list = transPackageDao.findLike(property + "", value + "%", "ID", true);
                break;
        }
        return list;
    }

    @Override
    public Response getTransPackage(String id) {
        TransPackage transPackage = transPackageDao.get(id);
        return Response.ok(transPackage).header("EntityClass", "TransPackage").build();
    }

    @Override
    public Response newTransPackage(String id, int uid) {
        TransPackage pk = null;
        try {
            pk = transPackageDao.get(id);
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        if (pk != null) {
            return Response.ok("包裹已存在!\n无法创建!").header("EntityClass", "E_TransPackage").build(); //已经存在
        }
        try {
            TransPackage npk = new TransPackage();
            npk.setID(id);
            npk.setStatus(TransPackage.STATUS.STATUS_CREATED);
            npk.setCreateTime(getCurrentDate());
            transPackageDao.save(npk);
            //将该包裹添加到打包角色的UsersPackage里面
            UsersPackage upk = new UsersPackage();
            upk.setUserU(userInfoDao.get(uid));
            upk.setPkg(transPackageDao.get(id));
            usersPackageDao.save(upk);
            return Response.ok(npk).header("EntityClass", "TransPackage").build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @Override
    public Response saveTransPackage(TransPackage obj) {
        try {
            transPackageDao.save(obj);
            return Response.ok(obj).header("EntityClass", "R_TransPackage").build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @Override
    public List<TransHistory> getTransHistory(String id) {
        List<TransHistory> list;
        list = transHistoryDao.findBy("ExpressID", id, "ActTime", true);
        return list;
    }

    @Override
    public Response addTransHistory(String id, String action) {
        String ACTION = "";
        try {
            TransHistory transHistory = new TransHistory();
            transHistory.setExpressID(id);
            transHistory.setActTime(getCurrentDate());
            switch (action) {
                case "received":
                    ACTION = "已揽收";
                    break;
                case "wrapped":
                    ACTION = "已打包";
                    break;
                case "transporting":
                    ACTION = "已发出";
                    break;
                case "arrived":
                    ACTION = "已到达";
                    break;
                case "unwrapped":
                    ACTION = "已拆包";
                    break;
                case "delivering":
                    ACTION = "派送中";
                    break;
                case "delivered":
                    ACTION = "已送达";
                    break;
                default:
                    ACTION = "未知操作";
            }
            transHistory.setAction(ACTION); //测试用；实际使用还需要加入ACTION的主体，如“xx结点”、“xx派送员”
            transHistoryDao.save(transHistory);
            return Response.ok(transHistory).header("EntityClass", "TransHistory").build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }
}
