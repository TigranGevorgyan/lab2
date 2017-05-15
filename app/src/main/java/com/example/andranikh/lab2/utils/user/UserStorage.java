package com.example.andranikh.lab2.utils.user;

import com.example.andranikh.lab2.pojos.User;

import java.util.List;

/**
 * Created by andranikh on 4/29/17.
 */

public abstract class UserStorage {

    public abstract void registerUser(User user, UserFoundListener actionListener);

    protected abstract List<User> getUsers();

    public abstract void checkAndGetUser(String userName, String password, UserFoundListener actionListener);

    public abstract void findUserById(long id, UserFoundListener actionListener);

    public abstract void findUserByUsername(String username, UserFoundListener actionListener);

    protected void notifyUserFound(User user, UserFoundListener actionListener){
        if(actionListener != null){
            actionListener.onUserFound(user);
        }
    }

    public interface UserFoundListener {
        void onUserFound(User user);
    }
}
