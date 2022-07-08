<%--
  Created by IntelliJ IDEA.
  User: 姜泽昊
  Date: 2022/3/21
  Time: 11:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="col-sm-3 col-md-2 sidebar">
  <div class="tree">
    <ul style="padding-left:0px;" class="list-group">
      <li class="list-group-item tree-closed" >
        <a href="main.html"><i class="glyphicon glyphicon-dashboard"></i> 控制面板</a>
      </li>
      <li class="list-group-item tree-closed">
        <span><i class="glyphicon glyphicon glyphicon-tasks"></i> 权限管理 <span class="badge" style="float:right">3</span></span>
        <ul style="margin-top:10px;display:none;">
          <li style="height:30px;">
            <a href="admin/get/page.html"><i class="glyphicon glyphicon-user"></i> 用户维护</a>
          </li>
          <li style="height:30px;">
            <a href="role/to/page.html"><i class="glyphicon glyphicon-king"></i> 角色维护</a>
          </li>
          <li style="height:30px;">
            <a href="menu/to/page.html"><i class="glyphicon glyphicon-lock"></i> 菜单维护</a>
          </li>
        </ul>
      </li>
      <li class="list-group-item tree-closed">
        <span><i class="glyphicon glyphicon-ok"></i> 业务审核 <span class="badge" style="float:right">3</span></span>
        <ul style="margin-top:10px;display:none;">
          <li style="height:30px;">
            <a href="member/get/page.html"><i class="glyphicon glyphicon-check"></i> 会员管理</a>
          </li>
          <li style="height:30px;">
            <a href="project/get/page.html"><i class="glyphicon glyphicon-check"></i> 项目审核</a>
          </li>
        </ul>
      </li>
    </ul>
  </div>
</div>
