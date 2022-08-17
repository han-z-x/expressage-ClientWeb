package ts.daoImpl;

import ts.daoBase.BaseDao;
import ts.model.UserInfo;

import java.util.List;


public class UserInfoDao extends BaseDao<UserInfo, Integer> {
    public UserInfoDao() {
        super(UserInfo.class);
    }

    @Override
    public UserInfo get(Integer uid) {
        return super.get(uid);
    }

    public UserInfo login(int uid, String pwd) {
        UserInfo userInfo = this.get(uid);
        if(userInfo.getPWD().equals(pwd)){
            return userInfo;
        }
        return null;
    }

    public UserInfo getUserByUID(int uid) {
        List<UserInfo> users = findBy("UID", uid, "UID", true);
        for (UserInfo userInfo : users) {
            return userInfo;    //先返回用户信息，核验密码交给具体接口
            //if (userInfo.getPWD().equals(password)) return userInfo;
        }
        return null;
    }
}
