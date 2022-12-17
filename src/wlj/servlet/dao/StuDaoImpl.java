package wlj.servlet.dao;

import wlj.servlet.pojo.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StuDaoImpl implements StuDao {
    private static final String URL="jdbc:mysql://localhost:3306/students?serverTimezone=GMT";
    private static final String USER="root";
    private static final String KEY="wp.m0:&#B2y>";
    private Connection conn = null;
    private PreparedStatement pa = null;
    private ResultSet rs = null;
    private User user = null;

    /**
     * 连接数据库
     */
    private void getConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn= DriverManager.getConnection(URL, USER, KEY);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 释放资源
     */
    private void release(){
        try {
            if (null !=rs) {
                rs.close();
            }
            if(null !=pa) {
                pa.close();
            }
            if(null !=conn) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询结果
     * @param rs
     */
    private void search(ResultSet rs) {
        try {
            while (rs.next()) {
                user = new User();
                user.setUname(rs.getString("uname"));
                user.setUpassword(rs.getString("upassword"));
                user.setUtype(rs.getInt("utype"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    @Override
    public User checkUserDao(String name, String password) {
        getConnection();
        try {
            String sql = "select *from users where uname=? and upassword=?";
            pa = conn.prepareStatement(sql);
            pa.setString(1, name);
            pa.setString(2, password);
            rs = pa.executeQuery();
            search(rs);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            release();
        }
        if(user !=null) {
            return this.user;
        }else{
            return null;
        }
    }

    @Override
    public Room chooseRoomDao(String name) {
        getConnection();
        Room room = null;
        try {
            String sql = "select *from dormroom where sname=?";
            pa = conn.prepareStatement(sql);
            pa.setString(1, name);
            rs = pa.executeQuery();
            while (rs.next()) {
                room = new Room();
                room.setSon(rs.getString("sno"));
                room.setSname(rs.getString("sname"));
                room.setSex(rs.getString("sex"));
                room.setSdept(rs.getString("sdept"));
                room.setDno(rs.getString("dno"));
                room.setScheckin(rs.getString("scheckin"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            release();
        }
        return room;
    }

    @Override
    public Express chooseExpDao(String name) {
        getConnection();
        Express express = null;
        try {
            String sql = "select *from wexpress where sname=?";
            pa = conn.prepareStatement(sql);
            pa.setString(1, name);
            rs = pa.executeQuery();
            while (rs.next()) {
                express = new Express();
                express.setSname(rs.getString(1));
                express.setDno(rs.getString(2));
                express.setMarrive(rs.getString(3));
                express.setMreceive(rs.getString(4));
                express.setMnumber(rs.getInt(5));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            release();
        }
        return express;
    }

    @Override
    public int updateTimeDao(String name, String value) {
        getConnection();
        int index = 0;
        try {
            String sql = "update  wexpress set mreceive=?,mnumber=0 where sname=?";
            pa = conn.prepareStatement(sql);
            pa.setString(1, value);
            pa.setString(2, name);
            index = pa.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            release();
        }
        return index;
    }

    @Override
    public List<Things> chooseThingsDao(String dno) {
        getConnection();
        List<Things> list = new ArrayList<>();
        Things things = null;
        try {
            String sql = "select *from service  where dno=?";
            pa = conn.prepareStatement(sql);
            pa.setString(1, dno);
            rs = pa.executeQuery();
            while (rs.next()){
                things = new Things();
                things.setDno(rs.getString(1));
                things.setPno(rs.getInt(2));
                things.setRsubmit(rs.getString(3));
                things.setRsolve(rs.getString(4));
                things.setReason(rs.getString(5));

                list.add(things);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            release();
        }
        return list;
    }

    @Override
    public int insertThingDao(String dno, int pno, String rsubmit, String reason) {
        getConnection();
        int index = 0;
        try {
            String sql = "insert into service values (?,?,?,default ,?)";
            pa = conn.prepareStatement(sql);
            pa.setString(1, dno);
            pa.setInt(2, pno);
            pa.setString(3, rsubmit);
            pa.setString(4, reason);
            index = pa.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            release();
        }
        return index;
    }

    @Override
    public List<Test> listUserDao(int limit, int offset) {
        getConnection();
        List<Test> list = new ArrayList<>();
        try {
            String sql = "select  *from  test2 where id>? and id<=?";
            pa = conn.prepareStatement(sql);
            pa.setInt(1, offset);
            pa.setInt(2, offset+limit);
            rs = pa.executeQuery();
            while (rs.next()){
                Test test =new Test();
                test.setId(rs.getInt(1));
                test.setNum(rs.getInt(2));
                list.add(test);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            release();
        }
        return list;
    }

    @Override
    public int insertLDdao(String sno, String dno, String ltime, String lreturn) {
        getConnection();
        int index = 0;
        try {
            String sql = "insert into lr values (?,?,?,?)";
            pa = conn.prepareStatement(sql);
            pa.setString(1, sno);
            pa.setString(2, dno);
            pa.setString(3, ltime);
            pa.setString(4, lreturn);
            index = pa.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            release();
        }
        return index;
    }

    @Override
    public List<LR> choosrLRdao(String sno) {
        getConnection();
        List<LR> list = new ArrayList<>();
        try {
            String sql = "select  *from  lr where sno=?";
            pa = conn.prepareStatement(sql);
            pa.setString(1, sno);
            rs = pa.executeQuery();
            while (rs.next()){
                LR lr = new LR();
                lr.setSno(rs.getString(1));
                lr.setDno(rs.getString(2));
                lr.setLtime(rs.getString(3));
                lr.setLreturn(rs.getString(4));
                list.add(lr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            release();
        }
        return list;
    }

    @Override
    public List<REC> chooseRECDao(String sno) {
        getConnection();
        List<REC> list = new ArrayList<>();
        try {
            String sql = "select  *from  record where sno=?";
            pa = conn.prepareStatement(sql);
            pa.setString(1, sno);
            rs = pa.executeQuery();
            while (rs.next()){
                REC rec = new REC();
                rec.setSno(rs.getString(1));
                rec.setDno(rs.getString(2));
                rec.setBtime(rs.getString(3));
                rec.setBreason(rs.getString(4));
                list.add(rec);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            release();
        }
        return list;
    }

    @Override
    public int insertRecDao(String sno, String dno, String btime, String breason) {
        getConnection();
        int index = 0;
        try {
            String sql = "insert into record values (?,?,?,?)";
            pa = conn.prepareStatement(sql);
            pa.setString(1, sno);
            pa.setString(2, dno);
            pa.setString(3, btime);
            pa.setString(4, breason);
            index = pa.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            release();
        }
        return index;
    }

    @Override
    public List<Room> listRoomDa0(String dno) {
        getConnection();
        List<Room> list = new ArrayList<>();
        try {
            String sql = "select  *from  dormroom where dno=?";
            pa = conn.prepareStatement(sql);
            pa.setString(1, dno);
            rs = pa.executeQuery();
            while (rs.next()){
                Room room = new Room();
                room.setSon(rs.getString(1));
                room.setSname(rs.getString(2));
                room.setSex(rs.getString(3));
                room.setSdept(rs.getString(4));
                room.setDno(rs.getString(5));
                room.setScheckin(rs.getString(6));
                list.add(room);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            release();
        }
        return list;
    }

    @Override
    public List<Express> listExpDao(int limit, int offset) {
        getConnection();
        List<Express> list = new ArrayList<>();
        try {
            System.out.println("offset: "+offset);
            String sql = "select  *from wexpress where id>? and id<=?";
            pa = conn.prepareStatement(sql);
            pa.setInt(1,offset );
            pa.setInt(2, offset+limit);
            rs = pa.executeQuery();
            while (rs.next()){
                Express express = new Express();
                express.setSname(rs.getString(1));
                express.setDno(rs.getString(2));
                express.setMarrive(rs.getString(3));
                express.setMreceive(rs.getString(4));
                express.setMnumber(rs.getInt(5));
                list.add(express);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            release();
        }
        return list;
    }

    @Override
    public int num(String tableName) {
        getConnection();
        int index = 0;
        try {
            String sql = "select  count(*) from "+tableName;
            pa = conn.prepareStatement(sql);
            rs = pa.executeQuery();
            while (rs.next()){
                index = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            release();
        }
        return index;
    }

    @Override
    public int insertExpDao(String sname, String dno, String marrive, String mnumber) {
        getConnection();
        int index = 0;
        try {
            String sql = "insert into wexpress(sname,dno,marrive,mnumber) values (?,?,?,?)";
            pa = conn.prepareStatement(sql);
            pa.setString(1, sname);
            pa.setString(2, dno);
            pa.setString(3, marrive);
            pa.setString(4, mnumber);
            index = pa.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            release();
        }
        return index;
    }

    @Override
    public List<Things> listSerDao(int limit, int offset) {
        getConnection();
        List<Things> list = new ArrayList<>();
        try {
            System.out.println("offset: "+offset);
            String sql = "select  *from service where id>? and id<=?";
            pa = conn.prepareStatement(sql);
            pa.setInt(1,offset );
            pa.setInt(2, offset+limit);
            rs = pa.executeQuery();
            while (rs.next()){
                Things things = new Things();
                things.setDno(rs.getString(1));
                things.setPno(rs.getInt(2));
                things.setRsubmit(rs.getString(3));
                things.setRsolve(rs.getString(4));
                things.setReason(rs.getString(5));
                list.add(things);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            release();
        }
        return list;
    }

    @Override
    public List<LR> listLR(int limit, int offset) {
        getConnection();
        List<LR> list = new ArrayList<>();
        try {
            System.out.println("offset: "+offset);
            String sql = "select  *from lr where id>? and id<=?";
            pa = conn.prepareStatement(sql);
            pa.setInt(1,offset );
            pa.setInt(2, offset+limit);
            rs = pa.executeQuery();
            while (rs.next()){
                LR lr = new LR();
                lr.setSno(rs.getString(1));
                lr.setDno(rs.getString(2));
                lr.setLtime(rs.getString(3));
                lr.setLreturn(rs.getString(4));
                list.add(lr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            release();
        }
        return list;
    }

    @Override
    public int updateSerDao(String dno, String pno, String rsolve) {
        getConnection();
        int index = 0;
        String sql = "update service set rsolve=? where dno=? and pno=?";
        try {
            pa=conn.prepareStatement(sql);
            pa.setString(1,rsolve );
            pa.setString(2,dno );
            pa.setString(3,pno );
            index = pa.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return index;
    }

    @Override
    public List<REC> listRecDao(int limit, int offset) {
        getConnection();
        List<REC> list = new ArrayList<>();
        try {
            System.out.println("offset: "+offset);
            String sql = "select  *from record where id>? and id<=?";
            pa = conn.prepareStatement(sql);
            pa.setInt(1,offset );
            pa.setInt(2, offset+limit);
            rs = pa.executeQuery();
            while (rs.next()){
                REC rec = new REC();
                rec.setSno(rs.getString(2));
                rec.setDno(rs.getString(3));
                rec.setBtime(rs.getString(4));
                rec.setBreason(rs.getString(5));
                list.add(rec);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            release();
        }
        return list;
    }
}
