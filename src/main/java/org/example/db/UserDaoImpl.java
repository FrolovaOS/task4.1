package org.example.db;

import org.example.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Repository
public class UserDaoImpl extends JdbcDaoSupport implements UserDao{

    @Autowired
    DataSource dataSource;

    @PostConstruct
    private void initialize(){
        setDataSource(dataSource);
    }

    @Override
    public void insert(User user) {

        String sql = "INSERT INTO info " +
                "(firstName, lastName ,age,timestamp) VALUES ( ?, ?, ?, ?)" ;
        getJdbcTemplate().update(sql, new Object[]{
                user.getFirstName(), user.getLastName(),user.getAge(),user.getTimestamp()
        });
    }

    @Override
    public List<User> loadAllCustomer() {
        String sql = "SELECT * FROM info";
        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);

        List<User> result = new ArrayList<User>();
        for(Map<String, Object> row:rows){
            User user = new User();

            user.setFirstName((String)row.get("firstName"));
            user .setLastName((String) row.get("lastName"));
            user.setAge((Integer)row.get("age"));
            user .setTimestamp((Long) row.get("timestamp"));
            result.add(user );
        }

        return result;
    }
}
