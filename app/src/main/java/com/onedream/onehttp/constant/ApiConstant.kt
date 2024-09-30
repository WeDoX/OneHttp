package com.onedream.onehttp.constant

/**
 * @author chenguijian
 * @since 2024/9/30
 */
object ApiConstant {
    //获取App版本更新信息接口，http头部无需携带token
    const val URL_APP_UPDATE_VERSION = "http://120.78.120.117/other/github_demo_api/update_version.php"
    //获取用户信息，http头部需要携带token
    const val URL_USER_INFO = "http://120.78.120.117/other/github_demo_api/user_info.php"
}