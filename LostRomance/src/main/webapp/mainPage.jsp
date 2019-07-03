<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/mainPage/left-part.css">
    <link rel="stylesheet" href="css/mainPage/slide-guide.css">
    <link rel="stylesheet" href="css/mainPage/right-part.css">
    <link rel="stylesheet" href="css/mainPage/mainPage.css">

    <script src="js/jquery.min.js"></script>
    <script src="js/mainPage/addRelationship.js"></script>
</head>
<body>
<div id="left_part">
    <div id="manage">
        <div id="profile">
            <div id="my_icon_bor">
                <img id="my_icon">
            </div>
            <!--显示用户名-->
            <b id="username"><%=session.getAttribute("username")%></b>
        </div>
        <!--滑动导航栏-->
        <div class="guide_out_bor">
            <!--控制面板-->
            <div class="guide_bor" id="control">
                <table class="guide_ta">
                    <!--一项-->
                    <tr class="guide_tr">
                        <td class="guide_td" id="guide_td1">
                            <div class="black_bor"></div>
                            刷新人脉
                        </td>
                    </tr>
                    <tr class="guide_tr">
                        <td class="guide_td" id="guide_td2">
                            <div class="black_bor"></div>
                            添加人脉
                        </td>
                    </tr>
                    <%--<tr class="guide_tr">--%>
                        <%--<td class="guide_td" id="guide_td3">--%>
                            <%--<div class="black_bor"></div>--%>
                            <%--搜索人脉--%>
                        <%--</td>--%>
                    <%--</tr>--%>
                </table>
            </div>
            <!--搜索面板-->
            <div id="search_bor">
                <div id="search_select">
                    <span id="search_select_hint">请选择查询的标签：</span>
                    <select class="selectpicker">
                        <option value="1">姓名</option>
                        <option value="2">关系</option>
                        <option value="3">职业</option>
                    </select>
                </div>
                <input type="text" class="form-control col-lg-3" id="search_input" placeholder="请输入字段名">
                <span class="input-group-btn">
                        <button class="btn btn-info btn-search" id="search_button">查找</button>
                    </span>
            </div>
            <!--添加面板-->
            <div id="addRel">
                <div id="addRelControl">
                    <button id="submit">提交</button>
                    <button id="more">更多</button>
                    <button id="delete">删除</button>
                </div>
                <span>姓名</span><input type="text" id="input_name" name="name" placeholder="请输入姓名">
                <div class="clear"></div>
                <span>关系</span><input type="text" id="input_relationship" name="relationship" placeholder="请输入关系">
                <div class="clear"></div>
                <div id="more_input">
                    <span>生日</span><input type="text" id="input_birthday" name="birthday" placeholder="请输入生日">
                    <div class="clear"></div>
                    <span>地址</span><input type="text" id="input_address" name="address" placeholder="请输入地址">
                    <div class="clear"></div>
                    <span>职业</span><input type="text" id="input_career" name="career" placeholder="请输入职业">
                    <div class="clear"></div>
                    <div id="contacts">
                        <button id="add_contact">添加联系方式</button>
                        <!-- <div class = "contact" id="contact1">
                                <input class="contact_input" type="text" id="method1" placeholder="请输入联系方式">
                                <input class="contact_input" type="text" id="value1" placeholder="请输入值">
                        </div> -->
                    </div>
                    <div id="skills">
                        <button id="add_skill">添加技能</button>
                        <!-- <div class = "skill" id="skill">
                                <input class="skill_input" type="text" id="ability" placeholder="请输入技能">
                                <input class="skill_input" type="text" id="proficiency" placeholder="请输入熟练度">
                        </div> -->
                    </div>
                    <div id="PSs">
                        <button id="add_PS">添加备注</button>
                        <!-- <div class = "PS" id="PS">
                                <input class="PS_input" type="text" id="title" placeholder="请输入标题">
                                <input class="PS_input" type="text" id="content" placeholder="请输入内容">
                        </div> -->
                    </div>
                </div>
            </div>
            <!--/滑动导航栏-->

        </div>
    </div>
</div>


<div id="right_part">
    <div id="RT_bor">
        <!--绿底-->
        <div id="title"></div>
        <div id="RT">
            <!--
            <div class="classfy_hint">高中同学</div>
            <div class="people_line">
                <div class="people" id="people1">
                    <div class="people_icon" id="icon1"></div>
                    <div class="people_name" id="name1">雷姆</div>
                </div>
                <div class="clear"> </div>
            </div>
            -->
        </div>
    </div>
    <div class="clear"></div>
</div>
</body>

</html>