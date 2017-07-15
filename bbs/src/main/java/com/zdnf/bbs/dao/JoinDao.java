package com.zdnf.bbs.dao;

import java.util.List;
import com.zdnf.bbs.domain.Join;
import com.zdnf.bbs.domain.Joiner;
import org.apache.ibatis.annotations.Param;

/**
 * Created by ZDNF on 2017/7/13.
 */
public interface JoinDao {
    public List<Join> GetAll();
    public Join GetById(int id);
    public void AddAssociation(@Param("name") String name, @Param("mail") String mail);
    public boolean AddPeople(@Param("joiner") Joiner joiner);
    public List<Joiner> GetAllPeople();
    public String GetUserNameById(String id);
    public String IsJoined(@Param("id")String id,@Param("username")String username);
}
