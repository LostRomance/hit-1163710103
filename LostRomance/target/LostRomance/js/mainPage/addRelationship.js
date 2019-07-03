var addContactNo = 0;
var addSkillNo = 0;
var addPSNo = 0;
var addPeopleUrl = "http://localhost:8080/addPeople";
var showPeopleUrl = "http://localhost:8080/showPeople";
var deletePeopleUrl = "http://localhost:8080/deletePeople";
var result;//一直保存返回的值

$(document).ready(function () {
    //刷新人脉

    $("#guide_td1").click(function () {
        var username = $("#username").html();
        RefreshRT(username);
    });
    //添加人脉
    $("#guide_td2").click(function () {
        ClearInput();
        $("#delete").hide();
        $("#addRel").show();
        $("#search_bor").hide();
    });
    //搜索人脉
    //TODO:
    $("#guide_td3").click(function () {
        $("#addRel").hide();
        $("#search_bor").show();
    });

    //"更多"
    $("#more").click(function () {
        $("#more_input").toggle();
    });
    //删除
    $("#delete").click(function () {
        var username = $("#username").html();
        var name = $("#input_name").val();
        $.ajax({
            url: deletePeopleUrl,
            type: "post",
            dataType: "text",
            data: {
                username: username,
                name:name
            },
            //传回来的response
            success: function (response) {
                console.log(response);
                alert(response.return);
            },
            error: function () {
            }
        });
    });
    //“添加联系方式”
    $("#add_contact").click(function () {
        var newContact = '<div class = "contact" id="contact' + addContactNo + '"><input class="contact_input" type="text" id="input_method' + addContactNo + '" placeholder="请输入联系方式' + addContactNo + '"><input class="contact_input" type="text" id="input_value' + addContactNo + '" placeholder="请输入值' + addContactNo + '"></div>';
        $("#contacts").append(newContact);
        addContactNo++;
    });
    //“添加技能”
    $("#add_skill").click(function () {
        var newSkill = '<div class = "skill" id="skill' + addSkillNo + '"><input class="skill_input" type="text" id="input_ability' + addSkillNo + '" placeholder="请输入技能' + addSkillNo + '"><input class="skill_input" type="text" id="input_proficiency' + addSkillNo + '" placeholder="请输入熟练度' + addSkillNo + '"></div>';
        $("#skills").append(newSkill);
        addSkillNo++;
    });
    //“添加备注”
    $("#add_PS").click(function () {
        var newPS = '<div class = "PS" id="PS' + addPSNo + '"><input class="PS_input" type="text" id="input_title' + addPSNo + '" placeholder="请输入标题' + addPSNo + '"><input class="PS_input" type="text" id="input_content' + addPSNo + '" placeholder="请输入内容' + addPSNo + '"></div>';
        $("#PSs").append(newPS);
        addPSNo++;
    });
    //提交
    $("#submit").click(function () {
        var inputJson = JSON.stringify(GetInput());
        //console.log("inputJson:"+inputJson);
        $.ajax({
            url: addPeopleUrl,
            type: "post",
            dataType: "json",
            contentType: "application/json",
            data:inputJson ,
            success(response) {
                alert(response.return);
                ClearInput();
            }, error() {
            }
        });
    });
});
//修改方面
//这里用到了动态绑定的方法，因为元素一开始不再里面，所以普通的click()行不通
$(document).on("click",".people",function () {
    ClearInput();
    $("#delete").show();
    GetInputFromMem(this);
    $("#addRel").show();
    $("#search_bor").hide();
});

