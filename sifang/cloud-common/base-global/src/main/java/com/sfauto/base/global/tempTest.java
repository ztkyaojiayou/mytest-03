package com.sfauto.base.global;

import com.sfauto.base.global.utils.UserInfoUtils;

public class tempTest {

    public static void main(String[] args) {
        try {
            //ExecProcessUtils.compress(new File("E:\\tmp\\aa.zip"), new File("E:\\tmp\\11"));
            String userinfoStr = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiLlubPljp_mlK_ooYwiLCJzY29wZSI6WyJyZWFkIiwid3JpdGUiXSwidGVuYW50SWQiOiIzMDAwIiwiZXhwIjoxNjE1MjI2OTQwLCJ1c2VySWQiOiI3OGM0YTRhOWY3ZDU0MDgxYjA0M2Q3MWM4Yzg1Y2Y3NCIsImRlZmF1bHRVcmwiOiJpbmRleGxhbiIsImF1dGhvcml0aWVzIjpbIlJPTEVfVVNFUiJdLCJqdGkiOiIzYjg2OTExYS0wZDg4LTRjNmYtODIxOS0zNTJiZWY3NjBmZDYiLCJjbGllbnRfaWQiOiJjbGllbnRzc28xIn0.wgCGtxKDBaeqc9Vb_76ipZQcpoQ9YBsDWz9SPA74PYc";
            System.out.println(UserInfoUtils.userInfoDecoder(userinfoStr));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
