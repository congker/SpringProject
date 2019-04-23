package com.example.ouath2.service;

/**
 * Created by xuwencong on 2019/4/23
 */
public interface UserService {
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addUser(UserDTO userDTO);

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteUser(Integer id);

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateUser(UserDTO userDTO);

    @Override
    public ResponseVO<List<UserVO>> findAllUserVO();

    @Override
    public ResponseVO login(LoginUserDTO loginUserDTO);

    /**
     * @description oauth2客户端刷新token
     * @param refreshToken
     * @date 2019/03/05 14:27:22
     * @author Zhifeng.Zeng
     * @return
     */
    private Token oauthRefreshToken(String refreshToken);
}