function GetInput() {
    var input = {};
    var contact = [];
    var skill = [];
    var PS = [];
    for (var i = 0; i < addContactNo; i++) {
        var contactItem = {};
        contactItem.method = $("#input_method" + i).val();
        contactItem.value = $("#input_value" + i).val();
        //console.log("method:" + contactItem.method);
        //console.log("value:" + contactItem.value);
        contact.push(contactItem);
    }
    for (var j = 0; j < addSkillNo; j++) {
        var skillItem = {};
        skillItem.ability = $("#input_ability" + j).val();
        skillItem.proficiency = $("#input_proficiency" + j).val();
        //console.log("ability:" + skillItem.ability);
        //console.log("proficiency:" + skillItem.proficiency);
        skill.push(skillItem);
    }
    for (var k = 0; k < addPSNo; k++) {
        var PSItem = {};
        PSItem.title = $("#input_title" + k).val();
        PSItem.content = $("#input_content" + k).val();
        //console.log("title:" + PSItem.title);
        //console.log("content:" + PSItem.content);
        PS.push(PSItem);
    }
    input.username = $("#username").html();
    input.name = $("#input_name").val();
    input.relationship = $("#input_relationship").val();
    input.birthday = $("#input_birthday").val();
    input.address = $("#input_address").val();
    input.career = $("#input_career").val();
    input.contact = contact;
    input.skill = skill;
    input.PS = PS;

    return input;
}
function GetInputFromMem(people) {
    console.log(result);
    $(result.answers).each(function (index,obj) {
        //console.log("a:"+$(people).children(".people_name").html()+",,,b:"+obj.name)
        if($(people).children(".people_name").html()==obj.name){
            $("#input_name").val(obj.name);
            $("#input_relationship").val(obj.relationship);
            $("#input_birthday").val(obj.birthday);
            $("#input_address").val(obj.address);
            $("#input_career").val(obj.career);
            for(var i=0;i<obj.contact.length;i++){
                var newContact = '<div class = "contact" id="contact' + addContactNo + '"><input class="contact_input" type="text" id="input_method' + addContactNo + '" placeholder="请输入联系方式' + addContactNo + '"><input class="contact_input" type="text" id="input_value' + addContactNo + '" placeholder="请输入值' + addContactNo + '"></div>';
                $("#contacts").append(newContact);
                $("#input_method"+i).val(obj.contact[i].method);
                //console.log("method:"+obj.contact[i].method);
                $("#input_value"+i).val(obj.contact[i].value);
                //console.log("value:"+obj.contact[i].value);
                addContactNo++;
            }
            for(var j = 0;j<obj.skill.length;j++){
                var newSkill = '<div class = "skill" id="skill' + addSkillNo + '"><input class="skill_input" type="text" id="input_ability' + addSkillNo + '" placeholder="请输入技能' + addSkillNo + '"><input class="skill_input" type="text" id="input_proficiency' + addSkillNo + '" placeholder="请输入熟练度' + addSkillNo + '"></div>';
                $("#skills").append(newSkill);
                $("#input_ability"+j).val(obj.skill[j].ability);
                $("#input_proficiency"+j).val(obj.skill[j].proficiency);
                addSkillNo++;
            }
            for(var k = 0;k<obj.PS.length;k++) {
                var newPS = '<div class = "PS" id="PS' + addPSNo + '"><input class="PS_input" type="text" id="input_title' + addPSNo + '" placeholder="请输入标题' + addPSNo + '"><input class="PS_input" type="text" id="input_content' + addPSNo + '" placeholder="请输入内容' + addPSNo + '"></div>';
                $("#PSs").append(newPS);
                $("#input_title"+k).val(obj.PS[k].title);
                $("#input_content"+k).val(obj.PS[k].content);
                addPSNo++;
            }
        }
    })
}
function ClearInput() {
    $("#input_name").val("");
    $("#input_relationship").val("");
    $("#input_birthday").val("");
    $("#input_address").val("");
    $("#input_career").val("");
    $("div").remove(".contact").remove(".skill").remove(".PS");
    addContactNo = 0;
    addSkillNo = 0;
    addPSNo = 0;
}
function ClearRT() {
    $("div").remove(".classfy_hint").remove(".people_line");
}
function GetTypes(answers) {
    var types = [];
    for (var i = 0; i < answers.length; i++) {
        //若types中没有则加入
        if(!types.Exists(answers[i].relationship)){
            types.push(answers[i].relationship);
        }
    }
    return types;
}
function RefreshRT(username){
    $("#addRel").hide();
    $("#search_bor").hide();
    //清理RT
    ClearRT();
    $.ajax({
        url: showPeopleUrl,
        type: "post",
        dataType: "json",
        data: {
            username: username
        },
        //传回来的response
        success: function (response) {
            result = response;
            ShowPeople(response);
        },
        error: function () {
        }
    });
}
function ShowPeople(response) {
    if (response.return === "success") {
        //调用json的字段，直接使用.即可
        var types = GetTypes(response.answers);
        var answers = response.answers;
        for(var i=0;i<types.length;i++){
            //console.log("typesNumber:"+i);
            //对每个relationship进行遍历
            var addClassfyHint = '<div class="classfy_hint" id="classfy_hint'+i+'">'+types[i]+'</div>';
            var addPeopleLine = '<div class="people_line" id="people_line'+i+'"></div>';
            $("#RT").append(addClassfyHint).append(addPeopleLine);
            $(answers).each(function (index,obj) {
                //console.log("relationship:"+obj.relationship+",type"+types[index]);
                var relationshipStr = obj.relationship.toString();
                if(relationshipStr==types[i]){
                    var addPeople = '<div class="people" id="people'+index+'"><div class="people_icon" id="icon'+index+'"></div><div class="people_name" id="name'+index+'">'+obj.name+'</div></div>';
                    $("#people_line"+i).append(addPeople);
                }
            })
        }

    }
}
//拓展函数来判断一个字符串是否在数组中
Array.prototype.Exists=function(v){var b=false;for(var i=0;i<this.length;i++){if(this[i]==v){b=true;break;}}return b;}