package com.backend.EEA.services;

import com.backend.EEA.exceptions.BusinessException;
import com.backend.EEA.business.dao.repositories.masterdat.UserRepository;
import com.backend.EEA.model.entity.masterdata.User;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.Optional;


@Transactional
@Service
public class CommonService {

    private final UserRepository userRepository;

    public CommonService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUser(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        return user.orElseGet(User::new);
    }

    public String right(String value, int length) {
        return value.substring(value.length() - length);
    }

    public User checkUserIdReq(Long userId) throws BusinessException {
        // check new data has id
        if (userId == null)
            throw new BusinessException("the data has no user id");
        // check that the user id already exists
        User user = this.getUser(userId);
        if (user == null)
            throw new BusinessException("User does not exist");
        return user;
    }


    public void checkStringReq(String name, String attributeName) throws BusinessException {
        // check new data has name
        if (name == null)
            throw new BusinessException("Data has no " + attributeName);

        // check new data has empty name
        if (name.trim().length() == 0)
            throw new BusinessException("Data has empty " + attributeName);

    }

    public void checkIntegerReq(Integer id, String exceptionMsg) throws BusinessException {
        // check new data has id
        if (id == null)
            throw new BusinessException(exceptionMsg);
    }

    public void checkLongReq(Long id, String exceptionMsg) throws BusinessException {
        // check new data has id
        if (id == null)
            throw new BusinessException(exceptionMsg);
    }

    public void checkDateReq(Date date, String attributeName) throws BusinessException {
        // check new data has date
        if (date == null)
            throw new BusinessException("Data has no " + attributeName);
    }
}
