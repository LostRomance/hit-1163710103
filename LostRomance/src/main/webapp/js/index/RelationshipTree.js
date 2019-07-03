$(document).ready(function () {
    /**登陆 */
    $("#login").click(function () {
        $("#login_submit").submit();
    });
    /**跳转去注册界面 */
    $("#gotoregister").click(function(){
        $("#inset_login").hide();
        $("#inset_register").show();
    });
    /**返回登陆界面 */
    $("#back").click(function () {
        $("#inset_register").hide();
        $("#inset_login").show();
      });
    /**完成注册 */
    $("#register").click(function () {
        $("#register_submit").submit();
      });
});
    
    

